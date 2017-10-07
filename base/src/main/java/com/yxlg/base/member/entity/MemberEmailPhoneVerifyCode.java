/*
 * MemberEmailPhoneVerifyCode.java
 *
 * Created Date: 2015年8月15日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.member.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>类的描述</p>
 */
@Entity
@Table(name = "yx_member_email_phone_verify_code")
public class MemberEmailPhoneVerifyCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755145239506698414L;
	/**
	 * 手机号验证码关系Id
	 */
	private String Id;
	/**
	 * 手机号
	 */
	private String emailPhone;
	/**
	 * 验证码
	 */
	private String verifyCode;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * @return the id
	 */
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_email_phone_verify_code_Id", unique = true, nullable = false, length = 40)
	public String getId() {
	
		return Id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {

		Id = id;
	}

	/**
	 * @return the emailPhone
	 */
	@Column(name = "email_phone", columnDefinition="varchar(40) default ''")
	public String getEmailPhone() {
	
		return emailPhone;
	}
	
	/**
	 * @param emailPhone the emailPhone to set
	 */
	public void setEmailPhone(String emailPhone) {
	
		this.emailPhone = emailPhone;
	}
	
	/**
	 * @return the verifyCode
	 */
	@Column(name = "verify_code", columnDefinition="varchar(40) default ''")
	public String getVerifyCode() {
	
		return verifyCode;
	}
	
	/**
	 * @param verifyCode the verifyCode to set
	 */
	public void setVerifyCode(String verifyCode) {
	
		this.verifyCode = verifyCode;
	}
	
	/**
	 * @return the remark
	 */
	@Column(name = "remark", columnDefinition="varchar(400) default ''")
	public String getRemark() {
	
		return remark;
	}
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	
	
}
