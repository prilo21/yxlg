/*
 * ResponseHeadVersionInterceptor.java
 *
 * Created Date: 2015年8月16日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Skeet.WU
 * @version <br>
 *          <p>
 *          拦截所有请求，在请求的response中加入版本号
 *          </p>
 */

@Component("responseHeadVersionInterceptor")
public class ResponseHeadVersionInterceptor extends HandlerInterceptorAdapter {
	
	
	/*
	 * 利用正则映射到需要拦截的路径
	 * 
	 * private String mappingURL;
	 * 
	 * public void setMappingURL(String mappingURL) {
	 * this.mappingURL = mappingURL;
	 * }
	 */
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
		
//		String ios_version = "1.0";
//		String and_version = C2MConstants.AppVersion.VERSION_AND_2_8;
//		String is_ios_need_update = "false";
//		String is_and_need_update = "false";
//		if (redisTemplate.hasKey(RedisBaseKeys.APP_VERSION_IOS) && redisTemplate.hasKey(RedisBaseKeys.APP_VERSION_AND)&& redisTemplate.hasKey(RedisBaseKeys.APP_IOS_NEED_UPDATE)&& redisTemplate.hasKey(RedisBaseKeys.APP_AND_NEED_UPDATE)) {
//			ValueOperations<String, String> valueops = redisTemplate.opsForValue();
//			ios_version = valueops.get(RedisBaseKeys.APP_VERSION_IOS);
//			and_version = valueops.get(RedisBaseKeys.APP_VERSION_AND);
//			is_ios_need_update = valueops.get(RedisBaseKeys.APP_IOS_NEED_UPDATE);
//			is_and_need_update = valueops.get(RedisBaseKeys.APP_AND_NEED_UPDATE);
//		}
//		response.setHeader(RedisBaseKeys.APP_VERSION_IOS, ios_version);
//		response.setHeader(RedisBaseKeys.APP_VERSION_AND, and_version);
//		response.setHeader(RedisBaseKeys.APP_IOS_NEED_UPDATE, is_ios_need_update);
//		response.setHeader(RedisBaseKeys.APP_AND_NEED_UPDATE, is_and_need_update);
//		if(AppVersionUtil.AndroidCompare(C2MConstants.AppVersion.VERSION_AND_2_8)==-1){
//			response.setHeader("IOS-VERSION", ios_version);
//			response.setHeader("AND-VERSION", and_version);
//		}
	}
	
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
}