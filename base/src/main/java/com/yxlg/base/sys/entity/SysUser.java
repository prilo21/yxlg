/*
 * SysUser.java
 * 
 * Created Date: 2015年5月6日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Edison.Ding
 * @version <br>
 *          <p>
 *          后台用户基本信息类
 *          </p>
 */
@Entity
@Table(name = "yx_manage_sysusers")
public class SysUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6699359927628596365L;
	
	/**
	 * 主键
	 */
	private String sysUserId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 登陆密码
	 */
	private String userPassword;
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * @return 主键id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id")
	public String getSysUserId() {
	
		return sysUserId;
	}
	
	/**
	 * @param id
	 *            the id to set
	 */
	public void setSysUserId(String sysUserId) {
	
		this.sysUserId = sysUserId;
	}
	
	/**
	 * @return the userName
	 */
	@Column(name = "user_name", columnDefinition="varchar(40) default ''")
	public String getUserName() {
	
		return userName;
	}
	
	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
	
		this.userName = userName;
	}
	
	/**
	 * @return the userPassword
	 */
	@Column(name = "user_password", columnDefinition="varchar(40) default ''")
	public String getUserPassword() {
	
		return userPassword;
	}
	
	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
	
		this.userPassword = userPassword;
	}

	
	/**
	 * @return 真实姓名
	 */
	@Column(name="real_name", columnDefinition="varchar(40) default ''")
	public String getRealName() {
	
		return realName;
	}

	
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
	
		this.realName = realName;
	}
	
}
