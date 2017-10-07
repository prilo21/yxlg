/*
 * ApiHttpClientUtils.java
 *
 * Created Date: 2017年3月5日
 *				
 * Copyright (c)  ydkj Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * ydkj Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * ydkj Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author marvin
 * @date 2017年3月5日  下午20:43:31
 * @Description: 第三方restfull http接口调用工具类
 * @version: 1.0
 */

public class RestHttpClientUtils {
	
	private static final Logger log = LoggerFactory.getLogger(RestHttpClientUtils.class);
	
	private static final String charset = "UTF-8";
	//初始化HttpClient
	private static HttpClient httpClient = new DefaultHttpClient();  
	
    /** 
     * POST方式调用 
     *  
     * @param url 
     * @param headers 参数值数组
     * @param params 参数为NameValuePair键值对对象 
     * @return 响应字符串 
     * @throws IOException 
     * @throws java.io.UnsupportedEncodingException 
     */ 
	public String executeByPOST(String url, Map<String, String> headers, List<NameValuePair> parameters) throws Exception {
		HttpPost post = new HttpPost(url);
		
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			//处理header
			if (headers != null && !headers.isEmpty()) {  
				for (Entry<String, String> entry : headers.entrySet()) {  
					post.addHeader(entry.getKey(), entry.getValue());  
				}  
			}
			//处理其他参数
			if(parameters != null) {
				post.setEntity(new UrlEncodedFormEntity(parameters, charset));
			}
			
			responseJson = httpClient.execute(post, responseHandler);
			log.info("HttpClient POST请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
	        log.info("HttpClient POST请求ClientProtocolException异常：" + e.getMessage());
        } catch (IOException e) {
        	log.info("HttpClient POST请求IOException异常：" + e.getMessage());
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && httpClient != null&& httpClient instanceof CloseableHttpClient) {
                ((CloseableHttpClient) httpClient).close();
            }
        }
        return responseJson;
	}
	
	/**
	 * 添加POST内容为jsonStr的方法
	 */
	public String executeJsonStrByPOST(String url, Map<String, String> headers, String jsonStr) throws Exception {
		HttpPost post = new HttpPost(url);
		
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			//处理header
			if (headers != null && !headers.isEmpty()) {  
				for (Entry<String, String> entry : headers.entrySet()) {  
					post.addHeader(entry.getKey(), entry.getValue());  
				}  
			}
			//处理其他参数
			StringEntity se = new StringEntity(jsonStr);
			se.setContentType("application/json");
			se.setContentEncoding(charset);
			post.setEntity(se);
            
			responseJson = httpClient.execute(post, responseHandler);
			log.info("HttpClient POST By JsonStr请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
	        log.info("HttpClient POST By JsonStr请求ClientProtocolException异常：" + e.getMessage());
        } catch (IOException e) {
        	log.info("HttpClient POST By JsonStr请求IOException异常：" + e.getMessage());
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && httpClient != null&& httpClient instanceof CloseableHttpClient) {
                ((CloseableHttpClient) httpClient).close();
            }
        }
        return responseJson;
	}
	
	
	/** 
     * Get方式请求 
     *  
     * @param url 带参数占位符的URL，例：http://ip/User/user/center.aspx?_action=GetSimpleUserInfo&codes={0}&email={1} 
     * @param headers 参数值数组
     * @param params 参数值数组，需要与url中占位符顺序对应 
     * @return 响应字符串 
	 * @throws Exception 
     * @throws java.io.UnsupportedEncodingException 
     */  
    public String executeByGET(String url, Map<String, String> headers, List<NameValuePair> params) throws Exception {
    	String body = null;
    	HttpGet httpget = new HttpGet(url);
    	if (params != null) {
    		String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
    		httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
    	}
        
		//处理header
		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
        try {
        	//执行
        	HttpResponse httpresponse = httpClient.execute(httpget);
        	// 获取返回数据 
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            log.info("HttpClient GET请求结果：" + body);
        } catch (ClientProtocolException e) {
            log.error("HttpClient GET请求ClientProtocolException异常：" + e.getMessage());
        } catch (IOException e) {
            log.error("HttpClient GET请求IOException异常：" + e.getMessage());  
        } finally {
        	httpget.releaseConnection();
            if (url.startsWith("https") && httpClient != null&& httpClient instanceof CloseableHttpClient) {
                ((CloseableHttpClient) httpClient).close();
            }
        }
        return body;
    }
  
}
