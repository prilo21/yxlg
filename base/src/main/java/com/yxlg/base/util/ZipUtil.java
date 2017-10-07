/*
 * ZipUtil.java
 * 
 * Created Date: 2016年11月8日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary
 * information of
 * Yuandian Technologies Co., Ltd.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it
 * only in accordance
 * with the terms of the license agreement you entered into
 * with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Michael.Sun
 *         2016年11月8日
 * @version <br>
 *          <p>
 *          文件压缩工具类
 *          </p>
 */

public class ZipUtil {
	
	/**
	 * 压缩单个文件
	 * 
	 * @param file
	 * @return
	 */
	public static File zipFile(File file, String zipPath) {
	
		try {
//			File zipFile = new File(System.getProperty("java.io.tmpdir") + "zipFile.zip");
			File zipFile = new File(zipPath);
			InputStream inputStream = new FileInputStream(file);
			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			int temp = 0;
			while ((temp = inputStream.read()) != -1) {
				zipOutputStream.write(temp);
			}
			inputStream.close();
			zipOutputStream.close();
			return zipFile;
		} catch (Exception e) {
			throw new BusinessException("单文件压缩异常", e);
		}
	}
	
	/**
	 * 压缩多个文件
	 * @param fileList
	 * @param zipPath
	 * @return
	 */
	public static File zipMultiFile(List<File> fileList, String zipPath) {
	
		try {
			InputStream inputStream = null;
			File zipFile = new File(zipPath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
			if (!ListUtils.isEmpty(fileList)) {
				for (File file : fileList) {
					inputStream = new FileInputStream(file);
					zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
					int temp = 0;
					while ((temp = inputStream.read()) != -1) {
						zipOutputStream.write(temp);
					}
					inputStream.close();
				}
				zipOutputStream.close();
			}
			return zipFile;
		} catch (Exception e) {
			throw new BusinessException("压缩多个文件异常", e);
		}
	}
}
