package com.yxlg.webservice.jerry.util;

import java.util.Random;

/**
 * 定义一个int型的一维数组，包含10个元素，
 * 分别赋一些随机整数，然后求出所有元素的最大值，最小值，平均值，和值，
 * 并输出出来。
 * @author yuandian
 *
 */
public class A3 {
	public static void main(String[] args) {
		int [] arr = new int[5];
		/*arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		arr[3] = 40;
		arr[4] = 50;*/
		Random rand = new Random();
		for (int i = 0; i<=4; i++) {
			arr[i] = rand.nextInt(100);
			System.out.println(arr[i]);
		}
		
		int max = 0;
		for(int i=0;i<=4;i++){
			if(max<arr[i]){
				max = arr[i];	
			}
		}
		System.out.println("最大值：" + max);
		
		int min =100;
		for(int i=0;i<=4;i++){
			if(min>arr[i]){
				min = arr[i];
			}
			
		}
		System.out.println("最小值：" + min);
		
		int num = 0;
		for(int i=0;i<=4;i++){
			num = num + arr[i];
		}
		System.out.println("和：" + num);
		
		System.out.println(num/arr.length);
	}
}
