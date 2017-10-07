/*
 * SendMailRunnable.java
 *
 * Created Date: 2016年5月25日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.util;

import java.util.HashMap;
import java.util.Map;

import com.yxlg.base.util.BusinessException;

/**
 * 
 * @author jerry.qin 2015/8/12 优化发送邮件响应时间 开线程
 * @version  <br>
 * 发邮件耗时十几秒，改为线程发送，不必等待发送结果
 * 201605025 Alisa.Yang 官网邮件统一线程发送
 */

public class SendMailRunnable  extends Thread {
	
	/**
	 * to收件人地址
	 */
	private String email = "";
	
	/**
	 * 
	 */
	private Map<String, String> varMap = new HashMap<String, String>();
	
	/**
	 * 邮件模板编号
	 */
	private String projectTemplate;

	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public void setVarMap(Map<String, String> varMap) {
		
		this.varMap = varMap;
	}
	
	public void setProjectTemplate(String projectTemplate) {
		
		this.projectTemplate = projectTemplate;
	}
	
	@Override
	public void run() {
	
		try {
			//SendService.SendMail(email, null, projectTemplate, varMap, null);
		} catch (Exception e) {
			throw new BusinessException("submail给" + email + "发送邮件异常！", e);
		}
	}
}
