/*
 * MemberAddressInfo.java
 * 
 * Created Date: 2015年5月11日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          收货地址类
 *          </p>
 */
@Entity
@Table(name="yx_member_address_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberAddressInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5562303099634213199L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 手机号
	 */
	private String phoneNo;
	/**
	 * 邮编
	 */
	private String postal;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String suburb;
	/**
	 * 详细地址
	 */
	private String detail;	
	/**
	 * 是否默认
	 */
	private boolean isDefault;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 删除标识
	 */
	private boolean isdelete;
	/**
	 * 所属会员
	 */
	private Member member;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_address_info_Id", unique = true, nullable = false, length = 40)
	public String getId() {
	
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
	
		this.id = id;
	}
	
	
	/**
	 * @return the receiver
	 */
	@Column(name = "member_address_info_receiver", columnDefinition="varchar(40) default ''")
	public String getReceiver() {
	
		return receiver;
	}

	
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
	
		this.receiver = receiver;
	}

	
	/**
	 * @return the phoneNo
	 */
	@Column(name = "member_address_info_phone_no", columnDefinition="varchar(40) default ''")
	public String getPhoneNo() {
	
		return phoneNo;
	}

	
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
	
		this.phoneNo = phoneNo;
	}

	
	/**
	 * @return the postal
	 */
	@Column(name = "member_address_info_postal", columnDefinition="varchar(40) default ''")
	public String getPostal() {
	
		return postal;
	}

	
	/**
	 * @param postal the postal to set
	 */
	public void setPostal(String postal) {
	
		this.postal = postal;
	}

	
	/**
	 * @return the province
	 */
	@Column(name = "member_address_info_province", columnDefinition="varchar(40) default ''")
	public String getProvince() {
	
		return province;
	}

	
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
	
		this.province = province;
	}

	
	/**
	 * @return the city
	 */
	@Column(name = "member_address_info_city", columnDefinition="varchar(40) default ''")
	public String getCity() {
	
		return city;
	}

	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
	
		this.city = city;
	}

	
	/**
	 * @return the suburb
	 */
	@Column(name = "member_address_info_suburb", columnDefinition="varchar(40) default ''")
	public String getSuburb() {
	
		return suburb;
	}

	
	/**
	 * @param suburb the suburb to set
	 */
	public void setSuburb(String suburb) {
	
		this.suburb = suburb;
	}

	
	/**
	 * @return the detail
	 */
	@Column(name = "member_address_info_detail", columnDefinition="varchar(400) default ''")
	public String getDetail() {
	
		return detail;
	}

	
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
	
		this.detail = detail;
	}

	
	/**
	 * @return the isDefault
	 */
	@Column(name = "member_address_info_is_default", columnDefinition="bit default false")
	public boolean isIsDefault() {
	
		return isDefault;
	}

	
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(boolean isDefault) {
	
		this.isDefault = isDefault;
	}

	
	/**
	 * @return the telephone
	 */
	@Column(name = "member_address_info_telephone", columnDefinition="varchar(40) default ''")
	public String getTelephone() {
	
		return telephone;
	}

	
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
	
		this.telephone = telephone;
	}

	
	/**
	 * @return the isdelete
	 */
	@Column(name = "member_address_info_is_delete", columnDefinition="bit default false")
	public boolean isIsdelete() {
	
		return isdelete;
	}

	
	/**
	 * @param isdelete the isdelete to set
	 */
	public void setIsdelete(boolean isdelete) {
	
		this.isdelete = isdelete;
	}

	/**
	 * @return the member
	 */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMember() {
	
		return member;
	}

	
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
	
		this.member = member;
	}	
}
