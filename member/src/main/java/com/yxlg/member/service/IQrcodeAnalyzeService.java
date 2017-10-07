/*
 * IQrcodeAnalyzeService.java
 *
 * Created Date: 2016年3月21日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.service;



/**
 * @author Marvin.Ma
 * @version <br>
 *          <p>
 *          二维码识别接口类
 *          </p>
 */
public interface IQrcodeAnalyzeService {

	public Integer qrcodeAnalyze(String type, String id, String secondId);
	
}
