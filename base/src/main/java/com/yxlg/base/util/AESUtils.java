/*
 * AESUtil.java
 *
 * Created Date: 2016年11月14日
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


/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>AES加密工具</p>
 */

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtils {
    /** 密钥算法 */
    private static final String KEY_ALGORITHM = "AES";
    
    private static final int KEY_SIZE = 128;
    
    public static final AESUtils instance = new AESUtils();

    public static boolean initialized = false;
    
    private final static Logger log = LoggerFactory.getLogger(AESUtils.class);
    
    /** 加密/解密算法/工作模式/填充方法 */
//    public static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";


    /**
     * 获取密钥
     * @return
     * @throws Exception
     */
    public static Key getKey() throws Exception{
        //实例化
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        //AES 要求密钥长度为128位、192位或256位
        kg.init(KEY_SIZE);
        //生成密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey;
    }

    /**
     * 转化密钥
     * @param key 密钥
     * @return Key 密钥
     * @throws Exception
     */
    public static Key codeToKey(String key) throws Exception{
        byte[] keyBytes = Base64.decodeBase64(key);
        SecretKey secretKey = new SecretKeySpec(keyBytes,KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static String decrypt(byte[] data,byte[] key) throws Exception{
        //还原密钥
        Key k = new SecretKeySpec(key,KEY_ALGORITHM);
        /**
         * 实例化
         * 使用PKCS7Padding填充方式，按如下方式实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        //初始化，设置解密模式
        cipher.init(Cipher.DECRYPT_MODE,k);
        //执行操作
        return new String(cipher.doFinal(data),"UTF-8");
    }

    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static String decrypt(String data,String key) throws Exception{
        return decrypt(Base64.decodeBase64(data), Base64.decodeBase64(key));
    }

    /**
     * 加密
     * @param data 待加密数据
     * @param key 密钥
     * @return bytes[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        //还原密钥
        Key k = new SecretKeySpec(key,KEY_ALGORITHM);
        /**
         * 实例化
         * 使用PKCS7Padding填充方式，按如下方式实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        //初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE,k);
        //执行操作
        return cipher.doFinal(data);
    }

    public static String encrypt(String data,String key) throws Exception{
        byte[] dataBytes = data.getBytes("UTF-8");
        byte[] keyBytes = Base64.decodeBase64(key);
        return Base64.encodeBase64String(encrypt(dataBytes, keyBytes));
    }
    
    /**
     * AES解密
     * 微信小程序始，传入iv，sessionKey，content
     * 参考链接：http://www.wxapp-union.com/article-903-1.html
     * @param content 密文
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
        	log.error("微信小程序始解密异常" + e);
        } catch (NoSuchPaddingException e) {
        	log.error("微信小程序始解密异常" + e);
        } catch (InvalidKeyException e) {
        	log.error("微信小程序始解密异常" + e);
        } catch (IllegalBlockSizeException e) {
        	log.error("微信小程序始解密异常" + e);
        } catch (BadPaddingException e) {
        	log.error("微信小程序始解密异常" + e);
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
        	log.error("微信小程序始解密异常" + e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	log.error("微信小程序始解密异常" + e);
        }
        return null;
    }

    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
    
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static String getKeyStr() throws Exception{
        return Base64.encodeBase64String(getKey().getEncoded());
    }

    /**
     * 测试加密
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception{
//        String key = "VxDksHQiTvQt9MMPtMVXdA==";
//        String wenjian = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<mainData>\n" +
//                "<config>\n" +
//                "     <operate>1</operate>                  <!--0:删除，1:新增，2:修改-->\n" +
//                "</config>\n" +
//                "<dataList type=\"personnel\">\n" +
//                "     <data id=\"员工主数据主键\">            <!--默认主数据代码-->\n" +
//                "       <code></code>                       <!--代码-->\n" +
//                "       <name></name>                       <!--姓名-->\n" +
//                "       <sex></sex>                         <!--性别-->\n" +
//                "       <birthday></birthday>               <!--出生日期-->\n" +
//                "       <education></education>             <!--文化程度-->\n" +
//                "       <idNumber></idNumber>               <!--身份证号码-->\n" +
//                "       <entryDate></entryDate>             <!--入职日期-->\n" +
//                "       <departureDate></departureDate>     <!--离职日期-->\n" +
//                "       <address></address>                 <!--住址-->\n" +
//                "       <phoneNumber></phoneNumber>         <!--电话-->\n" +
//                "       <mobilePhoneNumber></mobilePhoneNumber><!--移动电话-->\n" +
//                "       <email></email>                     <!--电子邮件-->\n" +
//                "       <position></position>               <!--职务-->\n" +
//                "       <maritalStatus></maritalStatus>     <!--婚姻状况-->\n" +
//                "       <partyAffiliation></partyAffiliation><!--政治面貌-->\n" +
//                "       <username></username>               <!--用户名-->\n" +
//                "       <sortNo></sortNo>                   <!--排序号-->\n" +
//                "       <status></status>                   <!--状态-->\n" +
//                "       <department></department>           <!--所属部门-->\n" +
//                "       <company></company>                 <!--所属公司-->\n" +
//                "    </data>\n" +
//                "</dataList>\n" +
//                "</mainData>";
//        StringBuffer buffer = new StringBuffer();
//        for(int index = 0;index < 20000;index ++){
//        	buffer.append(wenjian);
//        }
//        String jimm = buffer.toString();
//        
//        String mw = AESUtils.encrypt(jimm,key);
//        System.out.println("密文:" + mw);
//
//        String jm = AESUtils.decrypt(mw,key);
//        System.out.println("明文:" + jm);
//    }
    
    public static void main (String args[]) throws Exception{
    	String data  = "DrF/ZoIY9HctDNHkVQ7PZNdTbTW0EMFAmR0weioQ3et22CPFJIRV8gJj/RPMl18moNXpqfU/oXLEU4wr71IZl3Fbs0474zCLB6lsTF1/45dTQZsAITjaX8BmZgGPahx/sDeVQFh4TqPi7CYk52npO2Rg6lqkgDtvvZfxpPIooYhsxSjnr/BLcYLXCs5tT4Lm94Ssoro39j6MN6WmAgfA1EOp8Vr5tMaw5r7FCLo2sNWtik+IVZWnzf3e7k1bdlA6wVYzV77EsKdSsZc7WT4H3BchAhw16NkYA+jbS/TlxBE8TQC4lusNStv1236u+u1B1nevD0PZWrzny2HzcPjG7WsCF5WvZLWxCGr4YnRN6Zm+cRYaRxsZZo00PdpkAzyQRZOM3mO/vUeR8lr6/+8UBoOUKMTERGRzNLtAjEnVg0DqNb779xG8TPiVWOBouOjGG7gaW/ttP3pIxC6cpE7nJg==";
    	 String encryptedData = data;
    	    String iv = "nclALz1GTsLcnbS5O6ZRig==";
    	    String session = "PPaZvn3K+8je8xL1199tWg==";
    	    //从缓存中获取session_key
    	    //获取名称为userInfo的Redis Cache对象
//    	    Cache userInfoRedis = Redis.use("userInfo");
//    	    Object wxSessionObj =  userInfoRedis.get(session);
//    	    if(null==wxSessionObj){
//    	        renderNull();
//    	    }
//    	    String wxSessionStr = (String)wxSessionObj;
//    	    String session_key = wxSessionStr.split(",")[0];


    	    try {
    	        byte[] resultByte = AESUtils.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(session), Base64.decodeBase64(iv));
    	        if(null != resultByte && resultByte.length > 0){
    	            @SuppressWarnings("unused")
					String userInfo = new String(resultByte, "UTF-8");
    	          //  System.out.println(userInfo);
    	           // JSONObject json = JSONObject.fromObject(userInfo); //将字符串{“id”：1}
    	           // System.out.println(json);
    	        }
    	    } catch (InvalidAlgorithmParameterException e) {
    	        e.printStackTrace();
    	    } catch (UnsupportedEncodingException e) {
    	        e.printStackTrace();
    	    }
    }
    
}
