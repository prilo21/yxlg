/*
 * SysUserServiceImpl.java
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.impl.BaseDAOImpl;
import com.yxlg.base.sys.entity.Maintainer;
import com.yxlg.base.sys.entity.RoleResource;
import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.sys.entity.UserRole;
import com.yxlg.base.util.ComboTree;
import com.yxlg.base.util.NumberComparator;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.sys.service.IManageSysUserService;
/**
 * @author Edison.Ding
 * @version  <br>
 * <p>类的描述</p>
 */
@Service(value="sysUserService")
public class ManagerSysUserServiceImpl implements IManageSysUserService{

	@Override
	public SysUser getUser(String userName, String password) {
		SysUser user = null;
		password=DigestUtils.md5Hex(password);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		detachedCriteria.add(Restrictions.eq("userName",userName));
		detachedCriteria.add(Restrictions.eq("userPassword",password));

		List<SysUser> userList= this.baseDao.findByDetachedCriteria(detachedCriteria);
		
		//1.账号存在
		if(userList.size()>0){
			user = userList.get(0);
		}
		
		return user;
	}

	@Override
	public SysUser getUser(String userName) {
		SysUser user = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		detachedCriteria.add(Restrictions.eq("userName",userName));
		
		DetachedCriteria maintainerDet = getDeletedMaintainerDet();
		//添加条件,sysUserId不是那些在maintain表中被删除过的
		detachedCriteria.add(Property.forName("sysUserId").notIn(maintainerDet));
		/*DetachedCriteria delMeasurerDet = getDelMeasurerDet();
		detachedCriteria.add(Property.forName("sysUserId").notIn(delMeasurerDet));*/
		
		List<SysUser> userList= this.baseDao.findByDetachedCriteria(detachedCriteria);

		//1.账号存在
		if(userList.size()>0){
			user = userList.get(0);
		}
		return user;
	}	
	
	@Override
	public Set<SysResource> getUserPermission(SysUser user) {
	
		List<UserRole> listUserRole=baseDao.findListByProperty(UserRole.class, "user.sysUserId", user.getSysUserId());
		List<RoleResource> litRoleRource=new ArrayList<RoleResource>();
		Set<SysResource> setSysRource=new HashSet<SysResource>();
		for(UserRole u:listUserRole){
			
			litRoleRource = baseDao.findListByProperty(RoleResource.class, "role", u.getRole());
			for(RoleResource t:litRoleRource){
				SysResource res= t.getRes();
				setSysRource.add(res);
			}
		}
		return setSysRource;
	}
	/**
	 * 根据用户权限生成菜单树
	 * @param firstLevelMenu 一级菜单集合
	 * @param secondLevelMenu 二级菜单集合
	 * @return
	 */
	public String getMenuStrByList(Set<SysResource> setSysRource) {
	
		//一级菜单与二级菜单
		List<SysResource >firstMenu=new ArrayList<SysResource>();
		List<SysResource> secondMenu=new ArrayList<SysResource>();
		
		for(SysResource res:setSysRource){
			if("1".equals(res.getLeve())){
				if(!firstMenu.contains(res.getPresource())){
					firstMenu.add(res);
				}
			}else if("2".equals(res.getLeve())){
				secondMenu.add(res);
			}
		}
		//菜单排序
		Collections.sort(firstMenu,new NumberComparator());
		Collections.sort(secondMenu, new NumberComparator());
		StringBuffer menuBuffer = new StringBuffer();
		// 循环遍历一级菜单,组装菜单字符串
		for (SysResource confSysMenu : firstMenu) {
			//菜单类型
			if("menu".equals(confSysMenu.getResType())){
    			menuBuffer.append("<div title='").append(confSysMenu.getResName()).append("' iconCls=\"folder\">");
    			menuBuffer.append("<ul>");
    			for (SysResource confSysMenu2 : secondMenu) {
    				String  ziResourceID = confSysMenu.getSysResourceId();
    			      SysResource previous = confSysMenu2.getPresource();

    				if(null!=previous){
    					if (ziResourceID.equals(previous.getSysResourceId())) {
    						menuBuffer.append("<li><div title='").append(confSysMenu2.getResName()).append("' url='").append(confSysMenu2.getResUrl())
    						.append("'  iconCls=\"pencil\"><a class='").append(confSysMenu2.getResName())
    						.append("'><span class='icon pencil'>&nbsp;</span><span class='nav'>")
    						.append(confSysMenu2.getResName()).append("</span></a></div></li>");
    					}
    				}
    			}
    			menuBuffer.append("</ul></div>");
			}
		}
		return menuBuffer.toString();
	}
	@Resource
	private BaseDAOImpl baseDao;
	/* (non-Javadoc)
	 * @see com.yxlg.manage.sys.service.IManageSysUserService#findRoleByUserId(java.lang.String)
	 */
	public List<ComboTree> findRoleByUserId(String sysUserId) {
		
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		// 查询所有的角色
		List<SysRole> roleAllList = baseDao.findAll(SysRole.class);
		
		// 根据sysUserId获取已有权限的菜单ID
		List<UserRole> list = null;
		SysUser	 sysUser = this.baseDao.findById(SysUser.class, sysUserId);
			 list = 	 this.baseDao.findListByProperty(UserRole.class, "user", sysUser);
		List<SysRole> roleList=new ArrayList<SysRole>();
	
		if(list.size()>0){
			for(int i = 0; i<list.size();i++){
				SysRole role = list.get(i).getRole();
				roleList.add(role);
			}
		}
		for (SysRole sysRole : roleAllList) {
			ComboTree treeGrid = new ComboTree();
			// 设置角色ID
			treeGrid.setId(sysRole.getRoleId());
			// 设置角色名称
			treeGrid.setText(sysRole.getRoleName());
		
			if(roleList!=null && !roleList.isEmpty() ){
				for (SysRole sysRole2 : roleList) {
					if(sysRole2.getRoleId().equals(sysRole.getRoleId())){
						treeGrid.setChecked(true);
						break;
					}
				}
			}
			comboTrees.add(treeGrid);
		}
		
		return comboTrees;
	}

	/* (non-Javadoc)
	 * @see com.yxlg.manage.sys.service.IManageSysUserService#saveRoleMenu(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveRoleMenu(String sysUserId, String selectNodes) {
		
		SysUser sysUser	= baseDao.findById(SysUser.class, sysUserId);

		//查询出当然用户的关联用户角色   删除原有的用户角色
		List<UserRole> list1 =	this.baseDao.findListByProperty(UserRole.class, "user.sysUserId", sysUserId);
		this.baseDao.deleteAllEntity(list1);
		
		// 保存新的用户角色
		if (StringUtils.isNotEmpty(selectNodes)) {
			String[] roleIds = selectNodes.split(",");
			for (String roleId : roleIds) {
				UserRole userRole1 = new UserRole();
				SysRole sysRole = baseDao.findById(SysRole.class, roleId);
				userRole1.setRole(sysRole);
				userRole1.setUser(sysUser);
				baseDao.save(userRole1);
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.yxlg.manage.sys.service.IManageSysUserService#serchUserby(com.yxlg.base.util.PageBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Pagination serchUserby(PageBean pageBean, String userName,String realName) {
		
		DetachedCriteria userDet = DetachedCriteria.forClass(SysUser.class, "user");
		if (StringUtils.isNotEmpty(userName))
			userDet.add(Restrictions.like("user.userName", userName, MatchMode.ANYWHERE));
		if (StringUtils.isNotEmpty(realName))
			userDet.add(Restrictions.like("user.realName", realName, MatchMode.ANYWHERE));
			
		DetachedCriteria maintainerDet = getDeletedMaintainerDet();
		//添加条件,sysUserId不是那些在maintain表中被删除过的
		userDet.add(Property.forName("user.sysUserId").notIn(maintainerDet));
		
		/*DetachedCriteria delMeasurerDet = getDelMeasurerDet();
		userDet.add(Property.forName("user.sysUserId").notIn(delMeasurerDet));*/
		
		// 构造分页
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(userDet);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean, userDet);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}

	/**
	 * 获取条件对象,条件是所有的sysUserId,这些sysUserId在maintainer表中已被删除(deleteFlag为1的那些记录)
	 */
	 DetachedCriteria getDeletedMaintainerDet() {
	
		DetachedCriteria maintainerDet = DetachedCriteria.forClass(Maintainer.class, "maintainer");
		maintainerDet.add(Restrictions.eq("maintainer.deleteFlag", "1"));
		maintainerDet.setProjection(Projections.property("maintainer.sysUser.sysUserId"));

		return maintainerDet;
	}
	
	/**
	 * 获取条件对象，条件是 在量体师表中已删除或者离职的所有的sysUserId
	 * @return
	 */
	/*private DetachedCriteria getDelMeasurerDet(){
		DetachedCriteria delMeasurerDet = DetachedCriteria.forClass(BodyMeasurementPerson.class, "measurer");
		delMeasurerDet.add(Restrictions.isNotNull("measurer.sysUser"));
		delMeasurerDet.add(Restrictions.or(Restrictions.eq("measurer.jobResignation", C2MConstants.DELETED_FLAG), Restrictions.eq("measurer.delete_flag", C2MConstants.DELETED_FLAG)));
		delMeasurerDet.setProjection(Projections.property("measurer.sysUser.sysUserId"));
		
		return delMeasurerDet;
	}*/

	@Override
	public void updateSysUser(SysUser user) {
			baseDao.update(user);
	}

}
