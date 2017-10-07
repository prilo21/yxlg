/*
 * TaskScheduler.java
 * 
 * Created Date: 2015年10月13日
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

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * @author Skeet.WU
 * @version <br>
 *          <p>
 *          线程池
 *          </p>
 */

@Component("taskScheduler")
public class TaskScheduler extends ThreadPoolTaskScheduler {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3794534961026052205L;
	
	/**
	 * 
	 */
	public TaskScheduler() {
	
		super();
		setPoolSize(30);
	}
}
