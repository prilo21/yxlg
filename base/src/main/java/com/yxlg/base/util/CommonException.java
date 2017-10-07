/*
 * CommonException.java
 *
 * Created Date: Jun 14, 2015
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author Amos.Chu
 * @version <br>
 *          <p>
 *          打印通用异常
 *          </p>
 */
@Component("ExceptionResolver")
public class CommonException extends SimpleMappingExceptionResolver {
	
	public CommonException() {
		super.setWarnLogCategory("WARN");
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger("ExceptionLogger");
			
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		logger.error("------------------- New Unhandler Exception ------------------", ex);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#
	 * doResolveException(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		HandlerMethod mathod = (HandlerMethod) handler;
		ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
		// 设置状态码
		response.setStatus(HttpStatus.OK.value());
		// 判断有没有@ResponseBody的注解没有的话调用父方法
		if (body == null) {
			return super.doResolveException(request, response, handler, ex);
		}
		ModelAndView mv = new ModelAndView();
		PrintWriter printWriter = null;
		response.reset();
		// 设置ContentType
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// 避免乱码
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			printWriter = response.getWriter();
			Result result = new Result(HttpStatus.BAD_REQUEST.value(),
					ex.getMessage());
			
			printWriter.write((JsonUtil.toJson(result)));
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			logger.error("获取writer发生异常：", e);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
		
		return mv;
	}
}