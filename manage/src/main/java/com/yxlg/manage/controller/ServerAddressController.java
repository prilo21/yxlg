/*
 * ServerAddressController.java
 *
 * Created Date: 2016年6月23日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.controller;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>后台控制appserver的请求地址类</p>
 */
@Controller("serverAddressController")
public class ServerAddressController {
	
	@RequestMapping("server/address/swich")
	public @ResponseBody String updateVersion(@RequestParam String andVersion, @RequestParam String iosVersion, @RequestParam String andurl, @RequestParam String iosurl, @RequestParam String pwd) {
		
		if (pwd != null && pwd.equals("20160701")) {
			String msg = "";
			ValueOperations<String, String> valueops = redisTemplate.opsForValue();
			/* ***********************
			 * Pattern.CASE_INSENSITIVE 不区分大小写
			 * 仅仅以http://打头，不支持携带查询字符串
			 * [\\w-\\.] 限制域名和路径仅仅由a-z0-9_-.这么几个字符
			 * 支持端口号，但是不支持到/的路径，比如：http://www.baidu.com/
			 * (?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))? 这个表示路径可以为空、/、和.do结尾
			 *************************/
	        if (!andurl.endsWith("/") || !iosurl.endsWith("/")) {
	        	return "设置失败,url结尾不对！";
	        }
			Pattern exp = Pattern.compile("^(https|http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*/?)$",Pattern.CASE_INSENSITIVE );
			if (StringUtils.isNotEmpty(andVersion)) {
				//valueops.set(RedisBaseKeys.APP_VERSION_AND, andVersion);
				msg = msg + "android版本设置为:" + andVersion + "此版本以上（不包含）访问设定的url";
			}
	        if (exp.matcher(andurl).matches() || StringUtils.isEmpty(andurl)) {
				//valueops.set(RedisBaseKeys.SERVER_ADDRESS_AND, andurl);
				msg = msg + ",android的请求访问地址切换到:" + andurl;
			}
	        if (StringUtils.isNotEmpty(iosVersion)) {
				//valueops.set(RedisBaseKeys.APP_VERSION_IOS, iosVersion);
				msg = msg + "。ios版本设置为:" + iosVersion + "此版本以上（不包含）访问设定的url";
			}
			if (exp.matcher(iosurl).matches() || StringUtils.isEmpty(iosurl)) {
				//valueops.set(RedisBaseKeys.SERVER_ADDRESS_IOS, iosurl);
				msg = msg + ", iso的请求访问地址切换到:" + iosurl;
			}
			return msg + "~~~更新完成！";
		}
		
		return "不要乱猜，你猜不中的！";
	}
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
}
