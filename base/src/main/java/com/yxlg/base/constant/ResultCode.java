/*
 * C2MResultCode.java
 *
 * Created Date: 2016年5月23日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.constant;



/**
 * @author Marvin.Ma
 * @version  <br>
 * <p>接口返回码定义类</p>
 */

public class ResultCode {
	
	/**
	 * 常用的共通返回
	 */
	public final static class common {
		
		/**
		 * 操作成功
		 */
		public final static int OK = 200;
		
		/**
		 * 操作失败
		 */
		public final static int BAD_REQUEST = 400;
		
		/**
		 * 会员不存在
		 */
		public final static int NO_MEMBER = 10001;
		
		/**
		 * 国内手机号不合法
		 */
		public final static int ERROR_PHONE_NO = 10002;
		
	}
	
	
     /**
     * 手机验证码相关
     */
    public final static class PhoneCheck{
    	/**
    	 * 手机号漏传
    	 */
    	public final static Integer NO_PHONE_NO = 3101;
    	
    	/**
    	 * 验证码漏传
    	 */
    	public final static Integer NO_PHONE_CODE = 3102;
    	
    	/**
    	 * 过期
    	 */
    	public final static Integer OVERTIME = 3103;
    	
    	/**
    	 * 短信验证码错误
    	 */
    	public final static Integer ERROR_PHONE = 3104;
    	
    	/**
    	 * 图片验证码错误
    	 */
    	public final static Integer ERRROR_IMAGE_CODE = 3105;
    	/**
    	 * 手机号尚未注册
    	 */
    	public final static Integer MEMBER_UNREGISTER = 3106;
    	/**
    	 * cookie里没有imageRequestId
    	 */
		public static final int NO_COOKIE_FINDED = 3107;
		
		/**
    	 * 手机号为空
    	 */
		public static final int PHONE_NO_NULL = 3108;
    }
    
    /**
     * 手机验证码相关
     */
    public final static class wxapp{
    	
    	/**
    	 * 通过code获取sessionKey错误
    	 */
    	public final static Integer WX_SESSION_KEY_NULL = 3210;
    	
    	/**
    	 * 解密异常错误
    	 */
    	public final static Integer DECRYPT_SESSION_KEY_ERROR = 3200;
    	
    	/**
    	 * 请先获取sessionKey
    	 */
    	public final static Integer SESSION_KEY_NULL = 3201;
    	
    	/**
    	 * 授权错误
    	 */
    	public final static Integer KEY_NOT_MATCHED = 3202;
    	
    	/**
    	 * token为空
    	 */
    	public final static Integer TOKEN_NULL = 3203;
    	
    	/**
    	 * token解析错误
    	 */
    	public final static Integer TOKEN_ERROR = 3204;
    	
    	/**
    	 * 通过token找不到用户信息
    	 */
    	public final static Integer NO_DBUSER_BY_TOKEN = 3205;
    	
    }
    
}
