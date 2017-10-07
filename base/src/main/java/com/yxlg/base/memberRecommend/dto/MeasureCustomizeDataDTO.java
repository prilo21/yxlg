/*
 * MeasureCustomizeDataDTO.java
 *
 * Created Date: 2015年12月7日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */
package com.yxlg.base.memberRecommend.dto;

import java.io.Serializable;

public class MeasureCustomizeDataDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 款式数据id
	 */
	private String measureAdjustId;
	/**
	 * 风格数据id
	 */
	private String ds_Id;
	/**
	 * 品类
	 */
	private String dsCategory;
	/**
	 * 品类细分（休闲，礼服，正装）
	 */
	private String dsCategoryDetail;
	/**
	 * 穿衣习惯（大衣套西服，大衣不套西服）
	 */
	private String habitValues;
	/**
	 * 穿衣风格（修身，宽松等）
	 */
	private String styleValues;
	/**
	 * 款式数据品类内length_id
	 */
	private String aa;
	
	public String getDsCategory() {
		
		return dsCategory;
	}
	public void setDsCategory(String dsCategory) {
		
		this.dsCategory = dsCategory;
	}
	public String getDsCategoryDetail() {
		
		return dsCategoryDetail;
	}
	public void setDsCategoryDetail(String dsCategoryDetail) {
		
		this.dsCategoryDetail = dsCategoryDetail;
	}
	public String getHabitValues() {
		
		return habitValues;
	}
	public void setHabitValues(String habitValues) {
		
		this.habitValues = habitValues;
	}
	public String getStyleValues() {
		
		return styleValues;
	}
	public void setStyleValues(String styleValues) {
		
		this.styleValues = styleValues;
	}
	public String getMeasureAdjustId() {
		
		return measureAdjustId;
	}
	public void setMeasureAdjustId(String measureAdjustId) {
		
		this.measureAdjustId = measureAdjustId;
	}
	public String getDs_Id() {
		
		return ds_Id;
	}
	public void setDs_Id(String ds_Id) {
		
		this.ds_Id = ds_Id;
	}
	public String getAa() {
		
		return aa;
	}
	public void setAa(String aa) {
		
		this.aa = aa;
	}
}
