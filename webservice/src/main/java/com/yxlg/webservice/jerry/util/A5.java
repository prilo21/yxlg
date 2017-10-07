package com.yxlg.webservice.jerry.util;
/**
 * 
 * @author Abel
 *卖票系统可以识别四种证，身份证、学生证、军官证、残疾人证。身份证根据身份证号判断，其它直接根据证的名称判断。
 *根据身份证判断年龄，0-10免费、11-20七五折、21-60全票、60以上五折
 *学生证五折
 *军官证免费
 *残疾人证免费
 *门票价格100
 */
public class A5 {
	
	public String getCard(String a){
		if("学生证".equals(a)){
			return "B";
		}
		if("军官证".equals(a)){
			return "C";
		}
		if("残疾人证".equals(a)){
			return "D";
		}
		if(a.length() == 18){
			//字符串截取
			String b = a.substring(6, 10);
			//System.out.println(b);
			//System.out.println("A,"+b);
			return "A,"+b;
		}
		return "E";
		
	}
	
	public int getMoney(String d){
		int money = 100;
		if(d.equals("B")){
			return money/2;
		}
		if(d.equals("C")){
			return 0;
		}
		if(d.equals("D")){
			return 0;
		}
		//字符串包含
		if(d.contains("A")){
			//把以“,”隔开的字符串转换成数组
			 String[] result1 = d.split(",");
			 String e = result1[1];
			 //把string转换成int
			 int a = Integer.parseInt(e);
			 int f = 2017-a;
			 if(0<f && f<=10){
				 return 0;
			 }
			 if(11<f && 20>=f){
				 return money*3/4;
			 }
			 if(20<f && 60>=f){
				 return money;
			 }
			 if(f>60){
				 return money/2;
			 }
		}
		return 100;
	}
	
	public static void main(String[] args) {
		A5 c = new A5();
		//方法调用
		System.out.println(c.getMoney(c.getCard("372929199104012769")));
		
	}
}




