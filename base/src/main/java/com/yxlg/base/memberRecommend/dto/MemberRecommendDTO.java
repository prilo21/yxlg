/*
 * DesignerDTO.java
 *
 * Created Date: 2015年6月15日
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
import java.util.Date;

import com.yxlg.base.member.entity.Member;

/**
 * @author Jarvis.Lu
 * @version <br>
 *          <p>
 *          会员推荐关系表DTO
 *          </p>
 */
public class MemberRecommendDTO implements Serializable {


	private static final long serialVersionUID = -8980950293552367428L;

	
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
	 * 被推荐人的姓名
	 * 如果被推荐人注册了，则是被推荐人的名字
	 * 如果被推荐人没有注册，则显示为“<strong>尚未注册</strong>”
	 * 
	 */
	private String memberName;
	/**
	 * 2016-11-01
	 * 添加绑定方式在前端页面展示
	 */
	private String bindingMode;
	
	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
	
		this.id = id;
	}
	
	public String getPhoneNo() {
	
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
	
		this.phoneNo = phoneNo;
	}


	
	/**
	 * @return the member
	 */
	public Member getMember() {
	
		return member;
	}
	
	public void setMember(Member member) {
	
		this.member = member;
	}
	
	public Date getCreateTime() {
	
		return createTime;
	}

	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	
	public String getMemberName() {
	
		return memberName;
	}

	
	public void setMemberName(String memberName) {
	
		this.memberName = memberName;
	}


	public String getBindingMode() {
		
		return bindingMode;
	}


	public void setBindingMode(String bindingMode) {
		
		this.bindingMode = bindingMode;
	}

	

}
