package com.yxlg.base.util;

import org.springframework.http.HttpStatus;

import com.yxlg.base.constant.Constants;

/**
 * @author jarvis
 * @version v1.0
 *          <p>
 *          返回结果类
 *          </p>
 */

public class Result {
	
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
	private Object returnData;
	
	/**
	 * Michael.Sun
	 * 2016年01月27日
	 * 无参构造方法
	 */
	public Result(){
		this.returnCode = HttpStatus.OK.value();
		this.returnMsg = Constants.ReturnMsg.SC_SUCCESS;
	}
	
	/**
	 * 构造方法
	 *
	 * @param returnData
	 */
	public Result(Object returnData) {
		this.returnCode = HttpStatus.OK.value();
		this.returnMsg = Constants.ReturnMsg.SC_SUCCESS;
		this.returnData = returnData;
	}
	
	/**
	 * 构造方法
	 *
	 * @param returnCode
	 * @param returnMsg
	 */
	public Result(int returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	/**
	 * 构造方法
	 *
	 * @param returnCode
	 * @param returnMsg
	 * @param returnData
	 */
	public Result(int returnCode, String returnMsg, Object returnData) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}
	
	public int getReturnCode() {
		
		return returnCode;
	}
	
	public void setReturnCode(int returnCode) {
		
		this.returnCode = returnCode;
	}
	
	public String getReturnMsg() {
		
		return returnMsg;
	}
	
	public void setReturnMsg(String returnMsg) {
		
		this.returnMsg = returnMsg;
	}
	
	public Object getReturnData() {
		
		return returnData;
	}
	
	public void setReturnData(Object returnData) {
		
		this.returnData = returnData;
	}
	
}
