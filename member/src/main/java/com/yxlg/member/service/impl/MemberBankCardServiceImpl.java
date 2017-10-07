/*
 * MemberBankCardServiceImpl.java
 *
 * Created Date: 2015年6月3日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberBankCard;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberBankCardService;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>类的描述</p>
 */
@Service
public class MemberBankCardServiceImpl implements IMemberBankCardService{

	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberBankCardService#save(java.lang.String, java.util.Map)
	 */
	@Override
	public ResponseEntity<Result> save(String memberId, Map<String, String> map) {
		if(StringUtils.isEmpty(memberId)){
			return new ResponseEntity<Result>(new Result(400, "信息为空"), HttpStatus.BAD_REQUEST);
		}
		if(map.isEmpty()){
			return new ResponseEntity<Result>(new Result(400, "信息为空"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(map.get("bankCardNo"))){
			return new ResponseEntity<Result>(new Result(400, "银行卡号为空"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(map.get("ownerName"))){
			return new ResponseEntity<Result>(new Result(400, "持卡人姓名为空"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(map.get("bankName"))){
			return new ResponseEntity<Result>(new Result(400, "银行名称为空"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(map.get("idNo"))){
			return new ResponseEntity<Result>(new Result(400, "身份证号为空"), HttpStatus.BAD_REQUEST);
		}
		Member member = new Member();
		member.setMemberId(memberId);
		MemberBankCard memberBankCard = new MemberBankCard();
		memberBankCard.setBankCardNo(map.get("bankCardNo"));
		memberBankCard.setOwnerName(map.get("ownerName"));
		memberBankCard.setMember(member);
		memberBankCard.setBankId(map.get("bankId"));
		memberBankCard.setBankName(map.get("bankName"));
		memberBankCard.setIdNo(map.get("idNo"));
		dao.save(memberBankCard);
		Map<String, String> returnMap = new HashMap<String, String>();
		//TODO
		returnMap.put("bankCardId", memberBankCard.getId());
		return new ResponseEntity<Result>(new Result(returnMap), HttpStatus.OK);
	}

	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberBankCardService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseEntity<Result> delete(String memberId, String bankCardId) {
		if(StringUtils.isEmpty(memberId)||StringUtils.isEmpty(bankCardId)){
			return new ResponseEntity<Result>(new Result(400, "信息为空"), HttpStatus.BAD_REQUEST);
		}
		if(dao.findById(Member.class, memberId)==null){
			return new ResponseEntity<Result>(new Result(400, "用户信息错误"), HttpStatus.BAD_REQUEST);
		}
		MemberBankCard memberBankCard = dao.findById(MemberBankCard.class, bankCardId);
		if(memberBankCard==null){
			return new ResponseEntity<Result>(new Result(400, "无此银行卡"), HttpStatus.BAD_REQUEST);
		}
		dao.delete(memberBankCard);
		return new ResponseEntity<Result>(new Result(""),HttpStatus.OK);
	}
	
	@Resource
	private IBaseDAO dao;
}
