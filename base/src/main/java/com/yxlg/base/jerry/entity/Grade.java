package com.yxlg.base.jerry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "jer_grade")
public class Grade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433868692532626170L;
	private String gradeId;
	private String gradeName;
	private String Instructor;
	
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "grade_id", unique = true, nullable = false, length = 40)
	
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	@Column(name = "grade_name", columnDefinition="varchar(40) default ''")
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	@Column(name = "instructor", columnDefinition="varchar(40) default ''")
	public String getInstructor() {
		return Instructor;
	}
	public void setInstructor(String instructor) {
		this.Instructor = instructor;
	}
	
	
}
