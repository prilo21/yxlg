/*
 * MemberAddressInfoServiceImpl.java
 * 
 * Created Date: 2015年5月12日
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.util.BusinessException;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IMemberAddressInfoService;

/**
 * @author Michael.Sun
 * @version <br>
 *          <p>
 *          会员地址管理实现类
 *          </p>
 */
@Service
public class MemberAddressInfoServiceImpl implements IMemberAddressInfoService {
	private final static Logger log = LoggerFactory.getLogger(MemberAddressInfoServiceImpl.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberAddressInfoService#findAddresses
	 * (java.lang.String)
	 */
	
	@Override
	public ResponseEntity<Result> findAddresses(String memberId) {
	
		try {
			if (StringUtils.isEmpty(memberId)) {
				return new ResponseEntity<Result>(new Result(400, "用户Id为空"),
						HttpStatus.BAD_REQUEST);
			} else if (dao.findById(Member.class, memberId) == null) {
				return new ResponseEntity<Result>(new Result(400, "无此用户"),
						HttpStatus.BAD_REQUEST);
			} else {
				Member member = new Member();
				member.setMemberId(memberId);
				List<MemberAddressInfo> list = dao.findListByProperty(
						MemberAddressInfo.class, "member", member);
				List<Map<String, Object>> currentList = new ArrayList<Map<String, Object>>();
				for (MemberAddressInfo address : list) {
					if (address.isIsdelete() == true) {
						continue;
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("addressId", address.getId());
					map.put("receiver", address.getReceiver());
					map.put("phoneNo", address.getPhoneNo());
					map.put("postal", address.getPostal());
					map.put("province", address.getProvince());
					map.put("city", address.getCity());
					map.put("suburb", address.getSuburb());
					map.put("detail", address.getDetail());
					map.put("isDefault", address.isIsDefault());
					currentList.add(map);
				}
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("offset", 0);
				returnMap.put("limit", 10);
				returnMap.put("currentList", currentList);
				return new ResponseEntity<Result>(new Result(returnMap),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("id为" + memberId + "的用户查询用户地址失败" + e.getMessage());
			throw new BusinessException("查询用户地址失败",e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberAddressInfoService#addAddress(com
	 * .yxlg.base.member.entity.MemberAddressInfo)
	 */
	@Override
	public ResponseEntity<Result> addAddress(String memberId,
			MemberAddressInfo memberAddressInfo) {
	
		try {
			if (StringUtils.isEmpty(memberId) || memberAddressInfo == null) {
				return new ResponseEntity<Result>(new Result(400, "信息为空"),
						HttpStatus.BAD_REQUEST);
			} else if (dao.findById(Member.class, memberId) == null) {
				return new ResponseEntity<Result>(new Result(400, "无此用户"),
						HttpStatus.BAD_REQUEST);
			}
			String postCode = memberAddressInfo.getPostal();
			if(StringUtils.isEmpty(postCode)){
				return new ResponseEntity<Result>(new Result(400, "邮编为空"),
						HttpStatus.BAD_REQUEST);
			}
			if(!postCode.matches("[0-9]\\d{5}(?!\\d)")){
				return new ResponseEntity<Result>(new Result(400, "邮编错误"),
						HttpStatus.BAD_REQUEST);
			}
			Member member = new Member();
			member.setMemberId(memberId);
			memberAddressInfo.setMember(member);
			if (memberAddressInfo.isIsDefault()) {
				setIsDefaultFalse("valid", memberId);
			}
			// setIsDefaultFalse("valid", memberId);
			dao.save(memberAddressInfo);
			Map<String, String> map = new HashMap<String, String>();
			map.put("addressId", memberAddressInfo.getId());
			return new ResponseEntity<Result>(new Result(map), HttpStatus.OK);
		} catch (Exception e) {
			log.error("id为" + memberId + "的用户添加用户地址失败" + e.getMessage());
			throw new BusinessException("添加用户地址信息错误",e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberAddressInfoService#editAddress(
	 * com.yxlg.base.member.entity.MemberAddressInfo)
	 */
	@Override
	public ResponseEntity<Result> editAddress(String memberId,
			String memberAddressInfoId, MemberAddressInfo memberAddressInfo) {
	
		try {
			if (StringUtils.isEmpty(memberId)
					|| StringUtils.isEmpty(memberAddressInfoId)
					|| memberAddressInfo == null) {
				return new ResponseEntity<Result>(new Result(400, "用户信息为空"),
						HttpStatus.BAD_REQUEST);
			} else if (dao.findById(Member.class, memberId) == null) {
				return new ResponseEntity<Result>(new Result(400, "用户信息错误"),
						HttpStatus.BAD_REQUEST);
			}
			String postCode = memberAddressInfo.getPostal();
			if(StringUtils.isEmpty(postCode)){
				return new ResponseEntity<Result>(new Result(400, "邮编为空"),
						HttpStatus.BAD_REQUEST);
			}
			if(!postCode.matches("[0-9]\\d{5}(?!\\d)")){
				return new ResponseEntity<Result>(new Result(400, "邮编错误"),
						HttpStatus.BAD_REQUEST);
			}
			memberAddressInfo.setId(memberAddressInfoId);
			Member member = new Member();
			member.setMemberId(memberId);
			memberAddressInfo.setMember(member);
			if (memberAddressInfo.isIsDefault()) {
				setIsDefaultFalse(memberAddressInfoId, memberId);
			}
			// setIsDefaultFalse(memberAddressInfoId, memberId);
			dao.update(memberAddressInfo);
			return new ResponseEntity<Result>(new Result(200, "修改成功"),
					HttpStatus.OK);
		} catch (Exception e) {
			log.error("id为" + memberId + "的用户编辑用户地址id为" + memberAddressInfoId + "的地址失败" + e.getMessage());
			throw new BusinessException("编辑用户地址错误",e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberAddressInfoService#deleteAddress
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseEntity<Result> deleteAddress(String memberId,
			String memberAddressInfoId) {
	
		try {
			if (StringUtils.isEmpty(memberAddressInfoId)) {
				return new ResponseEntity<Result>(new Result(400, "地址Id为空"),
						HttpStatus.BAD_REQUEST);
			}
			MemberAddressInfo memberAddressInfo = dao.findById(
					MemberAddressInfo.class, memberAddressInfoId);
			memberAddressInfo.setIsdelete(true);
			memberAddressInfo.setIsDefault(false);
			dao.update(memberAddressInfo);
			return new ResponseEntity<Result>(new Result(200, "操作成功"),
					HttpStatus.OK);
		} catch (Exception e) {
			log.error("id为" + memberId + "的用户删除用户地址id为" + memberAddressInfoId + "的地址失败" + e.getMessage());
			throw new BusinessException("删除用户地址错误",e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.member.service.IMemberAddressInfoService#SetIsDefault
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void setIsDefaultFalse(String memberAddressInfoId, String memberId) {
	
		List<MemberAddressInfo> list = dao.findListByProperty(
				MemberAddressInfo.class, "member.id", memberId);
		for (MemberAddressInfo memberAddressInfo : list) {
			if (memberAddressInfo.getId().equals(memberAddressInfoId)) {
				dao.evict(memberAddressInfo);
			}
			
			// if (memberAddressInfo.isIsDefault() == true) {
			// if (memberAddressInfo.getId().equals(memberAddressInfoId)) {
			// return;
			// } else {
			// memberAddressInfo.setIsDefault(false);
			// dao.update(memberAddressInfo);
			// return;
			// }
			// }
			memberAddressInfo.setIsDefault(false);
		}
	}
	
	@Resource
	private IBaseDAO dao;
}
