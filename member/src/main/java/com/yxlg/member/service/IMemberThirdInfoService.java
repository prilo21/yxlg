/*
 * IMemberThirdInfoService.java
 *
 * Created Date: 2016年6月22日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.service;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.member.entity.MemberThirdInfo;
import com.yxlg.base.util.Result;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>会员第三方信息service接口</p>
 */

public interface IMemberThirdInfoService {
	
	/**
	 * 注册会员第三方信息
	 * @return
	 */
	public String memberThirdRegist(MemberThirdInfo info);
	
	/**
	 * 查询会员第三方信息
	 */
	public ResponseEntity<Result> findMemberThirdInfo(String memberId);
	
	/**
	 * 根据openId查询会员
	 */
	public ResponseEntity<Result> findMemberByThirdInfoId(String openId);
}
