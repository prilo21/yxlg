/*
 * MeasureProperty.java
 *
 * Created Date: 2015年5月18日
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
 * @version  v1.0
 * <p>类的描述</p>
 */

public class MeasureProperty {
	/**
	 * 属性code
	 */
	private String propertyCode;
	/**
	 * 属性名
	 */
	private String propertyName;
	/**
	 * 存放属性值
	 */
	private MeasurePropertyValue values;
	
	/**
	 * @return the propertyCode
	 */
	public String getPropertyCode() {
	
		return propertyCode;
	}
	
	/**
	 * @param propertyCode the propertyCode to set
	 */
	public void setPropertyCode(String propertyCode) {
	
		this.propertyCode = propertyCode;
	}
	
	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
	
		return propertyName;
	}
	
	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
	
		this.propertyName = propertyName;
	}
	
	/**
	 * @return the values
	 */
	public MeasurePropertyValue getValues() {
	
		return values;
	}
	
	/**
	 * @param values the values to set
	 */
	public void setValues(MeasurePropertyValue values) {
	
		this.values = values;
	}
	
}
