/*
 * NewResult.java
 *
 * Created Date: 2016年4月27日
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

import org.springframework.http.HttpStatus;

import com.yxlg.base.constant.Constants;


/**
 * @author yuandian
 * @version  <br>
 * <p>类的描述</p>
 */

public class NewResult<T> {
	/**
	 * 返回值
	 */
	private int returnCode;
	/**
	 * 提示信息
	 */
	private String returnMsg;
	/**
	 * 返回数据
	 */
	private T returnData;
	
	public NewResult(){
		this.returnCode = HttpStatus.OK.value();
		this.returnMsg = Constants.ReturnMsg.SC_SUCCESS;
	}
	
	public NewResult(T returnData) {
		this.returnCode = HttpStatus.OK.value();
		this.returnMsg = Constants.ReturnMsg.SC_SUCCESS;
		this.returnData = returnData;
	}
	
	public NewResult(int returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	public NewResult(int returnCode, String returnMsg, T returnData) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}

	
	/**
	 * @return the returnCode
	 */
	public int getReturnCode() {
	
		return returnCode;
	}

	
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
	
		this.returnCode = returnCode;
	}

	
	/**
	 * @return the returnMsg
	 */
	public String getReturnMsg() {
	
		return returnMsg;
	}

	
	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
	
		this.returnMsg = returnMsg;
	}

	
	/**
	 * @return the returnData
	 */
	public T getReturnData() {
	
		return returnData;
	}

	
	/**
	 * @param returnData the returnData to set
	 */
	public void setReturnData(T returnData) {
	
		this.returnData = returnData;
	}
}