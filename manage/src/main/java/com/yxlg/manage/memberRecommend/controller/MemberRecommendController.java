/*
 * MemberRelationController.java
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

package com.yxlg.manage.memberRecommend.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller("memberRelationController")
@RequestMapping("/memberRelation/*")
public class MemberRecommendController {

	/**
	 * 根据条件查询
	 * @param pageBean 分页条件
	 * @param memberId 会员ID
	 * @param memberName 推荐人姓名
	 * @param phoneNo	推荐人/被推荐人手机号
	 * @param memberType 推荐人会员类型
	 * @param rMemberName 被推荐人姓名
	 * @return
	 */
	@RequestMapping(value = "/findByDetachedCriteria", method = RequestMethod.POST)
	public @ResponseBody Pagination findByDetachedCriteria(PageBean pageBean,
			String memberId, String memberName, String phoneNo, String memberType, String rMemberName) {
		return managerMemberRelationService.findMemberWithPageCriteria(
				pageBean, memberId, memberName, phoneNo, rMemberName, memberType);
	}

	@Resource
	private IManagerMemberRecommendService managerMemberRelationService;

}
