/*
 * MemberType.java
 *
 * Created Date: 2015年5月25日
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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author jerry.qin
 * @version  <br>
 * <p>会员类型</p>
 */
@Entity
@Table(name = "yx_member_type")
public class MemberType implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4026599695053444121L;

	/**
	 * 会员类型id
	 */
	private String memberTypeId;
	
	/**
	 * 会员类型
	 */
	private String memberType;
	
	/**
	 * 创建人
	 */
	private String createPerson;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String modifyPerson;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 创建时间字符串
	 */
	//private String createTimeStr;
	
	/**
	 * 修改时间字符串
	 */
	//private String modifyTimeStr;
	
	

	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_type_id", unique = true, nullable = false, length = 40)
	public String getMemberTypeId() {
	
		return memberTypeId;
	}

	
	public void setMemberTypeId(String memberTypeId) {
	
		this.memberTypeId = memberTypeId;
	}

	@Column(name = "member_type", columnDefinition="varchar(40) default ''")
	public String getMemberType() {
	
		return memberType;
	}

	
	public void setMemberType(String memberType) {
	
		this.memberType = memberType;
	}


	
	@Column(name = "create_person", columnDefinition="varchar(40) default ''")
	public String getCreatePerson() {
	
		return createPerson;
	}


	
	
	public void setCreatePerson(String createPerson) {
	
		this.createPerson = createPerson;
	}


	
	@Column(name = "create_time", length = 40)
	public Date getCreateTime() {
	
		/*if(!StringUtils.isEmpty(createTimeStr)){
			try {
				return new SimpleDateFormat(C2MConstants.DateFormatConstant.DATE_FORMAT).parse(createTimeStr);
			} catch (ParseException e) {
				throw new BusinessException();
			}
		}*/
		return createTime;
	}


	
	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}


	
	@Column(name = "modify_person", columnDefinition="varchar(40) default ''")
	public String getModifyPerson() {
	
		return modifyPerson;
	}


	
	
	public void setModifyPerson(String modifyPerson) {
	
		this.modifyPerson = modifyPerson;
	}


	
	@Column(name = "modify_time", length = 40)
	public Date getModifyTime() {
	
		/*if(!StringUtils.isEmpty(modifyTimeStr)){
			try {
				return new SimpleDateFormat(C2MConstants.DateFormatConstant.DATE_FORMAT).parse(modifyTimeStr);
			} catch (ParseException e) {
				throw new BusinessException();
			}
		}*/
		return modifyTime;
	}


	
	
	public void setModifyTime(Date modifyTime) {
	
		this.modifyTime = modifyTime;
	}


	/*@Transient
	public String getCreateTimeStr() {
	
		if(createTime!=null){
			createTimeStr = DateFormatUtils.format(createTime, C2MConstants.DateFormatConstant.DATE_FORMAT);
		}
		return createTimeStr;
	}


	
	public void setCreateTimeStr(String createTimeStr) {
	
		this.createTimeStr = createTimeStr;
	}


	@Transient
	public String getModifyTimeStr() {
	
		if(modifyTime!=null){
			modifyTimeStr = DateFormatUtils.format(modifyTime, C2MConstants.DateFormatConstant.DATE_FORMAT);
		}
		return modifyTimeStr;
	}


	
	public void setModifyTimeStr(String modifyTimeStr) {
	
		this.modifyTimeStr = modifyTimeStr;
	}
	*/
	
}
