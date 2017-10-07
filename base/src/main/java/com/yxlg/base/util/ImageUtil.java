/*
 * ImageUtil.java
 *
 * Created Date: 2016年11月8日
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;



/**
 * @author Michael.Sun
 * 2016年11月8日
 * @version  <br>
 * <p>图片处理工具类</p>
 */

public class ImageUtil {
	/**
	 * 获取bufferedImage输入流
	 * @param bufferedImage
	 * @return
	 */
	/*public static InputStream getInputStream(BufferedImage bufferedImage){
		try {
			bufferedImage.flush();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
			ImageIO.write(bufferedImage, "png", imageOutputStream);
			InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			return inputStream;
		} catch (Exception e) {
			throw new BusinessException("获取图片输入流异常", e);
		}
	}*/
}
