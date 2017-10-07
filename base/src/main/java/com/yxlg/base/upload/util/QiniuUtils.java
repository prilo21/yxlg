/*
 * QiniuUtils.java
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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.BatchCallRet;
import com.qiniu.api.rs.Entry;
import com.qiniu.api.rs.EntryPath;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rs.URLUtils;
import com.qiniu.api.url.URLEscape;

/**
 * @author Marvin.Ma
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public abstract class QiniuUtils {
    
    // public static String QINIU_BUCKET = "c2mtest";
    // public static String QINIU_DOMAIN = "7xj3cx.com1.z0.glb.clouddn.com";
    // public static String ACCESS_KEY =
    // "zDo7y3rcnCeT61P2hPeHnWx7sDhB5GWnHdQkMHKZ"; //你的access_key
    // public static String SECRET_KEY =
    // "rLH0sjT9laA5sjTI2BbhCI98Kdt3Hhkv24GmYfIj"; //你的secret_key
    
    public static String QINIU_BUCKET_IMG = "redcollar";
    public static String QINIU_BUCKET_RESOURCES = "resources";
    public static String QINIU_BUCKET_PRIVATE = "private";
    public static String QINIU_DOMAIN = "img.magicmanufactory.com";
    public static String QINIU_DOMAIN_SOURCE = "source.magicmanufactory.com";
    public static String QINIU_DOMAIN_PRIVATE = "private.magicmanufactory.com";
    public static String ACCESS_KEY = "nyWhBA7y_0py9Kbopsi7vuZFcvhbBv5MnNe9DH0G";  // 你的access_key
    public static String SECRET_KEY = "DQNeMFYIf3WTNfuOVaTZqQpdv7l2XwHPmXgyFwJH";  // 你的secret_key
    private static Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
    
    private static final Logger logger = LoggerFactory.getLogger(QiniuUtils.class);
    
    /**
     * 上传文件
     * 
     * @param reader
     *            上传文件的字节流
     * @param fileName
     *            第二个参数是上传到七牛云之后的文件名
     * @throws org.json.JSONException
     */
    public static String upload(InputStream reader, String key, String bucket) throws AuthException, JSONException, org.json.JSONException {
    
        PutPolicy putPolicy = new PutPolicy(bucket);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.Put(uptoken, key, reader, extra);
        if (ret.ok()) {
            logger.info("QiniuUtils：Success for upload file, key:" + key);
            return key;
        } else {
            logger.error("QiniuUtils: failed! ret [statusCode:" + ret.getStatusCode() + "], exception:"+ ret.getResponse());
        }
        return "";
    }
    
    /**
     * @param key
     * @param file
     * @return
     * @throws AuthException
     * @throws JSONException
     * @throws org.json.JSONException
     */
    public static String upload(String key, File file, String bucket) throws AuthException, JSONException, org.json.JSONException {
    
        PutPolicy putPolicy = new PutPolicy(bucket);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        
        logger.info("It's uploading---" + file.getName());
        PutRet ret = IoApi.putFile(uptoken, key, file, extra);
        if (ret.ok()) {
            logger.info("QiniuUtils：Success for:" + file.getName());
        } else {
            logger.info("QiniuUtils：Failed for:" + file.getName());
        }
        return file.getName();
    }
    /**
     * 从七牛获取文件的完整地址
     * 
     * @param key
     *            文件名（可带前缀） image2/1111.png
     * @return 文件路径 e.g： http://7xj3cx.com1.z0.glb.clouddn.com/image2/1111.png
     * @throws EncoderException
     * @throws AuthException
     */
    public static String getPath(String domainName,String key) throws EncoderException, AuthException {
    	String baseUrl = "";
    	if (QINIU_DOMAIN.equals(domainName) || QINIU_DOMAIN_SOURCE.equals(domainName)) {
    		baseUrl = "https://" + domainName + "/" + URLEscape.escape(key);
    	} else {
    		baseUrl = URLUtils.makeBaseUrl(domainName, key);
    	}
        GetPolicy getPolicy = new GetPolicy();
        
        String downloadUrl = getPolicy.makeRequest(baseUrl, mac);
        
        return downloadUrl;
        
    }
    /**
     * 从七牛获取文件的完整地址
     * 
     * @param key
     *            文件名（可带前缀） image2/1111.png
     * @return 文件路径 e.g： http://7xj3cx.com1.z0.glb.clouddn.com/image2/1111.png
     * @throws EncoderException
     * @throws AuthException
     */
    public static String getFilePath(String key) throws EncoderException, AuthException {
    
        return QiniuUtils.getPath(QINIU_DOMAIN,key);
        
    }
    
    /**
     * 删除文件
     * 
     * @param key
     *            文件名（包括前缀）
     * @return
     */
    public static boolean delete(String key, String bucket) {
    
        boolean flag = false;
        
        RSClient client = new RSClient(mac);
        CallRet callRet = client.delete(bucket, key);
        flag = callRet.ok();
        if (flag) {
            logger.info("delete success, key:" + key);
        } else {
            logger.info("delete failed, statusCode:"+ callRet.getStatusCode() +", key:" + key);
        }
        return flag;
        
    }
    
    /**
     * 多个key 删除
     * 
     * @param keys
     */
    
    public static boolean batchDelete(Set<String> keys, String bucket) {
    
        if (keys == null || keys.size() == 0) {
            
            return false;
            
        }
        RSClient rs = new RSClient(mac);
        
        List<EntryPath> entries = new ArrayList<EntryPath>();
        
        for (String key : keys) {
            
            EntryPath e = new EntryPath();
            
            e.bucket = bucket;
            
            e.key = key;
            
            entries.add(e);
            
        }
        
        BatchCallRet bret = rs.batchDelete(entries);
        
        return bret.ok();
        
    }
        
    /**
     * 查看文件的属性及是否存在
     * @param key 七牛key值，如：sources/html/goods_details/details-1.ftl
     * @return  true  -  文件存在
     *          false - 文件不存在
     */
    public static boolean fileStat(String key, String bucket) {
    
        if (StringUtils.isBlank(key)) {
            return false;
        }
        
        RSClient rs = new RSClient(mac);
        Entry entry = rs.stat(bucket, key);
        // 参考七牛API, http://developer.qiniu.com/docs/v6/api/reference/rs/stat.html
//        if (612 == entry.getStatusCode()) {
        if (HttpStatus.OK.value() != entry.getStatusCode()) {
            // 612 目标资源不存在
            return false;
        }
        
        return true;
    }
   /**
    * 获得七牛上传token
    * @param bucketName 七牛空间名
    * @return
    * @throws AuthException
    * @throws org.json.JSONException
    */
    public static String uploadToken(String bucketName) throws AuthException, org.json.JSONException{
    	PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
        return uptoken;
    }
    /**
     * 上传到七牛的私有空间的token
     * @return
     * @throws org.json.JSONException 
     * @throws AuthException 
     */
    public static String upPrivateDomainToken() throws AuthException, org.json.JSONException{
    	return uploadToken(QINIU_BUCKET_PRIVATE);
    }
    public static String upPublicDomainToken() throws AuthException, org.json.JSONException{
    	return uploadToken(QINIU_BUCKET_RESOURCES);
    }
    /**
     * 下载私有空间文件路径
     * @param key
     * @return
     * @throws EncoderException
     * @throws AuthException
     */
	public static String getPathFromPrivateDomain(String key) throws EncoderException, AuthException {
		
		return QiniuUtils.getPath(QINIU_DOMAIN_PRIVATE,key);
	}
}
