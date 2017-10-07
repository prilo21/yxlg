package com.yxlg.member.service;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.util.Result;

public interface IGradeService {
	ResponseEntity<Result> findAllGrade();
}
