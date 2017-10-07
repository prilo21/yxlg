package com.yxlg.base.util;

import java.util.Comparator;

import com.yxlg.base.sys.entity.SysResource;

public class NumberComparator implements Comparator<Object> {
	
	@Override
	public int compare(Object o1, Object o2) {
		
		SysResource confSysMenu1 = (SysResource) o1;
		SysResource confSysMenu2 = (SysResource) o2;
		
		return confSysMenu1.getShowOrder() - confSysMenu2.getShowOrder();
	}
}