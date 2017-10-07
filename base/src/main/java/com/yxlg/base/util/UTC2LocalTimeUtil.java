/*
 * UTC2LocalTimeUtil.java
 *
 * Created Date: 2015年7月23日
 *				
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jetty.guo
 * @version  <br>
 * <p>UTC转本地时间工具类的描述</p>
 */

public class UTC2LocalTimeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(UTC2LocalTimeUtil.class);
	
	public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
		
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = utcFormater.parse(utcTime);
		} catch (ParseException e) {
			log.error("异常", e);
		}
		SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		localFormater.setTimeZone(TimeZone.getDefault());
		String localTime = localFormater.format(gpsUTCDate.getTime());
		return localTime;
	}
	
}
