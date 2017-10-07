package com.yxlg.base.entity;

import java.io.Serializable;
import java.util.List;

import com.yxlg.base.sys.entity.SysUser;

/**
 * @author yuandian
 * @version  <br>
 * <p>类的描述</p>
 */
public class UserContext implements Serializable {
	private static final long serialVersionUID = 1687964675322361938L;
	
	private SysUser user;
	
	private List<Serializable> storeIdList;
	
	public SysUser getUser() {
		return user;
	}
	
	public void setSysUser(SysUser user) {
		this.user = user;
	}
	
	/**
	 * 此用户的 功能权限集合
	 */
	private String functionList ;
	
	public String getFunctionList() {
	
		return functionList;
	}
	
	public void setFunctionList(String functionList) {
	
		this.functionList = functionList;
	}

	
	/**
	 * @return the storeIdList
	 */
	public List<Serializable> getStoreIdList() {
	
		return storeIdList;
	}

	
	/**
	 * @param storeIdList the storeIdList to set
	 */
	public void setStoreIdList(List<Serializable> storeIdList) {
	
		this.storeIdList = storeIdList;
	}
}
