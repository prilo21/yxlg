package com.yxlg.webservice.jerry.util;
/*
 给定一个百分制的分数，输出相应的等级
90分以上        	   A级         
80~89          B级         
70~79          C级         
60~69          D级         
60分以下       	   E级
*/
public class A1 {
	public static void main(String[] args) {
		A1 a = new A1();
		a.getPoint(50);
		
	}
	
	public void getPoint(int point) {
		if(point>90){
			System.out.println("A级");
		}else if(point>=80 && point<=89){
			System.out.println("B");
		}else if(point>=70 && point<=79){
			System.out.println("C");
		}else if(point>=60 && point<=69){
			System.out.println("D");
		}else{
			System.out.println("E");
		}
	}
}
