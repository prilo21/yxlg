/*
 * ResourceService.java
 *
 * Created Date: 2015年5月6日
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

import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.util.TreeGrid;

/**
 * @author Cary.yue
 * @version  <br>
 * <p>权限资源Service</p>
 */
public interface IManageSysResourceService {
	/**
	 * 权限资源查询
	 * @param 
	 * @return
	 */
	public List<TreeGrid> findRes(TreeGrid treeGrid);

	/**
	 * 保存权限
	 * @param res
	 */
	public String saveRes(SysResource res);

	/**
	 * 修改
	 * @param res
	 */
	public String update(SysResource res);

	/**
	 * 得到一级菜单
	 * @return
	 */
	public List<SysResource> getFirst();

	/**
	 * 根据父id查询
	 * @param menuId
	 * @return
	 */
	public List<SysResource> findByPid(String menuId);
	/**
	 * 删除资源陈功返回“成功”
	 * 含有子菜单禁止删除
	 * @param res
	 * @return
	 */
	public String delete (String resId);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysResource findById(String id);
}
