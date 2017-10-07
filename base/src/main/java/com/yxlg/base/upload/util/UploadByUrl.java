/*
 * UploadByUrl.java
 * 
 * Created Date: 2015年5月22日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.upload.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.yxlg.base.util.FileLoadUtil;

/**
 * @edit alison.liu
 * @version <br>
 *          <p>
 *          上传文件工具类
 *          </p>
 */

public class UploadByUrl {
	
	private static UploadByUrl uploadFile = new UploadByUrl();
	private static ExecutorService executorService = Executors.newFixedThreadPool(100);
	private static CompletionService<String> execcomp = new ExecutorCompletionService<String>(executorService);
//	static Logger logger = Logger.getLogger("UploadByUrl");
	private static final Logger logger = LoggerFactory.getLogger(UploadByUrl.class);
	
	/**
	 * Returns the Singleton instance.
	 *
	 * @return The Singleton
	 */
	public static UploadByUrl getInstance() {
	
		if (uploadFile == null)
			uploadFile = new UploadByUrl();
		return uploadFile;
	}
	
	/**
	 * 上传单个文件
	 * 
	 * @param file
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static String uploadFile(File file, String fileName) throws FileNotFoundException, InterruptedException, ExecutionException {
	
		InputStream filein = new FileInputStream(file);
		return uploadFile(filein, fileName);
	}
	
	/**
	 * 上传单个文件
	 * 
	 * @param reader
	 *            文件流
	 * @param fileName
	 *            上传之后文件名，包括文件夹名和后缀，比如\images\icon.png
	 * @return
	 */
	public static String uploadFile(InputStream reader, String fileName) throws InterruptedException, ExecutionException {
	
		String path = null;
		try {
			Future<String> execRetVal = execcomp.submit(new UploadThread(reader, fileName, QiniuUtils.QINIU_BUCKET_IMG));// 调用七牛上传接口
			String getUrl = execRetVal.get();
			if(StringUtils.isNotBlank(getUrl)){
				path = QiniuUtils.getFilePath(getUrl);// 获得返回文件完整路径
			}
		} catch (Exception e) {
			logger.error("upload file异常!, fileName:" + fileName, e);
		}
		
		return decodeQinuPath(path);
	}
	/**
	 * 上传单个文件
	 * 
	 * @param reader
	 *            文件流
	 * @param fileName
	 *            上传之后文件名，包括文件夹名和后缀，比如\images\icon.png
	 * @return
	 */
	public static String uploadPrivateFile(InputStream reader, String fileName) throws InterruptedException, ExecutionException {
	
		String path = null;
		try {
			Future<String> execRetVal = execcomp.submit(new UploadThread(reader, fileName, QiniuUtils.QINIU_BUCKET_PRIVATE));// 调用七牛上传接口
			path = QiniuUtils.getPath(QiniuUtils.QINIU_DOMAIN_PRIVATE,execRetVal.get());// 获得返回文件完整路径
		} catch (Exception e) {
			logger.error("upload file异常!, fileName:" + fileName, e);
		}
		return decodeQinuPath(path);
	}
	/**
	 * 上传图片
	 * 
	 * @param bi
	 * @param fileName
	 * @return
	 */
	public static String uploadImage(BufferedImage bi, String fileName) throws IOException, InterruptedException, ExecutionException {
	
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut;
		InputStream is;
		imOut = ImageIO.createImageOutputStream(bs);
		ImageIO.write(bi, "png", imOut);
		is = new ByteArrayInputStream(bs.toByteArray());
		return uploadFile(is, fileName);
		
	}
	
	/**
	 * 根据key获得文件，key为保存名
	 * 
	 * @param key
	 *            下载的文件名
	 * @return 返回文件路径
	 * @throws EncoderException
	 * @throws AuthException
	 */
	public static String download(String key) throws EncoderException, AuthException {
	
		return QiniuUtils.getFilePath(key);
	}
	
	/**
	 * 去掉七牛返回路径的token
	 * 
	 * @param path
	 * @return
	 */
	public static String decodeQinuPath(String path) {
	
		if (StringUtils.isEmpty(path)) {
			return path;
		}
		int index = path.indexOf("?e=");
		if (index == -1) {
			return path;
		}
		return path.substring(0, index).replace("%2F", "/");
	}
	
	/**
	 * 2016年1月30日 11:08:50
	 * 添加
	 * 根据路径获取KEY值
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	public static String findKeyForUrl(String url) throws MalformedURLException {
		// 去掉token
		url = decodeQinuPath(url);
		// http://7xj3cx.com1.z0.glb.clouddn.com/material%2Fimage%2F201506111102100
		// 去掉 http 的全路径
		URLDecoder decode = new URLDecoder();
		url = decode.decode(url);
		URL internetUrl = new URL(url);
		String name = internetUrl.getFile();
		return name.substring(1, name.length());
	}
	/**
	 * @param url 网络路径
	 * @return
	 * @throws MalformedURLException
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	public static String findHostForUrl(String url) throws MalformedURLException{
		url = decodeQinuPath(url);
		URLDecoder decode = new URLDecoder();
		url = decode.decode(url);
		URL internetUrl = new URL(url);
		String host = internetUrl.getHost();
		return host;
	}
	/**
	 * 给七牛路径
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws EncoderException
	 * @throws AuthException
	 */
	public static String getUrlBindToken(String url) throws MalformedURLException, EncoderException, AuthException{
		return QiniuUtils.getPath(UploadByUrl.findHostForUrl(url), UploadByUrl.findKeyForUrl(url));
	}
   public static void main(String[] args) throws Exception{
	  // String path = "https://img.magicmanufactory.com/images%2Fkey%2F978060f4-8c55-4f4c-874a-7a9c1f34cb64";
	   //System.out.println(UploadByUrl.getUrlBindToken("http://private.magicmanufactory.com/images%2Fkd%2F4c4d1f61-dfb7-4ebb-8831-29a5c4f73a30"));
//	   System.out.println(QiniuUtils.downPathFromPrivateDomain(UploadByUrl.findKeyForUrl(path)));
	//   System.out.println(UploadByUrl.findHostByUrl(path));
	   

   }
   public static String uploadByUrlPretty(MultipartFile file, String url){
	   return decodeQinuPath(FileLoadUtil.UploadFile(file, url));
   }
}
