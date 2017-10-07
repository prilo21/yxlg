/*
 * ManageMaintainUserServiceImpl。java
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

package com.yxlg.manage.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.sys.entity.Maintainer;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.sys.service.IManageSysMaintainerService;


/**
 * @author Edison.Ding
 * @version  <br>
 * <p>运维人员管理接口实现类</p>
 */

@Service
public class ManageSysMaintainerServiceImpl implements IManageSysMaintainerService {


	@Override
	public Pagination findMaintainer(PageBean pageBean, String maintainerName,String userName) {
	    
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Maintainer.class);
		DetachedCriteria maintainerCriteria = detachedCriteria.createCriteria("sysUser");
		if(!StringUtils.isEmpty(userName)){
			maintainerCriteria.add(Restrictions.like("userName", userName,MatchMode.ANYWHERE));
		}
		if(!StringUtils.isEmpty(maintainerName)){
			detachedCriteria.add(Restrictions.like("maintainerName", maintainerName, MatchMode.ANYWHERE));
		}
		detachedCriteria.add(Restrictions.eq("deleteFlag", "0"));
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(detachedCriteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,detachedCriteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.manage.sys.service.IManageSysMaintainerService#saveOrUpdate(com.yxlg.base.sys.entity.Maintainer)
	 */
	@Override
	public String saveOrUpdate(Maintainer maintainer, boolean newFlag) {
		if (newFlag) {
			List<SysUser> sysUsers = baseDao.findListByProperty(SysUser.class, "userName", maintainer.getMaintainerName());
			if (sysUsers != null && sysUsers.size() > 0) {
				return "用户名已存在,请更换用户名";
			}
			maintainer.setDeleteFlag("0");
			maintainer.getSysUser().setRealName(maintainer.getMaintainerName());
			maintainer.getSysUser().setUserPassword(DigestUtils.md5Hex(maintainer.getSysUser().getUserPassword()));
			baseDao.save(maintainer);
			return "添加成功!";
		} else if(!newFlag) {
			SysUser sysUser = baseDao.findById(SysUser.class, maintainer.getSysUser().getSysUserId());
			maintainer.setDeleteFlag("0");
			sysUser.setRealName(maintainer.getMaintainerName());
			maintainer.setSysUser(sysUser);
			baseDao.update(maintainer);
			return "修改成功!";
		}
		return "操作失败";
	}
	
	@Resource
	private IBaseDAO baseDao;

}
