/*
 * MeasureDS.java
 *
 * Created Date: 2015年8月13日
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

public class MeasureDS {
	/**
	 * 主键
	 */
	private String ds_Id;
	/**
	 * 所属品类
	 */
	private String dsCategory;
	/**
	 * 套装的戏份，西服/西裤
	 */
	private String  dsCategoryTz;
	/**
	 * 所在属性
	 */
	private String dsCategoryDetail;
	/**
	 * 值
	 */
	private String styleValues;
	/**
	 * 增值
	 */
	private String habitValues;
	
	public String getDs_Id() {
		
		return ds_Id;
	}
	
	public void setDs_Id(String ds_Id) {
		
		this.ds_Id = ds_Id;
	}
	
	public String getDsCategory() {
		
		return dsCategory;
	}
	
	public void setDsCategory(String dsCategory) {
		
		this.dsCategory = dsCategory;
	}
	
	public String getDsCategoryTz() {
		
		return dsCategoryTz;
	}
	
	public void setDsCategoryTz(String dsCategoryTz) {
		
		this.dsCategoryTz = dsCategoryTz;
	}
	
	public String getDsCategoryDetail() {
		
		return dsCategoryDetail;
	}
	
	public void setDsCategoryDetail(String dsCategoryDetail) {
		
		this.dsCategoryDetail = dsCategoryDetail;
	}
	
	public String getStyleValues() {
		
		return styleValues;
	}
	
	public void setStyleValues(String styleValues) {
		
		this.styleValues = styleValues;
	}
	
	public String getHabitValues() {
		
		return habitValues;
	}
	
	public void setHabitValues(String habitValues) {
		
		this.habitValues = habitValues;
	}
	
}
