package com.yxlg.member.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxlg.base.member.entity.Member;
import com.yxlg.base.service.IBaseService;
import com.yxlg.base.sso.entity.SSOUser;
import com.yxlg.base.sso.entity.UserRole;

@Service("sSOUtil")
public class SSOUtil {

	/**
	 * @param member
	 *            将要添加的member对象，并且拥有id
	 */
	public void insertSSO(Member member) {
		SSOUser ssoUser = new SSOUser();
		ssoUser.setUsername(member.getPhoneNo() + "," + member.getEmail());
		ssoUser.setPassword(member.getPassword());
		ssoUser.setSysid(member.getMemberId());
		ssoUser.setSys(1);
		ssoUser.grantRole(UserRole.WS_USER);

		baseService.save(ssoUser);
	}

	/**
	 * 将member表里面的用户全部导入sso的表中，权限是WS_USER,系统标识号为1
	 */
	public void insertSSOAllMember() {
		List<Member> members = baseService.findAll(Member.class);
		for (Member mt : members) {
			SSOUser ssoUser = new SSOUser();
			ssoUser.setUsername(mt.getPhoneNo() + "," + mt.getEmail());
			ssoUser.setPassword(mt.getPassword());
			ssoUser.setSysid(mt.getMemberId());
			ssoUser.setSys(1);
			ssoUser.grantRole(UserRole.WS_USER);
			baseService.save(ssoUser);
		}
	}

	/**
	 * @param member
	 *            在sso中更新现有的对象
	 */
	public void updateSSO(Member member) {
		SSOUser ssoUser = baseService.findUniqueByProperty(SSOUser.class,
				"sysid", member.getMemberId());
		if (ssoUser != null) {
    		ssoUser.setUsername(member.getPhoneNo() + "," + member.getEmail());
    		ssoUser.setPassword(member.getPassword());
    		ssoUser.setSysid(member.getMemberId());
    
    		baseService.update(ssoUser);
		}
	}

	/**
	 * @param member
	 *            在sso中删除现有对象
	 */
	public void deleteSSO(Member member) {
		SSOUser ssoUser = baseService.findUniqueByProperty(SSOUser.class,
				"sysid", member.getMemberId());
		if (ssoUser != null)
			baseService.delete(ssoUser);
	}
	
	@Autowired
	private IBaseService baseService;
}
