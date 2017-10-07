package com.yxlg.webservice.jerry.util;

public class TicketingSystem {

	private int ticket = 100;
	
	public String getCard(String a) {
		if ("studentCard".equals(a)) {
			return "A";
		} else if ("disabledCard".equals(a)) {
			return "B";
		} else if ("oldCard".equals(a)) {
			return "C";
		} else {
			String str = a.substring(6, 10);
			return "D," + str;
		}
	}
	public int getMoney(String a) {
		if ("A".equals(a)) {
			return ticket/2;
		} else if ("B".equals(a) || "C".equals(a)) {
			return 0;
		} else if (a.contains("D")){
			String[] b = a.split(",");
			//获取年龄
			String str = b[1];
			System.out.println("birthday is：" + str);
			//将字符串转换成int
			int tic = Integer.parseInt(str);
			int age = 2017-tic;
			if (age > 0 && age <= 10) {
				return ticket/2;
			} 
			if (age >10 && age <= 20) {
				return ticket*3/4;
			}
			if (age > 20 && age <= 50) {
				return ticket;
			} 
			if (age > 40) {
				return ticket*3/4;
			}
		} 
		return ticket;
	}
	
	public static void main(String[] args) {
		TicketingSystem ticketingSystem = new TicketingSystem();
		String a = ticketingSystem.getCard("372929199104012769");
		int b = ticketingSystem.getMoney(a);
		System.out.println(b);
	}
}
