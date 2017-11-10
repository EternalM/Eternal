package com.devsun.eternal.util;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * 正则工具
 * @author eternal
 *
 */
public class RegexUtil {

	public static boolean checkEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}

	public static boolean checkPhone(String phone) {
		String regex = "^1[3|4|5|7|8]\\d{9}$";
		return Pattern.matches(regex, phone);
	}

	public static boolean checkName(String name) {
		String regex = "^([\\u4e00-\\u9fa5]){2,5}$";
		return Pattern.matches(regex, name);
	}
	
	public static boolean checkBoole(String boole) {
		if (boole != null && !boole.equals("")){
			if (boole.equals("是") || boole.equals("否")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 验证时间
	 * @param val
	 * @return
	 */
	public static boolean checkDate(String val){
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(val!=null && !val.equals("")){
				sdf.setLenient(false);
				sdf.parse(val);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean checkNum(String num) {

		if (num != null && !num.equals("")) {
			String regex = "^\\d{4,5}$";
			return Pattern.matches(regex, num);
		}
		return false;

	}

}
