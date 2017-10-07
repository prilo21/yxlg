/*
 * AppResultUtil.java
 * 
 * Created Date: 2015年6月3日
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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * @author Stone.Cai
 * @version <br>
 *          <p>
 *          用于提供数据的返回值数据
 *          </p>
 */
public class AppResultUtil implements Serializable {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1367998236302045507L;
    
    private static final String GET = "get";
    private static final String SET = "set";
    
    /**
     * Stone.Cai
     * 2015年6月14日 13:41:23
     * 添加
     * 两个对象之间的相同属性进行数据拷贝
     * 例如：
     * A 类中 有 属性 a b c B类中有 b c d
     * 这样具有相同属性的数据会赋值 （b c）。
     * 并返回 A对象。
     * 数据对象，到模型对象的转换
     * 
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final Object dataModelToModel(Class cla, Object obj) throws Exception {
    
        Object modelObj = Class.forName(cla.getName()).newInstance();
        cla = modelObj.getClass();
        // 获取模板数据
        Field[] fields = cla.getDeclaredFields();
        // 获取数据对象的结构
        Class objClass = obj.getClass();
        for (Field mfield : fields) {
            Field ofield = objClass.getDeclaredField(mfield.getName());
            
            if (ofield != null && !ofield.getName().equals("serialVersionUID")) {
                // 如果这个不是null就说吗这个数据模型结构里面是有当前模型属性的
                String fieldName = firstCodeToUpperCase(ofield.getName());
                try {
                    Method dataMeghod = objClass.getDeclaredMethod(GET + fieldName);
                    cla.getDeclaredMethod(SET + fieldName, ofield.getType()).invoke(modelObj, dataMeghod.invoke(obj));
                } catch (Exception e) {
                    // 如果数据对象的GET方法不存在就会跳出程序
                	throw new BusinessException("", e);
                }
            }
        }
        return modelObj;
    }
    
    /**
     * Stone.Cai
     * 2015年6月14日 14:08:05
     * 添加
     * 把第一个当前数据字母大写
     */
    public static String firstCodeToUpperCase(String code) {
    
        char[] cs = code.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
