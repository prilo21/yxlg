package com.yxlg.base.util;

import java.util.ArrayList;
import java.util.List;

public class PageData<T> {

	//数据总数
	private long total=0;
	//数据列表
	private List<T> rows=new ArrayList<T>();

	
	/**
	 * @return the total
	 */
	public long getTotal() {
	
		return total;
	}

	
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
	
		this.total = total;
	}

	public PageData() {
		super();
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public PageData(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	
}
