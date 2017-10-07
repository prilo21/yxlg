/*
 * SysUserStoreDTO.java
 * 
 * Created Date: 2016年3月28日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.sys.entityDTO;

/**
 * @author Cary
 * @version <br>
 *          <p>
 *          加盟商DTO
 *          </p>
 */

public class SysUserStoreDTO {
	
	/**
	 * 加盟商id多个逗号
	 */
	private String userStoreIdsDto;
	/**
	 * 查询时的门店名模糊匹配
	 */
	private String companyNameDto;
	/**
	 * 
	 * 用户id
	 */
	private String sysUserId;
	/**
	 * 查询时的加盟商名模糊匹配
	 */
	private String storeNameDto;
	
	/**
	 * 查询时的管理者名称模糊匹配
	 */
	private String userNameDto;
	
	/**
	 * 门店ids
	 */
	private String companyIdsDto;
	/**
	 * 删除标志0有效，1已删除
	 */
	private String deleteFlag;
	
	/**
	 * @return the deleteFlag
	 */
	public String getDeleteFlag() {
	
		return deleteFlag;
	}
	
	/**
	 * @param deleteFlag
	 *            the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
	
		this.deleteFlag = deleteFlag;
	}
	
	/**
	 * @return the userStoreIdsDto
	 */
	public String getUserStoreIdsDto() {
	
		return userStoreIdsDto;
	}
	
	/**
	 * @param userStoreIdsDto
	 *            the userStoreIdsDto to set
	 */
	public void setUserStoreIdsDto(String userStoreIdsDto) {
	
		this.userStoreIdsDto = userStoreIdsDto;
	}
	
	/**
	 * @return the companyNameDto
	 */
	public String getCompanyNameDto() {
	
		return companyNameDto;
	}
	
	/**
	 * @param companyNameDto
	 *            the companyNameDto to set
	 */
	public void setCompanyNameDto(String companyNameDto) {
	
		this.companyNameDto = companyNameDto;
	}
	
	/**
	 * @return the storeNameDto
	 */
	public String getStoreNameDto() {
	
		return storeNameDto;
	}
	
	/**
	 * @param storeNameDto
	 *            the storeNameDto to set
	 */
	public void setStoreNameDto(String storeNameDto) {
	
		this.storeNameDto = storeNameDto;
	}
	
	/**
	 * @return the userNameDto
	 */
	public String getUserNameDto() {
	
		return userNameDto;
	}
	
	/**
	 * @param userNameDto
	 *            门店ids the userNameDto to set
	 */
	public void setUserNameDto(String userNameDto) {
	
		this.userNameDto = userNameDto;
	}
	
	/**
	 * @return the companyIdsDto
	 *         门店ids
	 */
	public String getCompanyIdsDto() {
	
		return companyIdsDto;
	}
	
	/**
	 * @param companyIdsDto
	 *            the companyIdsDto to set
	 */
	public void setCompanyIdsDto(String companyIdsDto) {
	
		this.companyIdsDto = companyIdsDto;
	}

	
	/**
	 * @return the sysUserId
	 */
	public String getSysUserId() {
	
		return sysUserId;
	}

	
	/**
	 * @param sysUserId the sysUserId to set
	 */
	public void setSysUserId(String sysUserId) {
	
		this.sysUserId = sysUserId;
	}
	
	
}
