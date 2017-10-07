/*
 * MemberController.java
 * 
 * Created Date: 2015年5月07日
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


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSONObject;
import com.yxlg.base.constant.Constants;
import com.yxlg.base.member.dto.MemberRegisterDto;
import com.yxlg.base.member.dto.PhoneCodeDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberService;
import com.yxlg.member.util.PWConfirm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller("memberController")
@Api(tags = "会员控制类")
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    
    /**
     * 新用户注册接口（添加验证码验证功能） Michael.Sun
     * 2016-8-19 alisa.yang 老带新活动推荐新注册用户并奖励
     * @param member
     * @param verifyCode
     * @return
     */
    @ApiOperation(value="注册接口", notes = "会员注册接口,可以输入推荐人手机号、活动来源等。先注册再处理推荐关系、注册送红包之类的业务逻辑。", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "verifyCode", value = "短信验证码", required = true, dataType = "String", paramType = "path", defaultValue = "234520")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/members/securityRegister/{verifyCode}", method = RequestMethod.POST)
    public ResponseEntity<Result> registerMemberNew(@RequestBody MemberRegisterDto memberDto, @PathVariable String verifyCode, HttpServletRequest request) {
		
		log.info("新用户开始注册，手机号：" + memberDto.getPhoneNo() + ",推荐人手机号：" + memberDto.getRecommendPhoneNo());
		
		Member member = new Member();
		BeanUtils.copyProperties(memberDto, member);
		ResponseEntity<Result> result = memberService.registerMember(member, verifyCode, request.getHeader(Constants.HEADER_C2M_IDENTIFY));
//		if (result.getBody().getReturnCode() == 200) {
//			memberService.registerReward(memberDto, member);
//		}
		return result;
	}
        
        // 用户在注册之后生成融云的聊天token
        // ResponseEntity<Result> responseEntity =
        // memberService.registerMember(member);
        // Result result = responseEntity.getBody();
        // @SuppressWarnings("unchecked")
        // Map<String, Object> map = (Map<String, Object>)
        // result.getReturnData();
        // if(HttpStatus.OK.value() == result.getReturnCode()){
        // String memberId = (String)map.get("memberId");
        // String iIMToken = iIMMemberService.getMemberChatToken(memberId);
        // map.put("iIMTocken", iIMToken);
        // return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(),
        // C2MConstants.ReturnMsg.MEMBER_REG_SUCCESS,map), HttpStatus.OK);
        // }
        // return responseEntity;
    
    
    /**
     * 验证注册手机或者邮箱
     * 
     * @param emailPhone
     * @return Result
     */
    @Deprecated
    @ApiIgnore
//    @RequestMapping(value = C2MConstants.API_VERSION_1 + "/public/registration/{emailPhone:.+}", method = RequestMethod.GET)
    public ResponseEntity<Result> verifyMemberRegistration(@PathVariable String emailPhone) {
    
        return memberService.verifyMemberRegistration(emailPhone, false);
    }
    
    /**
     * 验证手机或者邮箱(获取验证码)
     * 
     * @param emailPhone
     * @return Result
     */
    @Deprecated
    @ApiIgnore
//    @RequestMapping(value = C2MConstants.API_VERSION_1 + "/public/verification/{emailPhone:.+}", method = RequestMethod.GET)
    public ResponseEntity<Result> verifyMember(@PathVariable String emailPhone) {
    
        return memberService.verifyMember(emailPhone, false);
    }
    
    /**
     * 验证注册手机或者邮箱(获取验证码)
     * @param emailPhone
     * @return Result
     */
    @ApiOperation(value="请求注册短信接口(手机验证码)",notes = "cookie里需传回imageRequestId",produces="application/json")
     @ApiImplicitParams({
     	@ApiImplicitParam(name = Constants.HEADER_C2M_IDENTIFY, value = "平台信息", required = true, dataType = "string", paramType = "header",defaultValue="Platform:Android,APP Version:3.9.2,OS Version:1.5,Device Type:MI S1"),
     })
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/registration", method = RequestMethod.POST)
    public ResponseEntity<Result> verifyMemberRegistrationPost(@RequestBody PhoneCodeDto dto) {
    	
        return memberService.verifyMemberRegistration(dto, true);
    }
    
    /**
     * 验证手机或者邮箱(获取验证码)
     * 未注册会员不能请求本接口
     * @param emailPhone
     * @return Result
     */
    @ApiOperation(value="注册会员请求短信验证码", notes = "注册会员请求短信验证码,非注册会员不给发。入参{IDCode:区域代码，emailPhone:手机号或者邮箱}", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = Constants.HEADER_C2M_IDENTIFY, value = "平台信息", required = true, dataType = "string", paramType = "header",defaultValue="Platform:Android,APP Version:3.9.2,OS Version:1.5,Device Type:MI S1"),
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/verification", method = RequestMethod.POST)
    public ResponseEntity<Result> verifyMemberPost(@RequestBody JSONObject jsonObject) {
    	String emailPhoneBody = jsonObject.toString();
        return memberService.verifyMember(emailPhoneBody, true);
    }
    
    
    
    
    
    
    
    /**
     * 个人资料编辑
     * 
     * @param id
     * @param member
     * @param response
     * @return
     */
    @ApiOperation(value="个人资料编辑", notes = "修改会员自身资料", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e"),
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/members/{id}", method = RequestMethod.POST)
    public ResponseEntity<Result> updateMemberInfo(@PathVariable String id, @RequestBody Member member, HttpServletRequest request) {
    
        return memberService.updateMemberInfo(id, member, request);
    }
    
    /**
     * 账户安全
     * 
     * @param id
     * @return
     */
    @ApiOperation(value="账号查询", notes = "通过会员id查询账号信息，手机号和邮箱账号", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e"),
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/members/{id}/security", method = RequestMethod.GET)
    public ResponseEntity<Result> getSecurity(@PathVariable String id) {
    
        return memberService.getSecurity(id);
    }
    
    /**
     * 我的二维码(营销会员专用)
     * 
     * @param id
     * @return
     */
    @Deprecated
    @ApiOperation(value="营销会员钱包中的二维码", notes = "用营销会员id查询会员名称、二维码、icon、签名信息", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e"),
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/marketingMembers/members/{memberId}/qrCode", method = RequestMethod.GET)
    public ResponseEntity<Result> getQrcode(@PathVariable String memberId) {
    
        return memberService.getQrcode(memberId);
    }
    
    /**
     * 修改密码
     * 
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @ApiOperation(value="修改密码", notes = "通过会员id和验证码修改密码", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e"),
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/members/{id}/security/password", method = RequestMethod.POST)
    public ResponseEntity<Result> updatePassWord(@PathVariable String id, @RequestBody PWConfirm pwconfirm) {
    
        return memberService.updatePassWord(id, pwconfirm);
    }
    
    
    /**
     * 
     * @param id
     * @param member
     * @return
     */
    @ApiOperation(value="修改绑定邮箱", notes = "通过会员id修改绑定邮箱", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "id", value = "会员id", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e"),
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/members/{id}/security/email", method = RequestMethod.POST)
    public ResponseEntity<Result> changeEmail(@PathVariable String id, @RequestBody Member member) {
    
        return memberService.changeEmail(id, member);
    }
    
    /**
     * 根据邮箱和手机验证用户是否存在
     * 
     * @param emailPhone
     * @return
     */
    @ApiOperation(value="查询手机号或者邮箱是否已注册", notes = "查询手机号或者邮箱是否已注册", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "emailPhone", value = "手机号或邮箱账号", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/members/existence/{emailPhone}", method = RequestMethod.GET)
    public ResponseEntity<Result> memberIsExist(@PathVariable String emailPhone) {
    
        return memberService.memberIsExist(emailPhone);
    }
    
    /**
     * @author Jarvis.Lu
     * @version <br>
     *          <p>
     *          修改用户二维码信息
     *          </p>
     */
    @ApiOperation(value="更新用户二维码", notes = "更新用户二维码", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "memberId", value = "手机号或邮箱账号", required = true, dataType = "String", paramType = "path", defaultValue = "8a10a439514848fc0151485223b1002e")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/members/qrCode/update/{memberId}/", method = RequestMethod.GET)
    public String UpdateMemberQRCode(@PathVariable String memberId, HttpServletRequest request) {
    
        return memberService.UpdateMemberQRCode(memberId, request);
    }
    
    
    /**
     * 通过手机号判断是否是酷特注册会员
     */
    @ApiOperation(value="检验手机号是否注册", notes = "通过手机号判断是否是酷特注册会员,返回值1：已注册，0：未注册", produces="application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "phoneNo", value = "手机号", required = true, dataType = "String", paramType = "path", defaultValue = "15153229034")
    })
    @ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
    @RequestMapping(value = Constants.API_VERSION_1 + "/public/{phoneNo}/member", method = RequestMethod.GET)
    public ResponseEntity<Result> isWannaMember(@PathVariable String phoneNo) {
    	return memberService.isWannaMember(phoneNo);
    }

    @Resource
    private IMemberService memberService;
    
}
