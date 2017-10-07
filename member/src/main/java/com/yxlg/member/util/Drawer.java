/*
 * Drawer.java
 *
 * Created Date: 2015年5月11日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.member.util;

import com.yxlg.base.member.entity.Member;


/**
 * @author Michael.Sun
 * @version  <br>
 * <p>抽屉类，用于返回用户登录成功后的反回信息</p>
 */

public class Drawer {
	
	/**
	 * 会员基本信息
	 */
	private Member memberInfo;

	
	/**
	 * @return the memberInfo
	 */
	public Member getMemberInfo() {
	
		return memberInfo;
	}

	
	/**
	 * @param memberInfo the memberInfo to set
	 */
	public void setMemberInfo(Member memberInfo) {
	
		this.memberInfo = memberInfo;
	}
	
	

}
