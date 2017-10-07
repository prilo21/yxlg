package com.yxlg.base.util;

import java.util.List;

import com.yxlg.base.constant.Constants;

public class Pagination {
	
	private List<Object> rows = null;
	
	private Long total = 0L;
	
	/**
	 * Stone.Cai
	 * 2015年6月3日 14:42:55
	 * 添加HTTP 返回CODE
	 */
	private String returnCode = "200";
	/**
	 * Stone.Cai
	 * 2015年6月3日 14:43:02
	 * 添加HTTP 返回消息
	 */
	private String returnMsg = "SUCCESS";
	
	public Pagination() {
		
		super();
	}
	
	/**
	 * 构造方法
	 * 
	 * @param rows
	 * @param total
	 */
	public Pagination(Long total, List<Object> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	/**
	 * @param rows
	 * @param total
	 * @param returnCode
	 * @param returnMsg
	 */
	public Pagination(List<Object> rows, Long total, String returnCode,
			String returnMsg) {
			
		this.rows = rows;
		this.total = total;
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	
	/**
	 * 页内的数据列表.
	 */
	public List<Object> getRows() {
		
		return rows;
	}
	
	public void setRows(List<Object> rows) {
		
		this.rows = rows;
	}
	
	/**
	 * 总记录数.
	 */
	public Long getTotal() {
		
		return total;
	}
	
	public void setTotal(Long total) {
		
		this.total = total;
	}
	
	public String getReturnCode() {
		
		return returnCode;
	}
	
	public void setReturnCode(String returnCode) {
		
		this.returnCode = returnCode;
	}
	
	public String getReturnMsg() {
		
		return returnMsg;
	}
	
	public void setReturnMsg(String returnMsg) {
		
		this.returnMsg = returnMsg;
	}
	
}