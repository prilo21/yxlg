/*
 * GenerateSQL。java
 *
 * Created Date: Jun 3, 2015
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

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author amosc
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */

public class GenerateSQL {
	
	private static final Logger log = LoggerFactory.getLogger(GenerateSQL.class);
	/**
	 * 生成会员表数据
	 * 
	 * @param num
	 *            生成数量
	 * @param filePath
	 *            sql文件位置:例如D:\\member.sql
	 */
	public static void generateMemberSql(int num, String filePath) {
		
		String memberSql_part1 = "INSERT INTO `yx_member` (`member_id`, `member_birthday`, `member_cash_balance`, `member_email`, "
				+ "`member_gender`, `member_height`, `member_icon`, `member_isdelete`, `member_name`, `member_type`, "
				+ "`member_nation_id`, `member_password`, `member_phone_no`, `member_qrcode`, `member_regist_time`, "
				+ "`member_signature`, `member_virtual_currency`, `member_weight`)";
		for (int i = 0; i < num; i++) {
			String memberSql_part2 = " VALUES ('"
					+ IDGenerator.getInstance().generate() + "', '1988-07-10', "
					+ RandomValue.getNum(0, 9999) + ", '"
					+ RandomValue.getEmail(10, 20) + "', '"
					+ RandomValue.getSex() + "', '"
					+ RandomValue.getNum(145, 210) + "', 'site.com/icon', '', '"
					+ RandomValue.getChineseName()
					+ "', '', NULL, 'd488000a14d170a32972edf0ab6b2726', '"
					+ RandomValue.getTel()
					+ "', 'site.com/qrcode', NULL, 'me is me', "
					+ RandomValue.getNum(0, 10000) + ", '');";
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer;
			try {
				writer = new FileWriter(filePath, true);
				writer.write(memberSql_part1 + memberSql_part2 + "\n");
				writer.close();
			} catch (IOException e) {
				log.error("异常！", e);
			}
		}
	}
	
	/**
	 * 生成订单表数据
	 * 
	 * @param num
	 * @param filePath
	 */
	public static void generateTradeOrderSql(int num, String filePath) {
	
	}
	
	public static void main(String[] args) throws IOException {
		
		generateMemberSql(1000000, "D:\\member.sql");
	}
}
