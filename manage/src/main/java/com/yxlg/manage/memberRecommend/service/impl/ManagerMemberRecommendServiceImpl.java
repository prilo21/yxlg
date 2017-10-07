/*
 * ManagerMemberRelationServiceImpl.java
 *
 * Created Date: 2015年5月14日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.memberRecommend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.memberRecommend.dto.MemberRecommendDTO;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.memberRecommend.service.IManagerMemberRecommendService;

/**
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
@Service
public class ManagerMemberRecommendServiceImpl implements
		IManagerMemberRecommendService {

	@Override
	public Pagination findMemberWithPageCriteria(PageBean pageBean,
			String memberId, String memberName, String phoneNo, String rMemberName, String memberType) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberRecommend.class, "mr");
		DetachedCriteria memberCriteria = detachedCriteria.createCriteria("member");
		DetachedCriteria typeCriteria = memberCriteria.createCriteria("memberType");
		if ( !StringUtils.isEmpty(memberId)) {
			memberCriteria.add(Restrictions.like("memberId", memberId, MatchMode.ANYWHERE));
		}
		if ( !StringUtils.isEmpty(memberName)) {
			memberCriteria.add(Restrictions.like("memberName", memberName, MatchMode.ANYWHERE));
		}
		if ( !StringUtils.isEmpty(phoneNo)) {
			Member member = baseDao.findUniqueByProperty(Member.class, "phoneNo", phoneNo);
			detachedCriteria.add(Restrictions.or(
					Restrictions.like("phoneNo", phoneNo, MatchMode.ANYWHERE), 
					Restrictions.eq("member", member)));
		}
		if ( !StringUtils.isEmpty(memberType) && !"全部".equalsIgnoreCase(memberType)) {
			typeCriteria.add(Restrictions.like("memberType", memberType ,MatchMode.ANYWHERE));
		}
		
		//FIXME 由于实体中没有进行关联， 暂时无法查询，考虑采用sql语句的方式进行查询
		if ( !StringUtils.isEmpty(rMemberName)) {
			
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		Long count = baseDao.findAllCountCriteria(detachedCriteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
				detachedCriteria);
		List<Object> rowsDto = new ArrayList<Object>();
		for(Object obj: rows){
			MemberRecommend memberRecommend = (MemberRecommend) obj;
			Member member2 = baseDao.findUniqueByProperty(Member.class, "phoneNo", memberRecommend.getPhoneNo());
			MemberRecommendDTO mrDto = new MemberRecommendDTO(); 
			BeanUtils.copyProperties(memberRecommend, mrDto);
			mrDto.setMemberName(member2 != null ? member2.getMemberName() : "<strong>尚未注册</strong>");
			rowsDto.add(mrDto);
		}
		Pagination pagination = new Pagination();
		pagination.setRows(rowsDto);
		pagination.setTotal(count);
		return pagination;
	}

	@Resource
	private IBaseDAO baseDao;
	/**
	 * 注入SessionFactory属性
	 */
}
