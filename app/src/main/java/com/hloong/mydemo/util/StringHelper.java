package com.hloong.mydemo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringHelper {
	// 验证
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.equals("null");
	}

	/**
	 * 验证邮箱的合法性
	 * (^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)
	 * ?\\.)+[a-zA-Z]{2,}$)
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		if (isEmpty(email))
			return false;
		final String pattern_ = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern_);
		final Matcher mat = pattern.matcher(email);
		return mat.matches();
	}

	/**
	 * 判断日期格式是否正确
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 验证姓名是中文
	 */
	public static boolean isValidUserName(String name) {
		if (isEmpty(name)) {
			return false;
		} else {
			// ^[_a-zA-Z0-9+\.\u4e00-\u9fa5]{2,6}$
			name = new String(name.getBytes());// 用GBK编码
			// final String pattern_ = "^[_a-zA-Z0-9+\\.\u4e00-\u9fa5]{2,6}$";
			final String pattern_ = "^[\u4e00-\u9fa5]{2,6}$";
			final Pattern pattern = Pattern.compile(pattern_);
			final Matcher mat = pattern.matcher(name);
			return mat.matches();
		}

	}

	/**
	 * 验证内容只能是数字，英文，汉字
	 */
	public static boolean isCheckUserName(String name) {
		if (isEmpty(name)) {
			return false;
		} else {
			// ^[_a-zA-Z0-9+\.\u4e00-\u9fa5]{2,6}$
			name = new String(name.getBytes());// 用GBK编码
			final String pattern_ = "^[a-zA-Z0-9]+$";
			final Pattern pattern = Pattern.compile(pattern_);
			final Matcher mat = pattern.matcher(name);
			return mat.matches();
		}

	}

	public static boolean checkNumberword(String password) {
		boolean tag = false;
		if (isEmpty(password)) {
			tag = false;
		} else {
			final String pattern_ = "[0-9]{0,20}$";
			final Pattern pattern = Pattern.compile(pattern_);
			final Matcher mat = pattern.matcher(password);
			if (!mat.find()) {
				tag = true;
			}
		}

		return tag;
	}

	/**
	 * 验证密码为英文加数字 6-18位
	 */
	public static boolean checkPassword(String password) {
		boolean tag = false;
		if (TextUtils.isEmpty(password)) {
			tag = false;
		} else {
		    //final String pattern_ = "^[a-zA-Z0-9]{6,18}$";//6-18 纯数字或纯字母
//			final String pattern_ = "^(?=.*?[a-zA-Z])(?=.*?[0-6])[A-Za-z0-9]{6,18}$";//6-18 数字字母混合
			final String pattern_ = "^(?=.*?[a-zA-Z])(?=.*?[0-9])[A-Za-z0-9]{6,18}$";//6-18 数字字母混合
			final Pattern pattern = Pattern.compile(pattern_);
			final Matcher mat = pattern.matcher(password);
			if (mat.find()) {
				tag = true;
			}
		}

		return tag;
	}
	
	

	/**
	 * 验证身份证
	 */
	public static boolean checkIdCard(String idcard) {
		boolean tag = false;
		if (isEmpty(idcard)) {
			tag = false;
		} else {
			final String pattern_ = "[A-Za-z0-9]{5,30}";
			final Pattern pattern = Pattern.compile(pattern_);
			final Matcher mat = pattern.matcher(idcard);
			if (mat.matches()) {
				tag = true;
			} else {
				tag = false;
			}
		}

		return tag;
	}

	public static boolean isValidMobile(String mobile) {
		if (StringHelper.isEmpty(mobile))
			return false;
		// Pattern pattern=Pattern.compile("^[13,14,15,18,19]\\d{9}$");
		Pattern pattern = Pattern.compile("[0-9]{11}");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static boolean isValidQQ(String qq) {
		if (StringHelper.isEmpty(qq))
			return false;
		// Pattern pattern=Pattern.compile("^[13,14,15,18,19]\\d{9}$");
		Pattern pattern = Pattern.compile("^[1-9]\\d{3,}$");
		Matcher matcher = pattern.matcher(qq);
		return matcher.matches();
	}

	public static boolean isValidNumber(String url) {
		if (StringHelper.isEmpty(url))
			return false;
		Pattern pattern = Pattern.compile("^\\d{1,}$");
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	/**
	 * 将String转换为Long
	 * 
	 * @param str
	 * @return
	 */
	public static long parserLong(String str) {
		if (str == null || (str = str.trim()).length() <= 0) {
			return 0;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String input) {
		if (input.indexOf(".") > 0) {
			input = input.replaceAll("0+?$", "");// 去掉多余的0
			input = input.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return input;
	}

	// /**
	// * 半角转换为全角
	// *
	// * @param input
	// * @return
	// */
	// public static String ToDBC(String input) {
	// char[] c = input.toCharArray();
	// for (int i = 0; i < c.length; i++) {
	// if (c[i] == 12288) {
	// c[i] = (char) 32;
	// continue;
	// }
	// if (c[i] > 65280 && c[i] < 65375)
	// c[i] = (char) (c[i] - 65248);
	// }
	// return new String(c);
	// }

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
	    if(TextUtils.isEmpty(input)){
            return "";
        }
	    
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
	    if(TextUtils.isEmpty(input)){
	        return "";
	    }
	    
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);

		return returnString;
	}
	
	/**
     * 截取时间戳后8位
     * */
    public static String subLastEight(String str){  
        if(!TextUtils.isEmpty(str) && str.length() > 8){
            str = str.substring(str.length()-8,str.length());
        }
        return str;  
    }

    /**
     * 随机打乱一个字符串的排序方式
     * */
    public static String shuffleForSortingString(String s) {
        char[] c = s.toCharArray();
        List<Character> lst = new ArrayList<Character>();
        for (int i = 0; i < c.length; i++) {
            lst.add(c[i]);
        }
        Collections.shuffle(lst);
        String resultStr = "";
        //处理第一个字符不为0
        while (lst.get(0).equals('0')) {
            Collections.shuffle(lst);
        }
        for (int i = 0; i < lst.size(); i++) {
            resultStr += lst.get(i);
        }
        return resultStr;
    }
	
	/**
	 * 反转字符串
	 * */
	public static String reverse(String str){  
	    if(TextUtils.isEmpty(str)){
	        return "";
	    }
        return new StringBuilder(str).reverse().toString();  
    }
	
	/**
     * 截取字符串的前面一半
     * */
    public static String HalfStr(String str1){
        if(TextUtils.isEmpty(str1)){
            return "";
        }
        int temp_index = (int) Math.floor(str1.length() / 2.0);
        return str1.substring(0,temp_index);  
    } 
	
	/**
     * 将sB插入sA的中间位置
     * */
    public static String sBInsertsA(String strA, String strB){
        if(TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB)){
            return "";
        }
        int temp_index = (int) Math.floor(strA.length() / 2.0);
        return strA.substring(0, temp_index) + strB + strA.substring(temp_index, strA.length());  
    }  
 
}
