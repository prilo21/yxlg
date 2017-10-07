/*
 * ChangePhoneDto.java
 *
 * Created Date: 2016年11月28日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.member.dto;


/**
 * @author Michael.Sun
 * 2016年11月28日
 * @version  <br>
 * <p>类的描述</p>
 */

public class ChangePhoneDto {
	/**
	 * 新手机号
	 */
	private String phoneNo;
	/**
	 * 验证码
	 */
	private String phoneCode;
	
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
	
		return phoneNo;
	}
	
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
	
		this.phoneNo = phoneNo;
	}
	
	/**
	 * @return the phoneCode
	 */
	public String getPhoneCode() {
	
		return phoneCode;
	}
	
	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(String phoneCode) {
	
		this.phoneCode = phoneCode;
	}
	
}
