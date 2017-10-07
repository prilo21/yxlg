/*
 * MemberRelation.java
 *
 * Created Date: 2015年5月14日
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yxlg.base.member.entity.Member;


/**
 * @author jerry.qin
 * @edit alison.liu
 * @version  <br>
 * <p>类的描述</p>
 */
@Entity
@Table(name = "yx_member_recommend")
public class MemberRecommend implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4561501581448718123L;

	/**
	 * 会员关系id
	 */
	private String id;
	
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
	 * 会员关系剩余绑定时间
	 */
	private int surplusTime=90;
	
	/**
	 * 绑定会员最近交易时间
	 */
	private Date tradeTime;
	
	/**
	 * 绑定方式
	 */
	private String bindingMode;
	/**
	 * 短信验证码
	 */
	private String phoneCode;
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_recommend_id", unique = true, nullable = false, length = 40)
	public String getId() {
	
		return id;
	}

	
	public void setId(String id) {
	
		this.id = id;
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

	@Column(name = "surplus_time")
	public int getSurplusTime() {
	
		return surplusTime;
	}


	public void setSurplusTime(int surplusTime) {
	
		this.surplusTime = surplusTime;
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


	
	/**
	 * @return the phoneCode
	 */
	@Transient
	public String getPhoneCode() {
	
		return phoneCode;
	}


	
	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(String phoneCode) {
	
		this.phoneCode = phoneCode;
	}

	
}
