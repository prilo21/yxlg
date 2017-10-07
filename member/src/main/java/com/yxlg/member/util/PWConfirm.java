package com.yxlg.member.util;


public class PWConfirm {
	/**
	 * 旧密码
	 */
	private String oldPassword;
	/**
	 * 新密码
	 */
	private String newPassword;
	/**
	 * 确认密码
	 */
	private String confirmPassword;
	/**
	 * 短信验证码
	 */
	private String phoneCode;
	public String getOldPassword() {
	
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
	
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
	
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
	
		this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
	
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
	
		this.confirmPassword = confirmPassword;
	}

	
	/**
	 * @return the phoneCode
	 */
	public String getPhoneCode() {
	
		return phoneCode;
	}

	
	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(String phoneCode) {
	
		this.phoneCode = phoneCode;
	}
	
}
