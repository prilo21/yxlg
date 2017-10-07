/*
 * DynamicSiebelHttpClientCall.java
 *
 * Created Date: 2016年12月22日
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.yxlg.base.constant.Constants;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>Siebel 端接口通用类</p>
 */

public class DynamicSiebelHttpClientCall {
	/**
	 * 初始化Log4j实例
	 */
	private static final Logger logger = LoggerFactory.getLogger("orderLogger");
	/**
	 * webservice空间名称
	 */
	private String webserviceName;
	
	/**
	 * customUI空间名称
	 */
	private String customerUI;
	
	/**
	 * 方法名
	 */
	private String soapAction;
	/**
	 * wsdl地址
	 */
	private String wsdlLocation;
    
	/**
	 * 构造方法
	 * 
	 * @param namespace
	 *            名称空间
	 * @param methodName
	 *            调用方法名
	 * @param wsdlLocation
	 *            wsdl地址
	 */
	public DynamicSiebelHttpClientCall(String webserviceName, String customerUI, String soapAction, String wsdlLocation) {
		this.webserviceName = webserviceName;
		this.customerUI = customerUI;
		this.soapAction = soapAction;
		this.wsdlLocation = wsdlLocation;
	}
	
	/**
	 * 方法调用
	 * 
	 * @param patameterMap
	 *            xml参数
	 * @throws Exception
	 */
	public Map<String, Object> invoke(Map<String, String> headerMap, Map<String, String> bodyMap) throws Exception {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(wsdlLocation);
		// 设置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Constants.FactoryConstant.TIME_OUT)
				.setConnectionRequestTimeout(Constants.FactoryConstant.TIME_OUT).setSocketTimeout(Constants.FactoryConstant.TIME_OUT).setStaleConnectionCheckEnabled(true)
				.build();
		String soapRequestData = buildRequestData(headerMap, bodyMap);
		StringEntity stringEntity = new StringEntity(soapRequestData, "utf-8");
//		logger.debug("从siebel抓取下单那用户信息,提交xml串：" + soapRequestData);
		resultMap.put("subJson", soapRequestData);
		httpPost.setEntity(stringEntity);
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("SOAPAction", "document/" + customerUI + ":" + soapAction);
		httpPost.addHeader("Content-Type","text/xml; charset=utf-8");
		httpPost.addHeader("Accept", "application/soap+xml, application/dime, multipart/related, text/*");
		httpPost.addHeader("Accept-Encoding", "");
		CloseableHttpResponse response = null;
		
		
		try {
			response = httpClient.execute(httpPost);
//			logger.info("从siebel抓取下单那用户信息,调用工厂返回值：" + response);
			// 请求状态码
			int statusCode = response.getStatusLine().getStatusCode();
			
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, statusCode);
			// 调用成功
			if (HttpStatus.OK.value() == statusCode) {
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity, "UTF-8");
				resultMap.put(Constants.FactoryConstant.KEY_INVOKE_BODY, body);
			}
		} catch (Exception e) {
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, HttpStatus.BAD_REQUEST);
			logger.error("调用Siebel接口失败,地址【" + wsdlLocation + "】,提交xml串【"+soapRequestData+"】", e);
			throw e;
		}
		return resultMap;
	}
	
	private String buildRequestData(Map<String, String> headerMap, Map<String, String> bodyMap) {
		
		StringBuffer soapRequestData = new StringBuffer();
		
		soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		soapRequestData.append("<soapenv:Envelope xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/encoding' xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" + " xmlns:web=\"" + webserviceName + "\""
				+ " xmlns:cus=\"" +  customerUI + "\""
				+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		soapRequestData.append("<soapenv:Header>");
		Set<String> headerSet = headerMap.keySet();
		for (String name : headerSet) {
			if (StringUtils.isEmpty(headerMap.get(name))) {
				soapRequestData.append("<" + name + " />");
			} else {
				soapRequestData.append("<" + name + ">" + headerMap.get(name) + "</" + name + ">");
			}
		}
		soapRequestData.append("</soapenv:Header>");
		soapRequestData.append("<soapenv:Body>");
		soapRequestData.append("<cus:" + soapAction + "_Input>");
		
		Set<String> bodySet = bodyMap.keySet();
		for (String name : bodySet) {
			if (StringUtils.isEmpty(bodyMap.get(name))) {
				soapRequestData.append("<" + name + " />");
			} else {
				soapRequestData.append("<" + name + ">" + bodyMap.get(name) + "</" + name + ">");
			}
		}
		
		soapRequestData.append("</cus:" + soapAction + "_Input>");
		soapRequestData.append("</soapenv:Body>");
		soapRequestData.append("</soapenv:Envelope>");
		
		return soapRequestData.toString();
	}
	
}
