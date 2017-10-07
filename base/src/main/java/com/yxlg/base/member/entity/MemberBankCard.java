/*
 * MemberBankCard.java
 *
 * Created Date: 2015年6月3日
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>会员银行卡</p>
 */
@Entity
@Table(name = "yx_member_bank_card")
public class MemberBankCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032755893886569514L;
	/**
	 * 银行卡id
	 */
	private String id;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 持卡人姓名
	 */
	private String ownerName;
	/**
	 * 银行识别号
	 */
	private String bankId;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 所属会员
	 */
	private Member member;
	/**
	 * 身份证号
	 */
	private String idNo;

	/**
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_bank_Card_Id", unique = true, nullable = false, length = 40)
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
	 * @return the bankCardNo
	 */
	@Column(name = "member_bank_card_no", columnDefinition="varchar(40) default ''")
	public String getBankCardNo() {
	
		return bankCardNo;
	}
	
	/**
	 * @param bankCardNo the bankCardNo to set
	 */
	public void setBankCardNo(String bankCardNo) {
	
		this.bankCardNo = bankCardNo;
	}
	
	
	/**
	 * @return the ownerName
	 */
	@Column(name = "member_bank_card_owner_name", columnDefinition="varchar(400) default ''")
	public String getOwnerName() {
	
		return ownerName;
	}

	
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
	
		this.ownerName = ownerName;
	}

	/**
	 * @return the bankId
	 */
	@Column(name = "member_bank_card_bank_id", columnDefinition="varchar(40) default ''")
	public String getBankId() {
	
		return bankId;
	}
	
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(String bankId) {
	
		this.bankId = bankId;
	}
	
	/**
	 * @return the bankName
	 */
	@Column(name = "member_bank_card_bank_name", columnDefinition="varchar(40) default ''")
	public String getBankName() {
	
		return bankName;
	}
	
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
	
		this.bankName = bankName;
	}
	
	/**
	 * @return the member
	 */
	@ManyToOne
	@JoinColumn(name = "member_id")
	public Member getMember() {
	
		return member;
	}
	
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
	
		this.member = member;
	}
	
	/**
	 * @return the idNo
	 */
	@Column(name = "member_bank_card_id_no", columnDefinition="varchar(40) default ''")
	public String getIdNo() {
	
		return idNo;
	}

	
	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
	
		this.idNo = idNo;
	}
}
