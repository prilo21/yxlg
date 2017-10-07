/*
 * SiebelXMLTest.java
 *
 * Created Date: 2016年12月29日
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

import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>类的描述</p>
 */

public class SiebelXMLTest {
	
	private static final Logger logger = LoggerFactory.getLogger("orderLogger");
    static int socketTimeout = 30000;// 请求超时时间  
    static int connectTimeout = 30000;// 传输超时时间  
    
	public static String doPostSoap1_1(String postUrl, String soapXml,  
            String soapAction) {  
        String retStr = "";  
        // 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpPost httpPost = new HttpPost(postUrl);  
                //  设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(socketTimeout)  
                .setConnectTimeout(connectTimeout).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");  
            httpPost.setHeader("SOAPAction", soapAction);  
            StringEntity data = new StringEntity(soapXml,  
                    Charset.forName("UTF-8"));  
            httpPost.setEntity(data);  
            CloseableHttpResponse response = closeableHttpClient  
                    .execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();
			
			// 调用成功
			if (HttpStatus.OK.value() == statusCode) {
				HttpEntity entity = response.getEntity();
				@SuppressWarnings("unused")
				String body = EntityUtils.toString(entity);
				//System.out.println(body);
			}
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {  
            logger.error("exception in doPostSoap1_1", e);  
        }  
        return retStr;  
    }  
	
	public static void main(String args[]) {
		String soapXml = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:web='http://siebel.com/webservices' xmlns:cus='http://siebel.com/CustomUI' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><soapenv:Header><web:UsernameToken>CRMINTF</web:UsernameToken><web:PasswordText>CRMINTF</web:PasswordText><web:SessionType>None</web:SessionType><Accept>application/xml</Accept><web:SessionToken /></soapenv:Header><soapenv:Body><cus:KTS_spcDistribution_spcManager_spcOrder_spcWorkflow_1_Input><cus:account_spcCountry>0</cus:account_spcCountry><cus:end_spcDate /><cus:start_spcDate>11/05/2016 12:00:00</cus:start_spcDate></cus:KTS_spcDistribution_spcManager_spcOrder_spcWorkflow_1_Input></soapenv:Body></soapenv:Envelope>";
		@SuppressWarnings("unused")
		String result = doPostSoap1_1("http://219.146.80.197:9090/com.kutesmart.esb.siebel.Distribution_mg?wsdl", soapXml, "document/http://siebel.com/CustomUI:KTS_spcDistribution_spcManager_spcOrder_spcWorkflow_1");
		//System.out.println(result);
	}
}
