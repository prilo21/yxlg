/*
 * Maintainer。java
 *
 * Created Date: 2015年5月20日
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Edison.Ding
 * @version  <br>
 * <p>后台运维人员实体类</p>
 */

@Entity
@Table(name = "yx_manage_maintainers")
public class Maintainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5356963692052527439L;
	/**
	 * 主键
	 */
	private String maintainerId;
	/**
	 * 运维人员姓名
	 */
	private String maintainerName;
	/**
	 * 运维人员邮箱
	 */
	private String email;
	/**
	 * 运维人员地址
	 */
	private String address;
	/**
	 * 运维人员电话
	 */
	private String phone;
	/**
	 * 关联系统用户
	 */
	private SysUser sysUser;
	/**
	 * 删除标志,1为已删除,0未删除
	 */
	private String deleteFlag; 
	
	/**
	 * @return the maintainerId
	 */
	@GenericGenerator(name = "idGenerator",strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "maintainer_id")
	public String getMaintainerId() {
	
		return maintainerId;
	}
	
	/**
	 * @param maintainerId the maintainerId to set
	 */
	public void setMaintainerId(String maintainerId) {
	
		this.maintainerId = maintainerId;
	}
	/**
	 * @return 运维人员姓名
	 */
	@Column(name = "maintainer_name", columnDefinition="varchar(40) default ''")
	public String getMaintainerName() {
	
		return maintainerName;
	}
	
	/**
	 * @param maintainerName
	 *            the maintainerName to set
	 */
	public void setMaintainerName(String maintainerName) {
	
		this.maintainerName = maintainerName;
	}
	
	/**
	 * @return 运维人员邮箱
	 */
	@Column(name = "maintainer_email", columnDefinition="varchar(40) default ''")
	public String getEmail() {
	
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
	
		this.email = email;
	}
	
	/**
	 * @return 运维人员地址
	 */
	@Column(name = "maintainer_address", columnDefinition="varchar(40) default ''")
	public String getAddress() {
	
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
	
		this.address = address;
	}
	
	/**
	 * @return 运维人员电话
	 */
	@Column(name = "maintainer_phone", columnDefinition="varchar(40) default ''")
	public String getPhone() {
	
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
	
		this.phone = phone;
	}

	/**
	 * @return 关联系统用户
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sys_user_id")
	public SysUser getSysUser() {
	
		return sysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(SysUser sysUser) {
	
		this.sysUser = sysUser;
	}

	/**
	 * @return the deleteFlag
	 */
	@Column(name="delete_flag", columnDefinition = "varchar(4) default '0' ")
	public String getDeleteFlag() {
	
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
	
		this.deleteFlag = deleteFlag;
	}
}
