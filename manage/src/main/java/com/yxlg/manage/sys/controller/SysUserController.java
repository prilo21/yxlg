/*
 * SysUserController.java
 *
 * Created Date: 2015年5月8日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.manage.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxlg.base.constant.Constants;
import com.yxlg.base.entity.UserContext;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.sys.entity.SysRole;
import com.yxlg.base.sys.entity.SysUser;
import com.yxlg.base.util.ComboTree;
import com.yxlg.base.util.PageBean;
import com.yxlg.base.util.Pagination;
import com.yxlg.manage.sys.service.IManageSysUserService;


/**
 * @author Edison.Ding
 * @version  <br>
 * <p>系统用户控制类</p>
 */
@Controller("sysUserController")
@RequestMapping(value="/SysUser/*")
public class SysUserController {
	/**
	 *  添加用户基本信息
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String addUser(@ModelAttribute SysUser user){
			
		user.setUserPassword(DigestUtils.md5Hex(user.getUserPassword()));
		baseService.save(user);
		return   "用户添加成功！";
	}
	
	@RequestMapping(value="/resetPassword" ,method = RequestMethod.POST)
	public @ResponseBody String resetPassword(HttpServletRequest request,HttpServletResponse response){
		
		String oldPassword =  request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String corFormPassword = request.getParameter("conformpassword");
		
		UserContext   userContext=   (UserContext) request.getSession().getAttribute(Constants.session.USER);
		SysUser user = userContext.getUser() ;   
		String result = "新输入的密码不一致" ;
		
		if(user.getUserPassword().equals(DigestUtils.md5Hex(oldPassword))){
			if(newPassword.equals(corFormPassword)){
				user.setUserPassword(DigestUtils.md5Hex(corFormPassword));
				sysUserService.updateSysUser(user);
				result = "密码重置成功";
			}
		}else{
			result= "旧密码不正确";
		}
		
		return result;
		
	}
	
	
	/**
	 * 展示用户信息列表
	 * @return
	 */
	@RequestMapping(value="/findUserAll",method=RequestMethod.POST)
	public @ResponseBody Pagination showUserList(PageBean pageBean ,String userName,String realName){
		
			return sysUserService.serchUserby(pageBean,userName,realName);
	}
	
	/**
	 * 	
	 * @param 用户登陆基本信息表的主键
	 * @return
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public @ResponseBody String deleteUserById(@RequestParam String sysUserId){
		
		baseService.deleteById(SysUser.class, sysUserId);;
		return "删除成功!";
	}
	
	/**更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public @ResponseBody String updateUser(SysUser user){
		try {
			baseService.update(user);
		} catch (Exception e) {
			return "更新失败!";
		}
		return "更新成功!";
	}
	
	/**
	 * 查看所有角色
	 * @return
	 */
	@RequestMapping(value="/allRole",method=RequestMethod.GET)
	public @ResponseBody List<SysRole> findAllRole(){
		return baseService.findAll(SysRole.class);
	}
	
	@RequestMapping(value="/findRoleByUserId",method=RequestMethod.POST)
	public @ResponseBody List<ComboTree> findRoleByUserId(@RequestParam String sysUserId){
		 return this.sysUserService.findRoleByUserId(sysUserId);
	}
	
	/**
	 * 检测用户名是否已经存在
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/checkUserName", method=RequestMethod.POST)
	public @ResponseBody boolean checkUserNameExists(String userName) {
	    

		List<SysUser> SysUserList = baseService.findListByProperty(SysUser.class, "userName", userName);
		//如果用户名userName不为空,则校验通过
		if(!StringUtils.isEmpty(userName) && SysUserList.isEmpty()){
			return true;
		}else{
			//表示已经存在,跳出错误

			return false;
		}
		
	}
	
	
	/**
	 * 保存用户角色
	 * @param sysUserId 用户ID
	 * @param selectNodes 角色ID集合
	 * @return
	 */
	@RequestMapping(value="/saveRoleUser", method=RequestMethod.POST)
	public @ResponseBody String saveRoleUser(@RequestParam String sysUserId, @RequestParam String selectNodes) {
		sysUserService.saveRoleMenu(sysUserId, selectNodes);
		return "用户角色保存成功";
	}
	
	@Resource
	private IBaseService baseService;
	@Resource
	private IManageSysUserService sysUserService;
}
