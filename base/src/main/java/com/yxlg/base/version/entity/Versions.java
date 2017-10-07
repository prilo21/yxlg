/*
 * Versions.java
 *
 * Created Date: 2015年5月19日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.version.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Stone.Cai 2015年5月19日 14:01:11
 * @version <br>
 *          添加
 *          <p>
 *          版本控制类，所有的版本新都要经过这个类的管理
 *          </p>
 */
@Entity
@Table(name = "yx_version")
public class Versions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6149183920195816449L;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 版本类型
	 */
	private String versionType;
	/**
	 * 版本号
	 */
	private String versionNumber;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	@Column(name = "version_type", columnDefinition="varchar(40) default ''")
	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	@Column(name = "version_number", columnDefinition="varchar(40) default ''")
	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

}
