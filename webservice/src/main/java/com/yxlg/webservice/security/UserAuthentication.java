package com.yxlg.webservice.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yxlg.base.sso.entity.SSOUser;


/** 这个作为一个authentication将user直接进行了封装，鉴权通过之后就会保存这样一个对象
 * @author zach ma
 *
 */
public class UserAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7785458713394189281L;
	private final SSOUser user;
	private boolean authenticated = true;

	public UserAuthentication(SSOUser user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public SSOUser getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
