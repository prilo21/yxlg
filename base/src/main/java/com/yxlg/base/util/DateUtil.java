/*
 * DateUtil.java
 *
 * Created Date: 2016年10月27日
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

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Michael.Sun
 * 2016年10月27日
 * @version  <br>
 * <p>日期转换类</p>
 */

public class DateUtil {
	/**
	 * 按指定格式返回日期字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
}
