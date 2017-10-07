/*
 * StringUtil.java
 * 
 * Created Date: 2016年8月17日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary
 * information of
 * Yuandian Technologies Co., Ltd.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it
 * only in accordance
 * with the terms of the license agreement you entered into
 * with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Michael.Sun
 *         2016年8月17日
 * @version <br>
 *          <p>
 *          自定义字符串工具类
 *          </p>
 */

public class StringUtil {
	
	/**
	 * 判断给定的某几个字符串是否有空值
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean haveBlank(String... strings) {
	
		for (String string : strings) {
			if (StringUtils.isBlank(string)) {
				return true;
			}
		}
		return false;
	}
}
