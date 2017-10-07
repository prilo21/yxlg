package com.yxlg.base.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Alison.Liu
 * @version  <br>
 * <p>类的描述</p>
 */
public class CreateFileUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CreateFileUtil.class);
	
	public static boolean CreateFile(String destFileName) {
		
		File file = new File(destFileName);
		if (file.exists()) {
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			return false;
		}
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				return false;
			}
		}
		
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			log.error("", e);
			return false;
		}
	}
	
	public static boolean createDir(String destDirName) {
		
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String createTempFile(String prefix, String suffix, String dirName) {
		
		File tempFile = null;
		try {
			if (dirName == null) {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				return tempFile.getCanonicalPath();
			} else {
				File dir = new File(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists()) {
					if (!CreateFileUtil.createDir(dirName)) {
						return null;
					}
				}
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			}
		} catch (IOException e) {
			log.error("", e);
			return null;
		}
	}
	
	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 * @return
	 */
	public boolean deleteFile(String sPath) {
		
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		
		// 创建目录
		String dirName = "c:/test/test0/test1";
		CreateFileUtil.createDir(dirName);
		// 创建文件
		String fileName = dirName + "/test2/testFile.txt";
		CreateFileUtil.CreateFile(fileName);
		// 创建临时文件
		String prefix = "temp";
		String suffix = ".txt";
		for (int i = 0; i < 10; i++) {
			CreateFileUtil.createTempFile(prefix, suffix, dirName);
		}
		
	}
	
}
