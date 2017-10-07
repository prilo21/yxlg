package com.yxlg.manage.member.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.member.entity.MemberBankCard;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.base.util.Result;
import com.yxlg.manage.member.service.IManagerMemberService;
import com.yxlg.member.service.IMemberService;

/**
 * 
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          会员后台管理的controller类
 *          </p>
 */
@Controller("memberController")
@RequestMapping("/member/*")
public class MemberController {

	/**
	 * 查询会员
	 * 
	 * @param pageBean
	 * @param memberName
	 * @param gender
	 * @param phoneNo
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/findByDetachedCriteria", method = RequestMethod.POST)
	public @ResponseBody Pagination findByDetachedCriteria(PageBean pageBean,
			String memberId, String memberName, String gender, String phoneNo,
			String email, String registerTimeFrom, String registerTimeTo, String memberType, String birthday, String MemberLevelDiscountNoId,
			String platform, String deviceType, String city) {

		return memberService.findMemberWithPageCriteria(pageBean, memberId,
				memberName, gender, phoneNo, email, registerTimeFrom, registerTimeTo, memberType, birthday, 
				MemberLevelDiscountNoId, platform, deviceType, city);
	}

	/**
	 * 查询会员类型
	 * 
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/findMemberType", method = RequestMethod.POST)
	public @ResponseBody Pagination findPromoteMember(PageBean pageBean) {

		return memberService.findPromoteMember(pageBean);
	}
	/**
	 * 会员折扣等级查询
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/findLevelDiscountNo", method = RequestMethod.POST)
	public @ResponseBody Pagination findLevelDiscountNo(PageBean pageBean) {
		
		return memberService.findLevelDiscountNo(pageBean);
	}
	/**
	 * 设置会员等级折扣
	 * @param memberId
	 * @param MemberLevelDiscountNoId
	 * @return
	 */
	@RequestMapping(value = "/setLevelDiscountNo", method = RequestMethod.POST)
	public @ResponseBody String setLevelDiscountNo(String memberId, String MemberLevelDiscountNoId) {
		
		return memberService.setLevelDiscountNo(memberId, MemberLevelDiscountNoId);
	}
	/**
	 * 会员折扣等级查询2
	 * @return
	 */
	@RequestMapping(value = "/findLevelDiscountNo2", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> findLevelDiscountNo2() {
		
		return memberService.findLevelDiscountNo2();
	}
	/**
	 * 获取会员等级折扣json数据拼接
	 * @return
	 */
	@RequestMapping(value = "/discountNo", method = RequestMethod.GET)
	public @ResponseBody String discountNo() {
		
		return memberService.discountNo();
	}
	/**
	 * 查询会员等级
	 * 2017-1-11 alisa.yang 条件修改为id查询
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/findMemberGrade", method = RequestMethod.POST)
	public @ResponseBody Pagination findMemberGrade(PageBean pageBean, String memberTypeId) {
		
		return memberService.findMemberGrade(pageBean, memberTypeId);
	}
	
	@RequestMapping(value = "/promoteMemberGrade", method = RequestMethod.POST)
	public @ResponseBody String promoteMemberGrade(String memberId, String memberGradeId, HttpSession session) {
		
		return memberService.promoteMemberGrade(memberId, memberGradeId, session);
	}

	/**
	 * 查询会员所推荐会员的手机号
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/findPhoneNo", method = RequestMethod.POST)
	public @ResponseBody List<MemberRecommend> findPhoneNo(String memberId) {
		return memberService.findPhoneNo(memberId);
	}

	/**
	 * 查询所推荐会员的手机号及姓名
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/findPhoneNoAndName", method = RequestMethod.POST)
	public @ResponseBody List<Member> findPhoneNoAndName(String memberId) {
		return memberService.findPhoneNoAndName(memberId);
	}

	/**
	 * 根据会员id查询出收货地址
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/findAddress", method = RequestMethod.POST)
	public @ResponseBody List<MemberAddressInfo> findAddress(String memberId) {
		return memberService.findAddress(memberId);
	}
	
	@RequestMapping(value = "/editAddedName", method = RequestMethod.POST)
	public @ResponseBody int editAddedName(String memberId, String addedName) {
		return memberService.editAddedName(memberId, addedName);
	}
	
	/**
	 * 给内部会员（门店店员）生成发放代金券的二维码
	 * @param memberIds
	 * @return
	 */
	@RequestMapping(value = "/getVouchersQrcode", method = RequestMethod.POST)
	public @ResponseBody String getVouchersQrcode(String memberIds) {
		return memberService.getVouchersQrcode(memberIds);
	}
	
	/**
	 * 设置会员返不返利
	 * @param memberIds
	 * @param rebate
	 * @return
	 */
	@RequestMapping(value = "/setMemberRebate", method = RequestMethod.POST)
	public @ResponseBody String setMemberRebate(String memberIds,String rebate){
		return memberService.setMemberRebate(memberIds,rebate);
	}
	
	
	
	/**
	 * 查看会员所持银行卡
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/findBankCard", method = RequestMethod.POST)
	public @ResponseBody List<MemberBankCard> findBankCard(String memberId){
		
		return memberService.findBankCard(memberId);
	}
	
	
	@RequestMapping(value = "/msgmail/message", method = RequestMethod.POST)
	public @ResponseBody String sendMsg(String memberType, String phoneNos, String projectCode, String var1, String var2, String var3, String var4) throws Exception{
		return memberService.sendMsg(memberType, phoneNos, projectCode, var1, var2, var3, var4);
	}
	/**
	 * 设置会员地址
	 * @return
	 */
	@RequestMapping(value = "/setMemberCity")
	public @ResponseBody Result setCity(@RequestParam String startTime, @RequestParam String endTime) {
		
		return memberService2.setCity(startTime, endTime);
	}
	@Resource
    private IMemberService memberService2;
	
	@Resource
	private IBaseService baseService;

	@Resource
	private IManagerMemberService memberService;

}
