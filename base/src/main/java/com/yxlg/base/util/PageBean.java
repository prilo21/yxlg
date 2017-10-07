package com.yxlg.base.util;

public class PageBean {
	
	private int page = 1;
	private int rows = 15;
	
	public PageBean(){
		super();
	}
	
	public PageBean(int page, int rows){
		this.setPage(page);
		this.setRows(rows);
	}
	
	public int getPage() {
		
		return page;
	}
	
	public void setPage(int page) {
		
		this.page = page;
	}
	
	public int getRows() {
		
		return rows;
	}
	
	public void setRows(int rows) {
		
		this.rows = rows;
	}
}