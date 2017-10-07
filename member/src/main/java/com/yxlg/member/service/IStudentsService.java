package com.yxlg.member.service;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.jerry.entity.StudentsDto;
import com.yxlg.base.util.Result;

public interface IStudentsService {

	ResponseEntity<Result> findAllStudent();
	
	ResponseEntity<Result> findAllStudentByDet();
	
	public ResponseEntity<Result> addStudent(StudentsDto stu);
}
