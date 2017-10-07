/*
 * MemberGoodsCollectDTO.java
 * 
 * Created Date: 2015年5月21日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.member.dto;

import java.io.Serializable;

/**
 * @author Stone.Cai 2015年5月21日 13:35:53
 * @version <br>
 *          <p>
 *          会员收藏商品的时候用户传递会员商品数据的DTO
 *          </p>
 */

public class MemberGoodsCollectDTO implements Serializable {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -1201310246781812405L;
    
    /**
     * 会员ID
     */
    private String memberId;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 是否收藏
     */
    private String flag;
    
    /**
     * 套装ID
     */
    private String suitId;
    
    public String getMemberId() {
    
        return memberId;
    }
    
    public void setMemberId(String memberId) {
    
        this.memberId = memberId;
    }
    
    public String getGoodsId() {
    
        return goodsId;
    }
    
    public void setGoodsId(String goodsId) {
    
        this.goodsId = goodsId;
    }
    
    public String getFlag() {
    
        return flag;
    }
    
    public void setFlag(String flag) {
    
        this.flag = flag;
    }
    
    public String getSuitId() {
    
        return suitId;
    }
    
    public void setSuitId(String suitId) {
    
        this.suitId = suitId;
    }
    
}
