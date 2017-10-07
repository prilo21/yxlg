/*
 * AuthInterceptor.java
 * 
 * Created Date: 2015年8月21日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yxlg.base.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

/**
 * @author Skeet.WU
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	/*
	 * 利用正则映射到需要拦截的路径
	 * 
	 * private String mappingURL;
	 * 
	 * public void setMappingURL(String mappingURL) {
	 * this.mappingURL = mappingURL;
	 * }
	 */
	// private final static Logger log =
	// LoggerFactory.getLogger(AuthInterceptor.class);
	
	/**
	 * 在业务处理器处理请求之前被调用
	 * 如果返回false
	 * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true
	 * 执行下一个拦截器,直到所有的拦截器都执行完毕
	 * 再执行被拦截的Controller
	 * 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle()
	 * 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		String login = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		/*
		 * if (request.getServerPort() != 80) {
		 * login += ":" + request.getServerPort() + request.getContextPath();
		 * } else {
		 * login += request.getContextPath();
		 * }
		 */
		String requestType = request.getHeader("X-Requested-With");
		String url = request.getRequestURL().toString();
		// log.info("login:" + login + "/nport:" + request.getServerPort() +
		// "\nuri:" + uri + "\nurl:" + url);
		if ((!(url.trim().equals(login + "/") || url.trim().equals(login) || uri.endsWith(request.getContextPath()) || uri.endsWith(request.getContextPath() + "/")))
				&& uri.indexOf(".") == -1 && !uri.endsWith("login") && !uri.endsWith("r/login") && !uri.endsWith("login.jsp")) {
			Object obj = request.getSession().getAttribute(Constants.session.USER);
			if (null == obj) {
				PrintWriter printWriter = null;
				try {
					response.reset();
					if (StringUtils.isNotBlank(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {
						response.setHeader("sessionstatus", "timeout");
						return false;
					} else {
						request.setCharacterEncoding("UTF-8");
						response.setCharacterEncoding("utf-8");
						response.setContentType("text/html; charset=utf-8");
						printWriter = response.getWriter();
						StringBuilder str = new StringBuilder();
						str.append("<script type=\"text/javascript\">");
						str.append("alert('未登录或网页已过期，请重新登陆！');");
						str.append("window.top.location.href='");
						str.append(login + "/login.jsp?error=2");
						str.append("';");
						str.append("</script>");
						printWriter.print(str.toString());
						printWriter.flush();
						printWriter.close();
						return false;
					}
				} finally {
					if (null != printWriter) {
						printWriter.close();
						return false;
					}
				}
			} else
				return true;
		}
		return true;
	}
	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 * 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	
	}
	
	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	
	}
}
