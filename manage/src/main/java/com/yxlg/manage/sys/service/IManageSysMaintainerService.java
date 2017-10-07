/*
 * IManageMaintainUserService。java
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

package com.yxlg.manage.sys.service;

import com.yxlg.base.sys.entity.Maintainer;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;


/**
 * @author Edison.Ding
 * @version  <br>
 * <p>运维人员管理接口</p>
 */

public interface IManageSysMaintainerService {
	

	/**
	 * 运维人员查询
	 * @param pageBean
	 * @param maintainerName
	 * @param userName 
	 * @return
	 */
	public Pagination findMaintainer(PageBean pageBean, String maintainerName, String userName);
	
	
	/**
	 * 运维人员新建或者编辑
	 * @param pageBean
	 */
	public String saveOrUpdate(Maintainer maintainer, boolean newFlag);
}
