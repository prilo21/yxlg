package com.yxlg.base.jerry.entity;


public class StudentsDto {

	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 学号
	 */
	private String No;
	/**
	 * 省
	 */
	private String provice;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 出生年月
	 */
	private String bithday;
	/**
	 * 身份证号
	 */
	private String idCard;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBithday() {
		return bithday;
	}
	public void setBithday(String bithday) {
		this.bithday = bithday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
}
