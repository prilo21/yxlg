/*
 * InitRedis.java
 * 
 * Created Date: 2015年7月9日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;

/**
 * @author Cary
 * @version <br>
 *          <p>
 *          手动初始化redis
 *          </p>
 */
@Controller
@Api(tags = "手动初始化redis")
public class InitRedisController {
	
	/**
	 * 初始化订单redis
	 *//*
	@ApiOperation(value = "初始化订单redis", notes = "初始化订单redis", produces = "application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "操作失败"), @ApiResponse(code = 200, message = "操作成功") })
	@RequestMapping(value = C2MConstants.API_VERSION_1 + "/admin/redis/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=UTF-8")
	public @ResponseBody String Init() {
		return  orderRedis.initPaidOrder() + orderRedis.initUnPayOrder();
	}
	
	*//**
	 * 初始化默认工艺
	 *//*
	@ApiOperation(value = "初始化默认工艺", notes = "初始化默认工艺", produces = "application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "操作失败"), @ApiResponse(code = 200, message = "操作成功") })
	@RequestMapping(value = C2MConstants.API_VERSION_1 + "/admin/redis/initProcess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=UTF-8")
	public @ResponseBody String InitProcess() {
	
		return processRedis.initProcessDefaultProcess() + "," + processRedis.initProcessDefaultPresbyopia() + "," + processDefaultLapelRedis.initProcessDefaultLapel();
	}
	
	*//**
	 * 初始化订单工艺码pAMap
	 *//*
	
	 * @RequestMapping(value = C2MConstants.API_VERSION_1
	 * + "/admin/redis/updateProcessArchives", method =
	 * RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces
	 * = MediaType.APPLICATION_JSON_VALUE
	 * + ";charset=UTF-8")
	 * public @ResponseBody String UpdateProcessArchives() {
	 * 
	 * return orderRedis.UpdateProcessArchives();
	 * }
	 
	
	*//**
	 * 初始化商品生产码对照MakeCodeNotesMap
	 *//*
	@ApiOperation(value = "初始化商品生产码对照MakeCodeNotesMap", notes = "初始化商品生产码对照MakeCodeNotesMap", produces = "application/json")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "X-AUTH-TOKEN",required = true, dataType = "string", paramType = "header")
    })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "操作失败"), @ApiResponse(code = 200, message = "操作成功") })
	@RequestMapping(value = C2MConstants.API_VERSION_1 + "/admin/redis/updateMakeCodeNotesMap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=UTF-8")
	public @ResponseBody String UpdateMakeCodeNotesMap() {
	
		return orderRedis.UpdateMakeCodeNotes();
	}
	
	@Resource
	private IOrderRedisService orderRedis;
	@Resource
	private IProcessRedisService processRedis;
	@Resource
	private IProcessDefaultLapelRedisService processDefaultLapelRedis;*/
}
