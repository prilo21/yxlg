package com.yxlg.manage.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.yxlg.base.member.entity.Member;
import com.yxlg.base.member.entity.MemberAddressInfo;
import com.yxlg.base.member.entity.MemberBankCard;
import com.yxlg.base.memberRecommend.entity.MemberRecommend;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;

/**
 * 
 * @author jerry.qin
 * @version <br>
 *          <p>
 *          会员查询的接口
 *          </p>
 */
public interface IManagerMemberService {

	/**
	 * 通过多个参数分页查询会员列表
	 * 
	 * @param pageBean
	 * @param memberName
	 * @param gender
	 * @param phoneNo
	 * @param email
	 * @return
	 */
	public Pagination findMemberWithPageCriteria(PageBean pageBean,
			String memberId, String memberName, String gender, String phoneNo,
			String email, String registerTimeFrom, String registerTimeT, String memberType, String birthday, String searchDesignerId, 
			String platform, String deviceType, String city);

	/**
	 * 查询会员类型
	 * 
	 * @param pageBean
	 * @return
	 */
	public Pagination findPromoteMember(PageBean pageBean);

	/**
	 * 查询会员等级折扣
	 * @param pageBean
	 * @return
	 */
	public Pagination findLevelDiscountNo(PageBean pageBean);
	
	/**
	 * 设定会员等级折扣
	 * @param memberId
	 * @param MemberLevelDiscountNoId
	 * @return
	 */
	public String setLevelDiscountNo(String memberId, String MemberLevelDiscountNoId);
	
	/**
	 * 查询会员等级折扣2
	 * @return
	 */
	public Map<String, String> findLevelDiscountNo2();
	/**
	 * 会员折扣等级json数据拼接
	 * @return
	 */
	public String discountNo();
	/**
	 * 查询会员等级
	 */
	public Pagination findMemberGrade(PageBean pageBean, String memberTypeId);
	
	/**
	 * 升级会员等级
	 */
	public String promoteMemberGrade(String memberId, String memberGradeId, HttpSession session);
	
	/**
	 * 根据会员id查询出他所推荐的会员
	 * 
	 * @return
	 */
	public List<MemberRecommend> findPhoneNo(String memberId);

	/**
	 * 根据会员id查询出收货地址
	 * 
	 * @param memberId
	 * @return
	 */
	public List<MemberAddressInfo> findAddress(String memberId);

	/**
	 * 根据会员id查询出被推荐人的手机号及姓名
	 * 
	 * @param memberId
	 * @return
	 */
	public List<Member> findPhoneNoAndName(String memberId);
	/**
	 * 客服编辑用户姓名
	 * @return
	 */
	public int editAddedName(String memberId, String addedName);
	
	/**
	 * 给内部会员（门店店员）生成发放代金券的二维码
	 */
	public String getVouchersQrcode(String memberIds);

	/**
	 * 设置会员返不返利
	 * @param memberIds
	 * @param rebate
	 * @return
	 */
	public String setMemberRebate(String memberIds, String rebate);
	/**
	 * 查看会员银行卡
	 * @param memberId
	 * @return
	 */
	public List<MemberBankCard> findBankCard (String memberId);
	
	/**
	 * 给多个手机号发送短信
	 * @param memberType
	 * 			-1	全部会员
	 * 			-2	指定会员
	 * 			1 	普通会员
	 * 			2 	营销会员
	 * 			3	内部会员
	 * @param phoneNos 当memberType为-2时此字段不可空
	 * @param projectCode
	 * @param var1
	 * @param var2
	 * @param var3
	 * @param var4
	 * @return
	 * @throws Exception
	 */
	public String sendMsg(String memberType, String phoneNos, String projectCode, String var1, String var2, String var3, String var4) throws Exception;
}
