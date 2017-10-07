package com.yxlg.manage.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.entity.UserContext;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.sys.entity.SysResource;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.manage.sys.service.IManageSysUserService;

/**
 * @author jetty
 * @version <br>
 *          <p>
 *          登陆拦截验证类
 *          </p>
 */
@Controller
@RequestMapping(value = "/security/*")
public class SecurityControler {
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true) String password, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
	
		SysUser user = null;
		
		if (request.isSecure() && org.apache.commons.lang3.StringUtils.isNotEmpty(userName)) {
			// pki
		} else {// 用户名、密码登录
			if (org.apache.commons.lang3.StringUtils.isEmpty(userName)) {
				return Constants.page.LOGIN + "?error=1";
			}
			// 校验账号是否存在
			user = this.sysUserService.getUser(userName);
			if (user == null) {
				return Constants.page.LOGIN + "?error=3";
			}
			// 校验账号和密码是否匹配
			user = sysUserService.getUser(userName, password);
			if (user == null) {
				return Constants.page.LOGIN + "?error=4";
			}
			Set<SysResource> userPermission = this.sysUserService.getUserPermission(user);
			String menuStrByList = this.sysUserService.getMenuStrByList(userPermission);
			
			/*
			 * Assertion assertion = (Assertion)
			 * request.getSession().getAttribute
			 * (AbstractCasFilter.CONST_CAS_ASSERTION);
			 * String username = assertion.getPrincipal().getName();
			 * //返回值就是username
			 * System.out.println(username+
			 * "-------------------------------------------------");
			 */
			
			// 将权限和用户信息放入用户上下文
			UserContext userContext = new UserContext();
			userContext.setSysUser(user);
			userContext.setFunctionList(menuStrByList);
			request.getSession().setAttribute(Constants.session.USER, userContext);
		}
		
		/*
		 * Assertion assertion = (Assertion)
		 * request.getSession().getAttribute(AbstractCasFilter
		 * .CONST_CAS_ASSERTION);
		 * String username = assertion.getPrincipal().getName(); //返回值就是username
		 * //将权限和用户信息放入用户上下文
		 * UserContext userContext = new UserContext();
		 * SysUser sysUser = sysUserService.getUser(username);
		 * userContext.setSysUser(sysUser);
		 * Set<SysResource> userPermission =
		 * this.sysUserService.getUserPermission(sysUser);
		 * String menuStrByList =
		 * this.sysUserService.getMenuStrByList(userPermission);
		 * userContext.setFunctionList(menuStrByList);
		 * request.getSession().setAttribute(C2MConstants.session.USER,
		 * userContext);
		 */
		return Constants.page.WELCOME;
	}
	
	/**
	 * 登出操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
	
		// session 失效
		session.removeAttribute(Constants.session.USER);
		session.invalidate();
		return Constants.page.LOGIN + "?error=2";
	}
	
	/**
	 * 用户名检查
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkoutUserName")
	public @ResponseBody String checkoutUserName(String userName, String userId) {
	
		if (StringUtils.isNotBlank(userName)) {
			List<SysUser> userLsit = dao.findListByProperty(SysUser.class, "userName", userName);
			if (StringUtils.isBlank(userId)) {
				if (userLsit.size() > 0) {
					return "1";
				}
			} else if (userLsit.size() == 1 && (userLsit.get(0).getSysUserId().equals(userId))) {
				return "0";
			} else {
				return "1";
			}
		}
		return "0";
	}
	
	@Resource
	private IManageSysUserService sysUserService;
	@Resource
	private IBaseService dao;
	
}
