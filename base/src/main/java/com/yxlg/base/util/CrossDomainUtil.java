/*
 * CrossDomainUtil.java
 * 
 * Created Date: datetime
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
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * @author Michael.Sun
 *         2016年2月25日
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public class CrossDomainUtil {
//	private static final Logger log = LoggerFactory.getLogger(CrossDomainUtil.class);
	/**
	 * 跨域返回数据
	 * 
	 * @param request
	 * @param response
	 * @param data
	 */
	public static void writeCrossDomainData(HttpServletRequest request, HttpServletResponse response, Object data) {
	
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(request.getParameter("jsoncallback") + "(" + JSON.toJSONString(data) + ")");
		} catch (IOException e) {
			throw new BusinessException("获取printWirter异常,跨域返回数据失败", e);
		}
	}
}
