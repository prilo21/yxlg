/**
 * 
 */
package com.yxlg.base.member.dto;

/**
 * @author alison.liu
 *
 */
public class MeasureSpecialDto {
    private String zjx = "";
    /**
     * 右肩型
     */
    private String yjx;
    /**
     * 肚型
     */
    private String dx;
    /**
     * 手型
     */
    private String sx;
    
    /**
     * 背型
     * 2015-11-13 背型工厂不接收
     */
    // private String bx;
    
    /**
     * 臀型
     */
    private String tx;

	public String getZjx() {
		return zjx;
	}

	public void setZjx(String zjx) {
		this.zjx = zjx;
	}

	public String getYjx() {
		return yjx;
	}

	public void setYjx(String yjx) {
		this.yjx = yjx;
	}

	public String getDx() {
		return dx;
	}

	public void setDx(String dx) {
		this.dx = dx;
	}

	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}
    
}
