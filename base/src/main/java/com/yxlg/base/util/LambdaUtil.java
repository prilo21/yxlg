/*
 * LambdaUtil.java
 *
 * Created Date: 2016年6月20日
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
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author dirk
 * @version  <br>
 * <p>类的描述</p>
 */

public class LambdaUtil {
	public static <T, U> List<U> covertList(List<T> from, Function<T,U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}
}