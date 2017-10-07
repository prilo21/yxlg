/*
 * IBankCardService.java
 *
 * Created Date: 2015年6月3日
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

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.util.Result;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>会员银行卡管理接口类</p>
 */

public interface IMemberBankCardService {
	/**
	 * 添加银行卡
	 * @return
	 */
	public ResponseEntity<Result> save(String memberId, Map<String, String> map);
	/**
	 * 删除银行卡
	 * @return
	 */
	public ResponseEntity<Result> delete(String memberId, String bankCardId);
}
