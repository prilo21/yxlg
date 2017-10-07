package com.yxlg.base.jerry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "jer_students")
public class Students implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3941648325365992403L;
	/**
	 * id 
	 */
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
	private Date bithday;
	/**
	 * 身份证号
	 */
	private String idCard;
	
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", unique = true, nullable = false, length = 40)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "name", columnDefinition="varchar(40) default ''")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "no", columnDefinition="varchar(40) default ''")
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	
	@Column(name = "provice", columnDefinition="varchar(40) default ''")
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	
	@Column(name = "city", columnDefinition="varchar(40) default ''")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "age", columnDefinition="int(20) default 0 ")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name = "birthday")
	public Date getBithday() {
		return bithday;
	}
	public void setBithday(Date bithday) {
		this.bithday = bithday;
	}
	
	@Column(name = "id_card", columnDefinition="varchar(40) default ''")
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
