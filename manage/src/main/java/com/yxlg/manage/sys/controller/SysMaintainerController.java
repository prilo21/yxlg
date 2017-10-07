/*
 * SysMaintainerController。java
 *
 * Created Date: 2015年5月21日
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

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.service.IBaseService;
import com.yxlg.base.sys.entity.Maintainer;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.sys.service.IManageSysMaintainerService;


/**
 * @author Edison.Ding
 * @version  <br>
 * <p>运维人员管理控制器</p>
 */

@Controller("sysMaintainerController")
@RequestMapping(value = "/sysMaintainer/*")
public class SysMaintainerController {
	
	/**
	 *  查询运维人员信息
	 *  @param  pageBean 
	 *  @param  maintainerName
	 *  @param  userName
	 *  @return
	 */
	
	@RequestMapping(value="/findByDetachedCriteria",method=RequestMethod.POST)
	public @ResponseBody Pagination findMaintainer(PageBean pageBean,String maintainerName,String userName){
		
		return manageSysMaintainerService.findMaintainer(pageBean,maintainerName,userName);
	}
	/**
	 * 添加运维人员
	 * @param  maintainer
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody String save(Maintainer maintainer){
		return manageSysMaintainerService.saveOrUpdate(maintainer, true);
	}
	/**
	 * 修改运维人员
	 * @param maintainer
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody String update(Maintainer maintainer){

		return manageSysMaintainerService.saveOrUpdate(maintainer, false);
	}
	/**
	 * 删除运维人员
	 * @param  maintainerId
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody String delete(String maintainerId){
		Maintainer m = baseService.findById(Maintainer.class, maintainerId);
		m.setDeleteFlag("1");
		baseService.update(m);
		return "删除成功";
	}
	/**
	 * 判断用户名是否存在
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST)
	public @ResponseBody boolean checkExistedUserName(Maintainer maintainer){
		DetachedCriteria det = DetachedCriteria.forClass(Maintainer.class);
		det.createAlias("sysUser", "u");
		det.add(Restrictions.ne("deleteFlag", "1"));
		det.add(Restrictions.eq("u.userName", maintainer.getSysUser().getUserName()));
		List<Maintainer> maintainerList = baseService.findByDetachedCriteria(det);
		
		return maintainerList.isEmpty();
	}
	@Resource
	private IManageSysMaintainerService manageSysMaintainerService;
	
	@Resource
	private IBaseService baseService;
}
