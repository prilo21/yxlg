/*
 * TestWXDto.java
 *
 * Created Date: 2017年1月12日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.file;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Alisa.Yang
 * @version  <br>
 * <p>类的描述</p>
 */

@ApiModel
public class TestWXDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1547646071176131005L;
	
	@ApiModelProperty(value="图片url主键",dataType="string",example="comment")
	private String id;
	
	@ApiModelProperty(value="表单所选图片文件列表")
	private MultipartFile[] picFile;
	
	public String getId() {
		
		return id;
	}


	
	public void setId(String id) {
		
		this.id = id;
	}


	public MultipartFile[] getPicFile() {
		
		return picFile;
	}

	
	public void setPicFile(MultipartFile[] picFile) {
		
		this.picFile = picFile;
	}


}
