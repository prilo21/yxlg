package com.yxlg.base.member.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "yx_member_grade")
public class MemberGrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3924178910879159855L;

	/**
	 * 会员等级ID
	 */
	private String memberGradeId;
	/**
	 * 会员等级名称
	 */
	private String memberGradeName;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 常量
	 */
	private String constant;
	/**
	 * 会员类型
	 * 1,2,3,4
	 */
	private String memberType;
	
	/**
	 * 是否可显示APP尊享会员按钮
	 * 0-不显示
	 * 1-显示
	 */
	private String showVipButton;
	
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "member_grade_id", unique = true, nullable = false, length = 40)
	public String getMemberGradeId() {
		return memberGradeId;
	}

	public void setMemberGradeId(String memberGradeId) {
		this.memberGradeId = memberGradeId;
	}

	@Column(name = "member_grade_name", columnDefinition="varchar(40) default ''")
	public String getMemberGradeName() {
		return memberGradeName;
	}

	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}

	@Column(name = "create_user", columnDefinition="varchar(40) default ''")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time", length = 40)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user", columnDefinition="varchar(40) default ''")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_time", length = 40)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "constant", columnDefinition="varchar(4) default ''")
	public String getConstant() {
	
		return constant;
	}

	
	public void setConstant(String constant) {
	
		this.constant = constant;
	}

	@Column(name = "member_type", columnDefinition="varchar(40) default ''")
	public String getMemberType() {
	
		return memberType;
	}

	
	public void setMemberType(String memberType) {
	
		this.memberType = memberType;
	}

	
	@Column(name = "show_vip_button", columnDefinition="varchar(4) default '0'")
	public String getShowVipButton() {
		
		return showVipButton;
	}

	
	public void setShowVipButton(String showVipButton) {
		
		this.showVipButton = showVipButton;
	}
	

}
