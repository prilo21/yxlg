/*
 * AppVersion.java
 *
 * Created Date: 2015年9月11日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;


/**
 * @author Dirk
 * @version  <br>
 * <p>版本号封装类</p>
 */

public class AppVersion {
	
	/**
		是否IOS
	*/
	private boolean isIOS;
	
	/**
	 * 是否Android
	 */
	private boolean isAndroid;
	/**
	 * 是否是微信
	 */
	private boolean isWX;
	/**
	 * 是否Android
	 */
	private boolean isPC;
	/**
	 * 平台 IOS,Android,PC
	 */
	private String platform;
	
	/**
	 * 版本号
	 */
	private String versionNo;
	/**
	 * 手机系统版本ios 8.1，Android6.0
	 */
	private String OSversion;
	/**
	 * 设备信息iPhone 5 ，MI3
	 */
	private String deviceType;

	
	/**
	 * @return the isIOS
	 */
	public boolean isIOS() {
	
		return isIOS;
	}

	
	/**
	 * @param isIOS the isIOS to set
	 */
	public void setIOS(boolean isIOS) {
	
		this.isIOS = isIOS;
	}

	
	/**
	 * @return the isAndroid
	 */
	public boolean isAndroid() {
	
		return isAndroid;
	}

	
	
	/**
	 * @return the isPC
	 */
	public boolean isPC() {
	
		return isPC;
	}


	
	/**
	 * @param isPC the isPC to set
	 */
	public void setPC(boolean isPC) {
	
		this.isPC = isPC;
	}


	/**
	 * @param isAndroid the isAndroid to set
	 */
	public void setAndroid(boolean isAndroid) {
	
		this.isAndroid = isAndroid;
	}

	
	/**
	 * @return the platform
	 */
	public String getPlatform() {
	
		return platform;
	}

	
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
	
		this.platform = platform;
	}

	
	/**
	 * @return the versionNo
	 */
	public String getVersionNo() {
	
		return versionNo;
	}

	
	/**
	 * @param versionNo the versionNo to set
	 */
	public void setVersionNo(String versionNo) {
	
		this.versionNo = versionNo;
	}


	
	/**
	 * @return the oSversion
	 */
	public String getOSversion() {
	
		return OSversion;
	}


	
	/**
	 * @param oSversion the oSversion to set
	 */
	public void setOSversion(String oSversion) {
	
		OSversion = oSversion;
	}


	
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
	
		return deviceType;
	}


	
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
	
		this.deviceType = deviceType;
	}


	
	/**
	 * @return the isWX
	 */
	public boolean isWX() {
	
		return isWX;
	}


	
	/**
	 * @param isWX the isWX to set
	 */
	public void setWX(boolean isWX) {
	
		this.isWX = isWX;
	}
	
	
}
