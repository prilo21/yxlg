/*
 * MemberTypeController.java
 * 
 * Created Date: 2015年6月22日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.member.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.entity.UserContext;
import com.yxlg.base.member.entity.MemberGrade;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.service.IBaseService;
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
@Controller("memberTypeController")
@RequestMapping("/memberType/*")
public class MemberTypeController {

	/**
	 * 查询会员类型
	 * 
	 * @param pageBean
	 * @param memberType
	 * @param createPerson
	 * @param modifyPerson
	 * @return
	 */
	@RequestMapping(value = "/findByDetachedCriteria", method = RequestMethod.POST)
	public @ResponseBody Pagination findByDetachedCriteria(PageBean pageBean,
			String memberType, String createPerson, String modifyPerson) {

		return memberTypeService.findMemberType(pageBean, memberType,
				createPerson, modifyPerson);
	}

	/**
	 * 新建会员类型
	 * 
	 * @param memberType
	 * @return
	 */
	@RequestMapping(value = "/saveMemberType", method = RequestMethod.POST)
	public @ResponseBody String saveMemberType(MemberType memberType, HttpSession session) {

		return memberTypeService.saveMemberType(memberType, getOperator(session));
	}

	/**
	 * 更新会员类型
	 * 
	 * @param memberType
	 * @return
	 */
	@RequestMapping(value = "/updateMemberType", method = RequestMethod.POST)
	public @ResponseBody String updateMemberType(MemberType memberType, HttpSession session) {

		return memberTypeService.updateMemberType(memberType, getOperator(session));
	}

	/**
	 * 检查会员类型是否存在
	 * 
	 * @param memberType
	 * @return
	 */
	@RequestMapping(value = "/checkNameExsit", method = RequestMethod.POST)
	public @ResponseBody boolean checkNameExsit(MemberType memberType) {

		return memberTypeService.checkNameExsit(memberType);
	}

	/**
	 * 删除会员类型
	 * 
	 * @param memberTypeId
	 * @return
	 */
	@RequestMapping(value = "/deleteMemberType", method = RequestMethod.POST)
	public @ResponseBody String deleteMemberType(String memberTypeId) {

		baseService.deleteById(MemberType.class, memberTypeId);
		return Constants.ReturnMsg.SC_SUCCESS;
	}
	
	//获取当前登录人的方法
	private String getOperator(HttpSession session) {

		UserContext userContext = (UserContext) session.getAttribute(Constants.session.USER);
		if (null == userContext) {
			return "";
		}
		return userContext.getUser().getUserName();
	}
	
	@RequestMapping(value = "/findMemberGradeByType", method = RequestMethod.POST)
	public @ResponseBody Pagination findMemberGradeByType(PageBean pageBean, String memberType) {
		return memberTypeService.findMemberGradeByType(pageBean, memberType);
	}
	
	@RequestMapping(value = "/findMemberGradeAll", method = RequestMethod.GET)
	public @ResponseBody List<MemberGrade> findMemberGradeAll() {
		return memberTypeService.findMemberGradeAll();
	}
	
	

	@Resource
	private IBaseService baseService;

	@Resource
	private IManagerMemberTypeService memberTypeService;

}
