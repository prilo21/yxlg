/*
 * ManageSysRoleMenuServiceImpl.java
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

package com.yxlg.manage.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.impl.BaseDAOImpl;
import com.yxlg.base.sys.entity.RoleResource;
import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.UserRole;
import com.yxlg.base.util.ComboTree;
import com.yxlg.manage.sys.service.IManageSysRoleMenuService;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>角色权限接口实现</p>
 */
@Service
public class ManageSysRoleMenuServiceImpl implements IManageSysRoleMenuService{

	/**
	 * 根据角色id和菜单id查询树结构
	* @param roleId
	* @param menuId
	 */
	public List<ComboTree> getMenuByRoleId(String roleId,String menuId) {
		
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		List<SysResource> resList = new ArrayList<SysResource>();
		
		// 查询菜单
		// 如果没有ID,查询一级目录
		if (StringUtils.isEmpty(menuId)) {
			DetachedCriteria det=DetachedCriteria.forClass(SysResource.class);
			det.add(Restrictions.or((Restrictions.eq("presource.id", "")),Restrictions.isNull("presource.id")));
			det.addOrder(Order.asc("showOrder"));
			resList=baseDao.findByDetachedCriteria(det);
		// 查找二级目录
		} else {
			DetachedCriteria det=DetachedCriteria.forClass(SysResource.class);
			det.add(Restrictions.eq("presource.id", menuId));
			det.addOrder(Order.asc("showOrder"));
			resList=baseDao.findByDetachedCriteria(det);	
		}
		
		// 根据roleId获取已有权限的菜单ID
		List<RoleResource> roleMenuList = (List<RoleResource>) baseDao.findListByProperty(RoleResource.class,"role.roleId",roleId);
		
		for (SysResource confSysMenu : resList) {
			ComboTree comboTree = new ComboTree();
			// 设置菜单ID
			comboTree.setId(confSysMenu.getSysResourceId());
			// 设置菜单名称
			comboTree.setText(confSysMenu.getResName());
			// 设置选中状态
			if (roleMenuList!=null && !roleMenuList.isEmpty()) {
				for (RoleResource confRoleMenu : roleMenuList) {
					if (confSysMenu.getSysResourceId().equals(confRoleMenu.getRes().getSysResourceId())) {
						comboTree.setChecked(true);
						break;
					}
				}
			}
			// 判断是否有子节点
			List<SysResource> childrenList =baseDao.findListByProperty(SysResource.class, "presource.id", confSysMenu.getSysResourceId());
			if (childrenList!=null && !childrenList.isEmpty()) {
				comboTree.setState("closed");
				comboTree.setChecked(false);
			}
			comboTrees.add(comboTree);
		}
		return comboTrees;
	}
	
/**
 * 保存新的角色权限表
 * @param roleId
 * @param selectNodes
 */
	public void saveRoleMenu(String roleId, String selectNodes) {
		// 删除原有的角色菜单
		List<RoleResource>list=baseDao.findListByProperty(RoleResource.class, "role.id", roleId);
		baseDao.deleteAllEntity(list);
		// 保存新的角色菜单
		if (StringUtils.isNotEmpty(selectNodes)) {
			String[] menuIds = selectNodes.split(",");
			for (String menuId : menuIds) {
    			RoleResource rolRes = new RoleResource();
    			rolRes.setRes(baseDao.findById(SysResource.class, menuId));
    			rolRes.setRole(baseDao.findById(SysRole.class, roleId));
    			baseDao.save(rolRes);
			}
		}
	}
	
	/**
	 * 获取角色下的用户列表
	 * @param roleId
	 * @return
	 */
	public List<UserRole> getUserByRoleId(String roleId) {
		DetachedCriteria det=DetachedCriteria.forClass(UserRole.class);
		det.add(Restrictions.eq("user.id", roleId));
		return baseDao.findByDetachedCriteria(det);
	}
	@Resource
	private BaseDAOImpl baseDao;
}
