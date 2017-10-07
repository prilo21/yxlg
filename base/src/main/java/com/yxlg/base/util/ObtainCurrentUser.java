/*
 * ObtainCurrentUser.java
 *
 * Created Date: 2016年4月1日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import javax.servlet.http.HttpSession;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.entity.UserContext;


/**
 * @author jerry.qin
 * @version  <br>
 * <p>获取当前登录人的方法</p>
 */

public class ObtainCurrentUser {
	public static String getOperator(HttpSession session) {
	    
        UserContext userContext = (UserContext) session.getAttribute(Constants.session.USER);
        if (null == userContext) {
            return "";
        }
        return userContext.getUser().getUserName();
    }
}
