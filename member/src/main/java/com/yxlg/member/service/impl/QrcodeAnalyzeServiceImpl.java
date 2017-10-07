package com.yxlg.member.service.impl;

import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.member.service.IQrcodeAnalyzeService;

/**
 * @author marvin.ma
 * @version <br>
 *          <p>
 *          酷特二维码解析类
 *          </p>
 */

@Service
public class QrcodeAnalyzeServiceImpl implements IQrcodeAnalyzeService {
	
	@Override
	public Integer qrcodeAnalyze(String type, String firstId, String secondId) {
	
		if (Constants.qrcodeType.TYPE_1.contains(type)) {
			return 1;
		}
		if (Constants.qrcodeType.TYPE_2.contains(type)) {
			return 2;
		}
		if (Constants.qrcodeType.TYPE_3.contains(type)) {
			return 3;
		}
		return 0;
	}
	
}
