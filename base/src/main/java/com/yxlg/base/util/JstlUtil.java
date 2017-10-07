/*
 * JstlUtil.java
 *
 * Created Date: 2015年11月12日
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

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.taglibs.standard.tag.common.core.OutSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yuandian
 * @version  <br>
 * <p>类的描述</p>
 */

public class JstlUtil extends OutSupport {
	
	private static final Logger log = LoggerFactory.getLogger(JstlUtil.class);
	private static final long serialVersionUID = -9119193273980351398L;
	
	protected String value;
	protected String pattern;
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
	
		this.value = DateFormatUtils.format(Long.parseLong(value), pattern);
	}
	
	/**
	 * @return the pattern
	 */
	public String getPattern() {
	
		return pattern;
	}
	
	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
	
		this.pattern = pattern;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		try {
			out(pageContext, false, value);
		} catch (IOException e) {
			log.error("异常！", e);
		}
		return SKIP_BODY;
	}
}
