/*
 * ManageSysRoleServiceImpl.java
 * 
 * Created Date: 2015年5月9日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.sys.entity.RoleResource;
import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.sys.entity.UserRole;
import com.yxlg.manage.sys.service.IManageSysRoleService;

/**
 * @author Cary.yue
 * @version <br>
 *          <p>
 *          权限角色管理
 *          </p>
 */
@Service
public class ManageSysRoleServiceImpl implements IManageSysRoleService {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yxlg.manage.sys.service.IManageSysRoleService#getUserByRoleId
	 * (java.lang.String)
	 */
	@Override
	public List<SysUser> getUserByRoleId(String roleId) {
		
			DetachedCriteria userRoleDet = DetachedCriteria.forClass(UserRole.class);
			userRoleDet.createAlias("user", "u");
			userRoleDet.createAlias("role", "r");
			
			DetachedCriteria maintainerDet = sysUserService.getDeletedMaintainerDet();
			userRoleDet.add(Property.forName("u.sysUserId").notIn(maintainerDet));
			
			userRoleDet.add(Restrictions.eq("r.roleId", roleId));
			
			List<UserRole> list = baseDao.findByDetachedCriteria(userRoleDet);
			
			List<SysUser> reList = new ArrayList<SysUser>();
			for (int i = 0; i < list.size(); i++) {
				reList.add(list.get(i).getUser());
			}
			return reList;
	}
	
	@Override
	public String saveRole(SysRole role) {
	
		List<SysRole> roleList = baseDao.findListByProperty(SysRole.class,
				"code", role.getCode());
		if (roleList != null && roleList.size() != 0) {
			return "用户编号已存在";
		} else {
			baseDao.save(role);
		}
		return Constants.ReturnMsg.SC_SUCCESS;
	}
	
	@Override
	public String update(SysRole role) {
	
		//根据用户的code去获取所有的用户
		SysRole sysRole = baseDao.findUniqueByProperty(SysRole.class, "roleId", role.getRoleId());
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRole.class);
		detachedCriteria.add(Restrictions.eq("code", role.getCode()));
		
		List<SysRole> roles = baseDao.findByDetachedCriteria(detachedCriteria);
		
		boolean exist = false;
		//已经存在的集合，包括自身
		if(roles.size() > 0){
			for(int i = 0; i < roles.size(); i++){
				//排除自身的集合里面检查
				if( !(roles.get(i).getRoleId().equals(role.getRoleId())) ){
					//如果存在，就将标识设置为存在
					if(roles.get(i).getCode().equals(role.getCode())){
						exist = true;
					}
				}
			}
		//数据库里面没有这个编号
		}else{
		}
		
		//如果存在
		if(exist==true){
			return "编号已经存在!";
		//表示不存在
		}else{
			sysRole.setCode(role.getCode());
			sysRole.setRoleName(role.getRoleName());
			baseDao.update(sysRole);
			return Constants.ReturnMsg.SC_SUCCESS;
		}
	}
	
	@Override
	public String delet(String roleId) {
	
		if (!StringUtils.isEmpty(roleId)) {
			List<UserRole> list = baseDao.findListByProperty(UserRole.class,
					"role.roleId", roleId);
			if (list != null && list.size() != 0) {
				return "角色含有用户关系禁止删除";
			} else {
				List<RoleResource> roleResList = baseDao.findListByProperty(
						RoleResource.class, "role.roleId", roleId);
				baseDao.deleteAllEntity(roleResList);
				List<Serializable> list2 = new ArrayList<Serializable>();
				list2.add(roleId);
				baseDao.deleteByValueList(SysRole.class, "roleId", list2);
				return "删除成功";
			}
		} else
			return "删除失败";
	}
	
	@Resource
	private IBaseDAO baseDao;
	
	@Resource
	private ManagerSysUserServiceImpl sysUserService;


}
