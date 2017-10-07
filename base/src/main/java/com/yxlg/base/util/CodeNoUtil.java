/*
 * CodeNoUtil.java
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Marvin.Ma
 * @version <br>
 *          <p>
 *          生成券号工具类
 *          </p>
 */

public class CodeNoUtil {
	
	private static char[] charSet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();
//    private static char[] charSet2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
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
	 * 生成代金券号
	 */
	public static String getVouchersNo() {
		// 生成两位随机整数
		String vouchertsNo = null;
		int randomNo = (int) (Math.random() * 900) + 100;
		
		for (int i = 0; i < 10; i++) {
			long t = System.nanoTime();
			vouchertsNo = (_10_to_32(t, 10) + (randomNo / 100) + "V");
		}
		
		return vouchertsNo;
	}
	
	/**
	 * 生成礼品卡号
	 */
	public static String getGiftCardsNo() {
		// 生成两位随机整数
		String vouchertsNo = null;
		String pre = "LpK";//礼品卡前缀
		int randomNo = (int) (Math.random() * 900) + 100;
		long t = System.nanoTime();
		vouchertsNo = (pre + _10_to_32(t, 10) + (randomNo));
		return vouchertsNo;
	}
	
	
	/**
	 * 生成大礼包编号
	 */
	public static String getBigGiftPackageNo(int count) {
		String bigGiftPackageNos = "";
		String pre = "gP";//礼品卡前缀
		String NO = "";
		for (int i = 0; i < count; i++) {
			// 生成两位随机整数
			int preRandomNo = (int) (Math.random() * 90) + 10;
			long t = System.nanoTime();
			int endRandomNo = (int) (Math.random() * 90) + 10;
			NO = ((preRandomNo) + pre + _10_to_32(t, 10) + (endRandomNo));
			bigGiftPackageNos += NO + ",";
		}
		if(StringUtils.isEmpty(bigGiftPackageNos)) {
			return null;
		}
		return bigGiftPackageNos.substring(0, bigGiftPackageNos.length() - 1);
	}
	
	/**
	 * 生成魔卡卡号
	 * @return
	 */
	public static List<String> getMagicCardNo(int count){
		//不带4、2的6位随机数
		Set<String> no4_2Set = getRandom6no4_2Set(count); 
		//不带4，带2的6位随机数
		Set<String> no4have2Set = getRandom6no4have2Set(count);
		String time = String.valueOf(System.currentTimeMillis()).substring(0, 10);
		if(time.contains("4")){
			return getRandom6List(time.replace("4", "6"), no4_2Set);
		}else{
			return getRandom6List(time, no4have2Set);
		}
	}

	/**
	 * @param count
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static Set<String> getRandom6no4_2Set(int count) {
	
		Set<String> set = new HashSet<String>();
		int no;
		String noString;
		while(set.size() < count){
			no = RandomUtils.nextInt(0, 999999);
			noString = String.valueOf(no).format("%06d", no);
			if(noString.contains("4") || noString.contains("2")){
				continue;
			}
			set.add(noString);
		}
		return set;
	}

	/**
	 * @param count
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static Set<String> getRandom6no4have2Set(int count) {
	
		Set<String> set = new HashSet<String>();
		int no;
		String noString;
		while(set.size() < count){
			no = RandomUtils.nextInt(0, 999999);
			noString = String.valueOf(no).format("%06d", no);
			if(noString.contains("4") || !noString.contains("2")){
				continue;
			}
			set.add(noString);
		}
		return set;
	}

	/**
	 * @param time
	 * @param no4_2Set
	 * @return
	 */
	private static List<String> getRandom6List(String time, Set<String> no4_2Set) {
	
		List<String> list = new ArrayList<String>();
		for(String string : no4_2Set){
			list.add(time + string);
		}
		return list;
	}
	/**
	 * 批量生成8位密码
	 * @param count
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getMagicCardPassword(int count){
		List<String> list = new ArrayList<String>();
		int no;
		String password;
		while(list.size() < count){
			no = RandomUtils.nextInt(0, 99999999);
			password = String.valueOf(no);
			if(password.contains("4")){
				continue;
			}
			password = password.format("%08d", no);
			list.add(password);
		}
		return list;
	}
	/**
	 * 生成魔卡编号
	 * @param maxNumberStr
	 * 			数据库中已有的最大编码值
	 * @param count
	 * 			要生成的编码个数
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getMagicCardNumber(int maxNumberStr, int count){
//		if(maxNumberStr.startsWith("0")){
//			maxNumberStr = maxNumberStr.replaceFirst("0", "");
//			return getMagicCardNumber(maxNumberStr, count);
//		}
		int number = maxNumberStr + 1; 
		String numberStr;
		List<String> list = new ArrayList<String>();
		while(count-- > 0){
			numberStr = String.valueOf(number);
			numberStr = numberStr.format("%08d", number);
			list.add(numberStr);
			number++;
		}
		return list;
	}
}