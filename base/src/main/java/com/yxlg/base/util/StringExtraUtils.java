/*
 * StringExtraUtils.java
 *
 * Created Date: 2015年6月26日
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

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Ted.Ma
 * @version <br>
 *          <p>
 *          字符串补充工具类
 *          </p>
 */

public class StringExtraUtils {
	
	/**
	 * 校验一个字符串是否为空或值为0(包含0,0.0,0.00.....)<br>
	 * 
	 * @param target
	 * @return
	 * 
	 */
	public static boolean isEmptyOrZero(String target) {
		
		boolean r1 = StringUtils.isBlank(target);
		if (!r1) {
			try {
				double d = Double.parseDouble(target);
				return d == 0 ? true : false;
			} catch (Exception e) {
				return false;
			}
		}
		return r1;
	}
	
	/**
	 * 校验一个字符串不为空和0(包含0,0.0,0.00.....)<br>
	 * 
	 * @param target
	 * @return
	 * 
	 */
	public static boolean isNotEmptyAndZero(String target) {
		
		return !isEmptyOrZero(target);
	}
	/**
	 * 四舍五入
	 * @param num 带四舍五入的数据
	 * @param formatNum 保留小数的位数
	 * @return
	 */
	public static double round(double num,int formatNum ){
		StringBuffer format=new StringBuffer("#.");
		for(int i=0;i<formatNum;i++){
			format.append("0");
		}
		DecimalFormat d_f = new DecimalFormat(format.toString());
		return Double.parseDouble(d_f.format(num));
	}
}
