package com.crk.hs.tools;

import com.alibaba.fastjson.JSONObject;

import java.util.Random;
import java.util.UUID;

public class StringUtil {

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str);
	}

	public static String guid() {

		return UUID.randomUUID().toString().toUpperCase();
	}
	
	public static String isNull(String str){
		if(str == null){
			return "".trim();
		}
		return str;
	}
	
	public static String boolToString(boolean b){
		if(b == true){
			return "true";
		}else if(b == false){
			return "false";
		}
		return "";
	}
	

	
	public static String trimEnd(String str){
		
		if (str.length() > 1) {
			StringBuffer sb = new StringBuffer(str);
			sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, " ");
			return sb.toString();
		}
		return str;
	}
	public static String stringDate(String target,String str){
		JSONObject o = JSONObject.parseObject(str);
		String date = o.getString(target);
		String t  = date.substring(6,date.length()-7);
		str = str.replace("\"\\"+date.substring(0, date.length()-1)+"\\/\"", t);
		return str;
	}
	
	public static String getRandCode(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
	}

	/**
	 * 汉字编码，实现js escape(）
	 *
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		if (src == null) {
			return null;
		}
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 汉字解码 实现js unescap()
	 *
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		if (src == null) {
			return null;
		}
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 处理数组下标越界问题
	 * 如果下标越界,则返回空值,否则返回原值
	 *
	 * @param array		数组
	 * @param rowIndex	下标
	 * @return
	 */
	public static String arrayIndexHandler(String[] array, int rowIndex) {
		try {
			return array[rowIndex];
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	/**
	 * 实现同.net的padLeft功能
	 *
	 * @param src
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String padLeft(String src, int len, char ch) {
		int diff = len - src.length();
		if (diff <= 0) {
			return src;
		}

		char[] charr = new char[len];
		System.arraycopy(src.toCharArray(), 0, charr, 0, src.length());
		for (int i = src.length(); i < len; i++) {
			charr[i] = ch;
		}
		return new String(charr);
	}

	/**
	 * 实现同.net的padRight功能
	 *
	 * @param src
	 * @param len
	 * @param ch
	 * @return
	 */
	public static String padRight(String src, int len, char ch) {
		int diff = len - src.length();
		if (diff <= 0) {
			return src;
		}

		char[] charr = new char[len];
		System.arraycopy(src.toCharArray(), 0, charr, diff, src.length());
		for (int i = 0; i < diff; i++) {
			charr[i] = ch;
		}
		return new String(charr);
	}

	public static Object[] takeArray(Object[] array, int begin, int end) {
		if (end >= array.length - 1) {
			return array;
		} else {
			Object[] result = new Object[end - begin];
			for (int i = begin; i < end; i++) {
				result[i] = array[i];
			}
			return result;
		}
	}


}
