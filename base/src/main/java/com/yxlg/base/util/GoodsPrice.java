/*
 * GoodsPrice.java
 * 
 * Created Date: 2015年7月9日
 * 
 * Copyright (c) Yuandian Technologies Co., Ltd.
 * 
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.math.BigDecimal;

/**
 * @author Cary
 * @version <br>
 *          <p>
 *          商品价格计算
 *          </p>
 */

public class GoodsPrice {
	
	/**
	 * 未乘系数的工厂成本价(布料*用量+加工费)
	 * 
	 * @param price
	 *            布料单价
	 * @param usered
	 *            使用量
	 * @param cost
	 *            加工费
	 * @return
	 * 		
	 * 		Stone.Cai
	 *         2015年7月15日 15:35:08
	 *         修改
	 *         修改当前方法为把DOUBLE的类型和计算方法修改为BigDecimal
	 * 
	 */
	public static String factoryUnitCost(String price, String usered,
			String cost) {
			
		if (StringExtraUtils.isEmptyOrZero(price)) {
			price = "0";
		}
		if (StringExtraUtils.isEmptyOrZero(usered)) {
			usered = "0";
		}
		if (StringExtraUtils.isEmptyOrZero(cost)) {
			cost = "0";
		}
		BigDecimal fabricBD = new BigDecimal(price);
		fabricBD = fabricBD.multiply(new BigDecimal(usered));
		fabricBD = fabricBD.add(new BigDecimal(cost));
		return fabricBD.toString();
	}
	
	/**
	 * 通过处理计算价得到平台最终实际单价(位数处理为98)
	 * 
	 * @param price
	 * @param usered
	 * @param priceRatio
	 * @param cost
	 * @return
	 */
	public static double finalRealSellingPrice(String price, String usered,
			String priceRatio, String cost) {
			
		BigDecimal bd = new BigDecimal(factoryUnitCost(price, usered, cost));
		if (StringExtraUtils.isEmptyOrZero(priceRatio)) {
			priceRatio = "0";
		}
		bd = bd.multiply(new BigDecimal(priceRatio));
		// 平台计算实际价格（乘定价系数，但未处理）
		int originUnitRealSellingPrice = bd.intValue();
		/* 通过处理计算价得到平台最终实际单价(位数处理为98) */
		return finalRealSellingPrice(originUnitRealSellingPrice);
	}
	
	/**
	 * 通过处理计算价得到平台最终实际单价(位数处理为98)
	 * 
	 * @param originUnitRealSellingPrice
	 *            平台计算实际价格
	 * @return
	 */
	public static int finalRealSellingPrice(int originUnitRealSellingPrice) {
		
		/* 通过处理计算价得到平台最终实际单价(位数处理为98) */
		int finalRealSellingPrice = 0;
		// 如果价格小于98则售价为98
		if (originUnitRealSellingPrice < 98) {
			finalRealSellingPrice = 98;
		} else {// 大于98的情况下将价格处理成已98结尾
			/*
			 * 第一步：计算得到价格抹去小数点；
			 * 第二步：第一步得到的价格除以10取整；
			 * 第三步：第二步得到的价格乘以10；
			 * 第四步：判断十位是不是零，
			 * 如果是零，第三步得到的价格-10；
			 * 如果不是零，第三步得到的价格不变。
			 * 第五步：第四步得到的价格除以100取整；
			 * 第六步：第五步得到的价格乘以100；
			 * 第七步：第六步得到的价格加98即为最终价格。
			 */
			int step1 = (int) originUnitRealSellingPrice;
			int step2 = step1 / 10;
			int step3 = step2 * 10;
			int step4;
			if ((step3 / 10) % 10 == 0) {
				step4 = step3 - 10;
			} else {
				step4 = step3;
			}
			int step5 = step4 / 100;
			int step6 = step5 * 100;
			int step7 = step6 + 98;
			finalRealSellingPrice = step7;
		}
		return finalRealSellingPrice;
	}
	
}
