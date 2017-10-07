/*
 * BusinessException.java
 *
 * Created Date: 2015年7月3日
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

/**
 * @author dirk
 * @version <br>
 *          <p>
 *          业务层异常处理类
 *          </p>
 */

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 6565917956102686167L;
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}