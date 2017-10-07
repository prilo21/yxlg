package com.yxlg.member.util;
/**
 * @author alison
 * @version  v1.0
 * <p>返回结果类</p>
 */

public class ResultObject {
	/**
	 * 操作是否成功
	 */
	private String success;
	/**
	 * 操作提示信息
	 */
	private String msg;
	/**
	 * 操作
	 */
	private String code;
	/**
	 * 构造方法
	 * @param success
	 * @param msg
	 * @param errorCode
	 */
	public ResultObject(String success,String msg,String code){
		this.success = success;
		this.msg = msg;
		this.code = code;
	};
	public String getSuccess() {
	
		return success;
	}
	
	public void setSuccess(String success) {
	
		this.success = success;
	}
	
	public String getMsg() {
	
		return msg;
	}
	
	public void setMsg(String msg) {
	
		this.msg = msg;
	}
	
	public String getCode() {
	
		return code;
	}
	
	public void setCode(String code) {
	
		this.code = code;
	}
	
	
}
