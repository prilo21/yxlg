/*
 * HibernateObjectMapper.java
 * 
 * Created Date: 2015年5月12日
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author dirk
 * @version <br>
 *          <p>
 *          json字符串转换类
 *          解决hibernate延迟加载对象为空bug
 *          </p>
 */

public class HibernateObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = -1530051816633368543L;
	
	public HibernateObjectMapper() {
		
		disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
}