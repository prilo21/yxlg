package com.yxlg.manage.member.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.entity.UserContext;
import com.yxlg.base.util.ExportExcelUtil;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.PageData;
import com.yxlg.manage.member.service.MemberListService;

@Service
public class MemberListServiceImpl implements MemberListService{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageData getMembersBelowStore(HttpServletRequest request, PageBean pageBean, String memberName,
			String phoneNo, String memberType, String timeStart, String timeEnd) {
		UserContext userContext = (UserContext)request.getSession().getAttribute(Constants.session.USER);
		//获取session中user的门店ID
		List<Serializable> storeIds = userContext.getStoreIdList();
		if(storeIds == null || storeIds.isEmpty()){
			return new PageData();
		}else{
			StringBuilder sql  = initMainSql(storeIds);
			PageData pageData = new PageData();
			sql.append(assembleQueryParams(memberName,phoneNo,memberType,timeStart, timeEnd));
			List<Object[]> resultsCount = baseDao.queryBySQL(sql.toString());
			pageData.setTotal(resultsCount.size());
			sql.append(pagination(pageBean));
			List<Object[]> results = baseDao.queryBySQL(sql.toString());
			pageData.setRows(convert2Map(results));
			return pageData;
		}
	}
	
	/**
	 * 把Object[] 转成map
	 * @param results
	 * @return
	 */
	private List<Map<String, Object>> convert2Map(List<Object[]> results){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Object[] objs : results){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberName", objs[1]);
			map.put("memberType", objs[2]);
			map.put("phoneNo", objs[3]);
			map.put("regTime", objs[7]);
			map.put("storeName", objs[9]);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 组装sql，查找加盟商下的门店
	 * @param storeIds
	 * @return
	 */
	private StringBuilder initMainSql(List<Serializable> storeIds){
		//查找门店下对应的所有会员
		StringBuilder builder = new StringBuilder("select * from v_member_binding_store t where t.store_id in (");
		for(Serializable id : storeIds){
			builder.append("'").append(id.toString()).append("'").append(",");
		}
		int index = builder.lastIndexOf(",");
		builder.deleteCharAt(index);
		builder.append(")");
		return builder;
	}
	
	/**
	 * 按条件查找门店下对应的会员，并按门店名称排序
	 * @param memberName
	 * @param phoneNo
	 * @param memberType
	 * @param timeStart
	 * @param timeEnd
	 * @return
	 */
	private StringBuilder assembleQueryParams(String memberName,
			String phoneNo, String memberType, String timeStart, String timeEnd){
		StringBuilder builder = new StringBuilder();
		if(StringUtils.isNotBlank(memberName)){
			builder.append(" and t.member_name = '").append(memberName).append("'");
		}
		if(StringUtils.isNotBlank(phoneNo)){
			builder.append(" and t.phone_no = '").append(phoneNo).append("'");
		}
		if(StringUtils.isNotBlank(memberType)){
			builder.append(" and t.member_type = '").append(memberType).append("'");
		}
		if(StringUtils.isNotBlank(timeStart)){
			builder.append(" and t.create_time >= str_to_date('").append(timeStart).append("','%Y-%m-%d') ");
		}
		if(StringUtils.isNotBlank(timeEnd)){
			builder.append(" and t.create_time < str_to_date('").append(timeEnd).append("','%Y-%m-%d') ");
		}
		builder.append(" order by t.store_name");
		return builder;
	}
	
	/**
	 * 查询结果分页
	 * @param pageBean
	 * @return
	 */
	private StringBuilder pagination(PageBean pageBean){
		StringBuilder builder = new StringBuilder();
		builder.append(" limit ").append(((pageBean.getPage()> 1 ? pageBean.getPage(): 1) -1) * pageBean.getRows()).append(",").append(pageBean.getRows());
		return builder;
	}

	@Override
	public void exportMembers(OutputStream out, HttpServletRequest request, String memberName, String phoneNo,
			String memberType, String timeStart, String timeEnd) throws IOException {
		UserContext userContext = (UserContext)request.getSession().getAttribute(Constants.session.USER);
		List<Serializable> storeIds = userContext.getStoreIdList();
		if(storeIds == null || storeIds.isEmpty()){
			return;
		}else{
			StringBuilder sql  = initMainSql(storeIds);
			sql.append(assembleQueryParams(memberName,phoneNo,memberType,timeStart, timeEnd));
			List<Object[]> results = baseDao.queryBySQL(sql.toString());
			List<Object[]> rows = new ArrayList<Object[]>();
			for(Object[] objs : results){
				Object[] result = new Object[5];
				result[0]=objs[1];
				result[1]=objs[2];
				result[2]=objs[3];
				result[3]=objs[7];
				result[4]=objs[9];
				rows.add(result);
			}
			String headers[] = { "会员名", "会员类型", "会员电话", "注册时间","所属门店"};
			ExportExcelUtil.exportExcel(headers, rows, out);		
		}
	}
	
	@Resource
	private IBaseDAO baseDao;
}
