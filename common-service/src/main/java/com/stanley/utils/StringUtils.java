/**
 * 	文件名:   StringMessage.java
 *
 * 	Copyright (c) 2006 百联优力科技有限公司
 *
 * 	创建人:	吴克源
 *
 *	日 期:	2006-9-12
 * 
 *	修改人:	吴克源
 *	
 * 	日 期:	2006-9-13
 *	
 *	描 述:	字符串，消息处理工具类
 *
 *	版 本:	1.0
 */
package com.stanley.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 	类说明：字符串，消息处理工具类
 * 
 */
public class StringUtils {
	/**
	 * 不允许实例化
	 *
	 */
	private StringUtils(){
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return 空返回 true,非空返回false
	 */
	public static boolean isNull(String str){
		if(str == null)
			return true;
		
		if(str.trim().length() == 0)
			return true;
		
		if(str.trim().equalsIgnoreCase("null")){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断object是否为空
	 * @param obj
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2017年1月4日
	 */
	public static boolean isObjectNull(Object obj){
		if(obj == null)
			return true;
		
		return false;
	}

	/**
	 * 空值转为""
	 * @param str
	 * @return 空返回 "",非空返回本身
	 */
	public static String null2Blank(Object str){
		return ( str == null )?"":str.toString();
	}
	
	/**
	 * 空值为"0"
	 * @param str
	 * @return 空返回 "",非空返回本身
	 */
	public static String null2Zero(Object str){
		return ( str == null )?"0":str.toString();
	}
	
	/**
	 * 处理从页面获得的字符参数乱码问题<br>
	 * ISO8859-1 转 UTF-8
	 * @title      convertCharacter
	 * @author  ts    
	 * @date      2014年3月14日 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public  static String convertCharacter(String value,String charsetName)
	{
		String ret = "";
		if (value != null) {
			try {
				ret = new String(value.getBytes("ISO8859-1"),charsetName);
			} catch (Exception e) {
				System.err.println("exception:" + e.getMessage());
				System.err.println("The String is:" + value);
			}
		}
		return ret;
	}

	/**
	 * 传进一个dataTime格式，返回Data_Time
	* @Title: getTrueField 
	* @author xzm  2013-12-17
	* @Description: 
	* @param field
	* @param order
	* @return    
	* @return String    
	* @throws
	 */
	public static String getTrueField(Object field,Object order)
	{
		if(null == field || null == order){
			return null;
		}
		String trueField;
		String strField = field.toString();
		String strOrder = order.toString();
		String[] strOrderArray = null;
		if (strOrder.contains(",")) {
			strOrderArray = strOrder.split(",");
		}
		strField = strField + ",";
		int c = strField.length();
		int j = -1;
		trueField = String.valueOf(strField.charAt(0));
		for (int i = 1; i < c; i++) {
			Character cc = strField.charAt(i);
			if (String.valueOf(cc).equals(",")) {
				if (strOrderArray != null && strOrderArray.length > 0) {
					j = j + 1;
					trueField += " " + strOrderArray[j];
				} else {
					trueField += " " + strOrder;
				}
			}
			if (Character.isUpperCase(cc)) {
				trueField = trueField + "_" + String.valueOf(strField.charAt(i)).toLowerCase();
			} else {
				trueField += strField.charAt(i);
			}
		}
		return trueField.substring(0, trueField.length() - 1);
	}

	/**
	 * 字符集转换，处理乱码
	 * @param msg
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月5日
	 */
	public static String convert2UTF8(String msg){
		try {
			return new String(msg.getBytes("ISO-8859-1"), Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * map按key的降序排序
	 * @param oriMap
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2017年1月5日
	 */
	public static Map<String, String> sortMapByKeyDesc(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o2.compareTo(o1);
					}
			});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}
	
	/**
	  * 组合的算法
	  * @param a 数据数组
	  * @param num M选N中 N的个数
	  * @return
	  */
	private static List<String> combine(String[] a, int num) {
		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String[] b = new String[a.length];
		for (int i = 0; i < b.length; i++) {
			if (i < num) {
				b[i] = "1";
			} else
				b[i] = "0";
		}

		int point = 0;
		int nextPoint = 0;
		int count = 0;
		int sum = 0;
		String temp = "1";
		while (true) {
			// 判断是否全部移位完毕
			for (int i = b.length - 1; i >= b.length - num; i--) {
				if (b[i].equals("1"))
					sum += 1;
			}
			// 根据移位生成数据
			for (int i = 0; i < b.length; i++) {
				if (b[i].equals("1")) {
					point = i;
					sb.append(a[point]);
					sb.append(" ");
					count++;
					if (count == num)
						break;
				}
			}
			// 往返回值列表添加数据
			list.add(sb.toString());

			// 当数组的最后num位全部为1 退出
			if (sum == num) {
				break;
			}
			sum = 0;

			// 修改从左往右第一个10变成01
			for (int i = 0; i < b.length - 1; i++) {
				if (b[i].equals("1") && b[i + 1].equals("0")) {
					point = i;
					nextPoint = i + 1;
					b[point] = "0";
					b[nextPoint] = "1";
					break;
				}
			}
			// 将 i-point个元素的1往前移动 0往后移动
			for (int i = 0; i < point - 1; i++)
				for (int j = i; j < point - 1; j++) {
					if (b[i].equals("0")) {
						temp = b[i];
						b[i] = b[j + 1];
						b[j + 1] = temp;
					}
				}
			// 清空 StringBuffer
			sb.setLength(0);
			count = 0;
		}
		//
		// System.out.println("数据长度 " + list.size());
		return list;

	}
	  
	/**
	* 排列组合的算法
	* @param a 数据数组
	* @return
	*/
	private static List<List<String>> cal(List<String> a) {
		List<List<String>> res = new ArrayList<List<String>>();
		int size = a.size();
		List<String> temp = null;
		if (size == 2) {
			temp = new ArrayList<String>(2);
			temp.add(a.get(0));
			temp.add(a.get(1));
			res.add(temp);

			temp = new ArrayList<String>(2);
			temp.add(a.get(1));
			temp.add(a.get(0));
			res.add(temp);
		} else if (size > 2) {
			List<List<String>> tempRes = null;
			String firstNum = null;
			List<String> tempPar = null;
			for (int i = 0; i < size; i++) {
				firstNum = a.get(i);
				tempPar = new ArrayList<String>(a);
				tempPar.remove(i);
				tempRes = cal(tempPar);
				if (tempRes != null && tempRes.size() > 0) {
					for (List<String> intList : tempRes) {
						intList.add(0, firstNum);
						res.add(intList);
					}
				}

			}
		}

		return res;
	}

	/**
	 * 排列组合的算法
	 *
	 * @param s 字符串
	 * @return
	 */
	public static List<String> sortCombine(String s) {
		if (s == null || s == "")
			return null;

		List<String> zz = new ArrayList<String>();
		List<String> a = new ArrayList<String>();
		String[] qwe = s.split("[+]");
		for (String string : qwe) {
			a.add(string);
		}

		if (a.size() == 1) {
			zz.add("%" + a.get(0) + "%");
		} else {
			int k = a.size() - 1;
			for (int i = a.size() - 1; i >= 0; i--) {
				if (k == i) {
					List<List<String>> resList = cal(a);
					for (List<String> intList : resList) {
						StringBuilder sb = new StringBuilder("%");
						for (String j : intList) {
							sb.append(j + "%");
						}
						zz.add(sb.toString());
					}
				} else if (i == 0) {

					for (String string : a) {
						zz.add("%" + string + "%");
					}
				} else {
					for (String obj : combine(qwe, (i + 1))) {
						String[] zzp = obj.split(" ");
						List<String> abc = new ArrayList<String>();
						for (String string : zzp) {
							abc.add(string);
						}
						List<List<String>> resList = cal(abc);
						for (List<String> intList : resList) {
							StringBuilder sb = new StringBuilder("%");
							for (String j : intList) {
								sb.append(j + "%");
							}
							zz.add(sb.toString());
						}
					}
				}
			}
		}
		return zz;
	}

	/**
	 * 排列组合的算法
	 *
	 * @param s 字符串
	 * @return
	 */
	public static List<String> sortCombine2(String s) {
		if (s == null || s == "")
			return null;

		List<String> zz = new ArrayList<String>();
		List<String> a = new ArrayList<String>();
		String[] qwe = s.split("[+]");
		for (String string : qwe) {
			a.add(string);
		}

		if (a.size() == 1) {
			zz.add(a.get(0));
		} else {
			int k = a.size() - 1;
			for (int i = a.size() - 1; i >= 0; i--) {
				if (k == i) {
					List<List<String>> resList = cal(a);
					for (List<String> intList : resList) {
						StringBuilder sb = new StringBuilder("");
						for (String j : intList) {
							sb.append(j);
						}
						zz.add(sb.toString());
					}
				} else if (i == 0) {

					for (String string : a) {
						zz.add(string);
					}
				} else {
					for (String obj : combine(qwe, (i + 1))) {
						String[] zzp = obj.split(" ");
						List<String> abc = new ArrayList<String>();
						for (String string : zzp) {
							abc.add(string);
						}
						List<List<String>> resList = cal(abc);
						for (List<String> intList : resList) {
							StringBuilder sb = new StringBuilder("");
							for (String j : intList) {
								sb.append(j);
							}
							zz.add(sb.toString());
						}
					}
				}
			}
		}
		return zz;
	}

	/**
	 * 把request的cookie读取到map里面  
	 * @Description
	 * @date 2017年5月12日
	 * @author 童晟  13346450@qq.com
	 * @param @param request
	 * @param @return
	 * @return Map<String,String>
	 */
    public static Map<String, String> readCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies)
                cookieMap.put(cookie.getName(), cookie.getValue());
        }
        return cookieMap;
    }

}
