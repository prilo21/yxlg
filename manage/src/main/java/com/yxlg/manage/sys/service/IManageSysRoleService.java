/*
 * IManageSysRoleService.java
 *
 * Created Date: 2015年5月9日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.sys.service;

import java.util.List;

import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.SysUser;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>权限管理角色接口</p>
 */

public interface IManageSysRoleService {
	/**
	 * 根据角色Id查询角色下的用户
	 * @param roleId
	 * @return
	 */
	public  List<SysUser> getUserByRoleId(String roleId);
	/**
	 * 添加角色并验证编号是否重复
	 * @param role
	 * @return
	 */
	public String saveRole(SysRole role);
	/**
	 * 修改角色并验证编号是否重复
	 * @param role
	 */
	public String update(SysRole role);
	
	/**
	 * 根据id删除角色
	 * @param roleId
	 * @return
	 */
	public String delet(String roleId);
}
