package com.yxlg.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.jerry.entity.Grade;
import com.yxlg.base.jerry.entity.Students;
import com.yxlg.base.util.Result;
import com.yxlg.member.service.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {
	@Override
	public ResponseEntity<Result> findAllGrade() {
		String sql = "select * from jer_grade";
		List<Grade> gradeList = baseDao.queryBySQL(sql, Grade.class);
		
		return new ResponseEntity<Result>(new Result(gradeList),HttpStatus.OK);
	}
	
	@Resource 
	private IBaseDAO baseDao;
}
