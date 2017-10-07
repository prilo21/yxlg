/*
 * Measurer.java
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

import java.io.Serializable;


/**
 * @author alison.liu
 * @version  v1.0
 * <p>类的描述</p>
 */


public class Measurer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6026695016132252579L;
	/**
	 * 量体数据id
	 */
    private String measureId;
	/**
	 * 被量体人
	 */
	private String measurerName;
	/**
	 * 量体人身高
	 */
    private String height;
    /**
     * 量体人体重
     */
    private String weight;
    /**
     * 量体时间
     */
    private String measureDate;
    /**
     * 量体师
     */
    private MeasurePerson measurePerson;
	
	/**
	 * @return the measurerName
	 */
	public String getMeasurerName() {
	
		return measurerName;
	}

	
	/**
	 * @param measurerName the measurerName to set
	 */
	public void setMeasurerName(String measurerName) {
	
		this.measurerName = measurerName;
	}


	
	/**
	 * @return the height
	 */
	public String getHeight() {
	
		return height;
	}


	
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
	
		this.height = height;
	}


	
	/**
	 * @return the weight
	 */
	public String getWeight() {
	
		return weight;
	}


	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
	
		this.weight = weight;
	}


	
	/**
	 * @return the measureDate
	 */
	public String getMeasureDate() {
	
		return measureDate;
	}


	
	/**
	 * @param measureDate the measureDate to set
	 */
	public void setMeasureDate(String measureDate) {
	
		this.measureDate = measureDate;
	}


	public MeasurePerson getMeasurePerson() {
		return measurePerson;
	}


	public void setMeasurePerson(MeasurePerson measurePerson) {
		this.measurePerson = measurePerson;
	}


	public String getMeasureId() {
		return measureId;
	}


	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	
}
