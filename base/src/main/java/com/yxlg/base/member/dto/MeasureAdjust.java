/*
 * MeasureAdjust.java
 *
 * Created Date: 2015年11月11日
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
 * @author Alison.Liu
 * @version  <br>
 * <p>类的描述</p>
 */

public class MeasureAdjust {
	 /** 
	  * 主键
	 */
	private String measureAdjustId;
	/**
	 * 品类（西服、衬衫、大衣 等）
	 */
	private String category;
	/**
	 * 风格（偏短，正装，偏长 等）
	 */
	private String lengthStyleName;
	/**
	 * 在净体数据基础上的加减值（e.g. 正数：2， 负数： -2）
	 */
	private String lengthStyleValue;
	
	public String getMeasureAdjustId() {
		
		return measureAdjustId;
	}
	
	public void setMeasureAdjustId(String measureAdjustId) {
		
		this.measureAdjustId = measureAdjustId;
	}
	
	public String getCategory() {
		
		return category;
	}
	
	public void setCategory(String category) {
		
		this.category = category;
	}
	
	public String getLengthStyleName() {
		
		return lengthStyleName;
	}
	
	public void setLengthStyleName(String lengthStyleName) {
		
		this.lengthStyleName = lengthStyleName;
	}
	
	public String getLengthStyleValue() {
		
		return lengthStyleValue;
	}
	
	public void setLengthStyleValue(String lengthStyleValue) {
		
		this.lengthStyleValue = lengthStyleValue;
	}
	
}
