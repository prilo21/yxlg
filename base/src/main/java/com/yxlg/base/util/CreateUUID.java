/*
 * TestUUID.java
 *
 * Created Date: 2015年8月12日
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

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* Java的UUID生成工具并发测试 
* 
* @author Marvin.Ma 2015-8-12 16:25:13 
*/ 
public class CreateUUID implements Runnable { 
        public static void main(String[] args) { 
                ExecutorService pool = Executors.newFixedThreadPool(5); 
                for (int i = 0; i < 1; i++) { 
                        pool.execute(new CreateUUID()); 
                } 
                pool.shutdown(); 
                
        } 

        public static String genReqID() { 
                return UUID.randomUUID().toString(); 
//                return UUID.randomUUID().toString().toUpperCase(); 
        } 

        public void run() { 
//        	String urlString = "http://www.magicmanufactory.com/c2mwebservice/api/v1/public/salesPromotion/9QKU7RRNAC/";
//        	String aString = "";
//                for (int i = 0; i < 500; i++){
//                	String uuid = genReqID();
//                	    aString += uuid + ",";
//                        System.out.println(urlString + uuid); 
//                } 
//                System.out.println(aString);
        } 
        
}
