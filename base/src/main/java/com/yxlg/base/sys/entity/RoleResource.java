/*
 * RoleResource.java
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
 * <p>角色权限表</p>
 */
@Entity
@Table(name="yx_manage_role_resource")
public class RoleResource {
	private String roleResourceId;
	private SysRole role;
	private SysResource res;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
	public String getRoleResourceId() {
	
		return roleResourceId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setRoleResourceId(String roleResourceId) {
	
		this.roleResourceId = roleResourceId;
	}

	
	/**
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

	
	/**
	 * @return the res
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="res_id")
	public SysResource getRes() {
	
		return res;
	}

	
	/**
	 * @param res the res to set
	 */
	public void setRes(SysResource res) {
	
		this.res = res;
	}
	
	
}
