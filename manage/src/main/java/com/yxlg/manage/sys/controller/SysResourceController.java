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
package com.yxlg.manage.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.util.TreeGrid;
import com.yxlg.manage.sys.service.IManageSysResourceService;


/**
* @author Cary.yue
* @version  <br>
* <p>权限资源控制层</p>
*/
@Controller("SysResController")
@RequestMapping("/sysRes")
public class SysResourceController {
	
	/**
	 * 查询所有的菜单
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody List<TreeGrid> retrieve(TreeGrid treeGrid) {
		return sysSerImpl.findRes(treeGrid);
	}
	/**
	 * 添加权限资源
	 * @param res资源
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(SysResource res) {
		return sysSerImpl.saveRes(res);
	}
	/**
	 * 修改资源
	 * @param res
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(SysResource res) {
		return sysSerImpl.update(res);
	}
	/**
	 * 获取所有的父级菜单
	 * @return
	 */
	@RequestMapping(value="/getFirstLevelRes", method=RequestMethod.POST)
	public @ResponseBody List<SysResource> getFirstMenu() {
		return sysSerImpl.getFirst();
	}
	/**
	 * 根据id删除权限数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public @ResponseBody String delById(String id){
		return sysSerImpl.delete(id) ;
	}
	
	@Resource
	private IManageSysResourceService sysSerImpl;
}