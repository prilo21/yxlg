/*
 * SqlLog.java
 * 
 * Created Date: 2015年5月23日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import org.objectweb.util.monolog.wrapper.p6spy.P6SpyLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p6spy.engine.logging.Category;

/**
 * @author Skeet.WU
 * @version <br>
 *          <p>
 *          截取p6spy的log功能，把日志输出到logback中去打印
 *          </p>
 */

public class SqlLog extends P6SpyLogger {
	
	private static final Logger logger = LoggerFactory.getLogger("p6spyLogger");
	
	@Override
	public void logSQL(int connectionId, String now, long elapsed,
			Category category, String prepared, String sql) {
			
		if (logger.isDebugEnabled()) {
			if (sql.startsWith("select") || sql.startsWith("insert")
					|| sql.startsWith("update") || sql.startsWith("delete")) {
				logger.debug(sql);
			}
		}
	}
	
	@Override
	public void logText(String text) {
		
		if (logger.isDebugEnabled()) {
			logger.debug(text);
		}
	}
}
