package com.yxlg.webservice.jerry;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IGradeService;
import com.yxlg.member.service.IStudentsService;

@Controller("gradeController")
public class GrateController {
	@RequestMapping(value = Constants.API_VERSION_1 + "/public/jerry/grate/findAll", method = RequestMethod.GET)
	public ResponseEntity<Result> findAllStudents() {
		
		return gradeService.findAllGrade();
	}
	
	
	@Resource
	private IGradeService gradeService;
}
