package com.yxlg.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Jarvis.Lu
 * @version <br>
 *          <p>
 *          验证是否为手机号，是手机号返回true
 *          </p>
 */

public class PhoneNoValidator {
	
	public static boolean isPhoneNoValid2(String phoneNumber) {
		
		boolean isValid = false;
		String expression = "((^(11|12|13|14|15|16|17|18|19|10)[0-9]{9}$)|(^0[1,2]{1}d{1}-?d{8}$)|" + "(^0[3-9] {1}d{2}-?d{7,8}$)|(^0[1,2]{1}d{1}-?d{8}-(d{1,4})$)|" + "(^0[3-9]{1}d{2}-? d{7,8}-(d{1,4})$))";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
	
    public static boolean isPhoneNoValid(String phoneNumber) {
    	// phoneNumber位数太长抛异常, java.lang.NumberFormatException: for input String: "1392326581715153227331"
        // @SuppressWarnings("unused")
        // long num = Long.parseLong(phoneNumber);
    	if (phoneNumber.trim().length() == 11 && isNumeric(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 验证是纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String phoneNumber)
    {
          Pattern pattern = Pattern.compile("[0-9]*");
          Matcher isNum = pattern.matcher(phoneNumber);
          if( !isNum.matches() )
          {
                return false;
          }
          return true;
    }
    
    /**
     * 2016-12-12 alisa.yang 判断是否为中国国内手机号吗
     * @param phoneNumber
     * @return true-符合手机号吗标准；false-非法格式
     */
    public static boolean isCNMobilePhone(String phoneNumber){
    	if(StringUtils.isBlank(phoneNumber)){
    		return false;
    	}
    	Pattern pattern = Pattern.compile("^1[3,4,5,7,8]\\d{9}$");
		Matcher isPhone = pattern.matcher(phoneNumber.trim());
		return isPhone.matches();
    }
    
	public static boolean checkVerifyCodesTime(String verifyTimeStr){
		long verifyTime;
		long currentTime = System.currentTimeMillis();
		boolean timeFlag = false;
		long time = 15 * 60 * 1000;

		verifyTime = Long.parseLong(verifyTimeStr);
		if (currentTime - verifyTime < time) {
			timeFlag = true;
		}
		
		return timeFlag;
	}
	
}
