/*
 * MemberThirdInfoImpl.java
 *
 * Created Date: 2016年6月22日
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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.constant.ResultCode;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberThirdInfo;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberThirdInfoService;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>会员第三方信息实现类</p>
 */
@Service
public class MemberThirdInfoImpl implements IMemberThirdInfoService {
	private static final Logger log = LoggerFactory.getLogger("memberThirdInfoImpl");
	
	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberThirdInfoService#memberThirdRegist(com.yxlg.base.member.entity.MemberThirdInfo)
	 */
	@Override
	public String memberThirdRegist(MemberThirdInfo info) {
	
		if (null == info.getMember()) {
			return "信息不完整，缺少member信息";
		}
		log.info("【会员" + info.getMember().getMemberId() + "登记第三方信息.】");
		Member member = baseDAO.findById(Member.class, info.getMember().getMemberId());
		if (null != member) {
			MemberThirdInfo oldInfo = baseDAO.findById(MemberThirdInfo.class, info.getMemberThirdId());
			if (null == oldInfo) {
				info.setCreateTime(new Date());
				baseDAO.save(info);
			} else {
				oldInfo.setUpdateTime(new Date());
				baseDAO.update(oldInfo);
			}
		} else {
			return "会员id错误,会员不存在";
		}
		return  Constants.ReturnMsg.SC_SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberThirdInfoService#findMemberThirdInfo(java.lang.String)
	 */
	@Override
	public ResponseEntity<Result> findMemberThirdInfo(String memberId) {
	
		Member member = baseDAO.findById(Member.class, memberId);
		if (null == member) {
			return new ResponseEntity<Result>(new Result(ResultCode.common.OK, Constants.ReturnMsg.SC_NOT_FOUND), HttpStatus.OK);
		}
		MemberThirdInfo info = baseDAO.findUniqueByProperty(MemberThirdInfo.class, "member.member_id", memberId);
		return new ResponseEntity<Result>(new Result(ResultCode.common.OK, Constants.ReturnMsg.SC_SUCCESS, info), HttpStatus.OK);
	}
	

	/* (non-Javadoc)
	 * @see com.yxlg.member.service.IMemberThirdInfoService#findMemberByThirdInfoId(java.lang.String)
	 */
	@Override
	public ResponseEntity<Result> findMemberByThirdInfoId(String openId) {
	
		List<MemberThirdInfo> memberlist = baseDAO.findListByProperty(MemberThirdInfo.class, "openId", openId);
		return new ResponseEntity<Result>(new Result(ResultCode.common.OK, Constants.ReturnMsg.SC_SUCCESS, memberlist), HttpStatus.OK);
	}
	
	@Resource
	private IBaseDAO baseDAO;
}
