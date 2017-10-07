/*
 * ManagerMemberTypeServiceImpl.java
 * 
 * Created Date: 2015年6月23日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.member.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.MemberGrade;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.member.service.IManagerMemberTypeService;

/**
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
@Service
public class ManagerMemberTypeServiceImpl implements IManagerMemberTypeService {

	@Override
	public Pagination findMemberType(PageBean pageBean, String memberType,
			String createPerson, String modifyPerson) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(MemberType.class);
		if (!StringUtils.isEmpty(memberType)) {
			detachedCriteria.add(Restrictions.like("memberType", memberType,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(createPerson)) {
			detachedCriteria.add(Restrictions.like("createPerson",
					createPerson, MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(modifyPerson)) {
			detachedCriteria.add(Restrictions.like("modifyPerson",
					modifyPerson, MatchMode.ANYWHERE));
		}
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(detachedCriteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
				detachedCriteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public String saveMemberType(MemberType memberType, String managerName) {

		//id主键存在，不可保存！
		if(StringUtils.isNotBlank(memberType.getMemberTypeId())){
			MemberType type = baseDao.findById(MemberType.class, memberType.getMemberTypeId());
			if(type != null){
				return Constants.ReturnMsg.FA_FALIURE;
			}
		}
		
		//类型存在，不可保存！
		DetachedCriteria typeCountQ = DetachedCriteria.forClass(MemberType.class);
		typeCountQ.add(Restrictions.eq("memberType", memberType.getMemberType()));
		Long typeCount = baseDao.findAllCountCriteria(typeCountQ);
		if(typeCount != null && typeCount > 0){
			return Constants.ReturnMsg.FA_FALIURE;
		}
		
		Date createDate = new Date();
		//获取当前登录人
		memberType.setCreatePerson(managerName);
		//获取当前登录时间
		memberType.setCreateTime(createDate);
		memberType.setModifyPerson(managerName);
		memberType.setModifyTime(createDate);
		baseDao.save(memberType);
		return Constants.ReturnMsg.SC_SUCCESS;
	}

	@Override
	public String updateMemberType(MemberType memberType, String managerName) {
		
		//id空，或者有 非此id的记录类型类型相同，不可保存
		if(StringUtils.isBlank(memberType.getMemberTypeId()) || checkNameExsit(memberType)){
			return Constants.ReturnMsg.FA_FALIURE;
		}
		
		Date modifyDate = new Date();
		//根据id获取对象
		MemberType memberType2 = baseDao.findById(MemberType.class, memberType.getMemberTypeId());
		memberType.setCreatePerson(memberType2.getCreatePerson());
		memberType.setCreateTime(memberType2.getCreateTime());
		memberType.setModifyPerson(managerName);
		memberType.setModifyTime(modifyDate);
		//清除session中实体
		baseDao.evict(memberType2);
		baseDao.update(memberType);
		return Constants.ReturnMsg.SC_SUCCESS;
	}

	@Override
	public boolean checkNameExsit(MemberType memberType) {

		List<MemberType> list = baseDao.findListByProperty(MemberType.class, "memberType", memberType.getMemberType());
		if (list == null || list.size() == 0) {
			return false;
		} else {
			//循环遍历，只要有id不一致，就返回true,数据库已有此类型会重复
			for (MemberType memberType2 : list) {
				if(!memberType2.getMemberTypeId().equals(memberType.getMemberTypeId())){
					return true;
				}
			}
			return false;
		}
	}
	
	@Override
	public Pagination findMemberGradeByType(PageBean pageBean, String memberType){
		if(StringUtils.isBlank(memberType)){
			return null;
		}
		
		DetachedCriteria q = DetachedCriteria.forClass(MemberGrade.class);
		q.add(Restrictions.eq("memberType", memberType));
		q.addOrder(Order.asc("constant"));
		
		Pagination pagination = new Pagination();
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean, q);
		Long count = baseDao.findAllCountCriteria(q);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}
	
	@Override
	public List<MemberGrade> findMemberGradeAll(){
		DetachedCriteria q = DetachedCriteria.forClass(MemberGrade.class);
		q.addOrder(Order.asc("memberType"));
		q.addOrder(Order.asc("constant"));
		List<MemberGrade> list = baseDao.findByDetachedCriteria(q);
		return list;
	}

	@Resource
	private IBaseDAO baseDao;

}
