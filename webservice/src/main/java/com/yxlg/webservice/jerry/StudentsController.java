package com.yxlg.webservice.jerry;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.jerry.entity.Students;
import com.yxlg.base.jerry.entity.StudentsDto;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IStudentsService;

@Controller("studentsController")
public class StudentsController {

	@RequestMapping(value = Constants.API_VERSION_1 + "/public/jerry/student/findAll", method = RequestMethod.GET)
	public ResponseEntity<Result> findAllStudents() {
		
		return studentsService.findAllStudent();
	}
	
	@RequestMapping(value = Constants.API_VERSION_1 + "/public/jerry/student/findAllByDet", method = RequestMethod.GET)
	public ResponseEntity<Result> findAllStudentsByDet() {
		
		return studentsService.findAllStudentByDet();
	}
	
	
	@RequestMapping(value = Constants.API_VERSION_1 + "/public/jerry/student/addStudent", method = RequestMethod.POST)
	public ResponseEntity<Result> addStudent(@RequestBody StudentsDto stu) {
		return studentsService.addStudent(stu);
	}
	@Resource
	private IStudentsService studentsService;
}

