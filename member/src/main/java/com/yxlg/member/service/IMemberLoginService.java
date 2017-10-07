/*
 * IMemberLoginService.java
 * 
 * Created Date: 2015年5月7日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.service;

import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSONObject;
import com.yxlg.base.member.dto.MemberLoginDto;
import com.yxlg.base.member.dto.MemberLoginWeixinDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.util.Result;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          用户登录接口类
 *          </p>
 */

public interface IMemberLoginService {
	
	/**
	 * 用户登录入口
	 * 
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> login(MemberLoginDto memberLoginDto, String identify);
	
	/**
	 * 用户注销
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> logout(Member member);
	
	/**
	 * 用户通过短信验证码登录同时注册
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> registerMemberByValidateCode(MemberLoginDto memberDto, String identify);
	
	/**
	 * 2016-08-01 alisa.yang 第三方登录
	 * 登录方式：qq，微博，微信；token是自动登录的唯一验证方式，如果token和数据库不匹配，需要使用完整信息进行重新登录，那么登录分为以下步骤：
	 * 
	 * 1. token, 验证是否已登录过
	 * 		如果已登录过（说明授权成功并且有此会员），直接返回会员信息
	 * 		如果是首次通过授权登录，则需要返回APP输入手机号绑定会员；并且手机号通过普通验证码之后才可以进行下一步
	 * 2. 输入手机号和openid及相应token，进行登录
	 * 		如果手机号已注册，直接绑定
	 * 		如果手机号未注册，拿到微信（或qq或微博）的简单信息进行注册，然后绑定
	 * @param dto
	 * @param identify
	 * @return
	 */
	public ResponseEntity<Result> weiXinLogin(MemberLoginWeixinDto dto, String identify);
	
	
	/**
	 * 通过token登录
	 * @param token
	 * @return
	 */
	public ResponseEntity<Result> loginByAuthToken(JSONObject token, String loginUserToken);
}