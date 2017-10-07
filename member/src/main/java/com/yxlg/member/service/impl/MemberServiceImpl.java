/*
 * MemberServiceImpl.java
 * 
 * Created Date: 2015年5月9日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */
package com.yxlg.member.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yxlg.base.constant.Constants;
import com.yxlg.base.constant.ResultCode;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.dto.PhoneCodeDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberEmailPhoneVerifyCode;
import com.yxlg.base.member.entity.MemberGrade;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.memberRecommend.entity.MemberRecommendDelete;
import com.yxlg.base.sso.entity.SSOUser;
import com.yxlg.base.upload.util.CatchPicture;
import com.yxlg.base.util.AppVersion;
import com.yxlg.base.util.AppVersionUtil;
import com.yxlg.base.util.BusinessException;
import com.yxlg.base.util.JsonValidator;
import com.yxlg.base.util.ListUtils;
import com.yxlg.base.util.PhoneNoCity;
import com.yxlg.base.util.PhoneNoValidator;
import com.yxlg.base.util.QRCodeUtil;
import com.yxlg.base.util.RandomValue;
import com.yxlg.base.util.Result;
import com.yxlg.base.util.TokenHandler;
import com.yxlg.member.service.IMemberService;
import com.yxlg.member.util.PWConfirm;
import com.yxlg.member.util.SSOUtil;

import net.sf.json.JSONObject;

/**
 * @author Alison.liu
 * @version v1.0
 *          <p>
 *          会员管理接口实现
 *          </p>
 */
@Service
public class MemberServiceImpl implements IMemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Override
	public ResponseEntity<Result> updateMemberInfo(String id, Member member, HttpServletRequest request) {
	
		Member m = baseDao.findById(Member.class, id);
		if (m == null) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_NOT_EXIST), HttpStatus.BAD_REQUEST);
		}
		if (member.getMemberName() != null) {// 更新用户名
			m.setMemberName(member.getMemberName());
		}
		if (member.getIcon() != null) {// 更新头像
			m.setIcon(member.getIcon());
			updateQrcode(m, request.getHeader(Constants.HEADER_C2M_IDENTIFY));
		}
		if (member.getSignature() != null) {// 更新个性签名
			m.setSignature(member.getSignature());
		}
		if (member.getHeight() != null) {// 更新高度
			Pattern pattern = Pattern.compile("^(-)?\\d+(\\.\\d+)?$");
			Matcher matcher = pattern.matcher(member.getHeight());
			if (matcher.matches()) {
				if (member.getHeight().length() > 10) {
					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_EXCEED_LENGTH), HttpStatus.BAD_REQUEST);
				} else {
					m.setHeight(member.getHeight());
				}
			} else {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_FORMAT_ERROR), HttpStatus.BAD_REQUEST);
			}
			
		}
		if (member.getWeight() != null) {// 更新体重
			Pattern pattern = Pattern.compile("^(-)?\\d+(\\.\\d+)?$");
			Matcher matcher = pattern.matcher(member.getWeight());
			if (matcher.matches()) {
				if (member.getWeight().length() > 10) {
					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_EXCEED_LENGTH), HttpStatus.BAD_REQUEST);
				} else {
					m.setWeight(member.getWeight());
				}
			} else {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_FORMAT_ERROR), HttpStatus.BAD_REQUEST);
			}
		}
		if (member.getGender() != null) {// 更新性别
			m.setGender(member.getGender());
		}
		if (member.getBirthday() != null) {// 更新生日
			m.setBirthday(member.getBirthday());
		}
		baseDao.update(m);
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_EDIT_SUCCESS), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Result> getSecurity(String id) {
	
		Member m = baseDao.findById(Member.class, id);
		if (m == null) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_NOT_FOUND), HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNo", m.getPhoneNo());
		map.put("email", m.getEmail());
		return new ResponseEntity<Result>(new Result(map), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Result> getQrcode(String memberId) {
	
		try {
			if (StringUtils.isEmpty(memberId)) {
				return new ResponseEntity<Result>(new Result(400, "用户Id为空"), HttpStatus.BAD_REQUEST);
			} else {
				Member member = baseDao.findById(Member.class, memberId);
				if (member == null) {
					return new ResponseEntity<Result>(new Result(400, "无此用户"), HttpStatus.BAD_REQUEST);
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", member.getIcon());
					map.put("memberName", member.getMemberName());
					map.put("signature", member.getSignature());
					map.put("qrCode", member.getQrcode());
					return new ResponseEntity<Result>(new Result(map), HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			throw new BusinessException("id为:" + memberId + "的用户获取二维码失败", e);
		}
	}
	
	@Override
	public ResponseEntity<Result> updatePassWord(String id, PWConfirm pwconfirm) {
	
		Member oldMember = baseDao.findById(Member.class, id);
		if (oldMember == null) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_NOT_EXIST), HttpStatus.BAD_REQUEST);
		}
		if (pwconfirm.getOldPassword() != null && pwconfirm.getNewPassword() != null && pwconfirm.getConfirmPassword() != null) {
			if (!DigestUtils.md5Hex(pwconfirm.getOldPassword()).equals(oldMember.getPassword())) {
				log.info("用户" + oldMember.getMemberId() + "-" + oldMember.getPhoneNo() + "用户密码不正确！");
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PASSWORD_ERROR), HttpStatus.BAD_REQUEST);
			}
			if (!pwconfirm.getConfirmPassword().equals(pwconfirm.getNewPassword())) {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PASSWORD_NOT_MATCH), HttpStatus.BAD_REQUEST);
			}
			oldMember.setPassword(DigestUtils.md5Hex(pwconfirm.getNewPassword()));
			baseDao.update(oldMember);
			
			ssoUtil.updateSSO(oldMember);
			log.info("用户" + oldMember.getMemberId() + "-" + oldMember.getPhoneNo() + "用户密码修改成功！");
			return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_PASSWORD_EDIT_SUCCESS), HttpStatus.OK);
		} else
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.SC_INFO_INCOMPLETE), HttpStatus.BAD_REQUEST);
	}
	
	

	@Override
	public ResponseEntity<Result> memberIsExist(String emailPhone) {
	
		Member oldMember = null;
		if (PhoneNoValidator.isPhoneNoValid(emailPhone)) {
			oldMember = baseDao.findUniqueByProperty(Member.class, "phoneNo", emailPhone);
		} else if (GenericValidator.isEmail(emailPhone)) {
			oldMember = baseDao.findUniqueByProperty(Member.class, "email", emailPhone);
		}
		if (oldMember == null) {
			log.info("用户" + emailPhone + "没有找到！");
			return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_NOT_FIND), HttpStatus.OK);
		}
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_IS_EXIST, oldMember), HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<Result> registerMember(Member member, String verifyCode, String identify) {
		
		if (StringUtils.isEmpty(member.getPhoneNo()) && StringUtils.isEmpty(member.getEmail())) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_AND_EMAIL_EMPTY), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(member.getMemberName())) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_NAME_EMPTY), HttpStatus.BAD_REQUEST);
		}
		if (!JsonValidator.checkUserName(member.getMemberName())) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_NAME_VAL_FAILED), HttpStatus.BAD_REQUEST);
		}
		
		if (!StringUtils.isEmpty(member.getPhoneNo())) {
			if (!(baseDao.findUniqueByProperty(Member.class, "phoneNo", member.getPhoneNo()) == null)) {
				// log.info("手机号" + member.getPhoneNo() + "已经注册过了！");
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_EXIST), HttpStatus.BAD_REQUEST);
			}
			/**
			 * Michael.Sun 2015/08/15
			 */
			if (!"MARVIN_TEST_TO_DELETE".equals(verifyCode)) {
				Criteria criteria = baseDao.createCriteria(MemberEmailPhoneVerifyCode.class);
				criteria.add(Restrictions.eq("emailPhone", member.getPhoneNo()));
				criteria.addOrder(Order.desc("remark"));
				// List<MemberEmailPhoneVerifyCode> memberEmailPhoneVerifyCodes
				// =
				// baseDao.findListByProperty(MemberEmailPhoneVerifyCode.class,
				// "emailPhone", member.getPhoneNo());
				@SuppressWarnings("unchecked")
				List<MemberEmailPhoneVerifyCode> memberEmailPhoneVerifyCodes = criteria.list();
				// MemberEmailPhoneVerifyCode memberEmailPhoneVerifyCode =
				// baseDao.findUniqueByProperty(MemberEmailPhoneVerifyCode.class,
				// "emailPhone", member.getPhoneNo());
				if (memberEmailPhoneVerifyCodes == null || memberEmailPhoneVerifyCodes.size() == 0) {
					// log.info("手机号" + member.getPhoneNo()+"未请求验证码");
					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_NOT_VERIFY), HttpStatus.BAD_REQUEST);
				} else {
					boolean rightFlag = false;
					boolean timeFlag = false;
					// for (MemberEmailPhoneVerifyCode
					// memberEmailPhoneVerifyCode : memberEmailPhoneVerifyCodes)
					// {
					// if
					// (verifyCode.equals(memberEmailPhoneVerifyCode.getVerifyCode()))
					// {
					// rightFlag = true;
					// verifyTime =
					// Long.parseLong(memberEmailPhoneVerifyCode.getRemark());
					// if (currentTime - verifyTime < time) {
					// timeFlag = true;
					// baseDao.deleteAllEntity(memberEmailPhoneVerifyCodes);
					// break;
					// }
					// }
					// }
					if (verifyCode.equals(memberEmailPhoneVerifyCodes.get(0).getVerifyCode())) {
						rightFlag = true;
						timeFlag = PhoneNoValidator.checkVerifyCodesTime(memberEmailPhoneVerifyCodes.get(0).getRemark());
					}
					if (rightFlag == false) {
						log.info("手机号:" + member.getPhoneNo() + "请求注册时，验证码:" + verifyCode + "错误！");
						return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_VERIFY_ERROR), HttpStatus.BAD_REQUEST);
					} else if (timeFlag == false) {
						log.info("手机号:" + member.getPhoneNo() + "请求注册时，验证码:" + verifyCode + "超时！");
						return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_VERIFY_OVERTIME), HttpStatus.BAD_REQUEST);
					}
				}
			}
			
			// else{
			// for(MemberEmailPhoneVerifyCode memberEmailPhoneVerifyCode:
			// memberEmailPhoneVerifyCodes){
			// baseDao.delete(memberEmailPhoneVerifyCode);
			// }
			// }
			
		}
		
		if (!StringUtils.isEmpty(member.getEmail())) {
			if (!(baseDao.findUniqueByProperty(Member.class, "email", member.getEmail()) == null)) {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_EMAIL_EXIST), HttpStatus.BAD_REQUEST);
			}
		}
		
		member.setRegistTime(new Date());
		member.setVirtualCurrency(0);
		member.setCashBalance(0);
		member.setSignature(Constants.Registration.DEFAULT_SIGNATURE);
		if(StringUtils.isNotBlank(member.getPassword())){
			member.setPassword(DigestUtils.md5Hex(member.getPassword()));
		}
		//2016-10-12 alisa.yang 改为关联表
		MemberType type = new MemberType();
		type.setMemberTypeId(Constants.MemberType.COMMON_MEMBER_NO);
		member.setMemberType(type);
		List<MemberGrade> memberGrade = baseDao.findListByProperty(MemberGrade.class, "constant", Constants.MemberGrade.P1);
		if (memberGrade.size() == 0) {
			member.setMemberGrade(null);
		} else {
			member.setMemberGrade(memberGrade.get(0));
		}
		//2016年11月14日 保存用户登录时的app版本，手机型号等信息
		AppVersion versionInfo = AppVersionUtil.getAppVersion();
		member.setPlatform(versionInfo.getPlatform());
		member.setAPPVersion(versionInfo.getVersionNo());
		member.setOSVersion(versionInfo.getOSversion());
		member.setDeviceType(versionInfo.getDeviceType());
		member.setLastLoginTime(new Date());
		baseDao.save(member);
		// 2016-01-19 log在save之前，打印日志是"新用户注册成功,null-***"
		log.info("新用户注册成功," + member.getMemberId() + "-" + member.getPhoneNo());
		
		
/*		// 新注册会员时，检查成衣尺寸量体数据中有没有与新注册会员手机号相同的成衣量体数据;
		// 1. 若有，且memberId字段为空，则 1)将memberId字段更新 2)将量体数据关联的订单中的会员信息更新
		// 2. 若有，但memberId字段不为空，则逻辑实际是已注册会员密码找回，不做处理
		if(haveMemberMeasureCYInfo(member.getPhoneNo())){
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("memberId", member.getMemberId());
//			updateMap.put("measureOrder.member", member);
			baseDao.updateBatchByPropertyName(MemberMeasureCYInfo.class, updateMap, "measurerPhoneNo", member.getPhoneNo());
			updateMeasureOrder(member);
		}
*/
		// 2016-01-18 APP新注册用户即时关联手机号相同的量体师账号
		//this.setAPPUserToMeasurer(member);
		
		/**
		 * 赠送打折卡
		 */
		/*
		 * UserDiscountCard ud = new UserDiscountCard();
		 * ud.setAppliedCategory("0");// 0全部
		 * ud.setAppliedValue(0);// 0
		 * ud.setDiscountCardCount(1);// 1
		 * ud.setDiscountCardImage(StringUtils.EMPTY);// ""
		 * ud.setDiscountCardNo(StringUtils.EMPTY);// ""
		 * ud.setDiscountCardPercent(0.8);// 0.8
		 * ud.setDiscountCardQrCode(StringUtils.EMPTY);// ""
		 * ud.setDiscountCardState(C2MConstants.Coupon.COUPON_STATUS_NOUSE);//
		 * ud.setDiscountCardType("0");// 0
		 * ud.setDiscountCardUrl(StringUtils.EMPTY);// ""
		 * ud.setDiscountCategory(C2MConstants.TradeType.DISCOUNTCARD);
		 * ud.setIssuedisCount(null);// NULL
		 * ud.setPhone(member.getPhoneNo());// 会员手机号
		 * Date time = new Date();
		 * ud.setReceiveTime(time);// new Date
		 * Date date2 = null;
		 * try {
		 * date2 = DateUtils.parseDate(DateFormatUtils.format(time,
		 * C2MConstants.DateFormatConstant.DATE_FORMAT),
		 * C2MConstants.DateFormatConstant.DATE_FORMAT);
		 * } catch (ParseException e1) {
		 * throw new BusinessException();
		 * }
		 * Calendar c = Calendar.getInstance();
		 * c.setTime(date2);
		 * ud.setValidFrom(date2);// 时分秒为0
		 * // 有限时间
		 * // c.add(Calendar.DAY_OF_MONTH, t.getDeadline());
		 * c.add(Calendar.YEAR, 1);// 加100年
		 * ud.setValidTo(c.getTime());// 加100年
		 * ud.setReduceFlag("1");
		 * baseDao.save(ud);
		 * // 再保存生成两张卡
		 * baseDao.evict(ud);
		 * ud.setDiscountCardId(null);
		 * baseDao.save(ud);
		 */
		
		// 为新用户在 现金余额和酷特币 锁定表MemberCoinLocker 中创建Record
		/*
		 * MemberCoinLocker coinLocker = new MemberCoinLocker();
		 * coinLocker.setMember(member);
		 * coinLocker.setCashLocked(0);
		 * coinLocker.setCoinLocked(0);
		 * baseDao.save(coinLocker);
		 */
		
		// 向单点登录CAS中添加新用户
		ssoUtil.insertSSO(member);
		// 生成包含用户 MemberID信息的二维码图片，上传七牛服务器，并把将返回地址保存到数据库中
		updateQrcode(member, identify);
		// 添加未注册时领取的礼品卡
		//addMemberGift(member);
		
		// 获取用户的token信息并返回给APP，让新注册用户注册成功后不用再登录一次
		Map<String, Object> map = new HashMap<String, Object>();
		long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;
		map.put("memberId", member.getMemberId());
		TokenHandler tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(ssoKey));
		SSOUser ssoUser = baseDao.findUniqueByProperty(SSOUser.class, "sysid", member.getMemberId());
		ssoUser.setExpires(System.currentTimeMillis() + TEN_DAYS);
		String xauthtoken = tokenHandler.createTokenForUser(ssoUser);
		map.put("X-AUTH-TOKEN", xauthtoken);
		
		// 返回用户的其他个人信息
		Map<String, Object> memberInfoMap = new HashMap<String, Object>();
		memberInfoMap.put("memberId", member.getMemberId());
		memberInfoMap.put("icon", member.getIcon());
		memberInfoMap.put("memberName", member.getMemberName());
		memberInfoMap.put("signature", member.getSignature());
		memberInfoMap.put("qrCode", member.getQrcode());
		memberInfoMap.put("phoneNo", member.getPhoneNo());
		memberInfoMap.put("email", member.getEmail());
		map.put("memberInfo", memberInfoMap);
		Map<String, Object> personalInfoMap = new HashMap<String, Object>();
		personalInfoMap.put("gender", member.getGender());
		personalInfoMap.put("height", member.getHeight());
		personalInfoMap.put("dob", member.getBirthday());
		personalInfoMap.put("weight", member.getWeight());
		map.put("personalInfo", personalInfoMap);
		map.put("vitualCurrency", member.getVirtualCurrency());
		map.put("myWallet", member.getCashBalance());
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_REG_SUCCESS, map), HttpStatus.OK);
	}
	
	/**
	 * @param member
	 */
/*	private void updateMeasureOrder(Member member) {
		List<BodyMeasurementOrder> list = baseDao.findListByProperty(BodyMeasurementOrder.class, "phoneNo", member.getPhoneNo());
		if(!ListUtils.isEmpty(list)){
			for(BodyMeasurementOrder bodyMeasurementOrder : list){
				bodyMeasurementOrder.setMember(member);
			}
		}
	}

	*//**
	 * Michael.Sun
	 * 2016年6月27日 15:49
	 * 判断新注册的会员是否已经有了成衣量体数据
	 * @param phoneNo
	 * @return
	 *//*
	private boolean haveMemberMeasureCYInfo(String phoneNo) {
		
		List<MemberMeasureCYInfo> memberMeasureCYInfoList = baseDao.findListByProperty(MemberMeasureCYInfo.class, "measurerPhoneNo", phoneNo);
		if(ListUtils.isEmpty(memberMeasureCYInfoList)){
			return false;
		}
		return true;
	}
*/
	
	@Override
	public ResponseEntity<Result> verifyMemberRegistration(PhoneCodeDto dto, boolean isPost) {
	
		try { 
			if(PhoneNoValidator.isNumeric(dto.getEmailPhone()) && "reg".equals(dto.getCodePurpose())){
				Member member = baseDao.findUniqueByProperty(Member.class, "phoneNo", dto.getEmailPhone());
				if(member != null){
					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), "该手机号已注册，请直接登录"), HttpStatus.BAD_REQUEST);
				}
			}
			//2017-01-06 marvin.ma 微信网页预约量体传的platform是WX1，WX2等，老版本以强更，因此取消版本控制。
//			if(AppVersionUtil.isNewApp(AppVersionUtil.getHttpServletRequest(), C2MConstants.AppVersion.VERSION_AND_3_9_1, C2MConstants.AppVersion.VERSION_IOS_6_294)){
			HttpServletRequest request = AppVersionUtil.getHttpServletRequest();
			log.info("手机号" + dto.getEmailPhone() + "请求注册验证码,传入header：" + request.getHeader("C2M-Identify") + ",body:" + JSON.toJSONString(dto));
    			if(StringUtils.isEmpty(dto.getEmailPhone())) {
    				log.info("手机号" + dto.getEmailPhone() + "请求短信验证码时，手机号为空");
    				return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.PHONE_NO_NULL, "手机号不能为空啊"), HttpStatus.BAD_REQUEST);
    			}
				Cookie[] cookies = AppVersionUtil.getHttpServletRequest().getCookies();
				if(cookies == null || cookies.length == 0){
					log.info("手机号" + dto.getEmailPhone() + "请求短信验证码时，cookie为空");
					return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.NO_COOKIE_FINDED, "图片验证码错误或已过期"), HttpStatus.BAD_REQUEST);
				}
				for(Cookie cookie : cookies){
					if("imageRequestId".equalsIgnoreCase(cookie.getName())){
						dto.setImageRequestId(cookie.getValue());
					}
				}
				if(StringUtils.isBlank(dto.getImageRequestId())){
					log.info("手机号" + dto.getEmailPhone() + "请求短信验证码时，cookie中imageRequestId为空");
					return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.NO_COOKIE_FINDED, "图片验证码错误或已过期"), HttpStatus.BAD_REQUEST);
				}
				if(!verifyImageCode(dto)){
					return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.ERRROR_IMAGE_CODE, "图片验证码错误或已过期"), HttpStatus.BAD_REQUEST);
				}
//			}
			if(StringUtils.isBlank(request.getHeader("C2M-Identify"))){
//				log.info("手机号" + emailPhoneBody + "请求注册验证码header信息为空");
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), "请求频繁，请稍后再试"), HttpStatus.BAD_REQUEST);
			}
			String emailPhone = "";
			String IDCode = "";
			if (isPost) {
				emailPhone = dto.getEmailPhone();
				IDCode = dto.getIdCode();
				if (StringUtils.isBlank(IDCode)) {
					IDCode = "+86";
//					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), C2MConstants.ReturnMsg.MEMBER_PHONE_NO_IDCODE), HttpStatus.BAD_REQUEST);
				}
			} else {
				emailPhone = dto.getEmailPhone();
				IDCode = "+86";
			}
//			if (veryfyPhoneExist(emailPhone)) {
//				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), C2MConstants.ReturnMsg.MEMBER_PHONE_EXIST), HttpStatus.BAD_REQUEST);
//			}
			String randomCode = RandomValue.getRandomNumber(true, 6);
			MemberEmailPhoneVerifyCode memberPhoneVerifyCode = new MemberEmailPhoneVerifyCode();
			memberPhoneVerifyCode.setEmailPhone(emailPhone);
			memberPhoneVerifyCode.setVerifyCode(randomCode);
			String remark = String.valueOf(System.currentTimeMillis());
			memberPhoneVerifyCode.setRemark(remark);
			baseDao.save(memberPhoneVerifyCode);
			Map<String, String> varMap = new HashMap<String, String>();
			varMap.put("register_random_code", randomCode);
			if (PhoneNoValidator.isNumeric(emailPhone)) {
				/*if ("+86".equals(IDCode) || StringUtils.isEmpty(IDCode)) {
					SendService.SendMessage(emailPhone, C2MConstants.SubmailProject.PHONE_VERIFICATION, varMap);
				} else {
					String msg = C2MConstants.msgContent.MSG_PHONE_REGISTRATION.replace("@var(register_random_code)", randomCode);
					SendService.SendGlobalMessage(emailPhone, C2MConstants.SubmailProject.PHONE_VERIFICATION, varMap, IDCode, msg);
				}
				log.info("向手机号：" + emailPhone + "发送验证码:" + randomCode + "成功！");*/
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_PHONE_CAPTCHA_SENT), HttpStatus.OK);
			} else if (GenericValidator.isEmail(emailPhone)) {
				MyRunnable myrun = new MyRunnable();
				myrun.emailPhone = emailPhone;
				myrun.varMap = varMap;
				myrun.start();
				// SendService.SendMail(emailPhone, null,
				// C2MConstants.SubmailProject.MAIL_REGISTRATION, varMap, null);
				
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_EMAIL_CAPTCHA_SENT), HttpStatus.OK);
			} else {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_OR_EMAIL_INVALID), HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			throw new BusinessException("验证注册用户出错,手机号或邮箱为：" + dto.getEmailPhone(), e);
		}
	}
	
	/**
	 * 验证图片验证码是否正确
	 * @param dto
	 * @return
	 */
	private boolean verifyImageCode(PhoneCodeDto dto) {
		log.info("验证图片验证码方法入参：" + JSONObject.fromObject(dto));
		ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) valueOperations.get(dto.getCodePurpose() + "-" + dto.getImageRequestId());
		if(map!=null && dto!= null && !StringUtils.isBlank(dto.getImageCode()) 
				&& dto.getImageCode().equalsIgnoreCase((String) map.get("imageCode")) 
				&& timeEffective(3, (long)map.get("time"))){
			//图片验证码验证通过后，将其从redis删除
//			valueOperations.set(dto.getCodePurpose() + "-" + dto.getImageRequestId(), null);
			redisTemplate.delete(dto.getCodePurpose() + "-" + dto.getImageRequestId());
			return true;
		}
		return false;
	}

	/**
	 * 校验图片验证码是否过期
	 * @param effectiveTime
	 * @param originTime
	 * @return
	 */
	private boolean timeEffective(int effectiveTime, long originTime) {
	
		long currentTime = System.currentTimeMillis();
		if((currentTime - originTime) > effectiveTime*1000*60){
			return false;
		}
		return true;
	}

	@Override
	public ResponseEntity<Result> verifyMember(String emailPhoneBody, boolean isPost) {
	
		Map<String, String> map = new HashMap<String, String>();
		try {
			HttpServletRequest request = AppVersionUtil.getHttpServletRequest();
			log.info("手机号" + emailPhoneBody + "请求普通验证码,传入header：" + request.getHeader("C2M-Identify"));
			String emailPhone = "";
			String IDCode = "";
			if (isPost) {
				JSONObject json = JSONObject.fromObject(emailPhoneBody);
				emailPhone = json.optString("emailPhone");
				IDCode = json.optString("IDCode");
				if (StringUtils.isEmpty(IDCode)) {
					Member member;
					if(PhoneNoValidator.isNumeric(emailPhone)){
						member = baseDao.findUniqueByProperty(Member.class, "phoneNo", emailPhone);
					}else{
						member = baseDao.findUniqueByProperty(Member.class, "email", emailPhone);
					}
					if(AppVersionUtil.isNewApp(AppVersionUtil.getHttpServletRequest(), Constants.AppVersion.VERSION_AND_3_9_1, Constants.AppVersion.VERSION_IOS_6_294)
							&& member == null){
						map.put("status", "0");
						return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.MEMBER_UNREGISTER, "该手机号尚未注册", map), HttpStatus.BAD_REQUEST);
					}
					IDCode = (member == null ? "+86" : member.getIDCode());
				} else if (!IDCode.startsWith("+")) {
					IDCode = "+" + IDCode;
				}
			} else {
				emailPhone = emailPhoneBody;
				Member member = baseDao.findUniqueByProperty(Member.class, "phoneNo", emailPhone);
				if(AppVersionUtil.isNewApp(AppVersionUtil.getHttpServletRequest(), Constants.AppVersion.VERSION_AND_3_9_1, Constants.AppVersion.VERSION_IOS_6_294)
						&& member == null && PhoneNoValidator.isNumeric(emailPhone)){
					map.put("status", "0");
					return new ResponseEntity<Result>(new Result(ResultCode.PhoneCheck.MEMBER_UNREGISTER, "该手机号尚未注册", map), HttpStatus.BAD_REQUEST);
				}
				IDCode = (member == null ? "+86" : member.getIDCode());
			}
			//Marvin.Ma 增加逻辑，如果一个手机号在15分钟内请求过验证码，则不在发送新验证码。
			DetachedCriteria condts = DetachedCriteria.forClass(MemberEmailPhoneVerifyCode.class);
			condts.add(Restrictions.eq("emailPhone", emailPhone.trim()));
			condts.addOrder(Order.desc("remark"));
			List<MemberEmailPhoneVerifyCode> resList = baseDao.findByDetachedCriteria(condts);
			
			if (!ListUtils.isEmpty(resList)) {
				long now = new Date().getTime();
				long thatTime = Long.parseLong(resList.get(0).getRemark());
				// 验证码15分钟超时
				if (now - thatTime < 55 * 1000) {
					map.put("status", "0");
					return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_PHONE_CODE_BAD_REQUEST, map), HttpStatus.OK);
				}
			}
			deleteMemberEmailPhoneVerifyCode(emailPhone);
			String randomCode = RandomValue.getRandomNumber(true, 6);
			/**
			 * Michael.Sun
			 * 2015年12月24日
			 * 保存发放礼品卡时发送的验证码
			 */
			MemberEmailPhoneVerifyCode memberPhoneVerifyCode = new MemberEmailPhoneVerifyCode();
			memberPhoneVerifyCode.setEmailPhone(emailPhone);
			memberPhoneVerifyCode.setVerifyCode(randomCode);
			String remark = String.valueOf(System.currentTimeMillis());
			memberPhoneVerifyCode.setRemark(remark);
			baseDao.save(memberPhoneVerifyCode);
			Map<String, String> varMap = new HashMap<String, String>();
			varMap.put("register_random_code", randomCode);
			
			if (PhoneNoValidator.isNumeric(emailPhone)) {
				/*if ("+86".equals(IDCode) || StringUtils.isEmpty(IDCode)) {
					SendService.SendMessage(emailPhone, C2MConstants.SubmailProject.PHONE_VERIFICATION, varMap);
				} else {
					String msg = C2MConstants.msgContent.MSG_PHONE_VERIFICATION.replace("@var(register_random_code)", randomCode);
					SendService.SendGlobalMessage(emailPhone, C2MConstants.SubmailProject.PHONE_VERIFICATION, varMap, IDCode, msg);
				}*/
				
				map.put("status", "1");
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_PHONE_CAPTCHA_SENT, map), HttpStatus.OK);
			} else if (GenericValidator.isEmail(emailPhone)) {
				MyRunnable myrun = new MyRunnable();
				myrun.emailPhone = emailPhone;
				myrun.varMap = varMap;
				myrun.start();
				
				// SendService.SendMail(emailPhone, null,
				// C2MConstants.SubmailProject.MAIL_VERIFICATION, varMap, null);
				map.put("status", "1");
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_EMAIL_CAPTCHA_SENT, map), HttpStatus.OK);
			} else {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_OR_EMAIL_INVALID), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			throw new BusinessException("修改信息验证错误,手机号或邮箱为：" + emailPhoneBody, e);
		}
	}
	
	/**
	 * 发送验证码时先删除这个手机号的所有验证码
	 * 
	 * @param emailPhone
	 */
	private void deleteMemberEmailPhoneVerifyCode(String emailPhone) {
	
		List<MemberEmailPhoneVerifyCode> list = baseDao.findListByProperty(MemberEmailPhoneVerifyCode.class, "emailPhone", emailPhone);
		if (list != null && list.size() != 0) {
			
			baseDao.deleteAllEntity(list);
		}
		
	}
	
	
	
	/*private String getRecommentUrl() {
		
		AppointmentPage ap = baseDao.findUniqueByProperty(AppointmentPage.class, "isQrcode", 1);
		String rtn = (ap == null) ? C2MConstants.MEASURE_RECOMMENT_URL : ap.getPageLink();
		
		return rtn;
	}*/


	@Override
	public ResponseEntity<Result> changeEmail(String id, Member member) {
	
		Member m = baseDao.findById(Member.class, id);
		if (m == null) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_NOT_EXIST), HttpStatus.BAD_REQUEST);
		}
		if (!StringUtils.isEmpty(member.getEmail()) && GenericValidator.isEmail(member.getEmail())) {// 更新绑定邮箱
			List<Member> members = baseDao.findListByProperty(Member.class, "email", member.getEmail());
			if (members.size() > 1) {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), "邮箱已被别的账号绑定"), HttpStatus.BAD_REQUEST);
			} else if (members.size() == 1) {
				if (id.equals(members.get(0).getMemberId())) {
					m.setEmail(member.getEmail());
				} else {
					return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), "邮箱已被别的账号绑定"), HttpStatus.BAD_REQUEST);
				}
			} else {
				m.setEmail(member.getEmail());
			}
		} else {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_EMAIL_INVALID), HttpStatus.BAD_REQUEST);
		}
		baseDao.update(m);
		
		ssoUtil.updateSSO(member);
		
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_EMAIL_EDIT_SUCCESS), HttpStatus.OK);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberService#saveMemberGoodsCollect(
	 * com.yxlg.base.member.dto.MemberGoodsCollectDTO)
	 */
	
	
	@Override
	public String UpdateMemberQRCode(String memberId, HttpServletRequest request) {
	
		boolean isNewApp = AppVersionUtil.isNewApp(request, Constants.AppVersion.VERSION_AND_3_1_0, Constants.AppVersion.VERSION_IOS_5_8);
		List<Member> memberList = baseDao.findAll(Member.class);
		
		for (Member member : memberList) {
			// 生成包含用户 MemberID信息的二维码图片，上传七牛服务器，并把将返回地址保存到数据库中
			String qrCodePath = "qrcode/";
			SimpleDateFormat format = new SimpleDateFormat(Constants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
			int random = (int) (Math.random() * 1000000);
			qrCodePath = qrCodePath + format.format(new Date()) + String.valueOf(random) + ".jpg";
			
			if (GenericValidator.isUrl(member.getIcon())) {
				if (isNewApp) {
					member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.COMMON_MEMBER_QRCODE_BASEURL + member.getMemberId(),
							CatchPicture.downloadImgByNet(member.getIcon()), qrCodePath));
				} else {
					member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.MEMBER_RELATION_BASEURL + member.getMemberId(),
							CatchPicture.downloadImgByNet(member.getIcon()), qrCodePath));
				}
			} else {
				if (isNewApp) {
					member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.COMMON_MEMBER_QRCODE_BASEURL + member.getMemberId(), qrCodePath));
				} else {
					member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.MEMBER_RELATION_BASEURL + member.getMemberId(), qrCodePath));
				}
			}
			
			baseDao.update(member);
		}
		return "已将所有用户的二维码前缀修改为" + Constants.qrcodePath.MEMBER_RELATION_BASEURL;
	}
	
	/**
	 * Stone.Cai
	 * 2015年7月3日 09:02:51
	 * 添加
	 * REDIS 添加数据
	 */
	public void redisSave(String key, Object obj) {
	
		ValueOperations<String, Object> valueops = redisTemplate.opsForValue();
		valueops.set(key, obj);
	}
	
	/**
	 * 修改用户二维码图片 marvin
	 * 2015年8月3日 19:01:43
	 */
	private Member updateQrcode(Member member, String identify) {
		
		boolean isNewApp = AppVersionUtil.isNewApp(identify, Constants.AppVersion.VERSION_AND_3_1_0, Constants.AppVersion.VERSION_IOS_5_8);
		// 生成包含用户 MemberID信息的二维码图片，上传七牛服务器，并把将返回地址保存到数据库中
		String qrCodePath = "qrcode/";
		SimpleDateFormat format = new SimpleDateFormat(Constants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
		int random = (int) (Math.random() * 1000000);
		qrCodePath = qrCodePath + format.format(new Date()) + String.valueOf(random) + ".jpg";
		
		if (GenericValidator.isUrl(member.getIcon())) {
			if (isNewApp) {
				member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.COMMON_MEMBER_QRCODE_BASEURL + member.getMemberId(),
						CatchPicture.downloadImgByNet(member.getIcon()), qrCodePath));
			} else {
				member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.MEMBER_RELATION_BASEURL + member.getMemberId(), CatchPicture.downloadImgByNet(member.getIcon()),
						qrCodePath));
			}
		} else {
			if (isNewApp) {
				member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.COMMON_MEMBER_QRCODE_BASEURL + member.getMemberId(), qrCodePath));
			} else {
				member.setQrcode(QRCodeUtil.generateQrCode(Constants.qrcodePath.MEMBER_RELATION_BASEURL + member.getMemberId(), qrCodePath));
			}
		}
		log.debug("修改用户的 " + member.getPhoneNo() + " 二维码图片");
		return member;
	}
	
	
	/**
	 * 
	 * jerry.qin
	 * 2015/8/12
	 * 优化发送邮件响应时间 开线程
	 *
	 */
	private class MyRunnable extends Thread {
		
		public String emailPhone = "";
		public Map<String, String> varMap = new HashMap<String, String>();
		
		@Override
		public void run() {
		
			try {
				//SendService.SendMail(emailPhone, null, C2MConstants.SubmailProject.MAIL_VERIFICATION, varMap, null);
			} catch (Exception e) {
				throw new BusinessException("submail给" + emailPhone + "发送邮件错误", e);
			}
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberService#isWannaMember(java.lang
	 * .String)
	 */
	@Override
	public ResponseEntity<Result> isWannaMember(String phoneNo) {
	
		Member member = baseDao.findUniqueByProperty(Member.class, "phoneNo", phoneNo);
		String isExists = "0";
		if (member != null) {
			isExists = "1";
		}
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.SC_SUCCESS, isExists), HttpStatus.OK);
	}
	
	
	
	
	/**
	 * 
	 * @param dto 推荐信息
	 * @param member 新注册的会员信息
	 * @return
	 */
	
	
	
	
	@Override
	public Member findMemberByPhone(String phone) {
	
		Member member = baseDao.findUniqueByProperty(Member.class, "phoneNo", phone);
		return member;
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberService#deleteValidateCodeByPhoneNo(java.lang.String)
	 */
	@Override
	public void deleteValidateCodeByPhoneNo(String PhoneNo) {
		List<Serializable> list = new ArrayList<Serializable>();
		list.add(PhoneNo);
		baseDao.deleteByValueList(MemberEmailPhoneVerifyCode.class, "emailPhone", list);
		
	}


	

	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberService#verifyMemberRegistration(java.lang.String, boolean)
	 */
	@Override
	public ResponseEntity<Result> verifyMemberRegistration(String emailPhone, boolean isPost) {
	
		try { 
			String idCode = "+86";
			String randomCode = RandomValue.getRandomNumber(true, 6);
			MemberEmailPhoneVerifyCode memberPhoneVerifyCode = new MemberEmailPhoneVerifyCode();
			memberPhoneVerifyCode.setEmailPhone(emailPhone);
			memberPhoneVerifyCode.setVerifyCode(randomCode);
			String remark = String.valueOf(System.currentTimeMillis());
			memberPhoneVerifyCode.setRemark(remark);
			baseDao.save(memberPhoneVerifyCode);
			Map<String, String> varMap = new HashMap<String, String>();
			varMap.put("register_random_code", randomCode);
			if (PhoneNoValidator.isNumeric(emailPhone)) {
				/*if ("+86".equals(idCode) || StringUtils.isEmpty(idCode)) {
					SendService.SendMessage(emailPhone, C2MConstants.SubmailProject.PHONE_REGISTRATION, varMap);
				} else {
					String msg = C2MConstants.msgContent.MSG_PHONE_REGISTRATION.replace("@var(register_random_code)", randomCode);
					SendService.SendGlobalMessage(emailPhone, C2MConstants.SubmailProject.PHONE_REGISTRATION, varMap, idCode, msg);
				}*/
				log.info("向手机号：" + emailPhone + "发送验证码:" + randomCode + "成功！");
				Map<String, String> map = new HashMap<String, String>();
				map.put("verifyCode", randomCode);
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_PHONE_CAPTCHA_SENT, map), HttpStatus.OK);
			} else if (GenericValidator.isEmail(emailPhone)) {
				MyRunnable myrun = new MyRunnable();
				myrun.emailPhone = emailPhone;
				myrun.varMap = varMap;
				myrun.start();
				// SendService.SendMail(emailPhone, null,
				// C2MConstants.SubmailProject.MAIL_REGISTRATION, varMap, null);
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("verifyCode", randomCode);
				return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.MEMBER_EMAIL_CAPTCHA_SENT, map), HttpStatus.OK);
			} else {
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_OR_EMAIL_INVALID), HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			throw new BusinessException("验证注册用户出错,手机号或邮箱为：" + emailPhone, e);
		}
	}
	
	@Override
	public Result setCity(String startTime, String endTime) {
		Date date1 = null;
		Date date2 = null;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			date1=sim.parse(startTime);
			date2=sim.parse(endTime);
		} catch (ParseException e) {
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);
		detachedCriteria.add(Restrictions.ge("registTime", date1));
		detachedCriteria.add(Restrictions.le("registTime", date2));
		detachedCriteria.add(Restrictions.or(Restrictions.eq("province", ""), Property.forName("province").isNull()));
		List<Member> memberList = baseDao.findByDetachedCriteria(detachedCriteria);
		for (Member member : memberList) {
			if (member.getPhoneNo().length() == 11 && PhoneNoValidator.isPhoneNoValid2(member.getPhoneNo())) {
				String provinceAndCity = PhoneNoCity.getRequest(member.getPhoneNo());
				log.info("获取手机号"+member.getPhoneNo()+ "归属地：" + provinceAndCity);
				if (StringUtils.isNotEmpty(provinceAndCity) && provinceAndCity.contains(",")) {
					String[] aStrings = provinceAndCity.split(",");
					if(aStrings.length >= 2) {
						member.setProvince(aStrings[0]);
						member.setCity(aStrings[1]);
					} else if (aStrings.length ==1) {
						member.setProvince(aStrings[0]);
						member.setCity(aStrings[0]);
					}
					baseDao.update(member);
				}
			}
		}
		return new Result();
	}
	/**
	 * Stone.Cai
	 * 2015年7月3日 09:01:43
	 * 添加
	 * 注入REDIS
	 */
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	private IBaseDAO baseDao;
	
	@Resource
	private SSOUtil ssoUtil;
	
	@Value("${token.secret}")
	private String ssoKey;

}
