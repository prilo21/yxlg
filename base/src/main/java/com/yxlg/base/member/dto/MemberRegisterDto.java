/*
 * MemberRegisterDto.java
 *
 * Created Date: 2016年8月18日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.member.dto;

import java.util.Date;

import com.yxlg.base.member.entity.MemberGrade;

/**
 * @author Alisa.Yang 2016-8-18
 * @version  <br>
 * 应老带新活动的相关需求，注册接口修改为dto接收
 */

public class MemberRegisterDto {
	
	/**
	 * 推荐人手机号
	 */
	private String recommendPhoneNo;
	
	/**
	 * 推荐人 会员id
	 */
	private String recommendMemberId;
	
	/**
	 * 推荐方式
	 * AT - 通过某活动推荐
	 */
	private String recommendOriginType = "AT";
	
	/**
	 * 推荐来源id
	 */
	private String recommendOriginId;
	
	/**
	 * 推荐来源活动名称，比如：老带新一亿红包任性送
	 */
	private String recommendOriginName;
	
	
	//以下成员变量来自于会员类Member
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
	private float cashBalance;
	/**
	 * 注册时间
	 */
	private Date registTime;
	/**
	 * 会员类型
	 */
	private String memberType;
	
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
	private float giftCardsCount;
	
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
	 * 会员等级折扣id
	 */
	private String MemberLevelDiscountNoId = "1";

	/**
	 * Michael.Sun
	 * 2016年09月19日
	 * 注册来源（海尔双节注册发红包）
	 */
	private String origin;
	
	public String getRecommendPhoneNo() {
		
		return recommendPhoneNo;
	}

	
	public void setRecommendPhoneNo(String recommendPhoneNo) {
		
		this.recommendPhoneNo = recommendPhoneNo;
	}

	
	public String getRecommendMemberId() {
		
		return recommendMemberId;
	}

	
	public void setRecommendMemberId(String recommendMemberId) {
		
		this.recommendMemberId = recommendMemberId;
	}

	
	public String getRecommendOriginType() {
		
		return recommendOriginType;
	}

	
	public void setRecommendOriginType(String recommendOriginType) {
		
		this.recommendOriginType = recommendOriginType;
	}

	
	public String getRecommendOriginId() {
		
		return recommendOriginId;
	}

	
	public void setRecommendOriginId(String recommendOriginId) {
		
		this.recommendOriginId = recommendOriginId;
	}

	
	public String getRecommendOriginName() {
		
		return recommendOriginName;
	}

	
	public void setRecommendOriginName(String recommendOriginName) {
		
		this.recommendOriginName = recommendOriginName;
	}

	
	public String getMemberId() {
		
		return memberId;
	}

	
	public void setMemberId(String memberId) {
		
		this.memberId = memberId;
	}

	
	public String getMemberName() {
		
		return memberName;
	}

	
	public void setMemberName(String memberName) {
		
		this.memberName = memberName;
	}

	
	public String getPassword() {
		
		return password;
	}

	
	public void setPassword(String password) {
		
		this.password = password;
	}

	
	public String getGender() {
		
		return gender;
	}

	
	public void setGender(String gender) {
		
		this.gender = gender;
	}

	
	public String getIDCode() {
		
		return IDCode;
	}

	
	public void setIDCode(String iDCode) {
		
		IDCode = iDCode;
	}

	
	public String getPhoneNo() {
		
		return phoneNo;
	}

	
	public void setPhoneNo(String phoneNo) {
		
		this.phoneNo = phoneNo;
	}

	
	public String getEmail() {
		
		return email;
	}

	
	public void setEmail(String email) {
		
		this.email = email;
	}

	
	public String getIcon() {
		
		return icon;
	}

	
	public void setIcon(String icon) {
		
		this.icon = icon;
	}

	
	public String getSignature() {
		
		return signature;
	}

	
	public void setSignature(String signature) {
		
		this.signature = signature;
	}

	
	public Integer getNationId() {
		
		return nationId;
	}

	
	public void setNationId(Integer nationId) {
		
		this.nationId = nationId;
	}

	
	public String getQrcode() {
		
		return qrcode;
	}

	
	public void setQrcode(String qrcode) {
		
		this.qrcode = qrcode;
	}

	
	public boolean isIsdelete() {
		
		return isdelete;
	}

	
	public void setIsdelete(boolean isdelete) {
		
		this.isdelete = isdelete;
	}

	
	public String getBirthday() {
		
		return birthday;
	}

	
	public void setBirthday(String birthday) {
		
		this.birthday = birthday;
	}

	
	public String getHeight() {
		
		return height;
	}

	
	public void setHeight(String height) {
		
		this.height = height;
	}

	
	public String getWeight() {
		
		return weight;
	}

	
	public void setWeight(String weight) {
		
		this.weight = weight;
	}

	
	public Integer getVirtualCurrency() {
		
		return virtualCurrency;
	}

	
	public void setVirtualCurrency(Integer virtualCurrency) {
		
		this.virtualCurrency = virtualCurrency;
	}

	
	public float getCashBalance() {
		
		return cashBalance;
	}

	
	public void setCashBalance(float cashBalance) {
		
		this.cashBalance = cashBalance;
	}

	
	public Date getRegistTime() {
		
		return registTime;
	}

	
	public void setRegistTime(Date registTime) {
		
		this.registTime = registTime;
	}

	
	public String getMemberType() {
		
		return memberType;
	}

	
	public void setMemberType(String memberType) {
		
		this.memberType = memberType;
	}

	
	public MemberGrade getMemberGrade() {
		
		return memberGrade;
	}

	
	public void setMemberGrade(MemberGrade memberGrade) {
		
		this.memberGrade = memberGrade;
	}

	
	public String getAddedName() {
		
		return addedName;
	}

	
	public void setAddedName(String addedName) {
		
		this.addedName = addedName;
	}

	
	public Date getLastLoginTime() {
		
		return lastLoginTime;
	}

	
	public void setLastLoginTime(Date lastLoginTime) {
		
		this.lastLoginTime = lastLoginTime;
	}

	
	public String getDeviceToken() {
		
		return deviceToken;
	}

	
	public void setDeviceToken(String deviceToken) {
		
		this.deviceToken = deviceToken;
	}

	
	public String getVouchersQrcode() {
		
		return vouchersQrcode;
	}

	
	public void setVouchersQrcode(String vouchersQrcode) {
		
		this.vouchersQrcode = vouchersQrcode;
	}

	
	public boolean isRebate() {
		
		return rebate;
	}

	
	public void setRebate(boolean rebate) {
		
		this.rebate = rebate;
	}

	
	public String getIsEnterpriseMarketingMember() {
		
		return isEnterpriseMarketingMember;
	}

	
	public void setIsEnterpriseMarketingMember(String isEnterpriseMarketingMember) {
		
		this.isEnterpriseMarketingMember = isEnterpriseMarketingMember;
	}

	
	public float getGiftCardsCount() {
		
		return giftCardsCount;
	}

	
	public void setGiftCardsCount(float giftCardsCount) {
		
		this.giftCardsCount = giftCardsCount;
	}

	
	public String getPlatform() {
		
		return platform;
	}

	
	public void setPlatform(String platform) {
		
		this.platform = platform;
	}

	
	public String getAPPVersion() {
		
		return APPVersion;
	}

	
	public void setAPPVersion(String aPPVersion) {
		
		APPVersion = aPPVersion;
	}

	
	public String getOSVersion() {
		
		return OSVersion;
	}

	
	public void setOSVersion(String oSVersion) {
		
		OSVersion = oSVersion;
	}

	
	public String getDeviceType() {
		
		return DeviceType;
	}

	
	public void setDeviceType(String deviceType) {
		
		DeviceType = deviceType;
	}

	
	public Date getTimeStampOptimisticLock() {
		
		return timeStampOptimisticLock;
	}

	
	public void setTimeStampOptimisticLock(Date timeStampOptimisticLock) {
		
		this.timeStampOptimisticLock = timeStampOptimisticLock;
	}

	
	public String getMemberLevelDiscountNoId() {
		
		return MemberLevelDiscountNoId;
	}

	
	public void setMemberLevelDiscountNoId(String memberLevelDiscountNoId) {
		
		MemberLevelDiscountNoId = memberLevelDiscountNoId;
	}


	
	/**
	 * @return the origin
	 */
	public String getOrigin() {
	
		return origin;
	}


	
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
	
		this.origin = origin;
	}
	
}
