package com.yxlg.webservice.jerry.util;
/**
 * 求 1+2+3+4+……+99+100 的和值
 * @author guangmou
 *
 */
public class A2 {
	public static void main(String[] args) {
		int sum = 0;
		for(int i=1;i<=100;i++){
			sum = sum + i;
		}
		System.out.println(sum);
	}
}
