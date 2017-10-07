/*
 * IMemberAddressInfoService.java
 * 
 * Created Date: 2015年5月13日
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

import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.util.Result;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          会员地址管理接口类
 *          </p>
 */

public interface IMemberAddressInfoService {
	
	/**
	 * 查找会员所有的收货地址
	 * 
	 * @param memberId
	 * @return
	 */
	public ResponseEntity<Result> findAddresses(String memberId);
	
	/**
	 * 编辑用户收货地址
	 * 
	 * @param memberAddressInfo
	 * @return
	 */
	public ResponseEntity<Result> editAddress(String memberId,
			String memberAddressInfoId, MemberAddressInfo memberAddressInfo);
	
	/**
	 * 添加收货地址
	 * 
	 * @param memberAddressInfo
	 * @return
	 */
	public ResponseEntity<Result> addAddress(String memberId,
			MemberAddressInfo memberAddressInfo);
	
	/**
	 * 删除收货地址
	 * 
	 * @param memberId
	 * @param memberAddressInfoId
	 * @return
	 */
	public ResponseEntity<Result> deleteAddress(String memberId,
			String memberAddressInfoId);
	
	/**
	 * 查找isDefault属性为true的记录，将其更改为false。
	 * 
	 * @param memberId
	 */
	public void setIsDefaultFalse(String memberAddressInfoId, String memberId);
}
