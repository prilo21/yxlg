/*
 * Member.java
 *
 * Created Date: 2015年5月9日
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
 * @author Michael.Sun
 * @version  <br>
 * <p>会员实体类</p>
 */

@Entity
@Table(name = "yx_member")
public class Member implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -477411564461985744L;
	/**
	 * 会员ID
	 */
	private String memberId;
	/**
	 * 会员用户名
	 */
	private String memberName;
	/**
	 * 会员密码
	 */
	private String password;
	/**
	 * 会员性别
	 */
	private String gender;
	/**
	 * 会员手机号所在地区号，international Dialing Code 如中国：86
	 */
	private String IDCode;
	/**
	 * 会员手机号
	 */
	private String phoneNo;
	/**
	 * 会员邮箱
	 */
	private String email;
	/**
	 * 会员头像
	 */
	private String icon;
	/**
	 * 会员个性签名
	 */
	private String signature;
	/**
	 * 会员地区号
	 */
	private Integer nationId;
	/**
	 * QRCode二维码
	 */
	private String qrcode;
	/**
	 * 删除标识
	 */
	private boolean isdelete;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 会员身高
	 */
	private String height;
	/**
	 * 会员体重
	 */
	private String weight;
	/**
	 * 可用酷特币余额
	 */
	private Integer virtualCurrency;
	/**
	 * 可用现金余额
	 */
	private double cashBalance;
	/**
	 * 注册时间
	 */
	private Date registTime;
	/**
	 * 会员类型
	 * 2016-10-12 alisa.yang 改为关联表适应内部营销会员的需求
	 */
	private MemberType memberType;
	
	/**
	 * 会员等级
	 */
	private MemberGrade memberGrade;
	/**
	 * 会员姓名-供客服人员编辑使用
	 */
	private String addedName;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 友盟  设备token
	 */
	private String deviceToken;
	
	/**
	 * 内部门店店员发放代金券时的二维
	 */
	private String vouchersQrcode;
	
	/**
	 * 返利标志
	 */
	private boolean rebate;
	
	/**
	 * 是否是企业级营销会员
	 */
	private String isEnterpriseMarketingMember;
	
	/**
	 * 会员礼品卡中的金额
	 */
	private double giftCardsCount;
	
	/**
	 * 系统平台
	 */
	private String platform;
	/**
	 * APP版本
	 */
	private String APPVersion;
	/**
	 * 系统型号
	 */
	private String OSVersion;
	/**
	 * 硬件版本
	 */
	private String DeviceType;

	private Date timeStampOptimisticLock;
	
	/**
	 * 会员手机号所在省
	 */
	private String province;
	
	/**
	 * 会员手机号所在市
	 */
	private String city;
	
	/**
	 * 会员等级折扣id
	 */
	private String MemberLevelDiscountNoId = "1";
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp_optimistic")
	public Date getTimeStampOptimisticLock() {
		return timeStampOptimisticLock;
	}

	public void setTimeStampOptimisticLock(Date timeStampOptimisticLock) {
		this.timeStampOptimisticLock = timeStampOptimisticLock;
	}

	@Column(name = "device_token", columnDefinition="varchar(400) default ''")
	public String getDeviceToken() {
	
		return deviceToken;
	}


	public void setDeviceToken(String deviceToken) {
	
		this.deviceToken = deviceToken;
	}

	
	/**
	 * @return the addedName
	 */
	@Column(name = "added_name", columnDefinition="varchar(40) default ''")
	public String getAddedName() {
	
		return addedName;
	}



	
	/**
	 * @param addedName the addedName to set
	 */
	public void setAddedName(String addedName) {
	
		this.addedName = addedName;
	}



	/**
	 * @return the memberType
	 */
	@ManyToOne(targetEntity = MemberType.class)
	@JoinColumn(name = "member_type")
	public MemberType getMemberType() {
	
		return memberType;
	}
	
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType(MemberType memberType) {
	
		this.memberType = memberType;
	}

	@ManyToOne(targetEntity = MemberGrade.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "member_grade")
	public MemberGrade getMemberGrade() {
		return memberGrade;
	}



	public void setMemberGrade(MemberGrade memberGrade) {
		this.memberGrade = memberGrade;
	}



	/**
	 * @return the registTime
	 */
	@Column(name="member_regist_time")
	public Date getRegistTime() {
	
		return registTime;
	}

	
	/**
	 * @param registTime the registTime to set
	 */
	public void setRegistTime(Date registTime) {
	
		this.registTime = registTime;
	}

	/**
	 * @return the birthday
	 */
	@Column(name="member_birthday", columnDefinition="varchar(40) default ''")
	public String getBirthday() {
	
		return birthday;
	}
	
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthday(String birthday) {
	
		this.birthday = birthday;
	}

	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_Id", unique = true, nullable = false, length = 40)
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Column(name = "member_name", columnDefinition="varchar(40) default ''")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String userName) {
		this.memberName = userName;
	}
	
	@Column(name = "member_password", columnDefinition="varchar(40) default ''")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "member_gender", columnDefinition="varchar(40) default ''")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "member_phone_no", columnDefinition="varchar(40) default ''")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "member_email", columnDefinition="varchar(40) default ''")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "member_icon", columnDefinition="varchar(400) default ''")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "member_signature", columnDefinition="varchar(400) default ''")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Column(name = "member_nation_id", columnDefinition="int(20) default 0 ")
	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}
	
	@Column(name = "member_qrcode", columnDefinition="varchar(400) default ''")
	public String getQrcode() {
		return qrcode;
	}
	
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	@Column(name = "member_isdelete",columnDefinition="bit default false")
	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	
	@Column(name = "member_height", columnDefinition="varchar(40) default ''")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "member_weight", columnDefinition="varchar(40) default ''")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the virtualCurrency
	 */
	@Column(name="member_virtual_currency", columnDefinition="int(20) default 0 ")
	public Integer getVirtualCurrency() {
	
		return virtualCurrency;
	}
	
	/**
	 * @param virtualCurrency the virtualCurrency to set
	 */
	public void setVirtualCurrency(Integer virtualCurrency) {
	
		this.virtualCurrency = virtualCurrency;
	}
	
	/**
	 * @return the cashBalance
	 */
	@Column(name="member_cash_balance", columnDefinition="double(20) default 0 ")
	public double getCashBalance() {
	
		return cashBalance;
	}

	/**
	 * @param cashBalance the cashBalance to set
	 */
	public void setCashBalance(double cashBalance) {
	
		this.cashBalance = cashBalance;
	}

	@Column(name="last_login_time")
	public Date getLastLoginTime() {
		
		return lastLoginTime;
	}
	
	public void setLastLoginTime(Date lastLoginTime) {
		
		this.lastLoginTime = lastLoginTime;
	}
	
	@Column(name = "vouchers_qrcode", columnDefinition="varchar(400) default ''")
	public String getVouchersQrcode() {
		
		return vouchersQrcode;
	}
	
	public void setVouchersQrcode(String vouchersQrcode) {
		
		this.vouchersQrcode = vouchersQrcode;
	}

	@Column(name = "is_enterprise_marketing_member", columnDefinition = "varchar(4) default ''")
	public String getIsEnterpriseMarketingMember() {
		return isEnterpriseMarketingMember;
	}


	public void setIsEnterpriseMarketingMember(String isEnterpriseMarketingMember) {
		this.isEnterpriseMarketingMember = isEnterpriseMarketingMember;
	}
	@Column(name = "rebate",columnDefinition="bit default false")
	public boolean isRebate() {
		return rebate;
	}


	public void setRebate(boolean rebate) {
		this.rebate = rebate;
	}
		
	@Column(name="gift_cards_count", columnDefinition="double(20) default 0 ")
	public double getGiftCardsCount() {
	
		return giftCardsCount;
	}

	public void setGiftCardsCount(double giftCardsCount) {
	
		this.giftCardsCount = giftCardsCount;
	}
	
	
	/**
	 * @return the platform
	 */
	@Column(name = "platform", columnDefinition = "varchar(4) default ''")
	public String getPlatform() {
	
		return platform;
	}
	
	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
	
		this.platform = platform;
	}
	
	/**
	 * @return the aPPVersion
	 */
	@Column(name = "app_version", columnDefinition = "varchar(4) default ''")
	public String getAPPVersion() {
	
		return APPVersion;
	}
	
	/**
	 * @param aPPVersion
	 *            the aPPVersion to set
	 */
	public void setAPPVersion(String aPPVersion) {
	
		APPVersion = aPPVersion;
	}
	
	/**
	 * @return the oSVersion
	 */
	@Column(name = "os_version", columnDefinition = "varchar(4) default ''")
	public String getOSVersion() {
	
		return OSVersion;
	}
	
	/**
	 * @param oSVersion
	 *            the oSVersion to set
	 */
	public void setOSVersion(String oSVersion) {
	
		OSVersion = oSVersion;
	}
	
	/**
	 * @return the deviceType
	 */
	@Column(name = "device_type", columnDefinition = "varchar(4) default ''")
	public String getDeviceType() {
	
		return DeviceType;
	}
	
	/**
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
	
		DeviceType = deviceType;
	}

	@Column(name = "ID_code", columnDefinition = "varchar(4) default ''")
	public String getIDCode() {
		return IDCode;
	}


	public void setIDCode(String iDCode) {
		IDCode = iDCode;
	}

	
	/**
	 * @return the memberLevelDiscountNoId
	 */
	@Column(name = "member_level_discount_no_id", columnDefinition = "varchar(40) default '1'")
	public String getMemberLevelDiscountNoId() {
	
		return MemberLevelDiscountNoId;
	}

	
	/**
	 * @param memberLevelDiscountNoId the memberLevelDiscountNoId to set
	 */
	public void setMemberLevelDiscountNoId(String memberLevelDiscountNoId) {
	
		MemberLevelDiscountNoId = memberLevelDiscountNoId;
	}

	@Column(name = "province", columnDefinition = "varchar(40) default ''")
	public String getProvince() {
	
		return province;
	}

	public void setProvince(String province) {
	
		this.province = province;
	}

	@Column(name = "city", columnDefinition = "varchar(40) default ''")
	public String getCity() {
	
		return city;
	}

	public void setCity(String city) {
	
		this.city = city;
	}
	
	
}
