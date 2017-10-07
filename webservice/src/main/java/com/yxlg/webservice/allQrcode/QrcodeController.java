/*
 * QrcodeController.java
 *
 * Created Date: 2016年3月21日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.webservice.allQrcode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yxlg.base.constant.Constants;
import com.yxlg.member.service.IQrcodeAnalyzeService;

/**
 * @author Marvin.Ma
 * 2016年3月21日 12:37:53
 * @version  <br>
 * <p>酷特二维码解析类</p>
 */

@Controller("qrcodeController")
@Api(tags = "酷特app二维码识别通用类")
public class QrcodeController {

	@ApiOperation(value="酷特业务内二维码通用接口",notes = "使用酷特app扫描二维码，如果不是约定好的url，将跳转到下载页面")
	@RequestMapping(value = Constants.API_VERSION_1 + "/public/qrcode/{type}/{id}", method = RequestMethod.GET)
	public ModelAndView qrcodeAnalyze(@PathVariable String type, @PathVariable String id) {
		switch (service.qrcodeAnalyze(type, id, null)) {
			case 1:
				return new ModelAndView("mobileweb/aboutWanna");
			case 2:
				return new ModelAndView("mobileweb/verifyPhone");
		}
		return new ModelAndView("mobileweb/aboutWanna");
	}
	
	
	@Resource
	IQrcodeAnalyzeService service;
}
