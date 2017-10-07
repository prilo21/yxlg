/*
 * QrcodeDTO.java
 *
 * Created Date: 2015年5月29日
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
 * @author alison.liu
 * @version  <br>
 * <p>类的描述</p>
 */

public class QrcodeDTO {
	/**
	 * 二维码里的内容
	 */
	String qrCodeUniqueId;
	/**
	 * 二维码里的图片路径
	 */
	String logoPathAndName;
	
	/**
	 * @return the qrCodeUniqueId
	 */
	public String getQrCodeUniqueId() {
	
		return qrCodeUniqueId;
	}
	
	/**
	 * @param qrCodeUniqueId the qrCodeUniqueId to set
	 */
	public void setQrCodeUniqueId(String qrCodeUniqueId) {
	
		this.qrCodeUniqueId = qrCodeUniqueId;
	}
	
	/**
	 * @return the logoPathAndName
	 */
	public String getLogoPathAndName() {
	
		return logoPathAndName;
	}
	
	/**
	 * @param logoPathAndName the logoPathAndName to set
	 */
	public void setLogoPathAndName(String logoPathAndName) {
	
		this.logoPathAndName = logoPathAndName;
	}
	
}
