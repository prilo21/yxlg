/*
 * MemberAddressInfoController.java
 *
 * Created Date: 2015年5月12日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.member;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberAddressInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>会员收货地址管理入口</p>
 */
@Controller("memberAddressInfoController")
@Api(tags = "会员收货地址控制类")
public class MemberAddressInfoController {
	/**
	 * 我的收货地址
	 * @param memberId
	 * @return
	 */
	@ApiOperation(value="获取我的收货地址", notes = "获取我的收货地址列表接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1+"/members/{memberId}/addresses", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> findAllAddresses(@PathVariable String memberId){
		return memberAddressInfoService.findAddresses(memberId);
	}
	
	/**
	 * 编辑收货地址
	 * @param memberId
	 * @param memberAddressInfoId
	 * @param memberAddressInfo
	 * @return
	 */
	@ApiOperation(value="编辑我的收货地址", notes = "编辑我的收货地址接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "memberAddressInfoId", value = "待编辑的地址id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1+"/members/{memberId}/addresses/{memberAddressInfoId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> editAddress(@PathVariable String memberId, @PathVariable String memberAddressInfoId, @RequestBody MemberAddressInfo memberAddressInfo){
		return memberAddressInfoService.editAddress(memberId, memberAddressInfoId, memberAddressInfo);
	}
	
	/**
	 * 添加收货地址
	 * @param memberId
	 * @param memberAddressInfo
	 * @return
	 */
	@ApiOperation(value="添加收货地址", notes = "添加收货地址接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1+"/members/{memberId}/addresses", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> addAddress(@PathVariable String memberId, @RequestBody MemberAddressInfo memberAddressInfo){
		return memberAddressInfoService.addAddress(memberId,memberAddressInfo);
	}
	
	/**
	 * 删除收货地址
	 * @param memberId
	 * @param memberAddressInfoId
	 * @return
	 */
	@ApiOperation(value="删除我的收货地址", notes = "删除我的收货地址接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "memberAddressInfoId", value = "待删除的地址id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1+"/members/{memberId}/addresses/{memberAddressInfoId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteAddress(@PathVariable String memberId, @PathVariable String memberAddressInfoId){
		return memberAddressInfoService.deleteAddress(memberId, memberAddressInfoId);
	}
	
	@Resource
	private IMemberAddressInfoService memberAddressInfoService;
}
