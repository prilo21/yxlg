package com.yxlg.manage.member.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.PageData;
import com.yxlg.manage.member.service.MemberListService;

@Controller
@RequestMapping(value = "/memberList/*")
public class MemberListController {

	/**
	 * 查找加盟商门店下的会员
	 * @param request
	 * @param pageBean
	 * @param memberName
	 * @param phoneNo
	 * @param memberType
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "membersBelowStore", method = RequestMethod.POST)
	public @ResponseBody PageData getMembersBelowStore(HttpServletRequest request, PageBean pageBean, String memberName,
			String phoneNo, String memberType, String timeStart, String timeEnd) {
		return memberListService.getMembersBelowStore(request, pageBean, memberName, phoneNo, memberType, timeStart,
				timeEnd);
	}

	/**
	 * 导出门店会员到EXCEL
	 * @param response
	 * @param request
	 * @param memberName
	 * @param phoneNo
	 * @param memberType
	 * @param timeStart
	 * @param timeEnd
	 */
	@RequestMapping(value = "exportMembers", method = RequestMethod.POST)
	public void exportMembers(HttpServletResponse response, HttpServletRequest request, String memberName,
			String phoneNo, String memberType, String timeStart, String timeEnd) {
		OutputStream out = null;
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String filename = "STOREMEMBERS" + format.format(new Date().getTime()) + ".xls";
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			response.flushBuffer();
			out = response.getOutputStream();
			memberListService.exportMembers(out, request, memberName, phoneNo, memberType, timeStart, timeEnd);
		} catch (Exception e) {
			logger.error("导出门店会员到excel异常： " , e);
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("导出门店会员到excel异常： " ,e );
				}
			}
		}
	}

	private final static Logger logger = LoggerFactory.getLogger(MemberListController.class);
	@Resource
	private MemberListService memberListService;

}
