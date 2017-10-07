/*
 * DESUtils.java
 *
 * Created Date: 2016年6月7日
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

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.yxlg.base.constant.Constants;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


/**
 * @author dirk
 * @version  <br>
 * <p>DES加密工具类</p>
 */
public class DESUtils {
	 /**
     * <ul>
     * <li>Description:[根据流得到密钥]</li>
     * <li>Created by [Huyvanpull] [Jul 19, 2010]</li>
     * <li>Midified by [修改人] [修改时间]</li>
     * </ul>
     *
     * @param is
     * @return
     */
	public static Key getKey(InputStream is) {
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			return (Key) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("读取文件失败",e);
		}
	}
	
	public static Key getKey(String keyStr) {
		Key key = null;
		try {
    		KeyGenerator generator = KeyGenerator.getInstance("DES");  
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");  
            secureRandom.setSeed(keyStr.getBytes());  
            generator.init(secureRandom);  
            key = generator.generateKey(); 
            generator=null;  
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return key;
	}
	
	/** 
     * 对字符串进行加密，返回BASE64的加密字符串 
     * <功能详细描述> 
     * @param str 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
	public static String getEncryptString(String str, Key key) {
	
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			byte[] strBytes = str.getBytes(Constants.encoding.ENCODING_UTF_8);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64Encoder.encode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException("字符串加密失败",e);
		}
		
	}
      
    /** 
     * 对BASE64加密字符串进行解密 
     * <功能详细描述> 
     * @param str 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
	public static String getDecryptString(String str, Key key) {
	
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] strBytes = base64Decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(encryptStrBytes, Constants.encoding.ENCODING_UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("字符串解密失败" + e.getMessage() + ";str:" + str + "; key:" + key, e);
		}
	}
	
	public static void main(String[] args) {
	
//		System.out.println(getEncryptString("",getKey("U{Im8}L1JhPB-Uf")));
		System.out.println(getEncryptString("testuser",getKey("U{Im8}L1JhPB-Uf")));
	}
}