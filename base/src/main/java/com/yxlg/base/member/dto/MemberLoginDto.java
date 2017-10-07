/*
 * MemberLoginDto.java
 *
 * Created Date: 2016年7月26日
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>会员登录DTO</p>
 */
@ApiModel
public class MemberLoginDto {
	/**
	 * 会员手机号
	 */
	@ApiModelProperty(value="手机号码", dataType="string", example="15153229035")
	private String phoneNo;
	
	/**
	 * 会员邮箱
	 */
	@ApiModelProperty(value="会员邮箱", dataType="string", example="15153229035@qq.com")
	private String email;
	
	/**
	 * 会员密码
	 */
	@ApiModelProperty(value="密码", dataType="string", example="123456")
	private String password;
	
	/**
	 * 会员短信验证码登录时的手机验证码
	 */
	@ApiModelProperty(value="手机验证码", dataType="string", example="124394")
	private String validateCode;
	
	/**
	 * 友盟推送所需要的token
	 */
	@ApiModelProperty(value="友盟推送所需要的token", dataType="string", example="ASDLFU3OJ90sk0434l")
	private String deviceToken;

	
	public String getPhoneNo() {
	
		return phoneNo;
	}

	
	public void setPhoneNo(String phoneNo) {
	
		this.phoneNo = phoneNo;
	}

	
	public String getPassword() {
	
		return password;
	}

	
	public void setPassword(String password) {
	
		this.password = password;
	}

	
	public String getValidateCode() {
	
		return validateCode;
	}

	
	public void setValidateCode(String validateCode) {
	
		this.validateCode = validateCode;
	}


	
	public String getEmail() {
	
		return email;
	}


	
	public void setEmail(String email) {
	
		this.email = email;
	}


	
	public String getDeviceToken() {
		
		return deviceToken;
	}


	
	public void setDeviceToken(String deviceToken) {
		
		this.deviceToken = deviceToken;
	}
	
	
	
}
