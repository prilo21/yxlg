/*
 * AppVersionUtil.java
 * 
 * Created Date: 2015年9月11日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yxlg.base.constant.Constants;

/**
 * @author dirk
 * @version <br>
 *          <p>
 *          获取APP版本工具类
 *          </p>
 */

public class AppVersionUtil {
	
	private final static Logger log = LoggerFactory.getLogger(AppVersionUtil.class);
	
	/**
	 * 获取APP版本及平台
	 * 
	 * @param request
	 * @return
	 */
	public static AppVersion getAppVersion() {
	
		return getAppVersion(getHttpServletRequest());
	}
	
	public static HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	/**
	 * 获取APP版本及平台
	 * 
	 * @param request
	 * @return
	 */
	public static String getAppVersionString() {
	
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getHeader(Constants.HEADER_C2M_IDENTIFY);
	}
	/**
	 * 获取APP版本及平台
	 * 
	 * @param request
	 * @return HttpSession
	 */
	public static HttpSession getHttpSession() {
	
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
	/**
	 * 获取APP版本
	 * 
	 * @param request
	 * @return AppVersion
	 */
	public static AppVersion getAppVersion(String identify) {
		AppVersion appVersion = new AppVersion();
		if (StringUtils.isBlank(identify)) {
			appVersion.setPlatform(Constants.FactoryConstant.PC);
			appVersion.setVersionNo(Constants.AppVersion.VERSION_UNKNOWN);
		} else {
			String[] emp = identify.split("APP Version");
			// Platform
			String emp1 = emp[0].split("Platform:")[1];
			if (emp1.startsWith(":"))
				emp1 = emp1.substring(1, emp1.length());
			if (emp1.endsWith(","))
				emp1 = emp1.substring(0, emp1.length() - 1);
			
			// IOS
			if (Constants.FactoryConstant.IOS.equalsIgnoreCase(emp1)) {
				appVersion.setIOS(true);
				appVersion.setPlatform(Constants.FactoryConstant.IOS);
				// Android
			} else if (Constants.FactoryConstant.Android.equalsIgnoreCase(emp1)) {
				appVersion.setAndroid(true);
				appVersion.setPlatform(Constants.FactoryConstant.Android);
			} else if (emp1.contains(Constants.FactoryConstant.WX)){
				//WX
				appVersion.setWX(true);
				appVersion.setPlatform(Constants.FactoryConstant.WX);
			}else{
				// PC
				appVersion.setPC(true);
				appVersion.setPlatform(Constants.FactoryConstant.PC);
			}
			
			// APP Version
			emp = emp[1].split("OS Version");
			emp1 = emp[0];
			if (emp1.startsWith(":"))
				emp1 = emp1.substring(1, emp1.length());
			if (emp1.endsWith(","))
				emp1 = emp1.substring(0, emp1.length() - 1);
			appVersion.setVersionNo(emp1);
			
			// OS Version
			emp = emp[1].split("Device Type:");
			emp1 = emp[0];
			if (emp1.startsWith(":"))
				emp1 = emp1.substring(1, emp1.length());
			if (emp1.endsWith(","))
				emp1 = emp1.substring(0, emp1.length() - 1);
			appVersion.setOSversion(emp1);
			// Device Type
			emp1 = emp[1];
			if (emp1.startsWith(":"))
				emp1 = emp1.substring(1, emp1.length());
			if (emp1.endsWith(","))
				emp1 = emp1.substring(0, emp1.length() - 1);
			appVersion.setDeviceType(emp1);
		}
		return appVersion;
	}
	
	/**
	 * 获取APP版本
	 * 
	 * @param request
	 * @return AppVersion
	 */
	public static AppVersion getAppVersion(HttpServletRequest request) {
		
		return	getAppVersion(request.getHeader(Constants.HEADER_C2M_IDENTIFY));
		
	}
	
	/**
	 * IOS版本比较
	 * 
	 * @param IOSVersion
	 *            ：比较的版本
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         0：非IOS;
	 *         1：新版本;
	 */
	public static int IOSCompare(String IOSVersion) {
	
		try {
			// 得到request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			AppVersion appVession = getAppVersion(request);
			
			return IOSCompare(appVession, IOSVersion);
		} catch (Exception e) {
			log.error("IOS版本比较错误", e);
			return -3;
		}
	}
	
	
	/**
	 * IOS版本比较
	 * 
	 * @param IOSVersion
	 *            ：比较的版本
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         0：非IOS;
	 *         1：新版本;
	 */
	public static int IOSCompare(AppVersion appVession, String IOSVersion) {
	
		try {
			// IOS旧版包含w
			if (appVession.getVersionNo().contains("w"))
				return -1;
			if (appVession.isPC())
				return 1;
			if (StringUtils.isNotBlank(IOSVersion) && appVession.getPlatform().equalsIgnoreCase(Constants.FactoryConstant.IOS)) {
				if (appVession.getVersionNo().equalsIgnoreCase(IOSVersion)) {
					return -2;
				}
				if (appVession.getVersionNo().startsWith(IOSVersion) || appVession.getVersionNo().compareTo(IOSVersion) < 0) {
					return -1;
				} else {
					return 1;
				}
			}
			return 0;
		} catch (Exception e) {
			log.error("IOS版本比较错误", e);
			return -3;
		}
	}
	
	/**
	 * Android 版本比较
	 * 
	 * @param AndroidVersion
	 *            ：比较的版本
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         0：非Android;
	 *         1：新版本;
	 */
	public static int AndroidCompare(String AndroidVersion) {
	
		try {
			// 得到request
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			AppVersion appVession = getAppVersion(request);
			
			return AndroidCompare(appVession, AndroidVersion);
		} catch (Exception e) {
			log.error("Android版本比较错误", e);
			return -3;
		}
	}

	
	/**
	 * Android 版本比较
	 * 
	 * @param AndroidVersion
	 *            ：比较的版本
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         0：非Android;
	 *         1：新版本;
	 */
	public static int AndroidCompare(AppVersion appVession, String AndroidVersion) {
	
		try {
			if (appVession.isPC())
				return 1;
			if (StringUtils.isNotBlank(AndroidVersion) && appVession.getPlatform().equalsIgnoreCase(Constants.FactoryConstant.Android)) {
				if (appVession.getVersionNo().equalsIgnoreCase(AndroidVersion)) {
					return -2;
				}
				if (appVession.getVersionNo().startsWith(AndroidVersion) || appVession.getVersionNo().compareTo(AndroidVersion) < 0) {
					return -1;
				} else {
					return 1;
				}
			}
			return 0;
		} catch (Exception e) {
			log.error("Android版本比较错误", e);
			return -3;
		}
	}
	
	/**
	 * APP版本比较
	 * 
	 * @param appVersion
	 *            :app版本信息
	 * @param compareVersion
	 *            ：待比较的版本
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         1：新版本;
	 */
	public static int versionCompare(AppVersion appVersion, String compareVersion) {
	
		if (appVersion == null) {
			return 0;
		}
		if (appVersion.isPC())
			return 1;
		String platform = appVersion.getPlatform();
		if (StringUtils.isBlank(platform) || StringUtils.isBlank(compareVersion)) {
			return 0;
		}
		if (Constants.FactoryConstant.Android.equalsIgnoreCase(platform)) {
			return AppVersionUtil.AndroidCompare(appVersion, compareVersion);
		} else if (Constants.FactoryConstant.IOS.equalsIgnoreCase(platform)) {
			return AppVersionUtil.IOSCompare(appVersion, compareVersion);
		} else {
			log.info("版本比较，平台未识别，平台值为：" + platform);
			return -3;
		}
	}
	
	/**
	 * APP版本比较
	 * 
	 * @param iosVersion
	 *            :待比较的IOS版本号
	 * @param androidVersion
	 *            :待比较的Android版本号
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         1：新版本;
	 */
	public static int versionCompare(String iosVersion, String androidVersion) {
	
		AppVersion appVersion = AppVersionUtil.getAppVersion();
		if (appVersion.isPC())
			return 1;
		String platform = appVersion.getPlatform();
		if (Constants.FactoryConstant.Android.equalsIgnoreCase(platform)) {
			return AppVersionUtil.AndroidCompare(androidVersion);
		} else if (Constants.FactoryConstant.IOS.equalsIgnoreCase(platform)) {
			return AppVersionUtil.IOSCompare(iosVersion);
		} else {
			log.info("版本比较，平台未识别，平台值为：" + platform);
			return -3;
		}
	}
	
	/**
	 * APP版本比较
	 * 
	 * @param iosVersion
	 *            :待比较的IOS版本号
	 * @param androidVersion
	 *            :待比较的Android版本号
	 * @return
	 *         -3:比较异常
	 *         -2:版本相同(也是旧版);
	 *         -1：旧版本（包括此比较版本）;
	 *         1：新版本;
	 */
	public static int versionCompare(AppVersion appVersion, String iosVersion, String androidVersion) {
	
		if (appVersion.isPC())
			return 1;
		String platform = appVersion.getPlatform();
		if (Constants.FactoryConstant.Android.equalsIgnoreCase(platform)) {
			return AppVersionUtil.AndroidCompare(appVersion, androidVersion);
		} else if (Constants.FactoryConstant.IOS.equalsIgnoreCase(platform)) {
			return AppVersionUtil.IOSCompare(appVersion, iosVersion);
		} else {
			log.info("版本比较，平台未识别，平台值为：" + platform);
			return -3;
		}
	}
	
	public static boolean isNewApp(HttpServletRequest request, String androidVersion, String iosVersion) {
	
		AppVersion appVersion = AppVersionUtil.getAppVersion(request);
		if (appVersion.isWX()) {
			return true;
		}
		if (appVersion.isPC())
			return true;
		if (appVersion.isAndroid()) {
			int flag = AppVersionUtil.AndroidCompare(androidVersion);
			if (flag > -1) {
				return true;
			}
		}
		if (appVersion.isIOS()) {
			int flag = AppVersionUtil.IOSCompare(iosVersion);
			if (flag > -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNewApp(String identify, String androidVersion, String iosVersion){
		AppVersion appVersion = getAppVersion(identify);
		
		if (appVersion.isPC())
			return true;
		if (appVersion.isAndroid()) {
			int flag = AppVersionUtil.AndroidCompare(androidVersion);
			if (flag > -1) {
				return true;
			}
		}
		if (appVersion.isIOS()) {
			int flag = AppVersionUtil.IOSCompare(iosVersion);
			if (flag > -1) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * 2016-12-08 alisa.yang 只获取platform信息
	 * @param identify
	 * @return
	 */
	public static String getPlatformStr(String identify){
		
		if(StringUtils.isBlank(identify)){
			return Constants.FactoryConstant.PC;
		}
		
		int index1 = identify.indexOf(",");
		//从Platform:之后开始截取
		if(index1 > 9){
			return identify.substring(9, index1);
		}
		return identify.substring(9);
	}
}