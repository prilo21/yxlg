/*
 * Resuroce.java
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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>权限资源类</p>
 */
@Entity
@Table(name="yx_manage_resources")

public class SysResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7526180525824608648L;
	private String sysResourceId;
	private SysResource presource; 
	private String resName;
	private String leve;
	private String resType;
	private String resUrl;
	private Integer showOrder;
	private List<SysResource> resoruces;
	
	/**
	 * @return the id
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
	public String getSysResourceId() {
	
		return sysResourceId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setSysResourceId(String sysResourceId) {
	
		this.sysResourceId = sysResourceId;
	}
	
	/**
	 * 所属父资源
	 * @return the presource
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_id",columnDefinition="")
	public SysResource getPresource() {
	
		return presource;
	}
	
	/**
	 * @param presource the presource to set
	 */
	public void setPresource(SysResource presource) {
	
		this.presource = presource;
	}
	
	/**
	 * 资源名称
	 * @return the resName
	 */
	@Column(name="res_name",columnDefinition="varchar(40) default ''")
	public String getResName() {
	
		return resName;
	}
	
	/**
	 * @param resName the resName to set
	 */
	public void setResName(String resName) {
	
		this.resName = resName;
	}
	
	
	/**
	 * 资源菜单等级
	 * @return the leve
	 */
	@Column(name="res_leve" ,columnDefinition="varchar(40) default ''")
	public String getLeve() {
	
		return leve;
	}

	
	/**
	 * @param leve the leve to set
	 */
	public void setLeve(String leve) {
	
		this.leve = leve;
	}

	/**
	 * 资源类型
	 * @return the resType
	 */
	@Column(name="res_type",columnDefinition="varchar(40) default ''")
	public String getResType() {
	
		return resType;
	}
	
	/**
	 * @param resType the resType to set
	 */
	public void setResType(String resType) {
	
		this.resType = resType;
	}
	
	
	/**
	 * 资源地址
	 * @return the resUrl
	 */
	@Column(name="res_url",columnDefinition="varchar(40) default ''")
	public String getResUrl() {
	
		return resUrl;
	}

	
	/**
	 * @param resUrl the resUrl to set
	 */
	public void setResUrl(String resUrl) {
	
		this.resUrl = resUrl;
	}

	/**
	 * 资源显示顺序
	 * @return the showOrder
	 */
	@Column(name="res_order",columnDefinition="varchar(40) default ''")
	public Integer getShowOrder() {
	
		return showOrder;
	}
	
	/**
	 * @param showOrder the showOrder to set
	 */
	public void setShowOrder(Integer showOrder) {
	
		this.showOrder = showOrder;
	}
	
	/**
	 * 所包含子资源vo
	 * @return the resoruces
	 */
	@Transient
	public List<SysResource> getResoruces() {
	
		return resoruces;
	}
	
	/**
	 * @param resoruces the resoruces to set
	 */
	public void setResoruces(List<SysResource> resoruces) {
	
		this.resoruces = resoruces;
	}
}
