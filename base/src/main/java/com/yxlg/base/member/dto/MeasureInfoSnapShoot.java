package com.yxlg.base.member.dto;
/**
 * @author Alison.Liu
 * @version  <br>
 * <p>类的描述</p>
 */
public class MeasureInfoSnapShoot {
	/**
	 * 量体id
	 */
	private String measureId;
	
	/**
	 * 被量体人
	 */
	private String measurerName;
	/**
	 * 量体师编号
	 */
	private String measureMaster;

	/**
	 * 身高
	 */
	private String height;
	/**
	 * 体重
	 */
	private String weight;
	/**
	 * 创建时间
	 */
	private String measureDate;
	/**
	 * 是否是默认量体数据
	 * 1：默认量体数据  0：非默认量体数据
	 */
	private String isDefault;
	
	
	public String getMeasureId() {
		
		return measureId;
	}
	
	public void setMeasureId(String measureId) {
		
		this.measureId = measureId;
	}
	
	public String getMeasurerName() {
		
		return measurerName;
	}
	
	public void setMeasurerName(String measurerName) {
		
		this.measurerName = measurerName;
	}
	
	public String getMeasureMaster() {
		
		return measureMaster;
	}
	
	public void setMeasureMaster(String measureMaster) {
		
		this.measureMaster = measureMaster;
	}
	
	public String getHeight() {
		
		return height;
	}
	
	public void setHeight(String height) {
		
		this.height = height;
	}
	
	public String getWeight() {
		
		return weight;
	}
	
	public void setWeight(String weight) {
		
		this.weight = weight;
	}
	
	public String getMeasureDate() {
		
		return measureDate;
	}
	
	public void setMeasureDate(String measureDate) {
		
		this.measureDate = measureDate;
	}

	public String getIsDefault() {
		
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		
		this.isDefault = isDefault;
	}
	
}
