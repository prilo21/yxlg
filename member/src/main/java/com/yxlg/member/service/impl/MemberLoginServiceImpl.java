/*
 * MemberLoginService。java
 * 
 * Created Date: 2015年5月6日
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

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yxlg.base.constant.Constants;
import com.yxlg.base.constant.ResultCode;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.dto.MemberLoginDto;
import com.yxlg.base.member.dto.MemberLoginWeixinDto;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberEmailPhoneVerifyCode;
import com.yxlg.base.member.entity.MemberLevelDiscountNo;
import com.yxlg.base.member.entity.MemberThirdInfo;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.sso.entity.SSOUser;
import com.yxlg.base.upload.util.CatchPicture;
import com.yxlg.base.upload.util.UploadByUrl;
import com.yxlg.base.util.AppVersion;
import com.yxlg.base.util.AppVersionUtil;
import com.yxlg.base.util.CommonsMethod;
import com.yxlg.base.util.FileLoadUtil;
import com.yxlg.base.util.JsonValidator;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Result;
import com.yxlg.base.util.TokenHandler;
import com.yxlg.member.service.IMemberLoginService;
import com.yxlg.member.service.IMemberService;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          用户登录接口实现类
 *          </p>
 */
@Service
public class MemberLoginServiceImpl implements IMemberLoginService {
	private static final Logger log = LoggerFactory
			.getLogger(MemberLoginServiceImpl.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberLoginService#login(com.yuandian
	 * .c2mbase.entity.Member)
	 */
	@Override
	public ResponseEntity<Result> login(MemberLoginDto dto, String identify) {
		String dateString = CommonsMethod.getNowCorrect2SecondWithEnglishWord();
		log.info("member login,"+ dateString + ",手机号/密码:" + dto.getPhoneNo() + "/" + dto.getPassword() + ",deviceToken:" + dto.getDeviceToken());
		if (StringUtils.isEmpty(dto.getPhoneNo())
				&& StringUtils.isEmpty(dto.getEmail())
				&& StringUtils.isEmpty(dto.getPassword())) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_INPUT_VALID),
					HttpStatus.BAD_REQUEST);
		} else if (StringUtils.isEmpty(dto.getPhoneNo())
				&& StringUtils.isEmpty(dto.getEmail())) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_ACCOUNT_NUMBER_VALID),
					HttpStatus.BAD_REQUEST);
		} else if (StringUtils.isEmpty(dto.getPassword()) && StringUtils.isEmpty(dto.getValidateCode())) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_PASSWORD_VALID),
					HttpStatus.BAD_REQUEST);
		} else if (StringUtils.isEmpty(dto.getPassword()) && !StringUtils.isEmpty(dto.getValidateCode())) {
			return loginUseValidateCode(dto, identify);
		} else if (!StringUtils.isEmpty(dto.getPhoneNo())) {
			dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));
			return loginUsePhoneNo(dto, identify);
		} else {
			dto.setPassword(DigestUtils.md5Hex(dto.getPassword()));
			return loginUseEmail(dto, identify);
		}
	}
	
	/**
	 * 以手机短信验证码方式登录
	 * 
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> loginUseValidateCode(MemberLoginDto memberLoginDto, String identify) {
		ResponseEntity<Result> checkResult = checkValidateCode(memberLoginDto.getPhoneNo(), memberLoginDto.getValidateCode());
		if (ResultCode.common.OK != checkResult.getStatusCode().value()) {
			return checkResult;
		}
		Member memberFinded;
		List<Member> list = dao.findByPropertyisOrder(Member.class, "phoneNo",
				memberLoginDto.getPhoneNo(), true);
		if (list.isEmpty()) {
			//如果没有找到该用户，则使用手机号注册一个。
			return new ResponseEntity<Result>(new Result(
					ResultCode.common.NO_MEMBER,
					Constants.ReturnMsg.MEMBER_LOGIN_NO_SUCH_USER),
					HttpStatus.OK);
		}
		memberFinded = list.get(0);
		if (memberLoginDto.getDeviceToken() != null && (memberLoginDto.getDeviceToken().length() == 64 || memberLoginDto.getDeviceToken().length() == 44)) {
			memberFinded.setDeviceToken(memberLoginDto.getDeviceToken());
		}
		return loginSuccessMsg(memberFinded, memberLoginDto.getDeviceToken(), identify, memberFinded.getPassword());
	}
	
	/**
	 * 以手机号的方式登录
	 * 
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> loginUsePhoneNo(MemberLoginDto memberLoginDto, String identify) {
		Member memberFinded;
		/*ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
		String enableFlag = (String) valueOps.get(RedisBaseKeys.SUPER_PASSWORD_ENABLE);*/
		String enableFlag = "";
		List<Member> list = dao.findByPropertyisOrder(Member.class, "phoneNo",
				memberLoginDto.getPhoneNo(), true);
		if (list.isEmpty()) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_NO_SUCH_USER),
					HttpStatus.BAD_REQUEST);
		}
		memberFinded = list.get(0);
		if (!memberFinded.getPassword().equals(memberLoginDto.getPassword()) && 
				(!StringUtils.equals("true", enableFlag) || !superPw.equals(memberLoginDto.getPassword()))) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_PASSWORD_ERROR),
					HttpStatus.BAD_REQUEST);
		} else {
			if (memberLoginDto.getDeviceToken() != null && (memberLoginDto.getDeviceToken().length() == 64 || memberLoginDto.getDeviceToken().length() == 44)) {
				memberFinded.setDeviceToken(memberLoginDto.getDeviceToken());
			}
			return loginSuccessMsg(memberFinded, memberLoginDto.getDeviceToken(), identify, memberLoginDto.getPassword());
		}
	}
	
	/**
	 * 以邮箱的方式登录
	 * 
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> loginUseEmail(MemberLoginDto memberLoginDto, String identify) {
		Member memberFinded;
		/*ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
		String enableFlag = (String) valueOps.get(RedisBaseKeys.SUPER_PASSWORD_ENABLE);*/
		String enableFlag = "";
		List<Member> list = dao.findByPropertyisOrder(Member.class, "email",
				memberLoginDto.getEmail(), true);
		if (list.isEmpty()) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_NO_SUCH_USER),
					HttpStatus.BAD_REQUEST);
		}
		memberFinded = list.get(0);
		if (!memberFinded.getPassword().equals(memberLoginDto.getPassword()) && 
				(!StringUtils.equals("true", enableFlag) || !superPw.equals(memberLoginDto.getPassword()))) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_PASSWORD_ERROR),
					HttpStatus.BAD_REQUEST);
		} else {
			if (memberLoginDto.getDeviceToken() != null && (memberLoginDto.getDeviceToken().length() == 64 || memberLoginDto.getDeviceToken().length() == 44)) {
				memberFinded.setDeviceToken(memberLoginDto.getDeviceToken());
			}
			return loginSuccessMsg(memberFinded, memberLoginDto.getDeviceToken(), identify, memberLoginDto.getPassword());
		}
	}
	
	/**
	 * 用户登录成功后，返回用户的相关信息
	 * 
	 * @param member
	 * @return
	 */
	public ResponseEntity<Result> loginSuccessMsg(Member member, String deviceUMToken, String identify, String superPassword) {
		if(superPw.equals(superPassword)){
			dao.evict(member);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		long TEN_DAYS = 1000 * 60 * 60 * 24 * 60;
		//long TEN_DAYS = 1000 * 60 * 5;
		map.put("memberId", member.getMemberId());
		TokenHandler tokenHandler = new TokenHandler(
				DatatypeConverter.parseBase64Binary(ssoKey));
		SSOUser ssoUser = dao.findUniqueByProperty(SSOUser.class, "sysid",
				member.getMemberId());
		if (ssoUser == null) {
			log.debug("有非法用户登录，ssoUser为空，手机号：" + member.getPhoneNo());
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_ILLEGLE_USER),
					HttpStatus.BAD_REQUEST);
		}
		ssoUser.setExpires(System.currentTimeMillis() + TEN_DAYS);
		String xauthtoken = tokenHandler.createTokenForUser(ssoUser);
		map.put("X-AUTH-TOKEN", xauthtoken);
//		map.put("jsToken", xauthtoken);
		Map<String, Object> memberInfoMap = new HashMap<String, Object>();
		memberInfoMap.put("memberId", member.getMemberId());
		memberInfoMap.put("icon", member.getIcon());
		memberInfoMap.put("memberName", member.getMemberName());
		memberInfoMap.put("signature", member.getSignature());
		memberInfoMap.put("qrCode", member.getQrcode());
		memberInfoMap.put("phoneNo", member.getPhoneNo());
		memberInfoMap.put("email", member.getEmail());
		
		if(member.getMemberType() != null && StringUtils.isNotBlank(member.getMemberType().getMemberTypeId()) && StringUtils.isBlank(member.getMemberType().getMemberType())){
			MemberType memberType = dao.findById(MemberType.class, member.getMemberType().getMemberTypeId());
			memberInfoMap.put("memberType", memberType.getMemberType());
		}else{
			memberInfoMap.put("memberType", member.getMemberType() != null && StringUtils.isNotBlank(member.getMemberType().getMemberType()) ? member.getMemberType().getMemberType() : "");
		}
		
		map.put("memberInfo", memberInfoMap);
		Map<String, Object> personalInfoMap = new HashMap<String, Object>();
		personalInfoMap.put("gender", member.getGender());
		personalInfoMap.put("height", member.getHeight());
		personalInfoMap.put("dob", member.getBirthday());
		personalInfoMap.put("weight", member.getWeight());
		map.put("personalInfo", personalInfoMap);
		map.put("vitualCurrency", String.valueOf(member.getVirtualCurrency()));
		map.put("giftCardValue", String.valueOf(member.getGiftCardsCount()));
		map.put("myWallet", String.valueOf(member.getCashBalance()));
		//第一次登陆,给新注册会员推送打折卡,给新注册会员推送奖励酷特币通知
		if ( StringUtils.isBlank(member.getDeviceToken()) && StringUtils.isNotBlank(deviceUMToken)) {
			//推送打折卡
			/*SystemNotification notification = new SystemNotification();
			notification.setMemberId(member.getMemberId());
			Date date = new Date();
			notification.setNotificationDate(date);
			notification.setNotificationDetail("新注册会员,已赠送打折卡,请注意查看");
			notification.setNotificationType("1");
			notification.setNotificationTitle("新注册会员赠送打折卡");
			notification.setSpecifyMemberPhoneNos(member.getPhoneNo());
			notification.setReceiverMemberType("-2");
			dao.save(notification);
			try {
				boolean isSuccess = unicastSendMessage.sendMessage(deviceUMToken, notification);
				if(isSuccess){
					member.setDeviceToken(deviceUMToken);
				}
			} catch (Exception e) {
				log.error("向会员id为" + member.getMemberId() + "的新注册会员推送打折卡消息失败", e);
			}*/
			
			member.setDeviceToken(deviceUMToken);
			
		}else if( StringUtils.isNotBlank(member.getDeviceToken()) && StringUtils.isNotBlank(deviceUMToken) && !member.getDeviceToken().equals(deviceUMToken)){
			member.setDeviceToken(deviceUMToken);
		}
		
		
		map.put("giftPack", getGiftPack(member.getPhoneNo(), member));
//		map.put("salesPromotionPackage", getSalesPromotionPackage());
		member.setLastLoginTime(new Date());
		
		//2016年01月19日 保存用户登录时的app版本，手机型号等信息
		AppVersion versionInfo = AppVersionUtil.getAppVersion();
		member.setPlatform(versionInfo.getPlatform());
		member.setAPPVersion(versionInfo.getVersionNo());
		member.setOSVersion(versionInfo.getOSversion());
		member.setDeviceType(versionInfo.getDeviceType());
		if(!superPw.equals(superPassword)){
			dao.update(member);
		}	
		//删除firebasetoken
//		Map<String, Object> authPayload = new HashMap<String, Object>();
//		authPayload.put("uid", member.getMemberId());
//		TokenGenerator tokenGenerator = new TokenGenerator(C2MConstants.MeasureOrderNodesOnFirebase.FIRE_BASE_AUTH_TOKEN);
//		String token = tokenGenerator.createToken(authPayload);
		map.put("fireBaseToken", "mohuangongchangyuandian-firebaseio-com");
		map.put("certificationResult", "");
		MemberLevelDiscountNo memberLevelDiscountNo = getMemberLevelDiscountNo(member.getMemberLevelDiscountNoId());
		map.put("memberLevelDiscountNo", memberLevelDiscountNo == null ? "1" : memberLevelDiscountNo.getDiscountNo());
		map.put("memberLevel", memberLevelDiscountNo == null ? "1" : memberLevelDiscountNo.getLevel());
		return new ResponseEntity<Result>(new Result(map), HttpStatus.OK);
	};
	
	/**
	 * @param memberLevelDiscountNoId
	 * @return
	 */
	private MemberLevelDiscountNo getMemberLevelDiscountNo(String memberLevelDiscountNoId) {
		if(StringUtils.isEmpty(memberLevelDiscountNoId)){
			return null;
		}
		MemberLevelDiscountNo memberLevelDiscountNo = dao.findById(MemberLevelDiscountNo.class, memberLevelDiscountNoId);
		if(memberLevelDiscountNo == null){
			return null;
		}
		return memberLevelDiscountNo;
	}


//	/**
//	 * @return
//	 */
//	private Map<String, Object> getSalesPromotionPackage() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("hasPackage", "0");
//		map.put("greetingMsg", "");
////		List<SalesPromotionPackage> list = 
//		return map;
//	}

	private Map<String, Object> getGiftPack(String phoneNo, Member loginMember) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("giftType", "");
		map.put("hasGift", "0");
		map.put("greetingMsg", "");
		MemberRecommend memberRecommend = dao.findUniqueByProperty(MemberRecommend.class, "phoneNo", phoneNo);
		//会员未被推荐过
		if(memberRecommend == null){
//			map.put("hasGift", "0");
//			map.put("greetingMsg", "");
		}else{
			Member member = dao.findById(Member.class, memberRecommend.getMember().getMemberId());
			//System.out.println(loginMember.getLastLoginTime());
			//会员未被企业级会员推荐
			//TODO
//			&& loginMember.getLastLoginTime() == null
			if("1".equals(member.getIsEnterpriseMarketingMember()) && loginMember.getLastLoginTime() == null){
				map.put("hasGift", "1");
				map.put("greetingMsg", "恭喜您获得分众传媒赠送的酷特大礼包，快去您的钱包查看吧！");
			}
		}
		return map;
	}

	@Override
	public ResponseEntity<Result> logout(Member member){
		if (StringUtils.isEmpty(member.getMemberId())) {
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.SC_NOT_FOUND),
					HttpStatus.BAD_REQUEST);
		} else if(dao.findById(Member.class, member.getMemberId()) == null){
			return new ResponseEntity<Result>(new Result(
					HttpStatus.BAD_REQUEST.value(),
					Constants.ReturnMsg.MEMBER_LOGIN_NO_SUCH_USER),
					HttpStatus.BAD_REQUEST);
		} else {
			Member mem = dao.findById(Member.class, member.getMemberId());
			mem.setDeviceToken("");
			return new ResponseEntity<Result>(new Result(200,Constants.ReturnMsg.SC_SUCCESS),
					HttpStatus.OK);
		}
	}
	
	/**
	 * 短信验证码登录检验验证码是否有效
	 */
	private ResponseEntity<Result> checkValidateCode(String phoneNo, String validateCode) {
		Criteria criteria = dao.createCriteria(MemberEmailPhoneVerifyCode.class);
		criteria.add(Restrictions.eq("emailPhone", phoneNo));
		criteria.addOrder(Order.desc("remark"));
		@SuppressWarnings("unchecked")
		List<MemberEmailPhoneVerifyCode> memberEmailPhoneVerifyCodes = criteria.list();
		if (memberEmailPhoneVerifyCodes == null || memberEmailPhoneVerifyCodes.size() == 0) {
			return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_NOT_VERIFY), HttpStatus.BAD_REQUEST);
		} else {
			long verifyTime;
			long currentTime = System.currentTimeMillis();
			boolean rightFlag = false;
			boolean timeFlag = false;
			long time = 15 * 60 * 1000;
			if (validateCode.equals(memberEmailPhoneVerifyCodes.get(0).getVerifyCode())) {
				rightFlag = true;
				verifyTime = Long.parseLong(memberEmailPhoneVerifyCodes.get(0).getRemark());
				if (currentTime - verifyTime < time) {
					timeFlag = true;
					// baseDao.deleteAllEntity(memberEmailPhoneVerifyCodes);
				}
			}
			if (rightFlag == false) {
				log.info("手机号:" + phoneNo + "请求注册时，验证码:" + validateCode + "错误！");
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_VERIFY_ERROR), HttpStatus.BAD_REQUEST);
			} else if (timeFlag == false) {
				log.info("手机号:" + phoneNo + "请求注册时，验证码:" + validateCode + "超时！");
				return new ResponseEntity<Result>(new Result(HttpStatus.BAD_REQUEST.value(), Constants.ReturnMsg.MEMBER_PHONE_VERIFY_OVERTIME), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), Constants.ReturnMsg.SC_SUCCESS), HttpStatus.OK);
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberLoginService#registerMemberByValidateCode(com.yxlg.base.member.entity.Member)
	 */
	@Override
	public ResponseEntity<Result> registerMemberByValidateCode(MemberLoginDto dto, String identify) {
	
		Member member = new Member();
		member.setPhoneNo(dto.getPhoneNo());
		member.setMemberName(dto.getPhoneNo());
		member.setPassword(dto.getPhoneNo());
		ResponseEntity<Result> registResult = memberService.registerMember(member, dto.getValidateCode(), identify);
		if (ResultCode.common.OK == registResult.getStatusCode().value()) {
			dto.setPassword(dto.getPhoneNo());
			return login(dto, identify);
		} else {
			return registResult;
		}
	}
	
	@Override
	public ResponseEntity<Result> weiXinLogin(MemberLoginWeixinDto dto, String identify) {
		
		log.info("第三方登录:" + dto.getType() + ",token(必填):" + dto.getToken() + ",openid:" + dto.getOpenid() + ",手机号(可空):" + dto.getPhoneNo());
		
		Object returnData = null;
		int returnCode = HttpStatus.BAD_REQUEST.value();
		String returnMsg = Constants.ReturnMsg.FA_FALIURE;
		Date date = new Date();
		
		if (StringUtils.isBlank(dto.getType())) {
			returnCode = 16406;
			returnMsg = "登录方式错误！";
		} else {
			String type = dto.getType().toLowerCase();
			if (!Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(type) && !Constants.THIRD_LOGIN_TYPE_QQ.equals(type) && !Constants.THIRD_LOGIN_TYPE_WEIBO.equals(type) && !Constants.THIRD_LOGIN_TYPE_CCB.equals(type)) {
				returnCode = 16406;
				returnMsg = "还未开放此登录方式！";
			} else {
				//2016-12-16 alisa.yang 第三方登录新增建设银行
				if (StringUtils.isBlank(dto.getOpenid()) || (!Constants.THIRD_LOGIN_TYPE_CCB.equals(type) && StringUtils.isBlank(dto.getToken()))) {
					returnCode = 16401;
					returnMsg = "尚无授权账号";
				} else {
					//TODO 2016-11-18 alisa.yang 验证token和openid是否有效
					if(!checkAccessToken(dto)){
						returnCode = 16408;
						returnMsg = "账号授权失效，请重新登录";
					}else{
						MemberThirdInfo thridLoginInfo = null;
						List<MemberThirdInfo> thirdInfoList = null;
						//2016-11-21 alisa.yang token赋值为accessToken，有过期和无效的状态，所以token不再是唯一不变标识，微信为unionId，微博和QQ为openId
						if(Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(type)){
							thirdInfoList = dao.findListByProperty(MemberThirdInfo.class, type + "No", dto.getUnionid());
						}else{
							thirdInfoList = dao.findListByProperty(MemberThirdInfo.class, type + "OpenId", dto.getOpenid());
						}
						
						if (thirdInfoList != null && !thirdInfoList.isEmpty() && (thirdInfoList.get(0).getMember() == null || StringUtils.isBlank(thirdInfoList.get(0).getMember().getMemberId()))) {
							returnCode = 16403;
							returnMsg = "非首次登录，会员信息获取失败！";
						} else {
							//2016-11-21 alisa.yang 如果查找结果空，则首次登陆，肯定需要手机号码；如果结果不空，直接登录或验证吗可能过期等
							String memberId = null;
							Member member = null;
							
							if(thirdInfoList != null && !thirdInfoList.isEmpty()){
								thridLoginInfo = thirdInfoList.get(0);
							}
							// 结果匹配，直接登录
							if (thridLoginInfo != null && StringUtils.isNotBlank(thridLoginInfo.getMemberThirdId()) && thridLoginInfo.getMember() != null && StringUtils.isNotBlank(thridLoginInfo.getMember().getMemberId())) {
								// 已登录过，返回登录所需数据;
								member = thridLoginInfo.getMember();
								memberId = thridLoginInfo.getMember().getMemberId();
							} else if ((thridLoginInfo == null || StringUtils.isBlank(thridLoginInfo.getMemberThirdId())) && (StringUtils.isBlank(dto.getPhoneNo()) || StringUtils.isBlank(dto.getVerifyCode()))) {
								// 首次登录，需要手机号！
								returnCode = 16402;
								returnMsg = "需要验证手机号！";
							} else if (thridLoginInfo != null && (thridLoginInfo.getMember() == null || StringUtils.isBlank(thridLoginInfo.getMember().getMemberId()))) {
								returnCode = 16403;
								returnMsg = "非首次登录，会员信息获取失败！";
							} else {
								// 首次登录使用此方式的openId未登录过(也可能同一手机号使用其他方式登录过)；
								// 验证手机号
								boolean verifyFlag = checkVerifyCodesTime(dto.getPhoneNo(), dto.getVerifyCode());
								if (!verifyFlag) {
									returnCode = 16405;
									returnMsg = "手机验证码无效或已过期！";
								} else {
									DetachedCriteria query = DetachedCriteria.forClass(MemberThirdInfo.class).createAlias("member", "m").add(Restrictions.eq("m.phoneNo", dto.getPhoneNo()));
									List<MemberThirdInfo> infoList = dao.findByDetachedCriteria(query);
									// 理论上，同一会员在第三方登录表里只有一条记录（微信、微博、qq使用同一条记录）
									if (infoList != null && !infoList.isEmpty()) {
										// 如果之前已绑定过，同一登录方式其它账号（两个qq号）！！！
										String openId = "";
										if (Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(type)) {
											openId = infoList.get(0).getWeixinOpenId();
										} else if (Constants.THIRD_LOGIN_TYPE_QQ.equals(type)) {
											openId = infoList.get(0).getQqOpenId();
										} else if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(type)){
											openId = infoList.get(0).getWeiboOpenId();
										} else if(Constants.THIRD_LOGIN_TYPE_CCB.equals(type)){
											openId = infoList.get(0).getCcbOpenId();
										}
										
										if (StringUtils.isNotBlank(openId) && !dto.getOpenid().equals(openId)) {
											// 此手机号已绑定其他qq
											returnCode = 16407;
											returnMsg = "此手机号已绑定";
										} else {
											thridLoginInfo = infoList.get(0);
											// 如果已登录过，返回登录所需数据
											member = thridLoginInfo.getMember();
											memberId = thridLoginInfo.getMember().getMemberId();
										}
									} else {
										// 如果首次第三方登录；check是否是已注册会员，如果不是要先注册;最后一步登录
										List<Member> memberList = dao.findListByProperty(Member.class, "phoneNo", dto.getPhoneNo());
										if (memberList != null && !memberList.isEmpty()) {
											member = memberList.get(0);
											memberId = member.getMemberId();
										} else {
											// 先注册再登录；开始调用注册方法
											ResponseEntity<Result> responseEntity = memberService.registerMember(createNewMember(dto, identify, date), dto.getVerifyCode(), identify);
											Result result = responseEntity.getBody();
											if (HttpStatus.OK.value() == result.getReturnCode() && Constants.ReturnMsg.MEMBER_REG_SUCCESS.equals(result.getReturnMsg())) {
												// 注册成功！下一步 登录
												@SuppressWarnings("unchecked")
												Map<String, Object> returnData1 = (Map<String, Object>) result.getReturnData();
												memberId = returnData1.get("memberId").toString();
											} else {
												// 注册失败！返回给APP原因
												returnCode = 16404;
												returnMsg = result.getReturnMsg();
											}
										}
										
										// 保存MemberThirdInfo数据
										thridLoginInfo = new MemberThirdInfo();
										thridLoginInfo.setCreateTime(date);
									}
									
									if (thridLoginInfo != null) {
										if (Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(type)) {
											thridLoginInfo.setWeixinNo(dto.getUnionid());
											thridLoginInfo.setWeixinOpenId(dto.getOpenid());
										} else if (Constants.THIRD_LOGIN_TYPE_QQ.equals(type)) {
											thridLoginInfo.setQqNo(dto.getUnionid());
											thridLoginInfo.setQqOpenId(dto.getOpenid());
										} else if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(type)){
											thridLoginInfo.setWeiboNo(dto.getUnionid());
											thridLoginInfo.setWeiboOpenId(dto.getOpenid());
										} else if(Constants.THIRD_LOGIN_TYPE_CCB.equals(type)){
											thridLoginInfo.setCcbOpenId(dto.getOpenid());
										}
									}
								}
							}
							
							// 调用登录方法
							if (StringUtils.isNotBlank(memberId) && thridLoginInfo != null) {
								// 注册后只有memberId可用，没有完整member信息
								if (member == null || StringUtils.isBlank(member.getMemberId())) {
									member = dao.findById(Member.class, memberId);
									//memberService.registerReward(null, member);
								}
								// dao.evict(member);
								
								// 调用登录方法
								ResponseEntity<Result> responseEntity = loginSuccessMsg(member, dto.getDeviceToken(), identify, null);
								if (HttpStatus.OK.value() == responseEntity.getBody().getReturnCode()) {
									// 登录成功，更新第三方（微信）登录表
									
									thridLoginInfo.setUpdateTime(date);
									thridLoginInfo.setMember(member);
									if (Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(type)) {
										thridLoginInfo.setWeixinToken(dto.getToken());
									} else if (Constants.THIRD_LOGIN_TYPE_QQ.equals(type)) {
										thridLoginInfo.setQqToken(dto.getToken());
									} else if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(type)){
										thridLoginInfo.setWeiboToken(dto.getToken());
									} else if(Constants.THIRD_LOGIN_TYPE_CCB.equals(type)){
										
									}
									dao.saveOrUpdate(thridLoginInfo);
									
									returnData = responseEntity.getBody().getReturnData();
									returnCode = HttpStatus.OK.value();
									returnMsg = Constants.ReturnMsg.SC_SUCCESS;
								} else {
									returnCode = 16403;
									returnMsg = "登录失败！";
								}
							}
						}
					
					}// end else checkAccessToken
				}//end else check parameters
			}
		}
		
		log.info("第三方登录:" + dto.getType() + ",token:" + dto.getToken() + "，登录结果:" + returnCode + returnMsg);
		
		if (returnData == null) {
			returnData = new HashMap<String, Object>();
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> returnMap = (Map<String, Object>) returnData;
		returnMap.put("loginStatusCode", returnCode);
		
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), returnMsg, returnMap), HttpStatus.OK);
	}
	
	/**
	 * 首次第三方登录，需要注册时，新建会员
	 * @param dto
	 * @param identify
	 * @param date
	 * @return
	 */
	private Member createNewMember(MemberLoginWeixinDto dto, String identify, Date date) {
		
		Member member = new Member();
		member.setPhoneNo(dto.getPhoneNo());
		member.setPassword(dto.getPhoneNo());
		
		member.setIDCode(StringUtils.isNotBlank(dto.getIDCode()) ? dto.getIDCode() : "+86");
		
		if(StringUtils.isNotBlank(dto.getNickname()) && JsonValidator.checkUserName(dto.getNickname())){
			member.setMemberName(dto.getNickname());
		}else{
			member.setMemberName(dto.getPhoneNo());
		}
//		member.setMemberName(StringUtils.isNotBlank(dto.getNickname()) ? dto.getNickname() : dto.getPhoneNo());
		member.setGender("1".equals(dto.getSex()) ? "男" : "女");
		// member.setDeviceToken(dto.getDeviceToken());为了登录接口的对比，此处不放值
		
		String iconUrl = Constants.MEMBER_ICON_DEFAULT;
		if (StringUtils.isNotBlank(dto.getHeadimgurl())) {
			String qiniuUrl = FileLoadUtil.encodeName("memberIcon/thirdLogin/" + dto.getType() + "/"+ dto.getOpenid() + "/");
			try {
				BufferedImage imageBI = ImageIO.read(CatchPicture.downloadImgByNet(dto.getHeadimgurl()));
				iconUrl = UploadByUrl.uploadImage(imageBI, qiniuUrl);
			} catch (Exception e) {
				log.error("第三方登录微信，用户头像上传七牛异常！openId:" + dto.getOpenid() + ",imgUrl:" + dto.getHeadimgurl(), e);
			}
		}
		member.setIcon(StringUtils.isNotBlank(iconUrl) ? iconUrl : Constants.MEMBER_ICON_DEFAULT);
		member.setRegistTime(date);
		member.setSignature(Constants.Registration.DEFAULT_SIGNATURE);
		member.setIsdelete(false);
		
		AppVersion appVersion = AppVersionUtil.getAppVersion(identify);
		member.setAPPVersion(appVersion.getVersionNo());
		member.setDeviceType(appVersion.getDeviceType());
		member.setPlatform(appVersion.getPlatform());
		member.setOSVersion(appVersion.getOSversion());
		return member;
	}
	
	/**
	 * 根据手机号和验证码，check验证码是否正确或过期
	 * @param phoneNo
	 * @param code
	 * @return
	 */
	private boolean checkVerifyCodesTime(String phoneNo, String code){
		
		boolean timeFlag = false;
		if(StringUtils.isBlank(code) || StringUtils.isBlank(phoneNo)){
			return false;
		}
		
		DetachedCriteria q = DetachedCriteria.forClass(MemberEmailPhoneVerifyCode.class);
		q.add(Restrictions.eq("emailPhone", phoneNo));
		q.add(Restrictions.eq("verifyCode", code));
		q.addOrder(Order.desc("remark"));
		PageBean pageBean = new PageBean();
		pageBean.setRows(1);
		List<MemberEmailPhoneVerifyCode> verifyCodeList = dao.findAllWithPageCriteria(pageBean, q);
		if(verifyCodeList != null && !verifyCodeList.isEmpty()){
			long verifyTime;
			long currentTime = System.currentTimeMillis();
			long time = 15 * 60 * 1000;
			verifyTime = Long.parseLong(verifyCodeList.get(0).getRemark());
			if (currentTime - verifyTime < time) {
				timeFlag = true;
			}
		}
		
		return timeFlag;
	}
	
	/**
	 * 2016-11-18 alisa.yang 验证openid和token是否有效
	 * @param dto
	 * @return
	 */
	private boolean checkAccessToken(MemberLoginWeixinDto dto){
		
		if(Constants.THIRD_LOGIN_TYPE_CCB.equals(dto.getType()) && StringUtils.isNotBlank(dto.getOpenid())){
			return true;
		}
		
		if(StringUtils.isNotBlank(dto.getToken()) && StringUtils.isNotBlank(dto.getOpenid())){
			try {
				int errcode = -1;
				String errmsg = "还未验证";
				boolean result = false;
				
				String urlStr = null;
				if(Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(dto.getType()) && StringUtils.isNotBlank(dto.getUnionid())){
					urlStr = "https://api.weixin.qq.com/sns/auth?access_token=" + dto.getToken() + "&openid=" + dto.getOpenid();
				}else if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(dto.getType())){
					urlStr = "https://api.weibo.com/2/account/get_uid.json?access_token="+dto.getToken();
				}else if(Constants.THIRD_LOGIN_TYPE_QQ.equals(dto.getType())){
					urlStr = "https://graph.qq.com/oauth2.0/me?access_token=" + dto.getToken();
				}
				
				if(StringUtils.isNotBlank(urlStr)){
					URL url = new URL(urlStr);
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					urlConnection.setRequestMethod("GET"); 
				    urlConnection.setDoOutput(false); 
				    urlConnection.setDoInput(true);  
				    urlConnection.setUseCaches(false);
				    urlConnection.setRequestProperty("content-type", "application/json; charset=UTF-8");
				    if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(dto.getType())){
				    	//微博，header加参数
				    	urlConnection.setRequestProperty("access_token", dto.getToken());
				    }
				    if(200 == urlConnection.getResponseCode()){
				    	BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
						String response = "";
						String line;
						while ((line = reader.readLine()) != null) {
							response += line;
						}
						log.info("授权凭证（access_token）验证通过，urlStr:" + urlStr +",response:" + response);
						
						if(Constants.THIRD_LOGIN_TYPE_QQ.equals(dto.getType()) && response.startsWith("callback(") && response.endsWith(");")){
							//异常，Unclosed group near index 9，将左括号改为 \\(或者[(]
							response = response.replaceFirst("callback[(]", "");
							response = response.substring(0, response.lastIndexOf(");"));
						}
						JSONObject json = JSON.parseObject(response);
						urlConnection.disconnect();
						
						if(Constants.THIRD_LOGIN_TYPE_WEIXIN.equals(dto.getType())){
			    			//微信
					    	errcode = json.getIntValue("errcode");
							errmsg = json.getString("errmsg");
							if (errcode == 0) {
								result = true;
							}
			    		}else if(Constants.THIRD_LOGIN_TYPE_WEIBO.equals(dto.getType())){
			    			//微博
							if(json.containsKey("uid")){
//				    			返回结果 JSON示例 { "uid":"3456676543"}
								String uid = json.getString("uid");
								if(dto.getOpenid().equals(uid)){
									errcode = 0;
									errmsg = "ok";
									result = true;
								}else{
									errmsg = "与微博授权uid:"+uid+"不匹配";
								}
							}else{
								errcode = json.getIntValue("error_code");
								errmsg = json.getString("error");
							}
			    		}else if(Constants.THIRD_LOGIN_TYPE_QQ.equals(dto.getType())){
			    			//QQ
			    			if(json.containsKey("openid")){
			    				//返回包，callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
			    				String openid = json.getString("openid");
			    				if(dto.getOpenid().equals(openid)){
			    					errcode = 0;
									errmsg = "ok";
									result = true;
			    				}else{
			    					errmsg = "与QQ授权openid:"+openid+"不匹配";
			    				}
			    			}else{
			    				errmsg = response;
			    			}
			    		}else{
			    			errmsg = "不支持的登录类型";
			    		}
				    }else{
				    	errcode = urlConnection.getResponseCode();
						errmsg = urlConnection.getResponseMessage();
				    }
				}//请求验证结束
				
				if(errcode != 0){
					log.info(dto.getType() + "授权凭证（access_token）失效,openid:" + dto.getOpenid() + ",token:" + dto.getToken() + ",unionid:" + dto.getUnionid() + ",结果：" + errcode + "," + errmsg);
				}
				return result;
			} catch (MalformedURLException e) {
				log.error("第三方验证URL异常！type:"+ dto.getType() + ",openid:" + dto.getOpenid() + ",token:" + dto.getToken(), e);
			} catch (IOException e) {
				log.error("第三方验证IO异常！type:"+ dto.getType() + ",openid:" + dto.getOpenid() + ",token:" + dto.getToken(), e);
			}
		}
		return false;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberLoginService#loginByAuthToken(java.lang.String)
	 */
	@Override
	public ResponseEntity<Result> loginByAuthToken(JSONObject json, String loginUserToken) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(ssoKey));
		SSOUser ownUser = tokenHandler.parseUserFromToken(loginUserToken);//原账户
		if(null != ownUser) {
			log.debug("token登录，载入者：" + ownUser.getUsername());
		}
		log.debug(",token登录，需要登录的用户token：" + json);
		if(json.isEmpty() || !json.containsKey("c2mToken")) {
			return new ResponseEntity<Result>(new Result(ResultCode.wxapp.TOKEN_NULL, Constants.ReturnMsg.TOKEN_NULL), HttpStatus.OK);
		}
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(ssoKey));
		SSOUser user = tokenHandler.parseUserFromToken(json.getString("c2mToken"));
		MemberLoginDto memberLoginDto = new MemberLoginDto();
		if (null != user) {
			SSOUser dbuser = dao.findById(SSOUser.class, user.getId());
			if(null == dbuser) {
				return new ResponseEntity<Result>(new Result(ResultCode.wxapp.NO_DBUSER_BY_TOKEN, Constants.ReturnMsg.PACKAGE_ERROR_QRCODE), HttpStatus.OK);
			}
			Member member = dao.findById(Member.class, dbuser.getSysid());
			if(null == member) {
				return new ResponseEntity<Result>(new Result(ResultCode.common.NO_MEMBER, Constants.MSG.MSG_GIFTCADRS_ERROR_NO_MEMBER), HttpStatus.OK);
			}
			memberLoginDto.setPhoneNo(member.getPhoneNo());
			memberLoginDto.setPassword(member.getPassword());
			log.debug("使用token登录成功，登录者手机号：" + user.getUsername());
			ResponseEntity<Result> result = loginUsePhoneNo(memberLoginDto, null);
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) result.getBody().getReturnData();
			map.remove("X-AUTH-TOKEN");
			return new ResponseEntity<Result>(new Result(map), HttpStatus.OK);
		}
		return new ResponseEntity<Result>(new Result(ResultCode.wxapp.TOKEN_ERROR, Constants.ReturnMsg.TOKEN_ERROR), HttpStatus.OK);
	}
	
	@Resource
	private IBaseDAO dao;
	
	@Value("${token.secret}")
	private String ssoKey;
	
	@Value("${superpw}")
	private String superPw;
	
	@Resource
	private IMemberService memberService;
	
	private TokenHandler tokenHandler;
}
