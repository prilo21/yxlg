package com.yxlg.base.constant;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.yxlg.base.entity.UserContext;
import com.yxlg.base.upload.util.UploadByUrl;
import com.yxlg.base.util.FileLoadUtil;

/**
 * Created by amosc on 5/7/2015.
 */
public class Constants {
	
	private final static Logger log = LoggerFactory.getLogger(Constants.class);
	
	public static final String API_VERSION_1 = "/api/v1";	// 系统用户id
	public static final String API_VERSION_2 = "/api/v2";	// 系统用户id
	public static final String SYSMEMBERID = "8a10a4395238d532015238e03709006a";
	
	/**
	 * header头信息 C2M-Identify
	 */
	public static final String HEADER_C2M_IDENTIFY = "C2M-Identify";
	
	/**
	 * BaseURL
	 */
	public static final String BASE_SERVER_URL = BaseConstant.getInstance().getBASE_SERVER_URL();
	
	public static final String FACTORY_BASE_SERVER_URL = Constants.BaseConstant.getInstance().FACTORY_BASE_SERVER_URL;
	public static final String FACTORY_BASE_XML_SERVER_URL = Constants.BaseConstant.getInstance().FACTORY_BASE_XML_SERVER_URL;
	
	public static final String FACTORY_SIEBEL_XML_SERVER_IP = Constants.BaseConstant.getInstance().FACTORY_SIEBEL_XML_SERVER_IP;
	
	// public static final String BASE_SERVER_URL =
	// "http://www.test.magicmanufactory.com/";
	
	/**
	 * <P>
	 * 查看服务器类型，分为两种，OFFICIAL_SERVER和TEST_SERVER， 可以使用equle去判别。
	 * </p>
	 */
	public static final String SERVER_TYPE = BaseConstant.getInstance().getSERVER_TYPE();
	public static final String SERVER_TYPE_TEST = "TEST_SERVER";// 测试服务器
	public static final String SERVER_TYPE_OFFICAL = "OFFICIAL_SERVER";// 正式服务器
	public static final String SERVER_TYPE_DEV = "DEV_SERVER";// 开发服务器
	
	public static boolean isTestServer() {
	
		return SERVER_TYPE.equals(SERVER_TYPE_TEST);
	}
	
	public static boolean isOfficialServer() {
	
		return SERVER_TYPE.equals(SERVER_TYPE_OFFICAL);
	}
	
	/**
	 * 判断是否为自己本机
	 * 
	 * @return
	 */
	public static boolean isDevServer() {
	
		return SERVER_TYPE.equals(SERVER_TYPE_DEV);
	}

	/**
	 * RCMTM获取量体信息的url，用户名和密码串常量
	 */
	public static final String RCMTM_MEASURE_BASEURL = "http://api.rcmtm.com:7070/order-api/resources/customer/customersize/";
	public static final String RCMTM_PUSH_DATA_URL = "http://api.rcmtm.cn/api/order/customerInfo";
	public static final String RCMTM_MEASURE_USER = "SERVICE";
	public static final String RCMTM_MEASURE_PWD = "e10adc3949ba59abbe56e057f20f883e";
	
	
	/**
	 * 七牛上传面料图片路径
	 */
	public static final String QN_MATERIAL_IMAGE_PATH = "material/image/";
	/**
	 * 一天的毫秒数
	 */
	public static final long ONE_DAY = 24 * 60 * 60 * 1000;
	
	/**
	 * Stone.Cai
	 * 2015年6月19日 14:57:35
	 * 添加
	 * Redis需要刷新的拦截方法
	 */
	public final static class InterceptRedisMethod {
		
		/**
		 * Stone.Cai
		 * 2015年6月19日 15:02:28
		 * 添加
		 * 商品的更新需要清理APP首页接口
		 */
		public static final String GOODS_CASCADEUPDATE = "cascadeUpdate";
		/**
		 * Stone.Cai
		 * 2015年6月19日 15:02:28
		 * 添加
		 * 商品的添加需要清理APP首页接口
		 */
		public static final String GOODS_CASCADEINSERT = "cascadeInsert";
		/**
		 * Stone.Cai
		 * 2015年6月19日 15:59:36
		 * 添加
		 * 商品的删除需要清理APP首页接口
		 */
		public static final String GOODS_CASCADEDELETE = "deleteGoodsById";
		/**
		 * Stone.Cai
		 * 2015年6月19日 16:35:34
		 * 添加
		 * 商品的上下架需要清理APP首页接口
		 */
		public static final String GOODS_UPOFFSTIELF = "upOffStielf";
		/**
		 * Stone.Cai
		 * 2015年6月19日 16:26:29
		 * 添加
		 * 大片的添加需要清理APP首页接口
		 */
		public static final String SUIT_ADD_SUIT = "addSuit";
		/**
		 * Stone.Cai
		 * 2015年6月19日 16:31:58
		 * 添加
		 * 大片的修改需要清理APP首页接口
		 */
		public static final String SUIT_UPDATE_SUIT = "updateSuit";
	}
	
	/**
	 * <p>
	 * 页面常量
	 * </p>
	 */
	public final static class page {
		
		public final static String LOGIN = "redirect:/login.jsp";
		
		public final static String WELCOME = "redirect:/r/main/index";
		
	}
	
	/**
	 * <p>
	 * 页面常量
	 * </p>
	 */
	public final static class OpusType {
		
		public final static String MEIWU = "1";
		public final static String ZAOWU = "2";
	}
	
	/**
	 * <p>
	 * 页面常量
	 * </p>
	 */
	public final static class CoinType {
		
		public final static String VIRTUAL_COIN = "1";
		public final static String CURRENCY = "2";
	}
	
	/**
	 * 
	 * 
	 */
	public final static class CoinChangePurpose {
		
		public final static String NEW_REGISTER_AWARD = "新用户注册奖励酷特币";
		public final static String CAST_REGISTER_AWARD = "推荐用户成功注册奖励酷特币";
		public final static String BACK_MONEY = "购买商品赠送酷特币";
		public final static String COIN_RECOMMEND_AWARD = "建立会员推荐关系奖励酷特币";
		public final static String COIN_TRADE_COST = "购买商品使用酷特币";
		public static final String COIN_TRADE_BACK = "商品退款";
		public static final String COIN_RECOMMENDTRADE_AWARD = "推荐人购买对你奖励的酷特币";
		public static final String COIN_ACTIVITY_AWARD = "活动奖励酷特币";
		public static final String COIN_TACTIC_AWARD = "营销奖励酷特币";
	}
	
	/**
	 * app页面提示语
	 */
	public final static class MSG {
		
		public static final String MSG_COUCHERS_NO_REGISTER_MEMBER = "该店员不是酷特的注册用户，请先注册";
		public static final String MSG_VOUCHERS_USER_INFO_ERROR = "店员信息不正确，请联系客服";
		public static final String MSG_VOUCHERS_NO_QRCODE = "请先扫描店员二维码";
		public static final String MSG_VOUCHERS_QRCODE_ERROR = "二维码无效，请重新扫描";
		public static final String MSG_VOUCHERS_ILLEGAL_QRCODED = "二维码已过期，请重新扫描";
		
		// 礼品卡
		public static final String MSG_GIFTCADRS_ERROR_QRCODE = "二维码无效，请扫描酷特发放的礼品卡";
		public static final String MSG_GIFTCADRS_ERROR_NO_MEMBER = "会员信息错误，不存在该会员";
		public static final String MSG_GIFTCARD_ILLEGAL_QRCODED = "礼品卡已过期，请重新扫描";
	}
	
	/**
	 * <p>
	 * session常量
	 * </p>
	 *
	 */
	public final static class session {
		
		public final static String USER = "_currentUser";	// 用户ID
		public final static String USER_ISACTIVE_1 = "1";	// 记录当前用户是否已登录：1,已登录
		public final static String USER_ISACTIVE_0 = "0";	// 记录当前用户是否已登录：0,未登录
		public final static String THEMES = "_THEMES_SESSION";// 主题
	}
	
	/**
	 * 通过session获取后台操作员相关信息
	 * @param session
	 * @return
	 */
	public static String getUserNameBySession(HttpSession session){
		String userName = null;
		Object object = session.getAttribute(Constants.session.USER);
		if(object != null){
			UserContext userContext = (UserContext)object;
			if(userContext != null && userContext.getUser() != null){
				userName = userContext.getUser().getUserName();
			}
		}
		
		return userName;
	}
	
	/**
	 * 上传文件到七牛返回url的方法
	 * @param file 文件
	 * @param pathName 路径名
	 * @param fileNameId 文件名前缀
	 * @param oldFilePathUrl 原文件路径（被替换，需要删除的）
	 * @return
	 */
	public static String uploadImgFile(MultipartFile file, String pathName, String fileNameId, String oldFilePathUrl){
        if (file != null && !file.isEmpty()) {
            StringBuffer fileName = new StringBuffer("image/"+pathName);
            String originalFilename = file.getOriginalFilename();
            String filesuffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if(StringUtils.isNotBlank(fileName)){
            	fileName.append(fileNameId).append("/");
            }
            String uuid = FileLoadUtil.encodeName("");
            fileName.append(uuid);
            // 更换文件的名字，去除有中文或者其他语言的的影响
            fileName.append(filesuffix);
            if (!StringUtils.isEmpty(oldFilePathUrl)) {
                FileLoadUtil.deletFileByPath(oldFilePathUrl);
            }
            String url = FileLoadUtil.UploadFile(file, fileName.toString());
            return UploadByUrl.decodeQinuPath(url);
        }
        return null;
	}
	
	/**
	 * 
	 * @author Jarvis Lu
	 * @version <br>
	 *          <p>
	 *          短信和邮件模板ID
	 *          </p>
	 */
	public final static class SubmailProject {
		
		/**
		 * 订单生产短信模板
		 */
		public final static String ORDER_PRODUCT="S2auM2";
		/**
		 * 订单发货短信模板
		 */
		public final static String ORDER_SHIPMENT="WZcvY3";
		
		// 给用户发送手机短信，所有短信内容自定义
		// 需要给 msg_content 传短信内容
		public final static String PHONE_COMMON = "BbPV44";
		
		// 用于发送用户注册用的邮箱验证码
		// 需要给变量 register_random_code传一个随机6位数值
		public final static String MAIL_REGISTRATION = "UjgRl2";
		// 用于发送用户注册用的手机验证码
		// 需要给变量 register_random_code传一个随机6位数值
		public final static String PHONE_REGISTRATION = "wNetH3";
		// 用于发送用户修改用户信息用的邮箱验证码
		// 需要给变量 register_random_code传一个随机6位数值
		public final static String MAIL_VERIFICATION = "NBIfp2";
		// 给客服发送聊天记录下载地址
		public final static String MAIL_CHAT_HISTORY_URL = "VBkFR1";
		// 净体推成衣失败警告邮件模板
		public final static String MAIL_MEASURE_TO_CLOTHESSIZE = "ozpjS";
		// 提现审批成功之后发出的邮件
		public final static String MAIL_PAY_AGENT_AUDIT_SECCSSS = "Y3eNk2";
		// 用于发送用户修改用户信息用的手机验证码
		// 需要给变量 register_random_code传一个随机6位数值
		public final static String PHONE_VERIFICATION = "t2dau";
		// 用于预约量体成功后给店长发短信
		public final static String PHONE_MEASUREMENT_MANAGER = "VBWPf3";
		// 用于预约量体成功后给会员发短信
		public final static String PHONE_MEASUREMENT_MEMBER = "aMItt2";
		// 用于量体订单取消之后给量体师发短信
		public final static String PHONE_MEASUREORDER_CANCLE = "YWyIX";
		// 有新的量体预约订单时给客服发送提醒邮件
		public final static String MAIL_NEW_MEASURE_ORDER = "e5LUU2";
		// 给从siebel获取到的下单用户发送短信
		public final static String SIEBEL_ORDER_USER = "QSeea3";
		
		/**
		 * 代付提现成功短信模板
		 */
		public final static String PHONE_PAGENT_SUCCESS = "kIcVv";
		/**
		 * 代付提现失败短信模板
		 */
		public final static String PHONE_PAGENT_FALI = "8jjjZ4";
		/**
		 * 物流更新短信模板
		 */
		public final static String LOGISTICS_UPDATE = "vdyXV1";
		/**
		 * 复星年会大礼包短信模板
		 */
		public final static String SALES_PROMOTION = "f9Mla2";
		
		/**
		 * 缺失面料邮件模板
		 */
		public final static String MAIL_LACK_FABRIC = "RSyMl1";
		/**
		 * 老板电器短信模板
		 */
		public final static String MSG_PHONE_LAOBAN_CODE = "wTqIc1";
		
		/**
		 * 京东众筹短信模板
		 */
		public final static String MSG_PHONE_JD_CODE = "BKLBK3";
		
		public final static String MAIL_OFFICAL_WEBSITE_RECRUIT = "vDgMl3";
		public final static String MAIL_OFFICAL_WEBSITE_JOIN = "SpTZQ1";
		
		/**
		 * 量体活动页面领取完红包发送短信验证码
		 */
		public final static String SALES_PROMOTION_MSG_LIANGTI = "ZBxOS1";
		
	}
	
	/**
	 * 短信内容
	 * 
	 * @author yuandian
	 *
	 */
	public final static class msgContent {
		
		public final static String MSG_PHONE_LAOBAN = "【酷特】尊敬的@var(phone)您好,红包已发送到@var(phone)下,使用该手机号注册【酷特】http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna.";
		
		public final static String MSG_PHONE_REGISTRATION = "【酷特】 @var(register_random_code)（验证码），欢迎注册酷特定制个性化服务，酷特客服绝不会索取此验证码，请勿将此验证码告知他人。";
		
		public final static String MSG_PHONE_VERIFICATION = "【酷特】 @var(register_random_code)（验证码），酷特客服绝不会索取此验证码，请勿将此验证码告知他人。";
		
		public final static String MSG_LOGISTICS_UPDATE = "【酷特】尊敬的会员@var(member)您好,您提交订单的收货信息已更改,新的收货信息是:收货人@var(receiver),电话 @var(mobileNo),细地址 @var(province)@var(city)@var(suburb)@var(address).";
		
		public final static String MSG_PHONE_PAGENT_FALI = "【酷特】尊敬的@var(realName)您好,您账号@var(account)于@var(txnTime)申请提取现金@var(money),因输入的信息有误导致提取现金失败! 请重新操作并感谢您的支持.";
		
		public final static String MSG_PHONE_PAGENT_SUCCESS = "【酷特】尊敬的@var(realName)您好,您账号@var(account)于@var(txnTime)申请提取现金@var(money)已经到账.感谢您的支持.";
	}
	
	/**
	 * <p>
	 * 会员类型
	 * </p>
	 * 普通会员: 1
	 * 营销会员: 2
	 * 内部会员： 3
	 */
	public final static class MemberType {
		
		// 普通会员
		public final static String COMMON_MEMBER = "普通会员";
		
		public final static String COMMON_MEMBER_NO = "1";
		
		// 营销会员
		public final static String MARKETING_MEMBER = "营销会员";
		
		public final static String MARKETING_MEMBER_NO = "2";
		
		// 内部会员
		public final static String INSIDE_MEMBER = "内部会员";
		
		public final static String INSIDE_MEMBER_NO = "3";
		
		// 内部营销会员
		public final static String INSIDE_MARKETING_MEMBER = "内部营销会员";
		
		public final static String INSIDE_MARKETING_MEMBER_NO = "4";
				
		// 客服营销会员
		public final static String SERVICE_MARKETING_MEMBER = "客服营销会员";
				
		public final static String SERVICE_MARKETING_MEMBER_NO = "5";
	}
	
	/**
	 * 会员等级
	 * 
	 * @author jerry
	 *
	 */
	public final static class MemberGrade {
		
		// 注册时 会员赋初级
		public final static String PRIMARY_GRADE = "初级";
		
		// 普通会员初级
		public final static String P1 = "P1";
		
		// 营销会员初级
		public final static String V1 = "V1";
		
		// 营销会员中级
		public final static String V2 = "V2";
		
		// 营销会员高级
		public final static String V3 = "V3";
		
		// 营销会员VIP
		public final static String V4 = "V4";
		
		// 内部会员初级
		public final static String N1 = "N1";
	}
	
	/*
	 * cary
	 * app模块代码
	 */
	public final static class MoudelType {
		
		/*
		 * 奋斗者
		 */
		public final static String FIGHTER = "1";
		/*
		 * 成就者
		 */
		public final static String ACHIEVER = "2";
		/*
		 * 实现者
		 */
		public final static String IMPLEMENTOR = "3";
		/*
		 * 创业者
		 */
		public final static String ENTREPRENEUR = "4";
		
		/*
		 * 分销
		 */
		public final static String DISTRIBUTION_PERSONAL = "5";
		
		/**
		 * 美物
		 */
		public final static int CUSTOMIZATION=1;
		/**
		 * 造物
		 */
		public final static int CUSTOMIZATION_FULL=2;
		/**
		 * 美物微定制
		 */
		public final static int CUSTOMIZATION_SOME=3;
		/**
		 * 成品配饰
		 */
		public final static int ACCESSORY=4;
		
	}
	
	/*
	 * Marivn.Ma
	 * 资讯使用区域
	 */
	public final static class infoArea {
		
		/*
		 * home
		 */
		public final static String HOME = "1";
		/*
		 * index
		 */
		public final static String APPINDEX = "2";
		
	}
	
	/**
	 * 
	 * @author Jarvis Lu
	 * @version <br>
	 *          <p>
	 *          注册相关的常量定义
	 *          </p>
	 */
	public final static class Registration {
		
		public final static String DEFAULT_SIGNATURE = "我爱设计！";
	}
	
	/**
	 * 
	 * @author Jarvis Lu
	 * @version <br>
	 *          <p>
	 *          返回信息定义
	 *          </p>
	 */
	public final static class ReturnMsg {
		
		public final static String TOKEN_ERROR = "授权内容错误";
		public final static String TOKEN_NULL = "无授权信息";
		public final static String MEMBER_LOGIN_INPUT_VALID = "输入信息为空";
		public final static String MEMBER_LOGIN_ACCOUNT_NUMBER_VALID = "手机号或邮箱为空";
		public final static String MEMBER_LOGIN_PASSWORD_VALID = "密码为空";
		public final static String MEMBER_LOGIN_NO_SUCH_USER = "用户不存在";
		public final static String MEMBER_LOGIN_PASSWORD_ERROR = "密码错误";
		public final static String MEMBER_LOGIN_ILLEGLE_USER = "非法用户";
		
		public final static String SC_NOT_FOUND = "信息不存在";
		public final static String SC_SUCCESS = "操作成功";
		public final static String SC_NOT_REPEAT = "已操作成功，请勿重复操作";
		public final static String FA_FALIURE = "操作失败";
		public final static String FA_FALIURE_TIMEOUT = "操作失败,请重新登录";
		public final static String SC_EXCEED_LENGTH = "超出长度限制";
		public final static String SC_FORMAT_ERROR = "输入格式错误";
		public final static String SC_INFO_INCOMPLETE = "信息输入不全";
		public final static String SC_ERROR = "系统繁忙，请联系客服或稍后重试！";
		public final static String SC_NOT_SUCCESS = "N";
		
		public final static String MEMBER_NAME_EMPTY = "注册时用户名不能为空";
		public final static String MEMBER_NAME_VAL_FAILED = "用户名只能为数字、字母、汉字或其组合";
		public final static String MEMBER_PHONE_AND_EMAIL_EMPTY = "手机号和邮箱不能同时为空";
		public final static String MEMBER_PHONE_OR_EMAIL_INVALID = "输入的手机号或者邮箱不合法";
		public final static String MEMBER_PHONE_INVALID = "输入的手机号不合法";
		public final static String MEMBER_PHONE_EDIT_SUCCESS = "手机号修改成功";
		public final static String MEMBER_EMAIL_INVALID = "输入的邮箱不合法";
		public final static String MEMBER_EMAIL_EDIT_SUCCESS = "邮箱修改成功";
		public final static String MEMBER_PHONE_EXIST = "该手机号码已注册,请直接登录";
		public final static String MEMBER_PHONE_NO_IDCODE = "请先选择所属地区";
		public final static String MEMBER_EMAIL_EXIST = "邮箱已经注册过了";
		public final static String MEMBER_EMAIL_CAPTCHA_SENT = "邮箱验证码已成功发送";
		public final static String MEMBER_REG_SUCCESS = "用户注册成功";
		public final static String MEMBER_PASSWORD_ERROR = "用户密码不正确";
		public final static String MEMBER_PASSWORD_NOT_MATCH = "两次输入密码不匹配";
		public final static String MEMBER_NOT_EXIST = "用户不存在";
		public final static String MEMBER_IS_EXIST = "用户存在";
		public final static String MEMBER_NOT_FIND = "N";
		public final static String MEMBER_EDIT_SUCCESS = "用户信息修改成功";
		public final static String MEMBER_PASSWORD_EDIT_SUCCESS = "用户密码修改成功";
		public final static String MEMBER_PASSWORD_RESET_SUCCESS = "用户密码重置成功";
		public final static String MEMBER_RELATION_ADD_SUCCESS = "恭喜您加入酷特,赶快下载app体验个性化定制吧";
		public final static String MEMBER_RELATION_FULL = "您推荐的用户数已达上限";
		public final static String MEMBER_RELATION_EXISTED = "此手机号码已经注册过了";
		public final static String MEMBER_BEFORE_REGISTER_RECOMMENDED = "酷特等你好久了，快来注册吧";
		public final static String MEMBER_BEFORE_REGISTER_NOT_RECOMMENDED = "欢迎加入酷特，快来注册吧";
		public final static String MEMBER_AFTER_REGISTER_RECOMMENDED = "酷特再次等到你，快来登录吧";
		public final static String MEMBER_AFTER_REGISTER_NOT_RECOMMENDED = "欢迎来到酷特，快去登录吧";
		public final static String MEMBER_RELATION_MEMBER_TYPR_ERROR = "您是非营销会员，酷特还没有开放推荐权限";
		public final static String MEMBER_RELATION_OWN = "抱歉！平台不支持自己推荐自己";
		
		public final static String MEMBER_PHONE_ACTIVITY_EXISTED = "该手机号已参加过此活动";
		public final static String PHONE_NOT_MEMBER = "该手机号不是注册会员";
		
		public final static String VIRTUAL_COIN_NOT_FOUND = "没有找到酷特币消费记录";
		public final static String OPUS_NOT_FOUND = "没有找到相关作品";
		public final static String GOODS_NOT_FOUND = "没有找到相关商品";
		public final static String SERIES_NOT_FOUND = "没有找到商品系列信息";
		public final static String SERIES_SUIT_NOT_FOUND = "本系列不包含任何大片信息";
		public final static String POSITION_NOT_FOUND = "没有刺绣位置";
		public final static String PROCESS_NOT_FOUND = "没有找到此工艺";
		
		public final static String MEASURE_ADD_SUCCESS = "量体数据添加成功";
		public final static String MEASURE_ADD_SH_SUCCESS = "量体数据风格和习惯添加成功";
		public final static String MEASURE_STRING_EMPTY = "量体数据不能为空";
		public final static String MEASURE_DATA_OBJECT_EMPTY = "不存在量体人对象或者量体数据对象";
		public final static String MEASURE_MEASURERNAME_EMPTY = "量体人姓名不能为空";
		public final static String MEASURE_PHONE_EMPTY = "量体人手机号不能为空";
		public final static String MEASURE_PROPERTY_INCOMPLETE = "量体属性不存在";
		public final static String MEASURE_PROPERTYNAME_INCOMPLETE = "量体数据值添加的对象属性名不能为空";
		
		public final static String GROUP_ERROR = "团信息错误";
		public final static String MEASURER_NOT_FIND = "量体师不存在";
		public final static String MEASURER_LOGIN_SUCCESS = "量体师登录成功";
		public final static String MEASURER_NO_LOGIN = "量体师未登录";
		public final static String MEASURER_PASSWORD_INCORRECT = "密码不正确";
		public final static String MEASURER_PASSWORD_REPEAT = "新密码不可与原密码相同";
		public final static String MEASURER_INVALID = "量体师无效已被删除";
		public final static String MEASURER_DUPLICATE = "有多个量体师账号";
		
		public final static String QRCODE_GENERATE_SUCCESS = "生成二维码成功";
		public final static String QRCODE_URL_INVALID = "图片路径不合法";
		
		public final static String MEASURER_ORDER_NOT_FOUND = "量体订单不存在";
		public final static String UNFINISHED_ORDER_EXIST = "您已预约成功";
		public final static String MEASURER_ORDER_STATE_NOT_FOUND = "量体订单格式不正确";
		public final static String MEASURER_PERSON_NOT_FOUND = "量体师不存在或量体师不是注册会员";
		public final static String MEASURER_ORDER_INFO_DONE = "此单的量体数据已录入，请勿重复！";
		
		public final static String MEASURER_ORDER_FIREBASE_NULL = "服务器未连接firebase";
		public final static String MEASURER_ORDER_CITY_NULL = "量体订单城市为空";
		public final static String MEASURER_ORDER_TO_WEB = "请用网页版";
		public final static String MEASURER_ORDER_DUPLICATE = "有多条量体订单";
		
		public final static String TRADE_ORDER_PAY_FAL_GRAMAR = "上送报文格式不正确";
		public final static String TRADE_MIN_PAY = "最小支付" + Constants.FactoryConstant.MIN_PAY + "元";
		public final static String TRADE_GOODS_NOT_EXIST = "商品不存在";
		public final static String TRADE_GOODS_NOT_SALE = "9101：商品已下架";
		public final static String TRADE_MATERIAL_NOT_EXIST = "面料不存在";
		public final static String TRADE_OVER_COUNT = "总购买数量超过";
		
		public final static String TRADE_SUIT_NOT_EXIST = "套装不存在";
		public final static String TRADE_ORDER_NOT_EXIST = "您没有相关订单";
		public final static String TRADE_SHARED = "二维码已被扫描，请再次生成";
		public final static String TRADE_SHARE = "二维码只能使用一次，请再次生成";
		public final static String TRADE_ORDER_VIWS_FAIL = "查看订单详情失败";
		public final static String TRADE_ORDER_LIST_VIWS_FAIL = "查看订单列表失败,请升级应用后重试！";
		public final static String TRADE_ORDER_DEL_FAIL = "删除订单失败";
		public final static String TRADE_ORDER_DEL_NOT_ALLOWED = "订单在此状态下不能进行删除";
		public final static String TRADE_ORDER_DEL_SUCCESS = "删除订单成功";
		public final static String TRADE_UNVIRTUALCURRENCY = "商品不允许使用酷特币";
		public final static String STITCHWORK_OVER_LENGTH = "刺绣文字长度不能超过" + Constants.FactoryConstant.MAX_LENGTH_STITCHWORK;
		public final static String TRADE_ORDERPROCESS_NOT_EXIST = "工艺编码不能为空";
		public final static String TRADE_GOODSINFO_ERROR = "goodsTypeId和categories同时为空";
		public final static String TRADE_OVERVIRTUALCURRENCY = "酷特币使用超出商品限制";
		public final static String TRADE_NOVIRTUALCURRENCY = "酷特币不足";
		public final static String TRADE_NOWALLET = "钱包余额不足";
		public final static String TRADE_NOGIFTCARD = "魔卡不足";
		public final static String TRADE_WALLET_ERROR = "钱包余额验证失败";
		public final static String TRADE_NOCOUPON = "优惠券信息错误";
		public final static String TRADE_RECOUPON = "优惠券信息重复";
		public final static String TRADE_TACTIC_ERROR = "组合优惠已无效";
		public final static String TRADE_PRICE_ERROR = "价格获取失败，请联系客服或稍后重试！";
		public final static String TRADE_PROCESSCODE_ERROR = "造物工艺编码校验失败";
		public final static String TRADE_SAVE_ERROR = "订单保存失败";
		public final static String TRADE_RECORD_SAVE_ERROR = "交易消费记录保存失败";
		public final static String TRADE_PHOTO_SAVE_ERROR = "订单快照保存失败";
		public final static String SHOPPING_MAX_MESSAGE="购物车已满";
		public final static String TRADE_ERROR_STATUS="订单状态错误，请刷新";
		public final static String TRADE_ERROR_PAIED="订单已付款成功，请勿重复操作";
		public final static String TRADE_AUTOMATICALLY_CANCEL = "订单超时未付款已自动取消";
		public final static String TRADE_RECANCEL = "订单已被取消";
		public final static String TRADE_NOT_UNPAY = "订单已付款，如取消请联系客服";
		public final static String TRADE_PAYED = "该订单已成功支付";
		public final static String TRADE_CALLY_CANCEL_ERROR = "订单取消失败";
		public final static String TRADE_PAYED_ERROR = "修改订单为已付款失败";
		public final static String TRADE_PAYED_REDIS_ERROR = "redis操作异常!";
		public final static String TRADE_UPDATE_MONEY_ERROR = "扣除酷特币和余额失败";
		public final static String TRADE_PRODUCTION_ERROR = "订单提交生产失败";
		public final static String TRADE_RECEIVED_ERROR = "修改订单为买家确认收货失败";
		public final static String TRADE_REPAIRORDER_RECEIVED_ERROR = "此状态不能确认收货!";
		
		public final static String TRADE_CONFIRM__ERROR = "订单状态不正确确认收货失败";
		public final static String TRADE_REWARDS_VMONEY_ERROR = "奖励酷特币失败";
		public final static String TRADE_REWARDS_MONEY_ERROR = "订单返现失败";
		public final static String TRADE_CONFIRM_INFO_ERROR = "确认收货错误";
		
		public final static String TRADE_GOODS_SIZE_EMPTY = "商品尺寸不能为空";
		public final static String TRADE_GOODS_BODYSHAPE_EMPTY = "体型不能为空";
		public final static String TRADE_FACTORY_ORDERNO_EMPTY = "物流编号不能为空";
		public final static String TRADE_FACTORY_DELIVRYNO_EMPTY = "运单号不能为空";
		public final static String TRADE_FACTOR_INVOICE_EMPTY = "发票打印信息不能为空";
		public final static String TRADE_FACTORY_ORDERNO_ERROR = "订单编号错误";
		public final static String TRADE_FACTORY_ORDER_ERROR = "传参错误";
		public final static String TRADE_FACTORY_CODE_ERROR = "物流编号错误";
		public final static String TRADE_NOMATERIAL = "库存不足";
		public final static String TRADE_NOGOODS = "所选面料已售罄，请定制其他面料";
		public final static String TRADE_HANDWORK_ERROR = "手工工艺价格错误";
		public final static String TRADE_POSITIONS_ERROR = "工艺价格错误";
		public final static String MEMBER_PHONE_VERIFY_ERROR = "验证码错误";
		public final static String MEMBER_PHONE_VERIFY_OVERTIME = "验证码超时";
		public final static String MEMBER_PHONE_NOT_VERIFY = "该手机号尚未请求验证码";
		public final static String MEMBER_PHONE_CAPTCHA_SENT = "手机验证码已成功发送";
		public final static String MEMBER_PHONE_CODE_IN_TIME = "已发验证码有效,无需重新获取";
		public final static String MEMBER_PHONE_CODE_BAD_REQUEST = "请求频繁,稍后重试";
		// 活动礼包
		public final static String SALESPROMOTION_OVERTIME = "很遗憾,活动已经结束了,下次再来!";
		public final static String GET_PACKAGE_SUCCESS = "恭喜您成功领取礼包";
		public final static String GET_PACKAGE_ERROR = "礼包获取失败,请稍后重试";
		public final static String CHECK_PACKAGE_ERROR = "礼包校验码错误";
		public final static String PACKAGE_NOT_EXIST = "礼包不存在";
		public final static String PACKAGE_NOT_ENOUGH = "礼包数量不足";
		public final static String PACKAGE_USED = "礼包已被领取";
		public final static String PACKAGE_EARLY_STRING = "来早了,活动还没开始呢!";
		public final static String PACKAGE_OVERTIME = "很遗憾,活动已经结束了,下次再来!";
		public final static String PACKAGE_RECEIVED = "您已领取，不能重复领取";
		public final static String PACKAGE_HAD_BEEN_RECEVIDED = "您来晚了!这个大礼包已被抢走了";
		public final static String PACKAGE_ERROR_QRCODE = "您扫描的二维码无效!";
		public final static String PACKAGE_LOTTERY_FINISHED = "您的抽奖次数已用完!";
		
		// 实名认证
		public final static String MEMBER_CERTIFICATION_APPLY_SUCCESS = "实名认证提交申请成功";
		public final static String MEMBER_CERTIFICATION_APPLY_EXIST = "已经提交过认证申请";
		public final static String MEMBER_CERTIFICATION_INFO_NO_EXIST = "已经提交过认证申请";
		public final static String MEMBER_CERTIFICATION_INFO_FIND_SUCCESS = "实名认证信息获得成功";
		public final static String MEMBER_CERTIFICATION_APPLY_UPDATE_SUCCESS = "实名认证信息修改成功";
		
		// 推广活动
		public final static String PHONE_EMPTY = "手机号为空";
		public final static String ORDER_NO_EMPTY = "订单号为空";
		public final static String PHONE_CODE_EMPTY = "校验码为空";
		public final static String SALES_PROMOTION_PACKAGE_ID_EMPTY = "礼包id为空";
		public final static String PACKAGE_PASSWORD_EMPTY = "礼包密码为空";
		public final static String INVALID_PHONE_CODE = "验证码错误";
		public final static String PHONE_CODE_OVERTIME = "校验码已过期";
		
		//微信账号未授权
		public final static String PERMISSION_DENIED = "该账号没有授权 ，请联系管理员添加权限";
	}
	
	/**
	 * Dirk.Sun
	 * 2015-05-19
	 * 工厂端接口常量
	 */
	public final static class FactoryConstant {
		
		/**
		 * 红领工厂数据源标识
		 */
		public final static String FACTORY_REDCOLLAR = "redcollar";
		
		/**
		 * 推荐店员返现
		 */
		public final static String CLERK_BACK = "7";
		/**
		 * 服务店员返现
		 */
		public final static String SERVER_BACK = "3";
		/**
		 * 专业发票类型
		 */
		public final static String SPECIAL_INVOICE = "0";
		/**
		 * 普通发票类型
		 */
		public final static String NORMAL_INVOICE = "2";
		
		/**
		 * IOS
		 */
		public final static String IOS = "IOS";
		/**
		 * Android
		 */
		public final static String Android = "Android";
		/**
		 * PC
		 */
		public final static String PC = "PC";
		/**
		 * 名称空间
		 */
		public final static String NAMESPACE = "http://services.redcollar.cn/";
		
		/**
		 * 修改工厂端接口url为172.16.7.78:8080
		 * 面料wsdl地址 配饰提货
		 */
		// public final static String WSDL_FYERP_LOCATION =
		// "http://services.rcmtm.com/services/FyPayAgentRealTimeRecorderpService?wsdl";
		
		// public final static String WSDL_FYERP_LOCATION =
		// "http://172.16.7.78:8080/Services/services/FyerpService?wsdl";
		public final static String WSDL_FYERP_LOCATION = FACTORY_BASE_XML_SERVER_URL + "services/FyerpService?wsdl";
		
		/**
		 * 订单wsdl地址
		 * 返修订单提交WSDL 地址
		 */
		// public final static String WSDL_BXPP_LOCATION =
		// "http://172.16.7.78:8080/Services/services/BxppService?wsdl";
		public final static String WSDL_BXPP_LOCATION = FACTORY_BASE_XML_SERVER_URL + "services/BxppService?wsdl";
		
		/**
		 * 物流wsdl地址
		 */
		public final static String WSDL_LOGISTIC_LOCATION = FACTORY_BASE_XML_SERVER_URL + "services/LogisticService?wsdl";
		
		/**
		 * Stone.Cai
		 * 2015年6月26日 10:49:17
		 * 添加
		 * 商品工艺码 WSDL 地址
		 */
		public final static String WSDL_CRAFTWORKCODE_LOCATION = FACTORY_BASE_XML_SERVER_URL + "services/BxppService?wsdl";
		
		/**
		 * add by fengfeng.yu 20160926
		 * 
		 * 添加净体推成衣(预下单)WSDL地址
		 * 
		 */
		public final static String WSDL_MEASURE2CLOTH_LOCATION = FACTORY_BASE_XML_SERVER_URL + "services/BxppService?wsdl";
		/**
		 * cary 20170106
		 * 商品预计交货日期WSDL地址
		 */
		public final static String EXPECTED_DELIVERY_DATE = FACTORY_BASE_SERVER_URL + "services/FyerpService?wsdl";
		
		/**
		 * 提交订单接口地址（json）
		 */
		public final static String JSON_ORDER_LOCATION = FACTORY_BASE_SERVER_URL + "jservice/api/order/queue/sendOrderInfo";
		
		/**
		 * 量体数据生成成衣尺寸接口地址(JSONs)
		 */
		public final static String CYSIZE_LOCATION = FACTORY_BASE_SERVER_URL + "jservice/api/order/cysize";
		
		/**
		 * cary 20170106
		 * 商品预计交货日期方法名
		 */
		public final static String EXPECTED_DELIVERY_DATE_METHOD = "reckonDateByCapacity";
		/**
		 * cary 20170106
		 * 配饰预计交货日期方法
		 */
		public final static String EXPECTED_DELIVERY_DATE_NAME = "reckonDateByFactory";
	
		/**
		 * add by fengfeng.yu @2016-09-26
		 * 净体推成衣(预下单)方法
		 */
		public final static String METHOD_MEASURE2CLOTH = "doAdvanceSaveOrder";
		
		
		/**
		 * 获取库存面料方法名
		 */
		public final static String METHOD_FABRIC_STOCK_GET = "getFabricStock";
		
		/**
		 * 批量查询面料库存方法名
		 */
		public final static String METHOD_FABRIC_STOCK_CHECK = "checkFabricStock";
		
		/**
		 * 面料预占用方法名
		 */
		public final static String METHOD_FABRIC_ADVANCE_ACCOUNT = "doAdvanceAccountFabric";
		
		/**
		 * 工厂查询是否可以退货
		 */
		public final static String METHOD_TRADE_REFUND_QUERY = "doDelOrder";
		
		/**
		 * 
		 */
		public final static String METHOD_CONVERT_ORDER_STYLE_QUERY = "doConvertOrderToStyle";
		
		/**
		 * 提交生产方法名
		 */
		public final static String METHOD_ORDER_SAVE = "doSaveOrder";
		/**
		 * 配饰提货
		 */
		public final static String  METHOD_AUTODELIVERY  ="doAutoDeliveryByJson";
		
		/**
		 * 配饰配货
		 */
		public final static String  METHOD_ALLOCATIONGOODS  ="doAllocationGoodsByJson";
		
		/**
		 * 查询产品库存
		 */
		public final static String METHOD_CHECKPRODUCTSTCOK = "checkProductCodesStock";
		/**
		 * 提交返修单生产servers名
		 */
		public final static String METHOD_REPAIRORDER_SAVE = "IServiceToBxppService";
		/**
		 * 提交返修单生产名
		 */
		public final static String METHOD_REPAIRORDER_NAME = "IServiceToBxpp";
		
		/**
		 * 提交物流信息方法名
		 */
		public final static String METHOD_LOGISTICS_SAVE = "doSaveLogistic";
		
		/**
		 * 调用返回状态key值
		 */
		public final static String KEY_INVOKE_STATUS = "statusCode";
		
		/**
		 * 调用返回方法体key值
		 */
		public final static String KEY_INVOKE_BODY = "body";
		
		/**
		 * 全部面料
		 */
		public final static String FABRIC_ALL = "DBQ696A,DBQ1010,DBQ1011,DBQ1015,DBQ1016,DBQ900A,DBQ902A,DBQ904A,DBQ906A,SAQ451A,SAQ453A,DBQ916A,FLL149,FLL150,FLL178,FLL181,FLL603-132,FLL605-580,FLL623-HS,FLL624-050,FLL624-112,FLL3002-9,FLL3002-10,FLL3223-47,FLL7142-1,FLL7142-5,FLL8808N-509,FLL9727-11,FLL9727-BK,FLL86742-2,FLLDL009,FLLDL012,FLLDL013,FLLDL016,FLLDL023,FLLDL024,FLLDL032,FLLDL035,FLLDL037,FLLDL055,FLLF502-210,FLLVPX116,SAK067A,DBN262A,SAP074A,SAP631A,SAP633A,SAP040A,SAQ080A,SAN556A,SAM703A,SAN552A,SAP225A,SAN436A,SAP045A,SAM076A,SAM064A,SAQ022A,SAI303A,SAP235A,SAP224A,SAP234A,SAL038A,SAQ015A,SAQ014A,SAN425A,SAN424A,SAN057A,SAM705A,SAN564A,SAN563A,SAP230A,SAP231A,SAP232A,SAQ054A,SAQ058A,SAP625A,SAP626A,SAQ051A,SAQ052A,SAN090A,SAN129A,DBN263A,DBP553A,DBP552A,DBM590A,DBQ101A,DBQ105A,DBP680A,DBF344A,DBN417A,DBP753A,DBP749A,DBP676A,DBP745A,DBP323A,DBP690A,DBP636A,DBP685A,DAP270A,DBP740A,DBP646A,DBK052A,DBN982A,DBN983A,DBP594A,DBP595A,DBP934A,DBP933A,DBN859A,DAP384A,DAP383A,DAP960A,DAP966A,DBK544A,DBM568A,DBM570A,DBN265A,DBN266A,DBN267A,DBN270A,DBN310A,DBN312A,DBN320A,DBN321A,DBN322A,DBN342A,DBN343A,DBN345A,DBN346A,DBN383A,DBN395A,DBN795A,DBN846A,DBN850A,DBN851A,DBN852A,DBN853A,DBN858A,DBN865A,DBP031A,DBP032A,DBP033A,DBP035A,DBP037A,DBP038A,DBP040A,DBP042A,DBP1060,DBP1070,DBP1287,DBP160A,DBP181A,DBP182A,DBP183A,DBP189A,DBP206A,DBP207A,DBP208A,DBP213A,DBP218A,DBP532A,DBP590A,DBP591A,DBP596A,DBP597A,DBP688A,DBP689A,DBP744A,DBP952A,DBQ056A,DBQ131A,DBQ396A,DBQ500A,DBQ525A,DBQ526A,DBQ527A,DBQ528A,DBQ618A,DBQ692A,DBQ693A,DBQ694A,DBQ695A,DBQ697A,SAK066A,SAK434A,SAK567A,SAL173A,SAM049A,SAM122A,SAM123A,SAM706A,SAN024A,SAN025A,SAN035A,SAN036A,SAN056A,SAN414A,SAN428A,SAN429A,SAN431A,SAN454A,SAN464A,SAN468A,SAN650A,SAN652A,SAN653A,SAP006A,SAP023A,SAP031A,SAP049A,SAP050A,SAP051A,SAP096A,SAP452A,SAP455A,SAP615A,SAP616A,SAP629A,SAP630A,SAQ002A,SAQ003A,SAQ005A,SAQ011A,SAQ025A,SAQ036A,SAQ060A,SAQ066A,SAQ074A,SAQ078A,SAQ079A,SAQ550A,DAP2202,DAP2204,DAP2308,DAP875A,DAP962A,DAP963A,DAP967A,DAP969A,DAP973A,DAQ863A,DAQ899A,DAQ908A,DAR1161,DAR1247,DAR930A,DAR948A,SAP012A,SAP175A,DBN794A,SAP453A,DBN793A,SAP454A,DBQ190A,DBQ187A,DBP555A,DBP950A,DBP903A,DAP964A,DBP531A,DAR1206,DBQ061A,DBQ706A";
		
		/**
		 * 面料阀值
		 */
		public final static String FABRIC_THRESHOLD = "3";
		
		/**
		 * 面料库存有
		 */
		public final static String FABRIC_HAVE = "1";
		
		/**
		 * 面料库存无
		 */
		public final static String FABRIC_NONE = "0";
		
		/**
		 * 超时时间
		 */
		public final static Integer TIME_OUT = 10000;
		
		/**
		 * 超时时间
		 */
		public final static Integer REFUND_TIME_OUT = 60000;
		
		/**
		 * 订单前缀,测试时使用，正式发布时使用C2MA
		 */
		public final static String ORDER_PREFIX = "C2MB";
		
		/**
		 * 订单前缀：奋斗者
		 */
		public final static String ORDER_PREFIX_FIGHTERS = "C2MF";
		
		/**
		 * 订单前缀：成就者
		 */
		public final static String ORDER_PREFIX_ACHIEVERS = "C2MA";
		
		/**
		 * 订单前缀：实现者
		 */
		public final static String ORDER_PREFIX_IMPLEMENTERS = "C2MI";
		
		/**
		 * 订单前缀：分销个人
		 */
		public final static String ORDER_PREFIX_DISTRIBUTION = "C2MA";
		
		/**
		 * 订单前缀：创业者
		 */
		public final static String ORDER_PREFIX_ENTREPRENEURS = "C2ME";
		/**
		 * 返修单订单号
		 */
		public final static String REPAIR_ORDER_CODE = "FX2M";
		
		/**
		 * 订单最小付款(元)
		 */
		public final static double MIN_PAY = 1.0;
		
		/**
		 * 工厂刺绣字数限制
		 */
		public final static int MAX_LENGTH_STITCHWORK = 12;

		public static final String WX = "WX";
	}
	
	
	
	/**
	 * 日期格式常量
	 * 
	 * @author dirk
	 * @version <br>
	 */
	public final static class DateFormatConstant {
		
		/**
		 * yyyy-MM-dd
		 */
		public final static String DATE_FORMAT = "yyyy-MM-dd";
		/**
		 * yyyy年MM月dd日
		 */
		public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";
		/**
		 * yyyy-MM-dd HH:mm:ss
		 */
		public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
		/**
		 * yyyy年MM月dd日 HH:mm:ss
		 */
		public final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";
		/**
		 * yyyy-MM
		 */
		public final static String MONTH_FORMAT = "yyyy-MM";
		/**
		 * yyyyMMdd
		 */
		public final static String DAY_FORMAT = "yyyyMMdd";
		/**
		 * yyyyMMddHHmmss
		 */
		public final static String TIME_FORMAT_FOR_NAME = "yyyyMMddHHmmss";
		/**
		 * yyMMddHHmmss
		 */
		public final static String TIME_FORMAT_FOR_SHORT = "yyMMddHHmmss";
	}
	
	
	/**
	 * 
	 * @author Derrick.Yang
	 * @version <br>
	 *          <p>
	 *          逻辑删除状态常量
	 *          </p>
	 */
	/**
	 * 删除状态
	 */
	public final static String DELETED_FLAG = "Y";
	/**
	 * 未删除状态
	 */
	public final static String UN_DELETED_FLAG = "N";
	
	/**
	 * @author Derrick.Yang
	 * @version <br>
	 *          <p>
	 *          大片上下架状态常量
	 *          </p>
	 */
	/**
	 * 上架状态
	 */
	public final static String UP_SHIELF = "N";
	/**
	 * 下架状态
	 */
	public final static String OFF_SHIELF = "Y";
	
	/**
	 * @author Derrick.Yang
	 * @version <br>
	 *          <p>
	 *          大片查询常量
	 *          </p>
	 */
	/**
	 * 全部大片
	 */
	public final static String ALL_SUIT = "";
	/**
	 * 非设计师设计的大片
	 */
	public final static String NO_DESIGNER = "none";
	
	/**
	 * 后台用户默认密码
	 */
	public final static String DEFAULT_PWD = "111111";
	
	/**
	 * URL常量
	 * 
	 * @author Skeet WU
	 */
	private final static class BaseConstant {
		
		private String BASE_SERVER_URL;
		private String SERVER_TYPE;
		private String FACTORY_BASE_SERVER_URL;
		private String FACTORY_BASE_XML_SERVER_URL;
		private String FACTORY_SIEBEL_XML_SERVER_IP;
		
		private String BASE_FIREBASE_URL;
		private String FIREBASE_AUTH_TOKEN;
		private String FIREBASE_DEFAULT_UID;
		// 友盟常量key secret
		// 量体师APP
		private String UMENG_MEASUREPERSON_KEY;
		private String UMENG_MEASUREPERSON_SECRET;
		// 酷特APP
		private String UMENG_MAGICMANUFACTORY_IOS_KEY;
		private String UMENG_MAGICMANUFACTORY_IOS_SECRET;
		private String UMENG_MAGICMANUFACTORY_ANDROID_KEY;
		private String UMENG_MAGICMANUFACTORY_ANDROID_SECRET;
		
		// 友盟推送环境 常量
		private String UMENG_ENVIRONMENT;
		
		//微信小程序appid&appsecret
		private String WEIXIN_XCX_APPID;
		private String WEIXIN_XCX_APPSECRET;
		
		private String MEASURE_RECOMMEND_URL;
		
		private String HW_APPID_KEY;
		private String HW_SECRET;
		private String HW_USER_INFO_URL;
		private String HW_PUSH_MEASURE_URL;
		
		private BaseConstant() {
		
			try {
				String url = Thread.currentThread().getContextClassLoader().getResource("").toString();
				String path = "";
				
				String os = System.getProperty("os.name");
				if (os.toLowerCase().startsWith("win")) {
					path = url.substring(6) + "properties/base_config.properties";
				} else {
					path = url.substring(5) + "properties/base_config.properties";
				}
				log.info("properties的路径是" + path);
				Properties props = new Properties();
				props.load(new FileInputStream(path));
				BASE_SERVER_URL = props.getProperty("base.server.url");
				SERVER_TYPE = props.getProperty("base.server.type");
				FACTORY_BASE_SERVER_URL = props.getProperty("factory.base.server.url");
				FACTORY_BASE_XML_SERVER_URL = props.getProperty("factory.base.xml.server.url");
				FACTORY_SIEBEL_XML_SERVER_IP = props.getProperty("factory.siebel.xml.server.ip");
				
				BASE_FIREBASE_URL = props.getProperty("base.firebase.url");
				FIREBASE_AUTH_TOKEN = props.getProperty("base.firebase.auth.token");
				FIREBASE_DEFAULT_UID = props.getProperty("base.firebase.default.uid");
				
				UMENG_MEASUREPERSON_KEY = props.getProperty("umeng.measureperson.key");
				UMENG_MEASUREPERSON_SECRET = props.getProperty("umeng.measureperson.secret");
				UMENG_MAGICMANUFACTORY_IOS_KEY = props.getProperty("umeng.magicmanufactory.ios.key");
				UMENG_MAGICMANUFACTORY_IOS_SECRET = props.getProperty("umeng.magicmanufactory.ios.secret");
				UMENG_MAGICMANUFACTORY_ANDROID_KEY = props.getProperty("umeng.magicmanufactory.android.key");
				UMENG_MAGICMANUFACTORY_ANDROID_SECRET = props.getProperty("umeng.magicmanufactory.android.secret");
				
				UMENG_ENVIRONMENT = props.getProperty("umeng.environment");
				WEIXIN_XCX_APPID = props.getProperty("wxapp.appid");
				WEIXIN_XCX_APPSECRET = props.getProperty("wxapp.AppSecret");
				
				MEASURE_RECOMMEND_URL = props.getProperty("measure.recommend.url");
				
				HW_APPID_KEY = props.getProperty("hw.appid.key");
				HW_SECRET = props.getProperty("hw.secret");
				HW_USER_INFO_URL = props.getProperty("hw.user.info.url");
				HW_PUSH_MEASURE_URL = props.getProperty("hw.push.measure.url");
				

			} catch (Exception e) {
				log.error("读取base_config.properties文件失败", e);
			}
		}
		
		private static BaseConstant instance = null;
		
		public static synchronized BaseConstant getInstance() {
		
			if (instance == null) {
				instance = new BaseConstant();
			}
			return instance;
		}
		
		/**
		 * @return the bASE_SERVER_URL
		 */
		private String getBASE_SERVER_URL() {
		
			return BASE_SERVER_URL;
		}
		
		private String getSERVER_TYPE() {
		
			return SERVER_TYPE;
		}
		
		private String getBASE_FIREBASE_URL() {
		
			return BASE_FIREBASE_URL;
		}
		
		private String getFIREBASE_AUTH_TOKEN() {
		
			return FIREBASE_AUTH_TOKEN;
		}
		
		private String getFIREBASE_DEFAULT_UID() {
		
			return FIREBASE_DEFAULT_UID;
		}
		
		public String getUMENG_MEASUREPERSON_KEY() {
		
			return UMENG_MEASUREPERSON_KEY;
		}
		
		public String getUMENG_MEASUREPERSON_SECRET() {
		
			return UMENG_MEASUREPERSON_SECRET;
		}
		
		public String getUMENG_MAGICMANUFACTORY_IOS_KEY() {
		
			return UMENG_MAGICMANUFACTORY_IOS_KEY;
		}
		
		public String getUMENG_MAGICMANUFACTORY_IOS_SECRET() {
		
			return UMENG_MAGICMANUFACTORY_IOS_SECRET;
		}
		
		public String getUMENG_MAGICMANUFACTORY_ANDROID_KEY() {
		
			return UMENG_MAGICMANUFACTORY_ANDROID_KEY;
		}
		
		public String getUMENG_MAGICMANUFACTORY_ANDROID_SECRET() {
		
			return UMENG_MAGICMANUFACTORY_ANDROID_SECRET;
		}
		
		public String getUMENG_ENVIRONMENT() {
		
			return UMENG_ENVIRONMENT;
		}

		public String getWEIXIN_XCX_APPID() {
		
			return WEIXIN_XCX_APPID;
		}

		public String getWEIXIN_XCX_APPSECRET() {
		
			return WEIXIN_XCX_APPSECRET;
		}
		public String getMEASURE_RECOMMEND_URL() {
			
			return MEASURE_RECOMMEND_URL;
		}
		public String getHW_APPID_KEY() {
			
			return HW_APPID_KEY;
		}
		public String getHW_SECRET() {
			
			return HW_SECRET;
		}
		public String getHW_USER_INFO_URL() {
	
			return HW_USER_INFO_URL;
		}
		public String getHW_PUSH_MEASURE_URL() {
	
			return HW_PUSH_MEASURE_URL;
		}
	}
	
	/**
	 * 
	 * @author Michael.Sun
	 * @version <br>
	 *          <p>
	 *          造物价格常量
	 *          </p>
	 */
	public final static class FullCustomPrice {
		
		public static final String CATEGORY_ID_ERROR = "品类ID错误";
		public static final String NO_SUCH_MATERIAL = "无此面料";
		public static final String MATERIAL_NO_RELATE_PROCESS = "面料未关联加工费";
		public static final String LACK_PROCESS = "面料加工费关联数据缺少加工费";
		public static final String LACK_PROCESS_RECORD = "加工费表没有该加工费记录";
		public static final String NO_RELATE_CATEGORY = "加工费未关联品类";
		public static final String RELATE_CATEGORY_ERROR = "加工费关联品类错误";
		public static final String NO_PRICE_RATIO = "定价系数为空";
		public static final String PROCESS_VALID = "加工费为空";
		public static final String INNER_SERVER_ERROR = "服务器错误";
		
	}
	
	/**
	 * @author dirk
	 * @version <br>
	 *          <p>
	 *          订单与工厂交互状态
	 *          </p>
	 */
	public final static class ManufactoryState {
		
		/**
		 * 未提交到工厂
		 */
		public static final String STATE_UNCOMMITTED = "0";
		
		/**
		 * 已提交到工厂队列
		 */
		public static final String STATE_COMMITED = "1";
		
		/**
		 * 已得到工厂处理后响应:正确提交
		 */
		public static final String STATE_COMMITED_CALLBACK_SUCCESS = "2";
		
		/**
		 * 已得到工厂处理后响应：错误提交
		 */
		public static final String STATE_COMMITED_CALLBACK_FALIURE = "3";
		
		/**
		 * 提交物流：成功
		 */
		public static final String STATE_LOGISTICS_SUCCESS = "21";
		
		/**
		 * 提交物流：失败
		 */
		public static final String STATE_LOGISTICS_FAILURE = "22";
		
		/**
		 * 工厂制版
		 */
		public static final String MANUFACTURECODE_PLATE = "10030";
		/**
		 * 工厂生产
		 */
		public static final String MANUFACTURECODE_PRODUCTION = "10031";
		/**
		 * 工厂备货
		 */
		public static final String MANUFACTURECODE_PREPARE = "10032";
		/**
		 * 工厂发货
		 */
		public static final String MANUFACTURECODE_SHIPMENT = "10033";
		/**
		 * 工厂状态已撤单
		 */
		public static final String MANUFACTURECODE_REFUND = "99999";
	}
	
	public final static class AppVersion {
		
		/**
		 * 没有版本信息，默认
		 */
		public static final String VERSION_UNKNOWN = "1.0";
		/**
		 * 20150908前IOS版本
		 */
		public static final String VERSION_IOS_5_0 = "5.0";
		/**
		 * 20150911前IOS版本
		 */
		public static final String VERSION_IOS_5_1 = "5.1";
		/**
		 * 20150911前安卓版本
		 */
		public static final String VERSION_AND_1_4 = "1.4";
		/**
		 * 20150923封版IOS版本
		 */
		public static final String VERSION_IOS_5_2 = "5.2";
		/**
		 * 20150923封版ANDROID版本
		 */
		public static final String VERSION_AND_1_5 = "1.5";
		/**
		 * 20151008IOS测试版本
		 */
		public static final String VERSION_IOS_5_3 = "5.3";
		/**
		 * 20151008ANDROID测试版本
		 */
		public static final String VERSION_AND_1_6 = "1.6";
		/**
		 * 20151109IOS测试版本
		 */
		public static final String VERSION_IOS_5_4 = "5.4";
		/**
		 * 20151202IOS正式版本
		 */
		public static final String VERSION_IOS_5_5 = "5.5";
		/**
		 * 20151109ANDROID测试版本
		 */
		public static final String VERSION_AND_1_7 = "1.7";
		/**
		 * 20151209ANDROID测试版本
		 */
		public static final String VERSION_AND_1_9 = "1.9";
		/**
		 * 20160102IOS正式版本
		 */
		public static final String VERSION_IOS_5_6 = "5.6";
		/**
		 * 20160201IOS正式版本
		 */
		public static final String VERSION_IOS_5_7 = "5.7";
		public static final String VERSION_IOS_5_8 = "5.8";
		/**
		 * 20160109ANDROID测试版本
		 */
		public static final String VERSION_AND_2_1 = "2.1";
		
		public static final String VERSION_AND_2_2 = "2.2";
		
		public static final String VERSION_AND_2_3 = "2.3";
		
		public static final String VERSION_AND_2_4 = "2.4";
		
		public static final String VERSION_AND_2_5 = "2.5";
		
		public static final String VERSION_AND_2_6 = "2.6";
		
		public static final String VERSION_AND_2_8 = "2.8";
		
		public static final String VERSION_AND_2_8_1 = "2.8.1";
		public static final String VERSION_AND_3_1 = "3.1";
		// 20160405下一版Android app版本号
		public static final String VERSION_AND_3_1_0 = "3.1.0";
		
		//jerry.qin 2016年5月26日 
		public static final String VERSION_AND_3_4_0 = "3.4.0";
		public static final String VERSION_IOS_5_9 = "5.9";
		
		//jerry.qin 2016年11月16日
		public static final String VERSION_AND_3_9_1 = "3.9.1";
		public static final String VERSION_IOS_6_294 = "6.294";
		//Michael.Sun 2016年12月13日
		public static final String VERSION_AND_4_0_0 = "4.0.0";
		public static final String VERSION_IOS_6_299 = "6.299";
		
	}
	
	
	public final static class UMengConstant {
		
		public static final String UMENG_MEASUREPERSON_KEY = BaseConstant.getInstance().getUMENG_MEASUREPERSON_KEY();
		public static final String UMENG_MEASUREPERSON_SECRET = BaseConstant.getInstance().getUMENG_MEASUREPERSON_SECRET();
		public static final String UMENG_MAGICMANUFACTORY_IOS_KEY = BaseConstant.getInstance().getUMENG_MAGICMANUFACTORY_IOS_KEY();
		public static final String UMENG_MAGICMANUFACTORY_IOS_SECRET = BaseConstant.getInstance().getUMENG_MAGICMANUFACTORY_IOS_SECRET();
		public static final String UMENG_MAGICMANUFACTORY_ANDROID_KEY = BaseConstant.getInstance().getUMENG_MAGICMANUFACTORY_ANDROID_KEY();
		public static final String UMENG_MAGICMANUFACTORY_ANDROID_SECRET = BaseConstant.getInstance().getUMENG_MAGICMANUFACTORY_ANDROID_SECRET();
		public static final String UMENG_ENVIRONMENT = BaseConstant.getInstance().getUMENG_ENVIRONMENT();
	}
	
	//微信小程序常量
	public final static String WXAPP_APPID = BaseConstant.getInstance().getWEIXIN_XCX_APPID();
	public final static String WXAPP_SECRET = BaseConstant.getInstance().getWEIXIN_XCX_APPSECRET();
	
	
	/**
	 * 常用变量
	 * 
	 * @author Cary
	 * @version <br>
	 *          <p>
	 *          类的描述
	 *          </p>
	 */
	public final static class constant {
		
		public static final String ZERO = "0";
		public static final String ONE = "1";
		public static final String TWO = "2";
		public static final String THREE = "3";
		public static final String FOUR = "4";
		
		public static final int ZERO_INT = 0;
		public static final int ONE_INT = 1;
		public static final int TWO_INT = 2;
		public static final int THREE_INT = 3;
		public static final int FOUR_INT = 4;
		
		public static final String YES = "Y";
		public static final String NO = "N";
		
	}
	
	public final static class qiuniuUrl {
		
		public static final String GOOD_DETAILS_TEMPLATE_URL = "html/goods_details/tem/";
		
	}
	
	
	/**
	 * 分享途径
	 * 
	 * @author Cary
	 * @version <br>
	 *          <p>
	 *          类的描述
	 *          </p>
	 */
	public final static class Platform {
		
		/**
		 * 分享有效期（天）
		 * 活动2015-12-24原2天修改为30天
		 */
		public static final int EFFICACIOUS_DAY = 30;
		
		/**
		 * 短信
		 */
		public static final String MESSAGE = "10";
		
		/**
		 * 短信
		 */
		public static final String WEIXIN = "20";
		/**
		 * 朋友圈
		 */
		public static final String FRIENDS = "30";
		
	}
	
	public final static class SqlConstantsClass {
		
		public static final String DEMO_RETRIEVE_HQL = "retrievePersonHql";
		
		public static final String DEMO_RETRIEVE_HQL_WITH_PARAM = "retrievePersonHqlWithParam";
		
		public static final String DEMO_RETRIEVE_SQL = "retrievePersonSql";
		
		public static final String DEMO_RETRIEVE_SQL_WITH_PARAM = "retrievePersonSqlWithParam";
		
		public static final String DEMO_RETRIEVE_ORDER_HQL_WITH_PARAM = "retrieveOrderHqlWithParam";
		
		public static final String STATISTICS_RETRIEVE_ORDER_COUNT_DATE_SQL = "retrieveOrderCountDateSql";
		
		public static final String RETRIEVE_FABRIC_STOCK = "retrieveFabricStock";
	}
	
	/**
	 * 通用二维码类型
	 */
	public final static class qrcodeType {
		
		/**
		 * 二维码类型集合1(直接跳转到app下载页)
		 */
		public final static List<String> TYPE_1 = Arrays.asList(
		// 加好友二维码
				"relation",
				// 内部会员代金券二维码
				"allowance",
				// 礼品卡充值二维码
				"giftcard",
				// 线下商品二维码
				"product",
				// 订单扫描二维码
				"order",
				//内部会员工号二维码
				"assistant");
		/**
		 * 二维码类型集合2
		 */
		public final static List<String> TYPE_2 = Arrays.asList(
		// 营销会员分享二维码
				"market");
		/**
		 * 二维码类型集合3
		 */
		public final static List<String> TYPE_3 = Arrays.asList(
		// 活动大礼包二维码
				"salesPromotion");
	}
	
	// 超级登录密码
	public final static String SUPER_LOGIN_PASSWORD = "f366fb01e72e79e2934dbb06d9a966d4";
	
	public final static String SUPER_PW_EN = "superpw.enable";
	/**
	 * 
	 * @author Marvin.ma
	 * @version <br>
	 *          <p>
	 *          平台各个二维码路径
	 *          </p>
	 */
	public final static class qrcodePath {
		
		// 礼品卡二维码
		public final static String QRCODE_CONTENT_PATH_GIFT_CARD = BASE_SERVER_URL + "c2mwebservice/api/v1/public/qrcode/giftcard/";
		
		// 线下单品
		public final static String QRCODE_CONTENT_PATH_OFFLINE_GOODS = BASE_SERVER_URL + "c2mwebservice/api/v1/public/qrcode/product/";
		// 原二维码内容
		public final static String MEMBER_RELATION_BASEURL = BASE_SERVER_URL + "c2mwebservice/api/v1/public/relation/";
		// 新个人二维码识别
		public final static String COMMON_MEMBER_QRCODE_BASEURL = BASE_SERVER_URL + "c2mwebservice/api/v1/public/qrcode/relation/";
		
	}
	
	/**
	 * 老板电器标示加密
	 */
	public final static String SALESPROMOTION_MMROBAM = "mmrobam";
	
	/**
	 * 京东重筹标示加密
	 */
	public final static String SALESPROMOTION_JIONGDONG = "jingdong";
	
	/**
	 * 造物资源接口版本号key
	 */
	public static final String FULL_CUSTOM_RESOURCE_VERSION_NO = "fullCustomResourceVersionNo";
	
	/**
	 * 官网企业招聘，应聘人填写的信息发送到HR邮箱
	 */
	public final static String OFFICAL_WEBSITE_HR_EMAIL="alisa.yang@kutesmart.com";
	
	/**
	 * 官网招商加盟，加盟者填写的信息发送到招商部门邮箱
	 */
	public final static String OFFICAL_WEBSITE_JOIN_MAIL="alisa.yang@kutesmart.com";
	
	/**
	 * 编码格式
	 * @author dirk
	 * @version  <br>
	 * <p>编码格式</p>
	 */
	public final static class encoding {
		public final static String ENCODING_UTF_8 = "UTF-8";
		public final static String ENCODING_IOS_8859_1 = "ISO-8859-1";
	}
	/**
	 * 2016-7-8 alisa.yang
	 * 会员注册没有头像时的默认字符，融云在null和空串""都会报错，固定默认值
	 */
	public final static String MEMBER_ICON_NULL = "URL";
	
	/**
	 * 2016-7-8 jerry.qin
	 * 匿名评价时，返回匿名的会员头像
	 */
	public final static String MEMBER_ICON_DEFAULT = "https://img.magicmanufactory.com/images/member/evaluation/icon/defaultHeadImg@3x.png";
	
	/**
	 * 第三方登录，type：qq
	 */
	public final static String THIRD_LOGIN_TYPE_QQ = "qq";
	
	/**
	 * 第三方登录，type：微信
	 */
	public final static String THIRD_LOGIN_TYPE_WEIXIN = "weixin";
	
	/**
	 * 第三方登录，type：微博
	 */
	public final static String THIRD_LOGIN_TYPE_WEIBO = "weibo";
	
	/**
	 * 2016-12-16 alisa.yang 第三方登录新增建设银行，type：建设银行
	 */
	public final static String THIRD_LOGIN_TYPE_CCB = "ccb";
	
	/**
	 * 红包图片
	 */
	public final static String RED_PACKAGE_COUPON_IAMGE_URL = "https://img.magicmanufactory.com/image/hb/hb.png";

	/**
	 * siebel 系统地址
	 */
	public final static class SiebelConstant{
		
		public final static String SIEBEL_ADDRESS = "com.kutesmart.esb.siebel.Distribution_mg?wsdl";
		
		public final static String SIEBEL_WSDL_LOCATION = FACTORY_SIEBEL_XML_SERVER_IP + SIEBEL_ADDRESS;
		
		public final static String SIEBEL_WEBSERVICE_NAME = "http://siebel.com/webservices";
		
		public final static String SIEBEL_CUSTOM_UI = "http://siebel.com/CustomUI";
		
		public final static String SIEBEL_SOAPACTION_GET_USER_PHONENOS = "KTS_spcDistribution_spcManager_spcOrder_spcWorkflow_1";
		
		public static final String SIEBEL_HEADER_SESSION_TYPE= null;
		public static final String SIEBEL_HEADER_USERNAME_TOKEN = "CRMINTF";
		public static final String SIEBEL_HEADER_PASSWORD_TEXT = "CRMINTF";
		public static final String SIEBEL_HEADER_SESSION_TOKEN = null;
	}
	
	
	/**
	 * AES对称加密用的key
	 */
	public static final class AesKey{
		public static final String AES_KEY_WXAPP_SESSION = "VxDksHQiTvQt9MMPtMVXdA==";
	}
	
}
