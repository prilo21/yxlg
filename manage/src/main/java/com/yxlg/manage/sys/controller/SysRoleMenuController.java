/*
 * SysRoleMenuController.java
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
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.util.ComboTree;
import com.yxlg.manage.sys.service.IManageSysRoleMenuService;


/**
 * @author Cary.yue
 * @version  <br>
 * <p>角色权限控制层</p>
 */
@Controller
@RequestMapping("/roleMenu/*")
public class SysRoleMenuController {
	/**
	 * 根据角色和菜单查询资源菜单树
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findMenu",method=RequestMethod.POST)
	public @ResponseBody List<ComboTree>getMenuByRoleId( String roleId,HttpServletRequest request){
		return resRoleMenuService.getMenuByRoleId(roleId, request.getParameter("id"));
	}
	/**
	 * 保存新的角色权限表
	 * @param roleId
	 * @param selectNodes
	 * @return
	 */
	@RequestMapping(value="/saveRoleMenu",method=RequestMethod.POST)
	public @ResponseBody String saveRoleRes( String roleId, String selectNodes){
		resRoleMenuService.saveRoleMenu(roleId, selectNodes);
		return "保存成功";
	}
	@Resource 
	private IManageSysRoleMenuService resRoleMenuService;
	
}
