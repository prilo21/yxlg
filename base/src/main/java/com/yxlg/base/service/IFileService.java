/*
 * IFileService.java
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

package com.yxlg.base.service;

import org.springframework.http.ResponseEntity;

import com.yxlg.base.upload.dto.Base64File;
import com.yxlg.base.util.Result;


/**
 * @author alison.liu
 * @version  <br>
 * <p>类的描述</p>
 */

public interface IFileService {

	/**
	 * 客户端上传文件
	 * @param path
	 * @param base64File
	 * @return
	 */
	ResponseEntity<Result> uploadIMG(String id, Base64File base64File);
    /**
     * 客户端上传图片
     * @param key
     * @param base64File
     * @return
     */
	ResponseEntity<Result> uploadPrivateFile(String key, Base64File base64File);
	/**
	 * 获得私有空间上传token
	 * @return
	 */
	ResponseEntity<Result> getPrivateToken();
	/**
	 * 获得公有空间（resources）上传token
	 * @return
	 */
	ResponseEntity<Result> getPublicToken();
	
}
