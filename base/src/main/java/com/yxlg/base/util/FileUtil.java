/*
 * FileUtil.java
 *
 * Created Date: 2016年11月15日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @author Michael.Sun
 * 2016年11月15日
 * @version  <br>
 * <p>文件处理工具类</p>
 */

public class FileUtil {
	/**
	 * 根据inputStream创建文件
	 * @param inputStream
	 * @param fileName
	 * 			fileName包含完整文件路径
	 * @return
	 */
	public static File createFileThroughInputStream(InputStream inputStream , String fileName){
		FileOutputStream fileOutputStream; 
		try {
			File file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			int temp = 0;
			while((temp = inputStream.read()) != -1){
				fileOutputStream.write(temp);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			return file;
		} catch (Exception e) {
			throw new BusinessException("创建文件异常", e);
		} 
	}
}
