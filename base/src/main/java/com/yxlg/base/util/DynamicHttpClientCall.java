/*
 * DynamicHttpclientCall.java
 * 
 * Created Date: 2015年5月19日
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxlg.base.constant.Constants;

/**
 * @author dirk
 * @version <br>
 *          <p>
 *          工厂端接口通用类
 *          </p>
 */

public class DynamicHttpClientCall {
	
	/**
	 * 初始化Log4j实例
	 */
	private static final Logger logger = LoggerFactory.getLogger("orderLogger");
	/**
	 * 名称空间
	 */
	private String namespace;
	/**
	 * 方法名
	 */
	private String methodName;
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
	public DynamicHttpClientCall(String namespace, String methodName, String wsdlLocation) {
	
		this.namespace = namespace;
		this.methodName = methodName;
		this.wsdlLocation = wsdlLocation;
	}
	
	/**
	 * 方法调用
	 * 
	 * @param patameterMap
	 *            xml参数
	 * @throws Exception
	 */
	public Map<String, Object> invoke(Map<String, String> patameterMap) throws Exception {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(wsdlLocation);
		// 设置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Constants.FactoryConstant.TIME_OUT)
				.setConnectionRequestTimeout(Constants.FactoryConstant.TIME_OUT).setSocketTimeout(Constants.FactoryConstant.TIME_OUT).setStaleConnectionCheckEnabled(true)
				.build();
		String soapRequestData = buildRequestData(patameterMap);
		// Document document = DocumentHelper.parseText(soapRequestData);
		// String emp=document.asXML();
		StringEntity stringEntity = new StringEntity(soapRequestData, "utf-8");
		logger.info("访问工厂WSDL地址为：" + wsdlLocation + ",调用方法为：" + methodName);
		logger.info("提交工厂xml串：" + soapRequestData );
		resultMap.put("subJson", soapRequestData);
		httpPost.setEntity(stringEntity);
		httpPost.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(httpPost);
			logger.info("调用工厂返回值：" + response);
			// 请求状态码
			int statusCode = response.getStatusLine().getStatusCode();
			
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, statusCode);
			// 调用成功
			if (HttpStatus.OK.value() == statusCode) {
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				resultMap.put(Constants.FactoryConstant.KEY_INVOKE_BODY, body);
			}
		} catch (Exception e) {
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, HttpStatus.BAD_REQUEST);
			logger.error("调用工厂接口失败，调用方法名称为【"+methodName+"】", e);
			throw e;
		}
		return resultMap;
	}
	
	/**
	 * 方法调用
	 * 
	 * @param patameterMap
	 *            xml参数
	 * @throws Exception
	 */
	public Map<String, Object> invoke(Map<String, String> patameterMap, Integer timeout) throws Exception {
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(wsdlLocation);
		// 设置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout)
				.setStaleConnectionCheckEnabled(true).build();
		String soapRequestData = buildRequestData(patameterMap);
		StringEntity stringEntity = new StringEntity(soapRequestData, "utf-8");
		logger.info("提交工厂xml串：" + soapRequestData);
		httpPost.setEntity(stringEntity);
		
		httpPost.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(httpPost);
			logger.info("调用工厂返回值：" + response);
			// 请求状态码
			int statusCode = response.getStatusLine().getStatusCode();
			
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, statusCode);
			// 调用成功
			if (HttpStatus.OK.value() == statusCode) {
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				resultMap.put(Constants.FactoryConstant.KEY_INVOKE_BODY, body);
			}
		} catch (Exception e) {
			resultMap.put(Constants.FactoryConstant.KEY_INVOKE_STATUS, HttpStatus.BAD_REQUEST);
			logger.error("调用工厂接口失败，调用方法名称为【"+methodName+"】", e);
			throw e;
		}
		return resultMap;
	}
	
	private String buildRequestData(Map<String, String> patameterMap) {
	
		StringBuffer soapRequestData = new StringBuffer();
		
		soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" + " xmlns:ns0=\"" + namespace + "\""
				+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		soapRequestData.append("<soapenv:Body>");
		soapRequestData.append("<ns0:" + methodName + ">");
		
		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name) + "</" + name + ">");
		}
		
		soapRequestData.append("</ns0:" + methodName + ">");
		soapRequestData.append("</soapenv:Body>");
		soapRequestData.append("</soapenv:Envelope>");
		
		return soapRequestData.toString();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		String s="{\"goods\":[{\"amount\":2,\"code\":\"KXK0000DBQ693AMDT\",\"price\":5000.00,\"unit\":\"TIAO"
				+"}],\"moneytype\":\"RMB\",\"name\":\"C2M\",\"storesCode\":\"C2MC2M00\",\"warehouse\":\"2047\"}";
		
		String ss="{\"kckjnd\":2016,\"kckjyf\":7,\"kcczxh\":290005,\"xtxphm\":\"160713160420\",\"kcfkbz\":\"\",\"fkeym\":\"\""
				+ ",\"kcckdm\":\"K531T007\",\"xsrydm\":\"张三\",\"kcczrq\":\"2016-07-13 15:14:34\",\"khhybh\":\"\","
				+ "\"kcczry\":\"7665VC\",\"xtcxxp\":\"\",\"xtxsdm\":\"\",\"xthbdm\":\"RMB\",\"xtdhhl\":1,\"fckname\":"
				+ "\"济南市历下区泉城路180号\",\"fusername\":\"张三\",\"fdo\":0,\"fdate\":\"2016-07-13 13:25:46\",\"fhbname\":"
				+ "\"人民币\",\"u8Xs032s\":[{\"fshije\":678,\"kckjnd\":2016,\"kckjyf\":7,\"kcczxh\":290005,"
				+ "\"xtxphm\":\"160713160420\",\"kcfkhh\":1,\"kcfkfs\":\"1\",\"kcxsje\":678,\"kcfkbz\":\"\",\"fkeym\":\"\","
				+ "\"ffsname\":\"现金\",\"fzk\":678,\"fnote\":\"\",\"fdate\":\"2016-07-13 13:36:56\",\"fdo\":0}]}";
		
		Map<String,String> all_arg0=new HashMap<String, String>();
		all_arg0.put("arg0", ss);// doAutoDeliveryByJson
		DynamicHttpClientCall dynamicHttpClientCall = new DynamicHttpClientCall("http://services.redcollar.cn/", "doSaveU8Xs031",
				"http://218.58.54.236:8080/services/FyerpService?wsdl");
		try {
			Map<String, Object> result = dynamicHttpClientCall.invoke(all_arg0);
			Document doc = DocumentHelper.parseText((String) (result.get(Constants.FactoryConstant.KEY_INVOKE_BODY)));
			Element root = doc.getRootElement();
			Element element = (Element) root.selectNodes("//return").get(0);
			ObjectMapper mapper = new ObjectMapper();
			Map<?, ?> m = mapper.readValue(element.getTextTrim().replace("[", "").replace("]", ""), HashMap.class);
//			System.out.println(m);
		} catch (Exception e) {
			throw new BusinessException("", e);
		}
		
	}
}