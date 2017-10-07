/*
 * MeasurePropertyValue.java
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

public class MeasurePropertyValue {
	/**
	 * 值
	 */
	private String value;
	/**
	 * 增值
	 */
	private String increase;
	/**
	 * 减值
	 */
	private String reduce;
	
	/**
	 * @return the value
	 */
	public String getValue() {
	
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
	
		this.value = value;
	}
	
	/**
	 * @return the increase
	 */
	public String getIncrease() {
	
		return increase;
	}
	
	/**
	 * @param increase the increase to set
	 */
	public void setIncrease(String increase) {
	
		this.increase = increase;
	}
	
	/**
	 * @return the reduce
	 */
	public String getReduce() {
	
		return reduce;
	}
	
	/**
	 * @param reduce the reduce to set
	 */
	public void setReduce(String reduce) {
	
		this.reduce = reduce;
	}
	
}
