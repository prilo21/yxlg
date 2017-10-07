/*
 * MemberLoginWeixinDto.java
 *
 * Created Date: 2016年8月1日
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

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Alisa.Yang
 * @version  <br>
 * 2016-08-01 第三方登录，微信登录DTO
 */

@ApiModel(value="第三方账号授权后的登录信息")
public class MemberLoginWeixinDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1648999253183382551L;

	/**
	 * 会员手机号，首次登录需要
	 */
	@ApiModelProperty(value="手机号",example="")
	private String phoneNo;
	
	/**
	 * 有手机号就要有验证码
	 */
	@ApiModelProperty(value="手机号验证码",example="")
	private String verifyCode;
	
	/**
	 * 手机号国际编码，如果空则+86
	 */
	@ApiModelProperty(value="手机号国际编码，可空",example="+86")
	private String IDCode;
	
	/**
	 * 首次登录，推送打折卡,给新注册会员推送奖励酷特币通知
	 */
	@ApiModelProperty(value="友盟token,推送通知",example="")
	private String deviceToken;
	
	/**
	 * 第三方登录的类型，weixin、qq、weibo
	 */
	@ApiModelProperty(value="第三方登录的类型，weixin、qq、weibo",example="weixin")
	private String type;
	
	/**
	 * 登录标识，自动登录只有token即可，会有过期时，APP控制，如果授权过期，需要重新登录。
	 */
	@ApiModelProperty(value="登录token",example="")
	private String token;
	
	/**
	 * 微信openid
	 * 普通用户的标识，对当前开发者帐号唯一
	 */
	@ApiModelProperty(value="登录openid",example="")
	private String openid;
	
	/**
	 * 昵称
	 */
	@ApiModelProperty(value="第三方账号昵称",example="")
	private String nickname;
	
	/**
	 * 用户性别，1为男性，2为女性
	 */
	@ApiModelProperty(value="用户性别，1为男性，2为女性",example="1")
	private String sex;
	
	/**
	 * 用户个人资料填写的省份
	 */
	private String province;
	
	/**
	 * 用户个人资料填写的城市
	 */
	private String city;
	
	/**
	 * 国家，如中国为CN
	 */
	private String country;
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 * 比如http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0
	 */
	private String headimgurl;
	
	/**
	 * 用户特权信息，json数组
	 */
	private List<String> privilege;
	
	/**
	 * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
	 * 开发者最好保存unionID信息，以便以后在不同应用之间进行用户信息互通。
	 */
	@ApiModelProperty(value="用户统一标识，微信号，qq号，微博号",example="")
	private String unionid;

	
	public String getPhoneNo() {
		
		return phoneNo;
	}

	
	public void setPhoneNo(String phoneNo) {
		
		this.phoneNo = phoneNo;
	}

	
	public String getOpenid() {
		
		return openid;
	}

	
	public void setOpenid(String openid) {
		
		this.openid = openid;
	}

	
	public String getNickname() {
		
		return nickname;
	}

	
	public void setNickname(String nickname) {
		
		this.nickname = nickname;
	}

	
	public String getSex() {
		
		return sex;
	}

	
	public void setSex(String sex) {
		
		this.sex = sex;
	}

	
	public String getProvince() {
		
		return province;
	}

	
	public void setProvince(String province) {
		
		this.province = province;
	}

	
	public String getCity() {
		
		return city;
	}

	
	public void setCity(String city) {
		
		this.city = city;
	}

	
	public String getCountry() {
		
		return country;
	}

	
	public void setCountry(String country) {
		
		this.country = country;
	}

	
	public String getHeadimgurl() {
		
		return headimgurl;
	}

	
	public void setHeadimgurl(String headimgurl) {
		
		this.headimgurl = headimgurl;
	}

	
	public List<String> getPrivilege() {
		
		return privilege;
	}

	
	public void setPrivilege(List<String> privilege) {
		
		this.privilege = privilege;
	}

	
	public String getUnionid() {
		
		return unionid;
	}

	
	public void setUnionid(String unionid) {
		
		this.unionid = unionid;
	}


	
	public String getVerifyCode() {
		
		return verifyCode;
	}


	
	public void setVerifyCode(String verifyCode) {
		
		this.verifyCode = verifyCode;
	}


	
	public String getIDCode() {
		
		return IDCode;
	}


	
	public void setIDCode(String iDCode) {
		
		IDCode = iDCode;
	}


	
	public String getDeviceToken() {
		
		return deviceToken;
	}


	
	public void setDeviceToken(String deviceToken) {
		
		this.deviceToken = deviceToken;
	}


	
	public String getType() {
		
		return type;
	}


	
	public void setType(String type) {
		
		this.type = type;
	}


	
	public String getToken() {
		
		return token;
	}


	
	public void setToken(String token) {
		
		this.token = token;
	}
	
}
