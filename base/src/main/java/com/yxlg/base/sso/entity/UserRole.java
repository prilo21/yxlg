package com.yxlg.base.sso.entity;

/**
 * @author zach ma
 * 作为一个配置类，并不映射数据库
 *
 */
public enum UserRole {
	WS_USER, WS_ADMIN, MG_USER, MG_ADMIN;

	public SSOUserAuthority asAuthorityFor(final SSOUser user) {
		final SSOUserAuthority authority = new SSOUserAuthority();
		authority.setAuthority("ROLE_" + toString());
		authority.setUser(user);
		return authority;
	}

	public static UserRole valueOf(final SSOUserAuthority authority) {
		switch (authority.getAuthority()) {
		case "ROLE_WS_USER":
			return WS_USER;
		case "ROLE_WS_ADMIN":
			return WS_ADMIN;
		case "ROLE_MG_USER":
			return MG_USER;
		case "ROLE_MG_ADMIN":
			return MG_ADMIN;
		}
		throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}
}
