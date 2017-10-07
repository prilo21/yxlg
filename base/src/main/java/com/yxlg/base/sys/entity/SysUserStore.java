/*
 * UserStore.java
 * 
 * Created Date: 2016年3月23日
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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author Cary
 * @version <br>
 *          <p>
 *          加盟商
 *          </p>
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "yx_sysuser_store")
public class SysUserStore implements Serializable {
	
	private static final long serialVersionUID = 7378522015108448716L;
	/**
	 * id
	 */
	private String storeId;
	/**
	 * 加盟商名称
	 */
	private String storeName;
	/**
	 * 加盟商信息描述
	 */
	private String storeDescription;
	/**
	 * 加盟商创建时间
	 */
	private Date createDate;
	/**
	 * 创建人
	 */
	private SysUser createUser;
	
	/**
	 * 加盟商解散时间
	 */
	private Date dissolveDate;
	/**
	 * 删除标志0有效，1已删除
	 */
	private Integer deleteFlag;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String suburb;
	/**
	 * 详细地址
	 */
	private String detail;
	
	/**
	 * 加盟商管理员，一对一关系
	 */
	private SysUser user;
	
	/**
	 * @return the storeId
	 */
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "store_id")
	public String getStoreId() {
	
		return storeId;
	}
	
	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(String storeId) {
	
		this.storeId = storeId;
	}
	
	/**
	 * @return the storeName
	 */
	@Column(name = "store_name", columnDefinition = " vachar(40) default ''")
	public String getStoreName() {
	
		return storeName;
	}
	
	/**
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName) {
	
		this.storeName = storeName;
	}
	
	/**
	 * @return the createDate
	 */
	@Column(name = "create_date")
	public Date getCreateDate() {
	
		return createDate;
	}
	
	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}
	
	/**
	 * @return the dissolveDate
	 */
	@Column(name = "dissolve_date")
	public Date getDissolveDate() {
	
		return dissolveDate;
	}
	
	/**
	 * @param dissolveDate
	 *            the dissolveDate to set
	 */
	public void setDissolveDate(Date dissolveDate) {
	
		this.dissolveDate = dissolveDate;
	}
	
	/**
	 * @return the deleteFlag
	 */
	@Column(name = "delete_flag", columnDefinition = " int(4) default 0")
	public Integer getDeleteFlag() {
	
		return deleteFlag;
	}
	
	/**
	 * @param deleteFlag
	 *            the deleteFlag to set
	 */
	public void setDeleteFlag(Integer deleteFlag) {
	
		this.deleteFlag = deleteFlag;
	}
	
	/**
	 * @return the country
	 */
	@Column(name = "country")
	public String getCountry() {
	
		return country;
	}
	
	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
	
		this.country = country;
	}
	
	/**
	 * @return the province
	 */
	@Column(name = "province")
	public String getProvince() {
	
		return province;
	}
	
	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
	
		this.province = province;
	}
	
	/**
	 * @return the city
	 */
	@Column(name = "city")
	public String getCity() {
	
		return city;
	}
	
	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
	
		this.city = city;
	}
	
	/**
	 * @return the suburb
	 */
	@Column(name = "suburb")
	public String getSuburb() {
	
		return suburb;
	}
	
	/**
	 * @param suburb
	 *            the suburb to set
	 */
	public void setSuburb(String suburb) {
	
		this.suburb = suburb;
	}
	
	/**
	 * @return the detail
	 */
	@Column(name = "detail")
	public String getDetail() {
	
		return detail;
	}
	
	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
	
		this.detail = detail;
	}
	
	/**,cascade=CascadeType.ALL
	 * @return the user门店管理者
	 */
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "sysuser_id")
	public SysUser getUser() {
	
		return user;
	}
	
	/**
	 * @param user
	 *            门店管理者
	 *            the user to set
	 */
	public void setUser(SysUser user) {
	
		this.user = user;
	}
	
	/**
	 * @return the storeDescription
	 */
	@Type(type = "text")
	@Column(name = "store_description")
	public String getStoreDescription() {
	
		return storeDescription;
	}
	
	/**
	 * @param storeDescription
	 *            the storeDescription to set
	 */
	public void setStoreDescription(String storeDescription) {
	
		this.storeDescription = storeDescription;
	}
	
	/**
	 * @return the createUser
	 *         后台操作人员
	 */
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	public SysUser getCreateUser() {
	
		return createUser;
	}
	
	/**
	 * @param createUser
	 *            后台操作人员
	 *            the createUser to set
	 */
	public void setCreateUser(SysUser createUser) {
	
		this.createUser = createUser;
	}

	
	/**
	 * @return the idCard
	 */
	@Column(name="id_card")
	public String getIdCard() {
	
		return idCard;
	}

	
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
	
		this.idCard = idCard;
	}

	
	/**
	 * @return the phone
	 */
	@Column(name="phone")
	public String getPhone() {
	
		return phone;
	}

	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
	
		this.phone = phone;
	}
	
}
