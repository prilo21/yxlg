/*
 * IManagerMemberRelationService.java
 *
 * Created Date: 2015年5月14日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.memberRecommend.service;

import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public interface IManagerMemberRecommendService {

	/**
	 * 通过会员id分页查询会员关系表
	 * 
	 * @param pageBean
	 * @param memberId
	 * @return
	 */
	public Pagination findMemberWithPageCriteria(PageBean pageBean,
			String memberId, String memberName, String phoneNo, String rMemberName, String memberType);

}
