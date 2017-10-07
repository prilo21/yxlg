/*
 * SysUserController.java
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

package com.yxlg.manage.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.sys.service.IManageSysRoleService;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>角色管理控制层</p>
 */
@Controller
@RequestMapping(value="/role/*")
public class SysRoleController {
	/**
	 * 查询所有角色
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="find" ,method=RequestMethod.GET)
	public @ResponseBody Pagination findRole(PageBean pageBean){
		return baseService.findAllWithPage(pageBean, SysRole.class);
	}
	/**
	 * 查询角色下用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="findUserByRoleId",method=RequestMethod.POST)
	public @ResponseBody List<SysUser> getUserByRoleId( String roleId){
		return sysRoleService.getUserByRoleId(roleId);
	}
	
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody String saveRole(SysRole role){
		String rs=sysRoleService.saveRole(role);
		if(rs.equals( Constants.ReturnMsg.SC_SUCCESS))
			return "保存成功";
		else
			return rs;
	
	}
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public @ResponseBody String updateRole(SysRole role){
		String rs=sysRoleService.update(role);
		if(rs.equals( Constants.ReturnMsg.SC_SUCCESS))
			return "修改成功";
		else
			return rs;
	}
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="del", method=RequestMethod.POST)
	public @ResponseBody String deleteRole(String roleId){
		return sysRoleService.delet(roleId);
	}
	
	
	
	
	
	
	@Resource
	private IBaseService baseService;
	@Resource
	private IManageSysRoleService sysRoleService;
}
