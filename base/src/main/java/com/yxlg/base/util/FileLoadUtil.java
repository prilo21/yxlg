/*
 * FileLoadUtil.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.upload.util.QiniuUtils;
import com.yxlg.base.upload.util.UploadByUrl;

/**
 * @author Alisa.Yang
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public class FileLoadUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FileLoadUtil.class);
	
	/**
	 * Stone.Cai
	 * 2015年5月27日 09:40:19
	 * 添加
	 * 图片上传到七牛上
	 * 
	 * @throws IOException
	 */
	public static String UploadFile(MultipartFile file, String path) {
	
		String path1 = "";
		try {
			path1 = uploadInputStream(file.getInputStream(), path);
		} catch (IOException e) {
			log.error("FileLoadUtil->UploadFile 异常！", e);
		}
		if (path1 != null ) {
			path1.replaceAll("%2F", "/");
        }
		return path1;
	}
	
	public static String uploadFile(File file, String path) {
	
		String path1 = "";
		try {
			path1 = uploadInputStream(new FileInputStream(file), path);
		} catch (FileNotFoundException e) {
			log.error("FileLoadUtil->UploadFile FileNotFoundException异常！", e);
		}
		if (path1 != null ) {
			path1.replaceAll("%2F", "/");
        }
		return path1;
	}
	
	@SuppressWarnings("static-access")
	public static String uploadInputStream(InputStream inputStream, String path) {
	
		UploadByUrl qiniuUpload = UploadByUrl.getInstance();
		String url = "";
		try {
			url = qiniuUpload.uploadFile(inputStream, path);
		} catch (InterruptedException | ExecutionException e) {
			log.error("qiniuUpload.uploadFile异常", e);
		}
		if (url != null ) {
			url.replaceAll("%2F", "/");
        }
		return url;
	}
	
	/**
	 * 上传图片
	 * 
	 * @param file
	 * @param fileName
	 * @param targetFilePath
	 *            目标路径
	 * @param delSameFile
	 *            是否需要删除同名文件，默认true需要删除
	 * @param delFileStartName
	 *            需要删除的匹配文件名
	 * @return
	 */
	public static String UploadFile(MultipartFile file, String fileName, String targetFilePath, boolean delSameFile, String delFileStartName) {
	
		// 首先判断文件夹下是否需要删除"同名"的文件（此处模糊匹配），如需要则删除
		if (delSameFile) {
			deleteFileByFuzzyMatch(targetFilePath, delFileStartName);
		}
		// 新建文件然后保存
		File targetFile = new File(targetFilePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			log.error("IllegalStateException 异常！", e);
		} catch (IOException e) {
			log.error("IOException 异常！", e);
		}
		
		return null;
	}
	
	/**
	 * 删除文件，文件夹目录下的所有文件，进行模糊匹配，如果可以匹配，则删除该文件
	 * 
	 * @param path
	 *            文件所在目录，不包括文件名
	 * @param samename
	 *            需要迷糊匹配的文件名，根据文件名字从第一个字符开始匹配
	 * @return
	 */
	public static boolean deleteFileByFuzzyMatch(String path, String samename) {
	
		boolean isDel = false;
		File file = new File(path);
		// 是个目录并且是个文件夹，不是直接去匹配的文件
		if (file.exists() && file.isDirectory()) {
			File[] tempFile = file.listFiles();
			for (File file2 : tempFile) {
				if (file2.isFile() && file2.getName().startsWith(samename)) {
					file2.delete();
					isDel = true;
				}
			}
		}
		return isDel;
	}
	
	/**
	 * 根据文件的目录，直接删除文件
	 * 
	 * @param path
	 *            文件所在的目录，包括文件目录和文件名
	 * @return
	 */
	public static boolean deleteFileBypath(String path) {
	
		boolean isDel = false;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
			isDel = true;
		}
		return isDel;
	}
	
	/**
	 * @author Alison.liu
	 *         为上传的文件生成文件名
	 * @param name
	 * @return
	 */
	public static String encodeName() {
	
		return encodeName(null);
	}
	
	/**
	 * @author Alison.liu
	 *         为上传的文件生成文件名
	 * @param name
	 *            文件的分类
	 * @return
	 */
	public static String encodeName(String name) {
	
		// SimpleDateFormat formatter = new
		// SimpleDateFormat(C2MConstants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
		// int ram = (int)Math.random()*100000;
		String filename = "";
		if (name != null) {
			filename = name + UUID.randomUUID().toString();
		}
		return filename;
	}
	
	/**
	 * Stone.Cai
	 * 2015年6月11日 11:17:28
	 * 添加
	 * 根据KEY 删除7牛上的图片
	 */
	public static boolean deletFileByPath(String url) {
	
		if (!Constants.isTestServer() && Constants.isOfficialServer()) {
			// 在正式服务器上，删除七牛key，
			String key = "";
			try {
				key = UploadByUrl.findKeyForUrl(url);
			} catch (MalformedURLException e) {
				log.error("删除url:" + url + ",MalformedURLException 异常！", e);
			} // 获取key
			return QiniuUtils.delete(key, QiniuUtils.QINIU_BUCKET_IMG);
		} else {
			// 在测试服务器不必删除，以免影响正式库存储的七牛数据错误问题
			return true;
		}
	}
	
	public static String getNameByRemoteURL(String remoteUrl) {
	
		String[] urlname = remoteUrl.split("/");
		String uname = urlname[urlname.length - 1];// 获取文件名
		return uname;
	}
	
	/**
	 * 下载远程文件
	 * 
	 * @param remoteUrl
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void exportFile(String remoteUrl, String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		response.reset();
		URL url = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			url = new URL(remoteUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			in = conn.getInputStream();
			response.setContentType("multipart/form-data");  // 设置文件ContentType类型（自动判断下载文件类型）
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\""); // 设置文件头，最后一个参数是设置下载文件名
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-store");
			
			out = response.getOutputStream();
			byte[] content = new byte[1024];
			int length;
			while ((length = in.read(content, 0, content.length)) != -1) {
				out.write(content, 0, length);
			}
			in.close();
			out.flush();
			out.close();
			response.flushBuffer();
		} catch (IOException e) {
			log.error("IOException 异常！", e);
		} finally {
			if (null != in)
				in.close();
			if (null != out)
				out.close();
		}
	}
	
    /**
     * 
     * @param newUrlKey 新的路径的url的主要key
     * @param oldUrl 原文件路径
     * @param file 需要上传的文件
     * @param deleteFlag 是否删除原文件
     * @return
     */
    public static String uploadImgFileToQiniu(String newUrlKey, String oldUrl, MultipartFile file, String deleteFlag) {
    
        if (StringUtils.isNotBlank(deleteFlag) && StringUtils.isNotBlank(oldUrl)) {
            if (FileLoadUtil.deletFileByPath(oldUrl)) {
                oldUrl = "";
            }
        }
        
        String url = oldUrl;
        if (file != null && !file.isEmpty() && StringUtils.isNotBlank(newUrlKey)) {
            StringBuffer fileName = new StringBuffer(newUrlKey);
            String uuid = FileLoadUtil.encodeName("");
            fileName.append(uuid);
            
            // 更换文件的名字，去除有中文或者其他语言的的影响
            String originalFilename = file.getOriginalFilename();
            String filesuffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            fileName.append(filesuffix);
            
            String newUrl = FileLoadUtil.UploadFile(file, fileName.toString());
            if (StringUtils.isNotBlank(newUrl)) {
                url = newUrl;
            }
            
            url = UploadByUrl.decodeQinuPath(url);
        }
        
        return url;
    }
    
    /**
     * 获取文件扩展名 
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int dot = filename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (filename.length() - 1))) { 
                return filename.substring(dot + 1); 
            } 
        } 
        return filename; 
    }
}
