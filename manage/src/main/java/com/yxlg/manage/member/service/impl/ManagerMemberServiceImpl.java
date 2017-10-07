package com.yxlg.manage.member.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.constant.Constants.DateFormatConstant;
import com.yxlg.base.dao.IBaseDAO;
import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.member.entity.MemberBankCard;
import com.yxlg.base.member.entity.MemberGrade;
import com.yxlg.base.member.entity.MemberLevelDiscountNo;
import com.yxlg.base.member.entity.MemberType;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.upload.util.UploadByUrl;
import com.yxlg.base.util.BusinessException;
import com.yxlg.base.util.ObtainCurrentUser;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.base.util.QRCodeUtil;
import com.yxlg.manage.member.service.IManagerMemberService;

/**
 * 
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          会员查询接口的实现类
 *          </p>
 */
@Service
public class ManagerMemberServiceImpl implements IManagerMemberService {
	private static final Logger log = LoggerFactory.getLogger(ManagerMemberServiceImpl.class);
	@Override
	public Pagination findMemberWithPageCriteria(PageBean pageBean,
			String memberId, String memberName, String gender, String phoneNo,
			String email, String registerTimeFrom, String registerTimeTo, String memberType, String birthday, String searchDesignerId, 
			String platform, String deviceType, String city) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Member.class);
		if (!StringUtils.isEmpty(memberId)) {
			detachedCriteria.add(Restrictions.like("memberId", memberId,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(memberName)) {
			detachedCriteria.add(Restrictions.like("memberName", memberName,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(gender)) {
			detachedCriteria.add(Restrictions.eq("gender", gender));
		}
		if (!StringUtils.isEmpty(phoneNo)) {
			detachedCriteria.add(Restrictions.like("phoneNo", phoneNo,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(email)) {
			detachedCriteria.add(Restrictions.like("email", email,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(birthday)) {
			detachedCriteria.add(Restrictions.like("birthday", birthday,
					MatchMode.ANYWHERE));
		}
		
		if (!StringUtils.isEmpty(platform)) {
			detachedCriteria.add(Restrictions.like("platform", platform,
					MatchMode.ANYWHERE));
		}
		
		if (!StringUtils.isEmpty(deviceType)) {
			detachedCriteria.add(Restrictions.like("deviceType", deviceType,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(city)) {
			detachedCriteria.add(Restrictions.like("city", city,
					MatchMode.ANYWHERE));
		}
		
		if (!StringUtils.isEmpty(registerTimeFrom)) {
			Date dateFrom = null;
			try {
				dateFrom = DateUtils.parseDate(registerTimeFrom, Constants.DateFormatConstant.DATE_FORMAT);
				detachedCriteria.add(Restrictions.ge("registTime", dateFrom));
			} catch (ParseException e) {
				throw new BusinessException("", e);
			}
			
		}
		if (!StringUtils.isEmpty(registerTimeTo)) {
			Date dateTo = null;
			registerTimeTo += " 23:59:59";
			try {
				dateTo = DateUtils.parseDate(registerTimeTo, Constants.DateFormatConstant.TIME_FORMAT);
				detachedCriteria.add(Restrictions.le("registTime", dateTo));
			} catch (ParseException e) {
				throw new BusinessException("", e);
			}
			
		}
		if (!StringUtils.isEmpty(memberType)) {
			detachedCriteria.createCriteria("memberType").add(Restrictions.like("memberType", memberType,
					MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(searchDesignerId)) {
			detachedCriteria.add(Restrictions.eq("memberLevelDiscountNoId", searchDesignerId));
		}
		detachedCriteria.addOrder(Order.desc("registTime"));
		
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(detachedCriteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
				detachedCriteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public Pagination findPromoteMember(PageBean pageBean) {

		// 查询出所有会员类型
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(MemberType.class);
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(detachedCriteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
				detachedCriteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}


	@Override
	public List<MemberRecommend> findPhoneNo(String memberId) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(MemberRecommend.class);
		DetachedCriteria memberCriteria = detachedCriteria
				.createCriteria("member");
		memberCriteria.add(Restrictions.eq("memberId", memberId));
		List<MemberRecommend> list = baseDao
				.findByDetachedCriteria(memberCriteria);
		return list;
	}

	@Override
	public List<Member> findPhoneNoAndName(String memberId) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(MemberRecommend.class);
		DetachedCriteria memberCriteria = detachedCriteria
				.createCriteria("member");
		memberCriteria.add(Restrictions.eq("memberId", memberId));
		List<MemberRecommend> list = baseDao
				.findByDetachedCriteria(memberCriteria);
		List<Member> list2 = new ArrayList<Member>();
		for (int i = 0; i < list.size(); i++) {
			String phoneNo = list.get(i).getPhoneNo();
			list2.add(baseDao.findUniqueByProperty(Member.class, "phoneNo",
					phoneNo));
		}
		return list2;

	}

	@Override
	public List<MemberAddressInfo> findAddress(String memberId) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(MemberAddressInfo.class);
		DetachedCriteria memberCriteria = detachedCriteria
				.createCriteria("member");
		memberCriteria.add(Restrictions.eq("memberId", memberId));
		List<MemberAddressInfo> list = baseDao
				.findByDetachedCriteria(memberCriteria);
		return list;
	}

	@Override
	public Pagination findMemberGrade(PageBean pageBean, String memberTypeId) {
		/*
		String flag = C2MConstants.MemberType.COMMON_MEMBER_NO;
		if(C2MConstants.MemberType.MARKETING_MEMBER.equals(memberType)) {
			flag = C2MConstants.MemberType.MARKETING_MEMBER_NO;
		} else if(C2MConstants.MemberType.INSIDE_MEMBER.equals(memberType)) {
			flag = C2MConstants.MemberType.INSIDE_MEMBER_NO;
		}else if(C2MConstants.MemberType.INSIDE_MARKETING_MEMBER.equals(memberType)) {
			flag = C2MConstants.MemberType.INSIDE_MARKETING_MEMBER_NO;
		}
		*/
		if(StringUtils.isNotBlank(memberTypeId)){
			//2017-1-11 alisa.yang 由于增加会员类型名称与级别都要修改此处！！！太麻烦！！！直接用id查询才合适
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberGrade.class);
			detachedCriteria.add(Restrictions.eq("memberType", memberTypeId));
			Pagination pagination = new Pagination();
			Long count = baseDao.findAllCountCriteria(detachedCriteria);
			List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
					detachedCriteria);
			pagination.setRows(rows);
			pagination.setTotal(count);
			return pagination;
		}
		return null;
	}

	@Override
	public String promoteMemberGrade(String memberId, String memberGradeId, HttpSession session) {
		
		Serializable[] ids = memberId.split(",");
		List<Serializable> list = Arrays.asList(ids);
		//获取之前的会员类型
		String memberGradePre = baseDao.findById(Member.class, list.get(0)).getMemberGrade().getMemberGradeName();
		//获取当前登录人
		ObtainCurrentUser obtainCurrentUser = new ObtainCurrentUser();
		String userName = obtainCurrentUser.getOperator(session);
		
		Map<String, Object> map = new HashMap<String, Object>();
		MemberGrade memberGrade = baseDao.findById(MemberGrade.class, memberGradeId);
		map.put("memberGrade", memberGrade.getMemberGradeId());
		baseDao.updateBatchByPropertyName(Member.class, map, "memberId", list);
		String dateString = DateFormatUtils.format(new Date(), DateFormatConstant.TIME_FORMAT);
		log.info("会员【" + memberId + "】的会员级别于" + dateString + "由" + memberGradePre + "修改为" + memberGrade.getMemberGradeName() + ",修改人" + userName);
		return Constants.ReturnMsg.SC_SUCCESS;
	}
	
	
	@Override
	public int editAddedName(String memberId, String addedName){
		try {
			Member member = baseDao.findById(Member.class, memberId);
			member.setAddedName(addedName);
			baseDao.update(member);
			
		} catch (Exception e) {
			log.error("客服人员编辑用户姓名出错" + e.getMessage());
			throw new BusinessException("客服人员编辑用户姓名出错,memberId:" + memberId + ",addedName:" + addedName, e);
		}
		return HttpStatus.OK.value();
	}
	
	
	@Override
	public String getVouchersQrcode(String memberIds) {
		memberIds = memberIds.substring(0, memberIds.length() - 1);
		String[] memIds = memberIds.split(",");
		DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
		criteria.add(Restrictions.in("memberId", memIds));
		List<Member> memList = baseDao.findByDetachedCriteria(criteria);
		if (memList.size() == 0) {
			return "会员数据不是最新的，请刷新重试";
		}
		// 生成包含用户 MemberID信息的二维码图片，上传七牛服务器，并把将返回地址保存到数据库中
        String qrCodePath = "images/member/qrcode/vouchers/";
        SimpleDateFormat format = new SimpleDateFormat(Constants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
        int random = (int) (Math.random() * 1000000);
        qrCodePath = qrCodePath + format.format(new Date()) + String.valueOf(random) + ".jpg";
		for (Member member : memList) {
			String qrcodeSavePath = QRCodeUtil.generateQrCode(member.getMemberId(), qrCodePath);
	    	member.setVouchersQrcode(UploadByUrl.decodeQinuPath(qrcodeSavePath));
		}
		baseDao.updateBatch(memList);
		return "选择会员的代金券发放二维码已生成";
	}

	
	

	@Override
	public String setMemberRebate(String memberIds, String rebate) {
		Serializable[] ids = memberIds.split(",");
		List<Serializable> list = Arrays.asList(ids);
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.equals("1", rebate)){
			map.put("rebate", 1);
		}else{
			map.put("rebate", 0);
		}
		baseDao.updateBatchByPropertyName(Member.class, map, "memberId", list);
		return Constants.ReturnMsg.SC_SUCCESS;
	}

	@Override
	public Pagination findLevelDiscountNo(PageBean pageBean) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MemberLevelDiscountNo.class);
		Pagination pagination = new Pagination();
		Long count = baseDao.findAllCountCriteria(criteria);
		List<Object> rows = baseDao.findAllWithPageCriteria(pageBean,
				criteria);
		pagination.setRows(rows);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public String setLevelDiscountNo(String memberId,
			String MemberLevelDiscountNoId) {
		Member member = baseDao.findById(Member.class, memberId);
		member.setMemberLevelDiscountNoId(MemberLevelDiscountNoId);
		baseDao.update(member);
		return Constants.ReturnMsg.SC_SUCCESS;
	}

	@Override
	public Map<String, String> findLevelDiscountNo2() {
	
		List<MemberLevelDiscountNo> memberLevelDiscountNos = baseDao.findAll(MemberLevelDiscountNo.class);
		Map<String, String> map = new HashMap<String, String>();
		for (MemberLevelDiscountNo discountNo : memberLevelDiscountNos) {
			map.put(discountNo.getMemberLevelDiscountNoId(), discountNo.getLevelName());
		}
		return map;
	}

	@Override
	public String discountNo() {
	
		StringBuffer json = new StringBuffer("");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MemberLevelDiscountNo.class);
        List<MemberLevelDiscountNo> discountNoList = baseDao.findByDetachedCriteria(detachedCriteria);
        
        int totalSize = discountNoList.size();
        int curentSize = 1;
        
        json.append("[");
        
        // 拼接json字符串
        for (MemberLevelDiscountNo memberLevelDiscountNo : discountNoList) {
            json.append("{\"id\":").append("\"").append(memberLevelDiscountNo.getMemberLevelDiscountNoId())
            .append("\",\"text\":\"").append(memberLevelDiscountNo.getLevelName()).append("\"");
            
            json.append("}");
            
            if (totalSize != curentSize++) {
                json.append(",");
            }
        }
        
        json.append("]");
        
        return json.toString();
	}

	@Override
	public List<MemberBankCard> findBankCard(String memberId) {
	
		List<MemberBankCard> list = baseDao.findListByProperty(MemberBankCard.class, "member.memberId", memberId);
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.yxlg.manage.member.service.IManagerMemberService#sendMsg(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sendMsg(String memberType, String phoneNos, String projectCode, String var1, String var2, String var3, String var4) throws Exception {
		
		if(StringUtils.isNotBlank(memberType)){

			if(!"-2".equals(memberType)){
				DetachedCriteria criteria = DetachedCriteria.forClass(Member.class);
				if(!"-1".equals(memberType)){
					criteria.createAlias("memberGrade", "mGrade").add(Restrictions.eq("mGrade.memberType", memberType));
				}
				criteria.setProjection(Projections.property("phoneNo"));
				List<String> phoneNoList = baseDao.findByDetachedCriteria(criteria);
				if(phoneNoList != null && !phoneNoList.isEmpty()){
					phoneNos = StringUtils.join(phoneNoList, ",");
				}else{
					return "未找到发送的手机号";
				}
			}else if(StringUtils.isBlank(phoneNos)){
				return "所选手机号不可空";
			}
			return null;
		}
		return "未选接收会员类型";
	}
	
	@Resource
	private IBaseDAO baseDao;
	
}
