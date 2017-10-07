/*
 * MemberBankCardController.java
 * 
 * Created Date: 2015年6月3日
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

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberBankCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          我的银行卡操作入口
 *          </p>
 */
@Controller("memberBankCardController")
@Api(tags = "会员银行卡操作控制类")
public class MemberBankCardController {
	
	@ApiOperation(value="新增银行卡", notes = "新增银行卡接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "bankCardNo", value = "银行卡号", required = true, dataType = "string", paramType = "query", defaultValue=""),
		@ApiImplicitParam(name = "ownerName", value = "持卡人姓名", required = true, dataType = "string", paramType = "query", defaultValue=""),
		@ApiImplicitParam(name = "bankName", value = "银行名称", required = true, dataType = "string", paramType = "query", defaultValue=""),
		@ApiImplicitParam(name = "bankId", value = "银行id", required = true, dataType = "string", paramType = "query", defaultValue=""),
		@ApiImplicitParam(name = "idNo", value = "身份证号", required = true, dataType = "string", paramType = "query", defaultValue="")
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/members/{memberId}/bankCards", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> saveBankCard(@PathVariable String memberId, @RequestBody Map<String, String> map) {
	
		return service.save(memberId, map);
	}
	
	@ApiOperation(value="解绑银行卡", notes = "新增银行卡接口", produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "memberId", value = "会员id", required = true, dataType = "string", paramType = "path", defaultValue=""),
		@ApiImplicitParam(name = "bankCardId", value = "待解绑卡id", required = true, dataType = "string", paramType = "path", defaultValue=""),
	})
	@ApiResponses(value={@ApiResponse(code=400, message="操作失败"), @ApiResponse(code=200, message="操作成功")})
	@RequestMapping(value = Constants.API_VERSION_1 + "/members/{memberId}/bankCards/{bankCardId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Result> deleteBankCard(@PathVariable String memberId, @PathVariable String bankCardId) {
		return service.delete(memberId, bankCardId);
	}
	
	@Resource
	private IMemberBankCardService service;
}
