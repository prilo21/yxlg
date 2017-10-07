package com.yxlg.base.sso.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zach ma
 * 用户权限表
 */
@Entity
@IdClass(SSOUserAuthority.class)
@Table(name="yx_sso_all_userauthority")
public class SSOUserAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 2200866271710134756L;

	/**
	 * 角色关联的用户
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Id
	private SSOUser user;

	/**
	 * 用户的权限
	 */
	@NotNull
	@Id
	private String authority;

	public SSOUser getUser() {
		return user;
	}

	public void setUser(SSOUser user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SSOUserAuthority))
			return false;

		SSOUserAuthority ua = (SSOUserAuthority) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}
 
	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}
