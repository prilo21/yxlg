/*
 * ShortenUrl2.java
 *
 * Created Date: 2016年12月1日
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
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>短链接</p>
 */

public class ShortenUrl {
	
	/**
	 * 让dwz.cn生成长连接对应的短连接
	 * @param longUrl
	 * @return
	 */
	public static String createShortUrl(String longUrl){
		String json = sendGet("http://api.t.sina.com.cn/short_url/shorten.json", "source=3271760578&url_long=" + longUrl);
		String parms = json.replace("\"", "").replace("\\", "");
//		System.out.println(json);
		if(parms.contains("type:0")){
			return parms.substring(parms.indexOf("http:"), parms.indexOf(",", parms.indexOf("http:")));
		}else
			return "error";
	}
	
	/**
	 * get传递参数
	 **/
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
//            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
//            Map<String, List<String>> map = connection.getHeaderFields();
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            //System.out.println("发送GET请求出现异常！" + e);
          //  e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                //e2.printStackTrace();
            }
        }
        return result;
    }
    
	
	public static void main(String[] args) {
	//	System.out.println(createShortUrl("https://source.magicmanufactory.com/html/qrcode/new/oneqrcode/index.html?id=ca6f34a2649a4c85aa0b05ed9ec26fc3"));
	}
}
