/*
 * ResRoleMenuService.java
 *
 * Created Date: 2015年5月8日
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

import com.yxlg.base.util.ComboTree;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>角色权限接口</p>
 */

public interface IManageSysRoleMenuService {
	/**
	 * 根据角色和菜单查询资源菜单树
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public List<ComboTree> getMenuByRoleId(String roleId,String menuId);
	/**
	 * 保存角色权限
	 * @param roleId
	 * @param selectNodes
	 */
	public void saveRoleMenu(String roleId, String selectNodes);
	
}
