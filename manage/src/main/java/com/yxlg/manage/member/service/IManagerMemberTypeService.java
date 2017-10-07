/*
 * IManagerMemberTypeService.java
 *
 * Created Date: 2015年6月23日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.member.service;

import java.util.List;

import com.yxlg.base.member.entity.MemberGrade;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          会员类型基础表管理接口
 *          </p>
 */

public interface IManagerMemberTypeService {
	/**
	 * 查询会员类型
	 * 
	 * @param memberType
	 * @param createPerson
	 * @param modifyPerson
	 * @return
	 */
	public Pagination findMemberType(PageBean pageBean, String memberType,
			String createPerson, String modifyPerson);

	/**
	 * 新增会员类型
	 * 
	 * @param memberType
	 * @return
	 */
	public String saveMemberType(MemberType memberType, String managerName);

	/**
	 * 修改会员类型
	 * 
	 * @param memberType
	 * @return
	 */
	public String updateMemberType(MemberType memberType, String managerName);

	/**
	 * 检查会员类型名称是否存在
	 * 
	 * @param memberType
	 * @return
	 */
	public boolean checkNameExsit(MemberType memberType);
	
	/**
	 * 根据会员类型查询相应的会员级别列表
	 * @param pageBean
	 * @return
	 */
	public Pagination findMemberGradeByType(PageBean pageBean, String memberType);
	
	/**
	 * 获取所有的会员等级，按照type和级别排序
	 * @return
	 */
	public List<MemberGrade> findMemberGradeAll();
}
