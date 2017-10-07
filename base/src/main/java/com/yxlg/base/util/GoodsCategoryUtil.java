/*
 * GoodsCategoryUtil.java
 * 
 * Created Date: 2016年1月13日
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Cary
 * @version <br>
 *          <p>
 *          商品品类名称处理
 *          </p>
 */
public class GoodsCategoryUtil {
	public static String goodsCategoryToName(String categoryId, String categoryName) {
	
		// 4女装3男装5童装
		String categoryNames = "";
		if (StringExtraUtils.isEmptyOrZero(categoryId))
			return "全品类";
		
		if (!categoryId.contains(",")) {
			categoryId = categoryId + ",";
		}
		String[] categorys = categoryId.split(",");
		List<String> categoryIdsArray = Arrays.asList(categorys);
		List<String> categoryIds = new ArrayList<String>(categoryIdsArray);
		// categoryIds.addAll(categoryIdsArray);
		if ((categoryIds.contains("3") && categoryIds.contains("33") && categoryIds.contains("4") && categoryIds.contains("5")))
			return "全品类";
		// 西服类只包含男6女11童15
		if (categoryIds.contains("15") && categoryIds.contains("11") && categoryIds.contains("6")) {
			categoryIds.remove("15");
			categoryIds.remove("11");
			categoryIds.remove("6");
			categoryNames = "西服,";
		}
		// 西裤类只包含男8女12童19
		if (categoryIds.contains("8") && categoryIds.contains("12") && categoryIds.contains("19")) {
			categoryNames += "西裤,";
			categoryIds.remove("8");
			categoryIds.remove("12");
			categoryIds.remove("19");
		}
		// 衬衣类只包含男10女20童17
		if (categoryIds.contains("10") && categoryIds.contains("17") && categoryIds.contains("20")) {
			categoryNames += "衬衣,";
			categoryIds.remove("10");
			categoryIds.remove("17");
			categoryIds.remove("20");
		}
		// 大衣类只包含男9女13童8a24869351244b270151247f41c50037
		if (categoryIds.contains("9") && categoryIds.contains("13") && categoryIds.contains("8a24869351244b270151247f41c50037")) {
			categoryNames += "大衣,";
			categoryIds.remove("9");
			categoryIds.remove("13");
			categoryIds.remove("8a24869351244b270151247f41c50037");
		}
		// 马甲类只包含男7女21童无
		if (categoryIds.contains("7") && categoryIds.contains("21")) {
			categoryNames += "马甲,";
			categoryIds.remove("7");
			categoryIds.remove("21");
		}
		// 判断是否更换显示
		boolean ch = true;
		for (String string : categoryIds) {
			if (StringUtils.isNotBlank(string))
				ch = false;
		}
		if (ch) {
			if (categoryNames.endsWith(","))
				return (String) categoryNames.subSequence(0, categoryNames.length() - 1);
			else
				return categoryNames;
		} else {
			ch = true;
			categoryIds.clear();
			categoryIds.addAll(Arrays.asList(categorys));
			categoryNames = "";
			// 男装
			if (categoryIds.contains("3")) {
				categoryNames = "男装,";
				categoryIds.remove("3");
				categoryIds.remove("6");
				categoryIds.remove("7");
				categoryIds.remove("8");
				categoryIds.remove("9");
				categoryIds.remove("10");
				categoryIds.remove("26");
				categoryIds.remove("27");
				categoryIds.remove("29");
				categoryIds.remove("30");
				categoryIds.remove("31");
				categoryIds.remove("32");
				categoryIds.remove("33");
				categoryIds.remove("39");
				categoryIds.remove("38");
				categoryIds.remove("39");
				categoryIds.remove("40");
				categoryIds.remove("41");
			}
			// 女装
			if (categoryIds.contains("4")) {
				categoryNames = categoryNames + "女装,";
				categoryIds.remove("4");
				categoryIds.remove("11");
				categoryIds.remove("12");
				categoryIds.remove("13");
				categoryIds.remove("14");
				categoryIds.remove("20");
				categoryIds.remove("21");
				categoryIds.remove("23");
			}
			// 童装
			if (categoryIds.contains("5")) {
				categoryNames = categoryNames + "童装,";
				categoryIds.remove("8a24869351244b270151247f41c50037");
				categoryIds.remove("5");
				categoryIds.remove("15");
				categoryIds.remove("17");
				categoryIds.remove("19");
			}
			
			// 配饰
			if (categoryIds.contains("33")) {
				categoryNames = categoryNames + "配饰,";
				categoryIds.remove("33");
				categoryIds.remove("34");
				categoryIds.remove("35");
				categoryIds.remove("36");
				categoryIds.remove("37");
			}
			for (String string : categoryIds) {
				if (StringUtils.isNotBlank(string))
					ch = false;
			}
			if (ch) {
				if (categoryNames.endsWith(","))
					return (String) categoryNames.subSequence(0, categoryNames.length() - 1);
				else
					return categoryNames;
			} else {
				return categoryName;
			}
		}
	}
}
