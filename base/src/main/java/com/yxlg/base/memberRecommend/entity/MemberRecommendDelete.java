/*
 * MemberRecommendDelete.java
 *
 * Created Date: 2015年12月15日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.memberRecommend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yxlg.base.member.entity.Member;


/**
 * @author jerry.qin
 * @version  <br>
 * <p>会员关系删除表</p>
 */
@Entity
@Table(name = "yx_member_recommend_delete")
public class MemberRecommendDelete implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 595895780440100957L;
	/**
	 * 会员关系id
	 */
	private String memberRecommendDeleteId;
	
	/**
	 * 会员id
	 */
	private Member member;
	
	/**
	 * 被推荐人的手机号
	 */
	private String phoneNo;
	
	/**
	 * 建立关系的时间
	 */
	private Date createTime;
	
	/**
	 * 绑定会员最近交易时间
	 */
	private Date tradeTime;
	
	/**
	 * 绑定方式
	 */
	private String bindingMode;
	
	/**
	 * 会员关系删除时间
	 */
	private Date deleteTime;
	
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_recommend_delete_id", unique = true, nullable = false, length = 40)
	public String getMemberRecommendDeleteId() {
	
		return memberRecommendDeleteId;
	}

	
	public void setMemberRecommendDeleteId(String memberRecommendDeleteId) {
	
		this.memberRecommendDeleteId = memberRecommendDeleteId;
	}

	
	
	@Column(name = "phone_no",columnDefinition="varchar(40) default ''")
	public String getPhoneNo() {
	
		return phoneNo;
	}

	
	
	public void setPhoneNo(String phoneNo) {
	
		this.phoneNo = phoneNo;
	}


	
	/**
	 * @return the member
	 */
	@ManyToOne(cascade=CascadeType.MERGE)
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
	
	@Column(name = "create_time")
	public Date getCreateTime() {
	
		return createTime;
	}

	
	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}

	@Column(name = "trade_time")
	public Date getTradeTime() {
	
		return tradeTime;
	}

	
	public void setTradeTime(Date tradeTime) {
	
		this.tradeTime = tradeTime;
	}

	@Column(name = "binding_mode",columnDefinition="varchar(40) default ''")
	public String getBindingMode() {
	
		return bindingMode;
	}

	public void setBindingMode(String bindingMode) {
	
		this.bindingMode = bindingMode;
	}
	
	@Column(name = "delete_time")
	public Date getDeleteTime() {
	
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
	
		this.deleteTime = deleteTime;
	}

}
