/*
 * EncryptPropertyPlaceholderConfigurer.java
 *
 * Created Date: 2016年6月7日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.common;

import java.security.Key;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.yxlg.base.util.DESUtils;


/**
 * @author dirk
 * @version  <br>
 * <p>资源文件数据解密</p>
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String keyStr;
	
	
	/*private Resource keyLocation;*/
	
	/**
	 * @param key the key to set
	 */
	public void setKeyStr(String keyStr) {
	
		this.keyStr = keyStr;
	}

	/**
	 * 加密属性
	 */
	private String[] encryptPropNames = {"jdbc.username", "jdbc.password","jdbc.usernameRead","jdbc.passwordRead"};
	
	/*public void setKeyLocation(Resource keyLocation) {
	
		this.keyLocation = keyLocation;
	}*/

	/*@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		
		if (isEncryptProp(propertyName)) {
			String decryptValue = StringUtils.EMPTY;
			InputStream is = null;
			try {
				is = keyLocation.getInputStream();
				Key key = DESUtils.getKey(is);
				decryptValue = DESUtils.getDecryptString(propertyValue,key);
			} catch (Exception e) {
				log.error("读取数据库加密文件出错",e);
			} finally {
				if (is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("数据库加密文件关闭失败",e);
					}
				}
			}
			return decryptValue;
		}
		return propertyValue;
	}*/
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#convertProperty(java.lang.String, java.lang.String)
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = StringUtils.EMPTY;
			Key key = DESUtils.getKey(keyStr);
			decryptValue = DESUtils.getDecryptString(propertyValue,key);
			return decryptValue;
		}
		return propertyValue;
	}
	
	private boolean isEncryptProp(String propertyName) {
	
		for (String encryptName : encryptPropNames) {
			if (encryptName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}