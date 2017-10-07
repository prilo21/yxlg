/*
 * Role.java
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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>角色实体类</p>
 */
@Entity
@Table(name="yx_manage_rolers")
public class SysRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2764545092678328446L;
	private String roleId;
	private String code;
	private String roleName;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
	public String getRoleId() {
	
		return roleId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setRoleId(String roleId) {
	
		this.roleId = roleId;
	}
	
	
	
	/**
	 * 角色编号
	 * @return the code
	 */
	@Column(columnDefinition="varchar(40) default ''")
	public String getCode() {
	
		return code;
	}

	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
	
		this.code = code;
	}

	/**
	 * 角色名称
	 * @return the roleName
	 */
	@Column(name="role_name", columnDefinition="varchar(40) default ''")
	public String getRoleName() {
	
		return roleName;
	}
	
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
	
		this.roleName = roleName;
	}
	
}
