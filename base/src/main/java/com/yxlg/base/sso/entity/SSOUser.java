package com.yxlg.base.sso.entity;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author zach ma
 * sso用户表
 *
 */
@Entity
@Table(name = "yx_sso_all_user", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SSOUser implements UserDetails {
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = -609702772157044668L;

	public SSOUser() {
	}

	public SSOUser(String username) {
		this.username = username;
	}

	public SSOUser(String username, Date expires) {
		this.username = username;
		this.expires = expires.getTime();
	}

	
	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
	
		return accountExpired;
	}

	
	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
	
		this.accountExpired = accountExpired;
	}

	
	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
	
		return accountLocked;
	}

	
	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
	
		this.accountLocked = accountLocked;
	}

	
	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
	
		return credentialsExpired;
	}

	
	/**
	 * @param credentialsExpired the credentialsExpired to set
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
	
		this.credentialsExpired = credentialsExpired;
	}

	
	/**
	 * @return the accountEnabled
	 */
	public boolean isAccountEnabled() {
	
		return accountEnabled;
	}

	
	/**
	 * @param accountEnabled the accountEnabled to set
	 */
	public void setAccountEnabled(boolean accountEnabled) {
	
		this.accountEnabled = accountEnabled;
	}

	/**
	 * 用户本身id
	 */
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;

	/**
	 *  用户在另一系统的id
	 */
	@NotNull
	private String sysid;
	
	/**
	 *  用户在另一系统标识
	 */
	@NotNull
	private int sys;
	
	@JsonIgnore
	public int getSys() {
		return sys;
	}

	public void setSys(int sys) {
		this.sys = sys;
	}

	@JsonIgnore
	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	/**
	 * sso用户名  通过登录条件进行拼接
	 */
	@NotNull
	@Size(min = 4, max = 100)
	private String username;

	/**
	 * 用户密码
	 */
	@NotNull
	@Size(min = 4, max = 100)
	private String password;
	
	/**
	 * token过期时间
	 */
	@Transient
	private long expires;

	/**
	 * 账户过期时间
	 */
	@NotNull
	private boolean accountExpired;

	/**
	 * 账户是否锁定
	 */
	@NotNull
	private boolean accountLocked;

	/**
	 * 认证过期时间
	 */
	@NotNull
	private boolean credentialsExpired;

	/**
	 * 账户是否可用
	 */
	@NotNull
	private boolean accountEnabled;

	/**
	 * 暂时保留字段 
	 */
	@Transient
	private String newPassword;

	public void setAuthorities(Set<SSOUserAuthority> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<SSOUserAuthority> authorities;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	@JsonIgnore
	public Set<SSOUserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (SSOUserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<SSOUserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getUsername();
	}
}
