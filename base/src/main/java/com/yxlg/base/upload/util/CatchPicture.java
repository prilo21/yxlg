/*
 * CatchPicture.java
 *
 * Created Date: 2015年6月10日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.upload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yxlg.base.util.BusinessException;


/**
 * @author alison.liu
 * @version  <br>
 * <p>从网页下下载图片</p>
 */

public class CatchPicture {
	
	private static final Logger logger = LoggerFactory.getLogger(CatchPicture.class);
	
	/**
	 * 下载保存网络图片
	 * @param imgSrc
	 * @param filePath
	 * @param fileName
	 */
	public static void downloadImgByNet(String imgSrc, String filePath, String fileName){
		try{
			URL url = new URL(imgSrc);
			URLConnection conn = url.openConnection();
			//设置超时时间3秒
			conn.setConnectTimeout(3*1000);
			//防止屏蔽程序抓取而返回403错误  
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream in = conn.getInputStream();//输出流
			 //控制流的大小为1k  
            byte[] bs = new byte[1024];  
            //读取到的长度  
            int len = 0;  
            //是否需要创建文件夹  
            File saveDir = new File(filePath);    
            if(!saveDir.exists()){    
                saveDir.mkdir();    
            }    
            File file = new File(saveDir+File.separator+fileName);     
            //实例输出一个对象  
            FileOutputStream out = new FileOutputStream(file);  
            //循环判断，如果读取的个数b为空了，则is.read()方法返回-1，具体请参考InputStream的read();  
            while ((len = in.read(bs)) != -1) {  
                //将对象写入到对应的文件中  
                out.write(bs, 0, len);     
            }  
            out.flush();  
            out.close();  
            in.close();  
		} catch (Exception e){
			throw new BusinessException("", e);
		}
	}
	/**
	 * 网络图片生成字节流
	 * @param imgSrc
	 * @return
	 */
	public static InputStream downloadImgByNet(String imgSrc){
		InputStream in = null;
		try{
			if (GenericValidator.isUrl(imgSrc)){
				URL url = new URL(imgSrc);
				URLConnection conn = url.openConnection();
				//设置超时时间3秒,2017-02-08 alisa.yang 由于第三方微信图片下载网络超时异常，所以将超时时间3秒设为6秒
				conn.setConnectTimeout(6*1000);
				//防止屏蔽程序抓取而返回403错误  
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				
				//2017-02-08 alisa.yang
				Long startTime = System.currentTimeMillis();
				in = conn.getInputStream();
				logger.info("下载图片成功，读取时间：" + (System.currentTimeMillis() - startTime) + "ms, url:" + imgSrc);
				
			}
		} catch (Exception e) {
			throw new BusinessException("下载图片异常，url:"+imgSrc, e);
		}
		return in;
	}
}
