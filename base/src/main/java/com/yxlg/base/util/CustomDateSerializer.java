/*
 * CustomDateSerializer.java
 *
 * Created Date: 2015年6月3日
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

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yxlg.base.constant.Constants;

/**
 * @author yuandian
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public class CustomDateSerializer extends JsonSerializer<Date> {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
	 * com.fasterxml.jackson.core.JsonGenerator,
	 * com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider)
					throws IOException, JsonProcessingException {
					
		jgen.writeString(DateFormatUtils.format(value,
				Constants.DateFormatConstant.TIME_FORMAT));
	}
}
