/*
 * OrderNoUtil.java
 * 
 * Created Date: 2015年6月4日
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.Stack;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.yxlg.base.constant.Constants;

/**
 * @author dirk
 * @version <br>
 *          <p>
 *          订单工具类
 *          </p>
 */

public class OrderNoUtil {
	
	private static char[] charSet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();
private static char[] charSet2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	
	/**
	 * 将10进制转化为62进制
	 * 
	 * @param number
	 * @param length
	 *            转化成的62进制长度，不足length长度的话高位补0，否则不改变什么
	 * @return
	 */
	public static String _10_to_62(long number, int length) {
	
		Long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			stack.add(charSet2[new Long((rest - (rest / 62) * 62)).intValue()]);
			rest = rest / 62;
		}
		for (; !stack.isEmpty();) {
			result.append(stack.pop());
		}
		int result_length = result.length();
		StringBuilder temp0 = new StringBuilder();
		for (int i = 0; i < length - result_length; i++) {
			temp0.append('0');
		}
		
		return temp0.toString() + result.toString();
		
	}
	/**
	 * 将10进制转化为32进制
	 * 
	 * @param number
	 * @param length
	 *            转化成的32进制长度，不足length长度的话高位补0，否则不改变什么
	 * @return
	 */
	public static String _10_to_32(long number, int length) {
	
		Long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			stack.add(charSet[new Long((rest - (rest / 32) * 32)).intValue()]);
			rest = rest / 32;
		}
		for (; !stack.isEmpty();) {
			result.append(stack.pop());
		}
		int result_length = result.length();
		StringBuilder temp0 = new StringBuilder();
		for (int i = 0; i < length - result_length; i++) {
			temp0.append('0');
		}
		
		return temp0.toString() + result.toString();
		
	}
	
	/**
	 * 将62进制转换成10进制数
	 * 
	 * @param ident62
	 * @return
	 */
	public static String convertBase62ToDecimal(String ident62) {
	
		long decimal = 0;
		long base = 62;
		long keisu = 0;
		long cnt = 0;
		
		byte ident[] = ident62.getBytes();
		for (int i = ident.length - 1; i >= 0; i--) {
			int num = 0;
			if (ident[i] > 48 && ident[i] <= 57) {
				num = ident[i] - 48;
			} else if (ident[i] >= 65 && ident[i] <= 90) {
				num = ident[i] - 65 + 10;
			} else if (ident[i] >= 97 && ident[i] <= 122) {
				num = ident[i] - 97 + 10 + 26;
			}
			keisu = (long) java.lang.Math.pow((double) base, (double) cnt);
			decimal += num * keisu;
			cnt++;
		}
		return String.format("%08d", decimal);
	}
	
	/**
	 * 获取订单号
	 * 
	 * @return
	 *         生成的订单号
	 */
	public static String getOrderNo() {
	
			// 得到当前时间
			long currentTime = System.currentTimeMillis();
			
			// 生成两位随机整数
			int randomNo = (int) (Math.random() * 900) + 100;
			
			// 得到订单流水号
			Long serialNo = Long.parseLong(String.valueOf(currentTime - 1433000000000L) + randomNo);
			
			// 返回生成的订单号
			return Constants.FactoryConstant.ORDER_PREFIX + _10_to_62(serialNo, 8);
		}
	
	/**
	 * 获取订单号
	 * 
	 * @return
	 *         生成的订单号
	 */
	public static String getOrderNo(String orderType) {
	
		// 得到当前时间
		long currentTime = System.currentTimeMillis();
		/*
		 * Calendar c=Calendar.getInstance();
		 * c.set(2015, 6,1 ,12, 0, 0);//0月开始
		 * //减去的时间
		 * long t=c.getTimeInMillis();
		 * 值为1435723200915
		 */
		// 生成两位随机整数
		// int randomNo = (int) (Math.random() * 900) + 100;
		
		// 得到订单流水号
		Long serialNo = Long.parseLong(String.valueOf(currentTime - 1435723200915L));
		
		// 返回生成的订单号
		if (!Constants.isOfficialServer()) {
			return Constants.FactoryConstant.ORDER_PREFIX + _10_to_32(serialNo, 8);
		} else {
			switch (orderType) {
				case Constants.MoudelType.FIGHTER:
					return Constants.FactoryConstant.ORDER_PREFIX_FIGHTERS + _10_to_32(serialNo, 8);
				case Constants.MoudelType.ACHIEVER:
					return Constants.FactoryConstant.ORDER_PREFIX_ACHIEVERS + _10_to_32(serialNo, 8);
				case Constants.MoudelType.ENTREPRENEUR:
					return Constants.FactoryConstant.ORDER_PREFIX_ENTREPRENEURS + _10_to_32(serialNo, 8);
				case Constants.MoudelType.IMPLEMENTOR:
					return Constants.FactoryConstant.ORDER_PREFIX_IMPLEMENTERS + _10_to_32(serialNo, 8);
				case Constants.MoudelType.DISTRIBUTION_PERSONAL:
					return Constants.FactoryConstant.ORDER_PREFIX_DISTRIBUTION + _10_to_32(serialNo, 8);
			}
		}
		return Constants.FactoryConstant.ORDER_PREFIX + _10_to_32(serialNo, 8);
	}
	
	/**
	 * 2016.07.01版本获取订单号,当前时间(格式：yyyyMMddHHmmss)+4位随机数
	 * @param date 当前时间
	 */
	public static String getOrderNo(Date curTime) {
		String timeStr = DateFormatUtils.format(curTime, Constants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
		long randNum = Math.abs(new Random().nextLong());
		String numStr = String.valueOf(randNum).substring(0, 4);
		return timeStr + numStr;
	}
	
	/** 
	 * 获取测试订单的编号
	 * @return
	 */
	public static String  getTestAutoOrderNo(){
		 String s = String.valueOf(new Date().getTime());
		  s = "C2MB"+s.substring(5, 13);
		return s;
	}

	/**
	 * 生成代金券号
	 */
	public static String getVouchersNo() {
		// 生成两位随机整数
		String vouchertsNo = null;
		int randomNo = (int) (Math.random() * 900) + 100;
		
		for (int i = 0; i < 10; i++) {
			long t = System.nanoTime();
			vouchertsNo = (OrderNoUtil._10_to_32(t, 10) + (randomNo / 100) + "V");
		}
		
		return vouchertsNo;
	}


	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		shortBuffer.append( new Date().getTime()) ;
		return shortBuffer.toString();

	}
	/**
	 * 根据订单号得到生成的时间
	 * @param orderNo
	 * @return
	 */
	public static Date getDayByOrderNo(String orderNo) {
		return new Date(new BigDecimal(convertBase32ToDecimal(orderNo.substring(4))).add(new BigDecimal("1435723200915")).longValue()); 
	}
	/**
	 * 将32进制转换成10进制数
	 * 
	 * @param ident62
	 * @return
	 */
	private static String convertBase32ToDecimal(String ident32) {
	
		long decimal = 0;
		long base = 32;
		long keisu = 0;
		long cnt = 0;
		byte baseChar[]="23456789ABCDEFGHJKLMNPQRSTUVWXYZ".getBytes();
		byte ident[] = ident32.getBytes();
		for (int i = ident.length - 1; i >= 0; i--) {
			int num = 0;
			for(int t=0;t<baseChar.length;t++)
				if(baseChar[t]==ident[i])
					num=t;
			keisu = (long) java.lang.Math.pow((double) base, (double) cnt);
			decimal += num * keisu;
			cnt++;
		}
		return String.format("%08d", decimal);
	}
	
	/**
	 * 获取返修单订单号
	 * @return
	 */
	public static String getRepairOrderNo() {
		// 得到当前时间
		long currentTime = System.currentTimeMillis();
		// 得到订单流水号
		Long serialNo = Long.parseLong(String.valueOf(currentTime - 1435723200915L));
						
		// 返回生成的订单号
		return Constants.FactoryConstant.REPAIR_ORDER_CODE + _10_to_32(serialNo, 8);
	}
	/**
	 * 库存单号
	 * @param storeId 门店id
	 * @param TypeCode 入库出库类型
	 * @param date 时间
	 * @return
	 */
	public static String generateWarehouseOrderNo(String storeNO,String TypeCode,Date date) {
		return new StringBuilder(storeNO).append(TypeCode)
				.append(DateFormatUtils.format(date, "yyMMddHHmm")).append(getOrderNo("1").substring(8, 12)).toString();
	}
	
}