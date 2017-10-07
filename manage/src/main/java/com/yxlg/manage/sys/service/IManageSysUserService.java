/*
 * IManageSysUserService.java
 * 
 * Created Date: 2015年5月8日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.sys.service;

import java.util.List;
import java.util.Set;

import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.util.ComboTree;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * @author Edison.Ding
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public interface IManageSysUserService {
	
	// private String addUser();
	

	/**
	 * 根据用户查询用户权限
	 * @param user
	 * @return set集合
	 */
	public Set<SysResource> getUserPermission(SysUser user);
	
	/**
	 * 根据userName和用户名查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	SysUser getUser(String userName,String password);
	
	/**根据userName来查询用户
	 * @param userName
	 * @return
	 */
	SysUser getUser(String userName);
	
	/**
	 * 根据权限Set集合生成html菜单树
	 * 
	 * @param setSysRource
	 * @return
	 */
	public String getMenuStrByList(Set<SysResource> setSysRource);

	/**
	 * @param sysUserId
	 * @return
	 */
	public List<ComboTree> findRoleByUserId(String sysUserId);

	/**
	 * @param sysUserId
	 * @param selectNodes
	 */
	public void saveRoleMenu(String sysUserId, String selectNodes);

	/**
	 * 待条件分页查询
	 * @param pageBean
	 * @param userName
	 * @param realName
	 * @return
	 */
	public Pagination serchUserby(PageBean pageBean, String userName,
			String realName);

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateSysUser(SysUser user);

}
