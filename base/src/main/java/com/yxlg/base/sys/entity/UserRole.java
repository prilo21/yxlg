/*
 * UserRole.java
 *
 * Created Date: 2015年5月6日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>用户角色关系表</p>
 */
@Entity
@Table(name="yx_manage_user_role")
public class UserRole {
	private String userRoleId;
	private SysUser user;
	private SysRole role;
	
	/**
	 * 用户角色关系id
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
	public String getUserRoleId() {
	
		return userRoleId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setUserRoleId(String userRoleId) {
	
		this.userRoleId = userRoleId;
	}
	
	/**
	 * 用户
	 * @return the user
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SysUser_id")
	public SysUser getUser() {
	
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(SysUser user) {
	
		this.user = user;
	}
	
	/**
	 * 角色
	 * @return the role
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	public SysRole getRole() {
	
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(SysRole role) {
	
		this.role = role;
	}
	
}
