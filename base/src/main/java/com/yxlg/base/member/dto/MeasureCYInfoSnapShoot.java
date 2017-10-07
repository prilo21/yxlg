package com.yxlg.base.member.dto;


public class MeasureCYInfoSnapShoot {

	/**
	 * 量体id
	 */
	private String cyMeasureId;
	
	/**
	 * 被量体人
	 */
	private String measurerName;

	/**
	 * 创建时间
	 */
	private String measureDate;
	
	/**
     * 本成衣尺寸数据参考的标准码号码
     */
    private String originalSize = "";
    /**
     * 本成衣尺寸数据参考的标准码体型
     */
    private String originalShape = "";
    
	
	public String getCyMeasureId() {
		
		return cyMeasureId;
	}

	public void setCyMeasureId(String cyMeasureId) {
		
		this.cyMeasureId = cyMeasureId;
	}
	
	public String getMeasurerName() {
		
		return measurerName;
	}
	
	public void setMeasurerName(String measurerName) {
		
		this.measurerName = measurerName;
	}
	
	public String getMeasureDate() {
		
		return measureDate;
	}
	
	public void setMeasureDate(String measureDate) {
		
		this.measureDate = measureDate;
	}

	public String getOriginalSize() {
		
		return originalSize;
	}

	public void setOriginalSize(String originalSize) {
		
		this.originalSize = originalSize;
	}

	public String getOriginalShape() {
		
		return originalShape;
	}

	public void setOriginalShape(String originalShape) {
		
		this.originalShape = originalShape;
	}

}
