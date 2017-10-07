/*
 * Base64File.java
 *
 * Created Date: 2015年5月27日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.upload.dto;


/**
 * @author alison.liu
 * @version  <br>
 * <p>类的描述</p>
 */

public class Base64File {

	/**
	 * base64编码后的字符串
	 */
	private String base64String;
	
	
	/**
	 * @return the base64String
	 */
	public String getBase64String() {
	
		return base64String;
	}
	
	/**
	 * @param base64String the base64String to set
	 */
	public void setBase64String(String base64String) {
	
		this.base64String = base64String;
	}
	
}
