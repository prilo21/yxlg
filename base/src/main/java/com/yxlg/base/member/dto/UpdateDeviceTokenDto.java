/*
 * UpdateDeviceTokenDto.java
 *
 * Created Date: 2016年12月5日
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
 * <p>更新会员deviceToken DTO</p>
 */
@ApiModel
public class UpdateDeviceTokenDto {
	
	/**
	 * 会员id
	 */
	@ApiModelProperty(value = "会员id", dataType = "String", example = "dae2e193a26b499a8738aa24bac3b36d")
	private String memberId;
	
	/**
	 * 融云token
	 */
	@ApiModelProperty(value = "融云token", dataType = "String", example = "asefra3d9d39q8asdf3e")
	private String deviceToken;

	
	public String getMemberId() {
	
		return memberId;
	}

	
	public void setMemberId(String memberId) {
	
		this.memberId = memberId;
	}

	
	public String getDeviceToken() {
	
		return deviceToken;
	}

	
	public void setDeviceToken(String deviceToken) {
	
		this.deviceToken = deviceToken;
	}
	
	
}
