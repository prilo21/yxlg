/*
 * BeanUtilCopy.java
 *
 * Created Date: 2016年3月29日
 *				
 * Copyright (c)  Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */

package com.yxlg.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


/**
 * @author Cary
 * @version  <br>
 * <p>对象属性copy</p>
 */

public class BeanUtilCopy {
	private String a;
	private Date d;
	
	private AppVersion app;
	
	private static final Logger logger = LoggerFactory.getLogger(BeanUtilCopy.class);
	
	
	/**
	 * @return the app
	 */
	public AppVersion getApp() {
	
		return app;
	}




	
	/**
	 * @param app the app to set
	 */
	public void setApp(AppVersion app) {
	
		this.app = app;
	}




	/**
	 * @return the a
	 */
	public String getA() {
	
		return a;
	}



	
	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
	
		this.a = a;
	}



	
	/**
	 * @return the d
	 */
	public Date getD() {
	
		return d;
	}



	
	/**
	 * @param d the d to set
	 */
	public void setD(Date d) {
	
		this.d = d;
	}
   public static void main(String args[]) throws IllegalAccessException, InvocationTargetException{
	   BeanUtilCopy A=new BeanUtilCopy();
	   A.setA("A");
	   A.setD(new Date());
	   
	   AppVersion app=new AppVersion();
	   app.setOSversion("4.6");
	   app.setDeviceType("AT");
	   A.setApp(app);
	   BeanUtilCopy B=new BeanUtilCopy();
	   AppVersion appB=new AppVersion();
	   appB.setOSversion("4.6");
	   //appB.setDeviceType("BT");
	   appB.setPlatform("BBB");
	   B.setApp(appB);
	   B.setA("a");
	   beanCopy(A, B);
	   logger.info("B.D:" + B.getD() + ",A.App:" + A.getApp().getOSversion() + ",A.A:" + A.getA() + ",A.App.DeviceType:" + A.getApp().getDeviceType() + ",A.App.Platform:" + A.getApp().getPlatform());
   }


	public static <T> void beanCopy (T des ,T org){
		Class<? extends Object> cla=org.getClass();
		Field[] fields=cla.getDeclaredFields();
		try{
			for (Field f : fields) {
				f.setAccessible(true);
				if(f.get(org)!=null&&StringUtils.isNotBlank(f.get(org).toString())&&(!f.get(org).equals(f.get(des))))
				{
					if(f.getName().contains("yuandian"))
						beanCopy(f.get(des),f.get(org));
					else
						f.set(des, f.get(org));
				}
			}
		}catch(Exception e){
			throw new BusinessException("", e);
		}
	}
	
	public static String[] getNullPropertyNames (Object source) {
		 final BeanWrapper src = new BeanWrapperImpl(source);
		 java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		 Set<String> emptyNames = new HashSet<String>();
		 for(java.beans.PropertyDescriptor pd : pds) {
		 Object srcValue = src.getPropertyValue(pd.getName());
		 if (srcValue == null) emptyNames.add(pd.getName());
		}
		 String[] result = new String[emptyNames.size()];
		 return emptyNames.toArray(result);
	}
	
	/**
	 * 对象属性拷贝,忽略null
	 * @param src
	 * @param target
	 */
	public static void copyPropertiesIgnoreNull(Object src, Object target){
		 BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
}
