/*
 * PhoneNoCity.java
 *
 * Created Date: 2016年12月19日
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import net.sf.json.JSONObject;



/**
 * @author jerry.qin
 * @version  <br>
 * <p>查询手机号归属地方法类</p>
 */

public class PhoneNoCity {
	private static final Logger logger = LoggerFactory.getLogger(PhoneNoCity.class);
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 3000000;
    public static final int DEF_READ_TIMEOUT = 3000000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置申请的KEY
    public static final String APPKEY ="8ded95e9a166b272dd90492f417fcb41";
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getRequest(String phoneNo) {
    	String provinceAndCityString = "";
    	String result =null;
    	String url ="http://apis.juhe.cn/mobile/get";//请求接口地址
    	Map params = new HashMap();//请求参数
    	params.put("phone", phoneNo);//需要查询的手机号码或手机号码前7位
    	params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
    	//params.put("dtype","");//返回数据的格式,xml或json，默认json
    	try {
    		result =net(url, params, "GET");
    		JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                Map<Object, Object> map = (Map<Object, Object>) object.get("result");
                String province = (String) map.get("province");
                String city = (String) map.get("city");
                provinceAndCityString = province+","+city;
            }else{
            	logger.error("获取手机号" + phoneNo + "归属地失败");
            }
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return provinceAndCityString;
    }
    /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
   @SuppressWarnings({ "unchecked", "rawtypes" })
   public static String net(String strUrl, Map params,String method){
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setRequestProperty("User-agent", userAgent);
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                   out.writeBytes(urlencode(params));
               } catch (Exception e) {
                   e.printStackTrace();
               }
                
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
   //将map型转为请求参数型
   @SuppressWarnings("rawtypes")
   public static String urlencode(Map<String,String> data) {
       StringBuilder sb = new StringBuilder();
       for (Map.Entry i : data.entrySet()) {
           try {
               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
       return sb.toString();
   }
}
