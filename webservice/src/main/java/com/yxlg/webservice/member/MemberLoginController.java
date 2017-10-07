/*
 * MemberLoginController2.java
 *
 * Created Date: 2015年5月11日
 *
 * Copyright (c) Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.member;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yxlg.base.constant.Constants;
import com.yxlg.base.constant.ResultCode;
import com.yxlg.base.member.dto.MemberLoginDto;
import com.yxlg.base.member.dto.MemberLoginWeixinDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.upload.util.CatchPicture;
import com.yxlg.base.util.NewResult;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberLoginService;
import com.yxlg.member.service.IMemberService;
import com.yxlg.member.service.IMemberSuperPWService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          会员登录入口
 *          </p>
 */
@Controller("memberLoginController")
@Api(tags = "会员登录接口")
public class MemberLoginController {

	/**
	 * 用户登录
	 *
	 * @param member
	 * @return
	 */
	@ApiOperation(value="会员登录接口", notes = "通过此接口登录酷特APP", produces="application/json")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "Token信息", required = true, dataType = "string", paramType = "header"),
//			@ApiImplicitParam(name = "phoneNo", value = "会员手机号", required = true, dataType = "string", paramType = "body"),
//			@ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "string", paramType = "body"),
//			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "body"),
//			@ApiImplicitParam(name = "validateCode", value = "手机验证码", required = true, dataType = "string", paramType = "body"),
//			@ApiImplicitParam(name = "deviceToken", value = "设备token", required = true, dataType = "string", paramType = "body")
//	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> login(@RequestBody MemberLoginDto dto, HttpServletRequest request) {
		ResponseEntity<Result> responseEntity = service.login(dto, request.getHeader(Constants.HEADER_C2M_IDENTIFY));
		Result result = responseEntity.getBody();
		
		if (ResultCode.common.NO_MEMBER == result.getReturnCode()) {
			//手机短信登录
			responseEntity = service.registerMemberByValidateCode(dto, request.getHeader(Constants.HEADER_C2M_IDENTIFY));
		}
		//删除手机验证码
		memberService.deleteValidateCodeByPhoneNo(dto.getPhoneNo());
		if(HttpStatus.OK.value() != result.getReturnCode()){
			return responseEntity;
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) result.getReturnData();
		if(map == null){
			return responseEntity;
		}
		return new ResponseEntity<Result>(new Result(map),
				responseEntity.getStatusCode());
	}

	/**
	 * 用户注销  把deviceToken置成""
	 * @param member
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = Constants.API_VERSION_1 + "/members/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> logout (@RequestBody Member member) {

		return service.logout(member);
	}
	
	/**
	 * 2016-08-01 alisa.yang 微信第三方登录
	 * 1. token, 验证是否已登录过
	 * 		如果和数据库匹配已登录过（说明授权成功并且有此会员），直接返回会员信息
	 * 		如果是首次通过授权登录，则需要返回APP输入手机号绑定会员；并且手机号通过普通验证码之后才可以进行第2步
	 * 		如果是（根据openid判断）已登录过，但是token过期了，需要使用原绑定会员的手机号进行验证！
	 * 2. 输入手机号和openid及相应token，进行登录
	 * 		如果手机号已注册，直接绑定
	 * 		如果手机号未注册，拿到微信（或qq或微博）的简单信息进行注册，然后绑定
	 * @param dto
	 * @param request
	 * @return
	 */
	
	@CrossOrigin(origins = {"https://website.magicmanufactory.com", "https://w.cotte.cn", "https://s.cotte.cn", "https://localhost:3000", "http://localhost:3000"}, maxAge = 3600)
	@ApiOperation(value="第三方登录接口-微信登录",notes = "微信授权登录，200登录成功；16401：获取openid或token失败；16402：首次登录，获取phoneNo失败；16403：登录失败；16404：首次登录，非会员，注册失败；16405：手机验证码过期；16406：未开放此登录方式；16407：手机号已绑定；16408：账号授权凭证失效，请重新登录",produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = Constants.HEADER_C2M_IDENTIFY, value = "平台信息", required = true, dataType = "string", paramType = "header",defaultValue="Platform:Android,APP Version:1.5,OS Version:1.5,Device Type:MI S1")
    })
	@ApiResponses(value={@ApiResponse(code=400,message="操作失败"),@ApiResponse(code=200,message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_2 + "/public/member/login/weixin", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> weiXinLogin(@ApiParam(value = "登录信息；sex： 1-男，2-女；type：weixin、qq、weibo、ccb；unionid：微信号，qq号，微博号", required = true) @RequestBody MemberLoginWeixinDto dto, HttpServletRequest request){

		String originStr = request.getHeader("Origin");
		if(Constants.isOfficialServer() && StringUtils.isNotBlank(originStr) && originStr.contains("localhost")){
//			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//			headers.add("Access-Control-Allow-Credentials", "false");
//			headers.add("Access-Control-Allow-Origin", originStr);
			return new ResponseEntity<Result>(new Result(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()+";正式版禁用localhost访问", null), HttpStatus.FORBIDDEN);
		}
		return service.weiXinLogin(dto, request.getHeader(Constants.HEADER_C2M_IDENTIFY));
	}
	
	/**
	 * 2017-02-08 alisa.yang 测试下载头像图片超时的问题，查看服务器的问题接口
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = Constants.API_VERSION_2 + "/public/member/login/weixin/testDownLoadWXImg", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> testDownLoadWXImg(@RequestBody MemberLoginWeixinDto dto){
		
		if(StringUtils.isNotBlank(dto.getHeadimgurl())){
//			String qiniuUrl = FileLoadUtil.encodeName("memberIcon/thirdLogin/test/");
			
			String returnMsg = "";
			try {
				Long startTime = System.currentTimeMillis();
				InputStream inputStream = CatchPicture.downloadImgByNet(dto.getHeadimgurl());
				returnMsg = "读取时间：" + (System.currentTimeMillis() - startTime) + "ms,";
				BufferedImage imageBI = ImageIO.read(inputStream);
//				String iconUrl = UploadByUrl.uploadImage(imageBI, qiniuUrl);
				returnMsg += "图片下载成功！url:" + dto.getHeadimgurl() + ",height:" + imageBI.getHeight() + ",width:" + imageBI.getWidth();
			} catch (IOException e) {
				returnMsg += "第三方登录微信，用户头像上传七牛异常！" + e.getMessage();
			}
			return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), returnMsg), HttpStatus.OK);
		}
		
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), "传入图片地址为空！"), HttpStatus.OK);
	}
	

	/**
	 * 查看超级密码是否enable
	 * @return
	 */
	@ApiOperation(value="超级密码查看接口",notes = "查看会员登录的超级密码是否可用",produces="application/json")
	@ApiResponses(value={@ApiResponse(code=400,message="操作失败"),@ApiResponse(code=200,message="enableFlag is:true/false/null")})
	@RequestMapping(value = Constants.API_VERSION_2 + "/public/super/status", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<NewResult<String>> getSuperPWStatus() {
		return ipw.getSuperPWStatus();
	}
	
	/**
	 * 设置超级密码开关值(true/false)
	 * @param enableValue = true / false
	 * @return
	 */
	@ApiOperation(value="设置超级密码是否可用接口",notes = "超级密码设置，true:可用，false:不可用",produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "enableValue", value = "可用标识", required = true, dataType = "string", paramType = "path")
    })
	@ApiResponses(value={@ApiResponse(code=400,message="操作失败"),@ApiResponse(code=200,message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_2 + "/public/super/enable/{enableFlag}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<NewResult<String>> setSuper(@PathVariable String enableFlag){
		return ipw.setSuperStatus(enableFlag);
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@ApiOperation(value="token登录接口",notes = "使用c2mtoken登录app，目前的使用场景是量体师扫描客户token二维码登录客户账户",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constants.HEADER_C2M_IDENTIFY, value = "平台信息", required = true, dataType = "string", paramType = "header",defaultValue="Platform:Android,APP Version:1.5,OS Version:1.5,Device Type:MI S1")
	})
	@ApiResponses(value={@ApiResponse(code=400,message="操作失败"),@ApiResponse(code=200,message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/member/tokens", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Result> loginByAuthToken(@RequestBody JSONObject token, HttpServletRequest request) {
		return service.loginByAuthToken(token, request.getHeader("X-AUTH-TOKEN"));
	}

	
	@Resource
	IMemberLoginService service;
	@Resource
	IMemberService memberService;
	@Resource
	IMemberSuperPWService ipw;
}
