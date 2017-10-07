package com.yxlg.base.member.dto;

public class MeasurePerson {
	/**
	 * 量体师id
	 */
	private String bodyMeasurementPersonId;
	
	/**
	 * 量体师编号
	 */
	private String personNo;
	
	/**
	 * 量体师姓名
	 */
	private String personName;
	
	/**
	 * 量体证编号
	 */
	private String measurementDiplomaNo;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 * 手机号码
	 */
	private String phoneNo;

	public String getBodyMeasurementPersonId() {
		return bodyMeasurementPersonId;
	}

	public void setBodyMeasurementPersonId(String bodyMeasurementPersonId) {
		this.bodyMeasurementPersonId = bodyMeasurementPersonId;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMeasurementDiplomaNo() {
		return measurementDiplomaNo;
	}

	public void setMeasurementDiplomaNo(String measurementDiplomaNo) {
		this.measurementDiplomaNo = measurementDiplomaNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
