/*
 * FileServiceImpl.java
 *
 * Created Date: 2015年5月26日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.service.IFileService;
import com.yxlg.base.upload.dto.Base64File;
import com.yxlg.base.upload.util.QiniuUtils;
import com.yxlg.base.upload.util.UploadByUrl;
import com.yxlg.base.util.Base64Util;
import com.yxlg.base.util.FileLoadUtil;
import com.yxlg.base.util.Result;


/**
 * @author alison.liu
 * @version  <br>
 * <p>类的描述</p>
 */
@Service
public class FileServiceImpl implements IFileService {

	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
	public ResponseEntity<Result> uploadIMG(String id, Base64File base64File){
		String path = FileLoadUtil.encodeName("images/"+id+"/");
		String filepath = null;
		try {
			InputStream in = new ByteArrayInputStream(Base64Util.decodeBase64ToByte(base64File.getBase64String()));
			filepath = UploadByUrl.uploadFile(in, path);
			filepath = UploadByUrl.decodeQinuPath(filepath);
		} catch (Exception e) {
			return new ResponseEntity<Result>(new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					Constants.ReturnMsg.SC_ERROR,filepath), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(),
				Constants.ReturnMsg.SC_SUCCESS,filepath), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Result> uploadPrivateFile(String key,
			Base64File base64File) {
		String path = FileLoadUtil.encodeName("images/"+key+"/");
		String filepath = null;
		try {
			InputStream in = new ByteArrayInputStream(Base64Util.decodeBase64ToByte(base64File.getBase64String()));
			filepath = UploadByUrl.uploadPrivateFile(in, path);
			filepath = UploadByUrl.decodeQinuPath(filepath);
		} catch (Exception e) {
			return new ResponseEntity<Result>(new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					Constants.ReturnMsg.SC_ERROR,filepath), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(),
				Constants.ReturnMsg.SC_SUCCESS,filepath), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Result> getPrivateToken() {
		String token = "";
		try {
			token = QiniuUtils.upPrivateDomainToken();
		} catch (Exception e) {
			log.error("获得七牛私有空的token出现异常", e);
		}
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(),
				Constants.ReturnMsg.SC_SUCCESS,token), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Result> getPublicToken() {
		String token = "";
		try {
			token = QiniuUtils.upPublicDomainToken();
		} catch (Exception e) {
			log.info("获得七牛私有空的token出现异常", e);
		}
		return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(),
				Constants.ReturnMsg.SC_SUCCESS,token), HttpStatus.OK);
	}
	 
}
