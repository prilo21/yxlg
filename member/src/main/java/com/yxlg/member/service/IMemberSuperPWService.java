package com.yxlg.member.service;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.util.NewResult;

public interface IMemberSuperPWService {
	/**
	 * 查看超级开关是否enable, 返回responseEntity
	 * @return
	 */
	public ResponseEntity<NewResult<String>> getSuperPWStatus();
	/**
	 * 更改redis中的superpw enable开关
	 * @param enableFlag : true / false
	 */
	public ResponseEntity<NewResult<String>> setSuperStatus (String enbaleFlag);
}
