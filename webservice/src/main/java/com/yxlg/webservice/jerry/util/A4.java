package com.yxlg.webservice.jerry.util;
//求  2/1 + 3/2 + 5/3 + 8/5 + 13/8.....前20项之和
public class A4 {

	public static void main(String[] args) {
		double sum=0;          
		double fenZi=2.0, fenMu=1.0;    //初始的分子 (fenZi)＝2，分母(fenMu)＝1          
		for(int i=1; i<=4; i++){             
			sum += fenZi / fenMu;
			double a = fenZi;
			fenZi = fenMu + fenZi;         //下一项的分子 ＝ 上一项的分子加分母      
			fenMu = a;           //下一项的分母 ＝ 上一项的分子             
			 
		}          
		System.out.println("sum= " + sum);
		
	}
}
