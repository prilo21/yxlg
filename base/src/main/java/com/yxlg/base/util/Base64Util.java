/*
 * Base64Util.java
 *
 * Created Date: 2015年5月27日
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

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import java.io.IOException;

/**
 * @author alison.liu
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public class Base64Util {
	
	public static String encode(byte[] bstr){   
		BASE64Encoder encoder = new BASE64Encoder();
		   return encoder.encode(bstr);    
	}
	
	public static byte[] decodeBase64ToByte(String imgStr) throws IOException {// 对字节数组字符串进行Base64解码为byte数组
		
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		return bytes;
	}

	/*public static void main(String [] args){

		String wen = "11111111111111111111111111111111111111,22222222222222222222222222222,333333333333333333333333333,44444444444444444444,44444444444444444444,44444444444444asddddddddddssssssssddddd";
		System.out.println (encode(wen.getBytes ()));
	}*/
	
}
