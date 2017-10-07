package com.yxlg.manage.member.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.PageData;

public interface MemberListService {

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
	PageData getMembersBelowStore(HttpServletRequest request, PageBean pageBean, String memberName, String phoneNo, String memberType, String timeStart, String timeEnd);

	/**
	 * 导出门店会员到EXCEL
	 * @param out
	 * @param request
	 * @param memberName
	 * @param phoneNo
	 * @param memberType
	 * @param timeStart
	 * @param timeEnd
	 * @throws IOException
	 */
	void exportMembers(OutputStream out, HttpServletRequest request, String memberName, String phoneNo,
			String memberType, String timeStart, String timeEnd) throws IOException;
}
