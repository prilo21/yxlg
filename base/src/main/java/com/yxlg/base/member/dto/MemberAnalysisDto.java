/*
 * MemberAnalysisDto.java
 *
 * Created Date: 2016年12月9日
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
 * @author fengfeng.yu
 * @version  0.1<br>
 * <p>
 * 	用户分析DTO
 * </p>
 */

public class MemberAnalysisDto {
	
	private String memberName;
	
	private String memberPhone;
	
	private String regTime;

	
	/**
	 * @return the memberName
	 */
	public String getMemberName() {
	
		return memberName;
	}

	
	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
	
		this.memberName = memberName;
	}

	
	/**
	 * @return the memberPhone
	 */
	public String getMemberPhone() {
	
		return memberPhone;
	}

	
	/**
	 * @param memberPhone the memberPhone to set
	 */
	public void setMemberPhone(String memberPhone) {
	
		this.memberPhone = memberPhone;
	}

	
	/**
	 * @return the regTime
	 */
	public String getRegTime() {
	
		return regTime;
	}

	
	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(String regTime) {
	
		this.regTime = regTime;
	}
	
	
}
