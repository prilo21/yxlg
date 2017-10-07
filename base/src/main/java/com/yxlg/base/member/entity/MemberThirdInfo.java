/*
 * MemberThirdInfo.java
 *
 * Created Date: 2016年6月22日
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>会员第三方信息存储表</p>
 * 
 * 同一手机号的会员，微信，微博和qq都有登录，那么此表只有一条记录，而不是三条
 */
@Entity
@Table(name = "yx_member_third_info")
public class MemberThirdInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8041611843298762529L;
	
	/**
	 * 第三方信息id
	 */
	private String memberThirdId;
	
	/**
	 * 会员id
	 */
	private Member member;
	
	/**
	 * 微信号
	 */
	private String weixinNo = "";
	
	/**
	 * 微信-openid
	 */
	private String weixinOpenId = "";
	
	/**
	 * 微信token
	 * 和APP一致，第三方微信自动登录的标识，有过期时间，如果过期，重新登陆
	 * 解决 更换手机登录根据openid直接自动登录的问题
	 */
	private String weixinToken = "";
	
	/**
	 * QQ号
	 */
	private String qqNo = "";
	
	
	/**
	 * QQ-openid
	 */
	private String qqOpenId = "";
	
	/**
	 * QQ-token
	 * 和APP一致，第三方QQ自动登录的标识，有过期时间，如果过期，重新登陆
	 */
	private String qqToken = "";
	
	/**
	 * 微博号
	 */
	private String weiboNo = "";
	
	/**
	 * 微博-openid
	 */
	private String weiboOpenId = "";
	
	/**
	 * 微博-token
	 */
	private String weiboToken = "";
	
	/**
	 * 2016-12-16 alisa.yang 第三方登录新增建设银行
	 */
	private String ccbOpenId = "";
	
	/**
	 * 插入时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_third_id", length = 40, nullable = false)
	public String getMemberThirdId() {
	
		return memberThirdId;
	}

	
	public void setMemberThirdId(String memberThirdId) {
	
		this.memberThirdId = memberThirdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	public Member getMember() {
	
		return member;
	}

	
	public void setMember(Member member) {
	
		this.member = member;
	}

	@Column(name = "weixin_no", columnDefinition = "varchar(40) default ''")
	public String getWeixinNo() {
	
		return weixinNo;
	}

	
	public void setWeixinNo(String weixinNo) {
	
		this.weixinNo = weixinNo;
	}

	@Column(name = "open_id", columnDefinition = "varchar(400) default ''")
	public String getWeixinOpenId() {
		
		return weixinOpenId;
	}


	
	public void setWeixinOpenId(String weixinOpenId) {
		
		this.weixinOpenId = weixinOpenId;
	}


	@Column(name = "weixin_token", columnDefinition = "varchar(400) default ''")
	public String getWeixinToken() {
		
		return weixinToken;
	}


	
	public void setWeixinToken(String weixinToken) {
		
		this.weixinToken = weixinToken;
	}

	@Column(name = "qq", columnDefinition = "varchar(40) default ''")	
	public String getQqNo() {
		
		return qqNo;
	}


	
	public void setQqNo(String qqNo) {
		
		this.qqNo = qqNo;
	}


	@Column(name = "qq_open_id", columnDefinition = "varchar(400) default ''")	
	public String getQqOpenId() {
		
		return qqOpenId;
	}


	
	public void setQqOpenId(String qqOpenId) {
		
		this.qqOpenId = qqOpenId;
	}


	
	@Column(name = "qq_token", columnDefinition = "varchar(400) default ''")	
	public String getQqToken() {
		
		return qqToken;
	}


	
	public void setQqToken(String qqToken) {
		
		this.qqToken = qqToken;
	}


	@Column(name = "weibo", columnDefinition = "varchar(40) default ''")
	public String getWeiboNo() {
		
		return weiboNo;
	}


	
	public void setWeiboNo(String weiboNo) {
		
		this.weiboNo = weiboNo;
	}


	
	@Column(name = "weibo_open_id", columnDefinition = "varchar(400) default ''")
	public String getWeiboOpenId() {
		
		return weiboOpenId;
	}


	
	public void setWeiboOpenId(String weiboOpenId) {
		
		this.weiboOpenId = weiboOpenId;
	}


	
	@Column(name = "weibo_token", columnDefinition = "varchar(400) default ''")
	public String getWeiboToken() {
		
		return weiboToken;
	}


	
	public void setWeiboToken(String weiboToken) {
		
		this.weiboToken = weiboToken;
	}
	
	
	@Column(name = "ccb_open_id", columnDefinition = "varchar(400) default ''")
	public String getCcbOpenId() {
		
		return ccbOpenId;
	}


	
	public void setCcbOpenId(String ccbOpenId) {
		
		this.ccbOpenId = ccbOpenId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
	
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	public Date getUpdateTime() {
	
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
	
		this.updateTime = updateTime;
	}
}
