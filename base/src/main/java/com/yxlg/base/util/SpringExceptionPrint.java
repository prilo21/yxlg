/*
 * SpringExceptionPrint.java
 *
 * Created Date: 2016年6月2日
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
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;


/**
 * @author Cary
 * @version  <br>
 * <p>Spring异常打印</p>
 */
public class SpringExceptionPrint extends DefaultHandlerExceptionResolver {
	 private static final Logger LOG = LoggerFactory  
	            .getLogger(SpringExceptionPrint.class);  
	  
		@Override
		protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
			LOG.error(request.getRequestURI(), ex);
			
			return returnJson(request, response, handler, ex);
//			return super.doResolveException(request, response, handler, ex); 
		}

		/**
		 * @param request
		 * @param response
		 * @param handler
		 * @param ex
		 * @return
		 */
		private ModelAndView returnJson(HttpServletRequest request, HttpServletResponse response,
				Object handler, Exception ex) {
		
			HandlerMethod mathod = (HandlerMethod) handler;
			ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
			// 判断有没有@ResponseBody的注解没有的话调用父方法
			if (body == null) {
				return super.doResolveException(request, response, handler, ex);
			}
			ModelAndView mv = new ModelAndView();
			PrintWriter printWriter = null;
			response.reset();
			// 设置状态码
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			// 设置ContentType
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			// 避免乱码
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				printWriter = response.getWriter();
				Result result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						ex.getMessage() + (StringUtils.isEmpty(ex.getMessage()) ? ex.toString() : ""));
				
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
