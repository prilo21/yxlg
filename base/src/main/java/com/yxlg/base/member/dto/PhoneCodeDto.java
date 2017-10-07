/*
 * PhoneCodeDto.java
 *
 * Created Date: 2016年11月25日
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
 * @author Michael.Sun
 * 2016年11月25日
 * @version  <br>
 * <p>请求验证码参数封装类</p>
 */
@ApiModel
public class PhoneCodeDto {
	/**
	 * 请求验证码用途（注册：reg,领红包：red,第三方登录：log）
	 */
	@ApiModelProperty(value="请求用途（注册：register,领红包：redPackage）",dataType="string",example="reg")
	private String codePurpose;
	/**
	 * 国家代码
	 */
	@ApiModelProperty(value="国家代码",dataType="string",example="+86")
	private String idCode;
	/**
	 * 手机号或邮箱
	 */
	@ApiModelProperty(value="手机号或邮箱",dataType="string",example="")
	private String emailPhone;
	/**
	 * 短信或邮件验证码
	 */
	@ApiModelProperty(value="请求短信时，不需要传入该值",dataType="string",example="")
	private String phoneCode;
	/**
	 * 图片验证码
	 */
	@ApiModelProperty(value="图片验证码",dataType="string",example="")
	private String imageCode;
	/**
	 * 图片请求id
	 */
	@ApiModelProperty(value="该值后台会从cookie获取，不需要作为requestbody的参数传入",dataType="string",example="")
	private String imageRequestId;
	
	/**
	 * @return the codePurpose
	 */
	public String getCodePurpose() {
	
		return codePurpose;
	}
	
	/**
	 * @param codePurpose the codePurpose to set
	 */
	public void setCodePurpose(String codePurpose) {
	
		this.codePurpose = codePurpose;
	}		

	
	/**
	 * @return the idCode
	 */
	public String getIdCode() {
	
		return idCode;
	}

	
	/**
	 * @param idCode the idCode to set
	 */
	public void setIdCode(String idCode) {
	
		this.idCode = idCode;
	}

	/**
	 * @return the emailPhone
	 */
	public String getEmailPhone() {
	
		return emailPhone;
	}
	
	/**
	 * @param emailPhone the emailPhone to set
	 */
	public void setEmailPhone(String emailPhone) {
	
		this.emailPhone = emailPhone;
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
	
	/**
	 * @return the imageCode
	 */
	public String getImageCode() {
	
		return imageCode;
	}
	
	/**
	 * @param imageCode the imageCode to set
	 */
	public void setImageCode(String imageCode) {
	
		this.imageCode = imageCode;
	}
	
	/**
	 * @return the imageRequestId
	 */
	public String getImageRequestId() {
	
		return imageRequestId;
	}
	
	/**
	 * @param imageRequestId the imageRequestId to set
	 */
	public void setImageRequestId(String imageRequestId) {
	
		this.imageRequestId = imageRequestId;
	}
	
}
