/*
 * UploadThread.java
 *
 * Created Date: 2015年5月22日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.upload.util;

import java.io.InputStream;
import java.util.concurrent.Callable;


/**
 * @author alison.liu
 * @version  <br>
 * <p>上传线程</p>
 */

public class UploadThread implements Callable<String> {
	InputStream reader;//文件流
	String fileName;//
	String bucket;
	public UploadThread(InputStream reader, String fileName, String bucket ) {
		this.reader = reader;
		this.fileName = fileName;
		this.bucket = bucket;
	}

	public String call() throws Exception {
		//2016-08-03 图片上传失败，url置空
		return QiniuUtils.upload(reader, fileName, bucket);
//		return fileName;//返回文件路径
	}
}
