/*
 * ListUtils.java
 *
 * Created Date: 2016年6月6日
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

import java.util.List;


/**
 * @author Michael.Sun
 * 2016年6月6日
 * @version  <br>
 * <p>List判空工具类</p>
 */

public class ListUtils {
	public static <T> boolean  isEmpty(List<T> list){
		if(list == null || list.isEmpty()){
			return true;
		}
		return false;
	}
}
