/**
 * 
 */
package com.yxlg.base.member.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Michael.Sun
 * 2016年3月8日
 * @version  <br>
 * <p>类的描述</p>
 */
@Entity
@Table(name = "yx_member_level_discount_no")
public class MemberLevelDiscountNo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9097048032537956750L;
	/**
	 * 主键
	 */
	private String MemberLevelDiscountNoId;
	/**
	 * 打折等级
	 */
	private String level;
	/**
	 * 等级名称
	 */
	private String levelName;
	/**
	 * 折扣数
	 */
	private String discountNo;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * @return the memberLevelDiscountNoId
	 */
	@Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "member_level_discount_no_Id", unique = true, nullable = false, length = 40)
	public String getMemberLevelDiscountNoId() {
	
		return MemberLevelDiscountNoId;
	}
	
	/**
	 * @param memberLevelDiscountNoId the memberLevelDiscountNoId to set
	 */
	public void setMemberLevelDiscountNoId(String memberLevelDiscountNoId) {
	
		MemberLevelDiscountNoId = memberLevelDiscountNoId;
	}
	
	/**
	 * @return the level
	 */
	@Column(name = "level")
	public String getLevel() {
	
		return level;
	}
	
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
	
		this.level = level;
	}
	
	/**
	 * @return the levelName
	 */
	@Column(name = "level_name")
	public String getLevelName() {
	
		return levelName;
	}
	
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
	
		this.levelName = levelName;
	}
	
	/**
	 * @return the discountNo
	 */
	@Column(name = "discount_no")
	public String getDiscountNo() {
	
		return discountNo;
	}
	
	/**
	 * @param discountNo the discountNo to set
	 */
	public void setDiscountNo(String discountNo) {
	
		this.discountNo = discountNo;
	}
	
	/**
	 * @return the remark
	 */
	@Column(name = "remark")
	public String getRemark() {
	
		return remark;
	}
	
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	
}
