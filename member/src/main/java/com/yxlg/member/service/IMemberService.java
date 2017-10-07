/*
 * IMemberService.java
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
package com.yxlg.member.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.member.dto.PhoneCodeDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.util.Result;
import com.yxlg.member.util.PWConfirm;

/**
 * @author Alison.liu
 * @version  v1.0
 * <p>会员管理接口</p>
 */

public interface IMemberService {

	/**
	 * 编辑会员基本信息
	 * @param id
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> updateMemberInfo(String id, Member member, HttpServletRequest request);
	/**
	 * 帐号安全
	 * @param id
	 * @return
	 */
	public ResponseEntity<Result> getSecurity(String id);
	/**
	 * 我的二维码
	 * @param id
	 * @return
	 */
	public ResponseEntity<Result> getQrcode(String id);
	/**
	 * 修改会员密码
	 * @param id
	 * @param pwconfirm
	 * @return
	 */
	public ResponseEntity<Result> updatePassWord(String id, PWConfirm pwconfirm);
	/**
	 * 用户注册
	 * @param member
	 *
	 * @return
	 */
	public ResponseEntity<Result> registerMember(Member member, String verifyCode, String identify);
	/**
	 * 验证注册用户的手机或者邮箱
	 * @param emailPhone
	 * @return
	 */
	public ResponseEntity<Result> verifyMemberRegistration(PhoneCodeDto dto, boolean isPost);
	/**
	 * 验证修改的手机或者邮箱
	 * @param emailPhone
	 * @param IDCode 国家代号
	 * @return
	 */
	public ResponseEntity<Result> verifyMember(String emailPhone, boolean isPost);

	/**
	 * 修改绑定邮箱
	 * @param id
	 * @param member
	 * @return public ResponseEntity<Result>
	 */
	public ResponseEntity<Result> changeEmail(String id, Member member);
	
	/**
	 * 根据手机号和邮箱查找用户
	 * @param emailPhone
	 * @return
	 */
	public ResponseEntity<Result> memberIsExist(String emailPhone);
	/**
	 * 更新用户二维码
	 * @param memberId
	 * @return
	 */
	public String UpdateMemberQRCode(String memberId, HttpServletRequest request);
	
	/**
	 * 通过手机号判断是不是酷特营销会员
	 * @param phoneNo
	 * @return
	 */
	public ResponseEntity<Result> isWannaMember(String phoneNo);
	/**
	 * 通过会员手机号获得会员信息
	 * @param phone
	 * @return
	 */
	public Member findMemberByPhone(String phone);
	
	/**
	 * 根据手机号删除请求过的手机验证码
	 */
	public void deleteValidateCodeByPhoneNo(String PhoneNo);
	/**
	 * @param emailPhone
	 * @param isPost
	 * @return
	 */
	public ResponseEntity<Result> verifyMemberRegistration(String emailPhone, boolean isPost);
	
	/**
	 * 根据手机号归属地设置会员地址
	 * @return
	 */
	public Result setCity(String startTime, String endTime);
}
