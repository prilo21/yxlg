/*
 * HmacSHA1.java
 *
 * Created Date: 2016年1月29日
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

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Alison.Liu
 * @version  <br>
 * <p>HMAC-SHA1数据签名</p>
 */

public class HmacSHA1 {
	/**
	 * 秘钥算法名称
	 */
    private static final String HMAC_SHA1 = "HmacSHA1";
    /**
     * 获得HmacSHA1数字签名
     * @param encryptText 签名内容
     * @param encryptKey 秘钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException 
     * @throws UnsupportedEncodingException 
     */
    public static byte[] getSignature(String encryptText,String encryptKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
    	byte[] keybytes = encryptKey.getBytes("UTF-8");
    	//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
    	SecretKey secretKey = new SecretKeySpec(keybytes,HMAC_SHA1);
    	//生成一个指定 Mac 算法 的 Mac 对象 
    	Mac mac = Mac.getInstance(HMAC_SHA1);
    	//用给定密钥初始化 Mac 对象
    	mac.init(secretKey);
    	byte[] data = encryptText.getBytes("UTF-8");
    	return mac.doFinal(data); 
    }
    /**
     * 获得HmacSHA1数字签名，返回base64加密后的字符串
     * @param encryptText
     * @param encryptKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException 
     * @throws UnsupportedEncodingException 
     */
    public static String getSignatureBase64Encode(String encryptText,String encryptKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException{
    	byte[] data = getSignature(encryptText,encryptKey);
    	return Base64Util.encode(data);
    }
}
