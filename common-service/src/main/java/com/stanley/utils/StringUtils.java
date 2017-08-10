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
	final static String[] chineseNumber = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	final static String[] chineseIntBit = {"元","拾","佰","仟"};
	final static String[] chineseLongBit = {"万","亿"};
	final static String[] chineseDecBit = {"角","分"};
	final static String   chineseZero = "整";
	
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
		
		/*if(String.valueOf(obj).trim().length() == 0)
			return true;*/
		
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
	 * 设置字符串为空
	 * @param strText
	 * @return
	 */
	public static String setNull(String strText){
		strText = "null";
		return strText;
	}
	
	/**
	 * 格式化字符串，数量字段专用，返回##0.0000.
	 * @param sQty
	 * @return
	 */
	public static String formatQty(String sQty){
		if(sQty == null || sQty.equals(""))
			sQty = "0";
		
		Double dQty = new Double(sQty);
		
		DecimalFormat df = new DecimalFormat("##0.000");
		sQty=df.format(dQty);
		return sQty;
	}
	
	
	/**
	 * 格式化字符串，数量字段专用，当f=null时默认返回###,##0.0000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sQty
	 * @param f
	 * @return
	 */
	public static String formatQty(String sQty,String f){
		if(sQty == null || sQty.equals(""))
			sQty = "0";
		
		Double dQty = new Double(sQty);
		
		DecimalFormat df = new DecimalFormat("##0.0000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sQty=df.format(dQty);
		return sQty;
	}
	
	/**
	 * 格式化字符串，数量字段专用，返回######.0000，并自动去除小数点后的0
	 * @param dQty
	 * @return
	 */
	public static String formatQty(double dQty){
		DecimalFormat df = new DecimalFormat("##0.0000");
		
		return df.format(dQty);
	}
//	两位小数
	public static String formatQty2(double dQty){
		DecimalFormat df = new DecimalFormat("##0.00");
		
		return df.format(dQty);
	}
	//三位小数
	public static String formatQty3(double dQty){
		DecimalFormat df = new DecimalFormat("##0.000");
		
		return df.format(dQty);
	}
	/**
	 * 格式化字符串，数量字段专用，当f=null时默认返回###,###.0000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dQty
	 * @param f
	 * @return
	 */
	public static String formatQty(double dQty,String f){
		DecimalFormat df = new DecimalFormat("##0.0000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sQty=null;
		sQty=df.format(dQty);
		return sQty;
	}
	
	/**
	 * 格式化字符串，单价字段专用，返回######.000000，并自动去除小数点后的0
	 * @param sQty
	 * @return
	 */
	public static String formatPrice(String sPrice){
		if(sPrice == null || sPrice.equals(""))
			sPrice = "0";
		
		Double dPrice = new Double(sPrice);
		DecimalFormat df = new DecimalFormat("##0.000");
		return df.format(dPrice);
	}
	
	/**
	 * 格式化字符串，单价字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sPrice
	 * @param f
	 * @return
	 */
	public static String formatPrice(String sPrice,String f){
		if(sPrice == null || sPrice.equals(""))
			sPrice = "0";
		Double dPrice = new Double(sPrice);
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sPrice=df.format(dPrice);
		return  sPrice;
	}
	
	/**
	 * 格式化字符串，单价字段专用，返回######.000000，并自动去除小数点后的0
	 * @param dPrice
	 * @return
	 */
	public static String formatPrice(double dPrice){
		DecimalFormat df = new DecimalFormat("##0.000000");
		return df.format(dPrice);
	}
	//两位小数
	public static String formatPrice2(double dPrice){
		DecimalFormat df = new DecimalFormat("##0.000");
		return df.format(dPrice);
	}
	
	/**
	 * 格式化字符串，单价字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dPrice
	 * @param f
	 * @return
	 */
	public static String formatPrice(double dPrice,String f){
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sPrice=df.format(dPrice);
		return sPrice;
	}
	
	/**
	 * 格式化字符串，金额字段专用，返回######.000000，并自动去除小数点后的0
	 * @param sQty
	 * @return
	 */
	public static String formatMny(String sMny){
		if(sMny == null || sMny.equals(""))
			sMny = "0";
		
		Double dMny = new Double(sMny);
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(dMny);
	}
	
	/**
	 * 格式化字符串，金额字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param sMny
	 * @param f
	 * @return
	 */
	public static String formatMny(String sMny,String f){
		if(sMny == null || sMny.equals(""))
			sMny = "0";
		
		Double dMny = new Double(sMny);
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		sMny=df.format(dMny);
		return sMny;
	}
	
	/**
	 * 格式化字符串，金额字段专用，返回######.000000，并自动去除小数点后的0
	 * @param dMny
	 * @return
	 */
	public static String formatMny(double dMny){
		DecimalFormat df = new DecimalFormat("##0.000000");
		return df.format(dMny);
	}
	//两位小数
	public static String formatMny2(double dMny){
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(dMny);
	}
	
	/**
	 * 格式化字符串，金额字段专用，当f=null时默认返回###,###.000000，并自动去除小数点后的0
	 * 其它按格式返回
	 * @param dMny
	 * @param f
	 * @return
	 */
	public static String formatMny(double dMny,String f){
		DecimalFormat df = new DecimalFormat("##0.000000");
		if(f != null){
			df = new DecimalFormat(f);
		}
		String sMny=null;
		sMny=df.format(dMny);
		return sMny;
	}
	

	/**
	 *摘要：数字格式化字符串
	 *@说明：如果为0，显示为空
	 *@创建：作者:lj		创建时间：2007-12-13
	 *@param dMny	格式化数字
	 *@param f		格式字符串
	 *@param isShowZero		true-显示0值，false-不显示0值
	 *@return 
	 *@修改历史：
	 *		[序号](lj	2007-12-13)<修改说明>
	 */
	public static String formatMum(double dMny,String f,boolean isShowZero){
		String sMny=null;
		
		if(isShowZero==false && dMny==0){
			sMny="";
		}else{
			DecimalFormat df ;
			if(f == null){
				df = new DecimalFormat("##0.000000");
			}else{
				df = new DecimalFormat(f);
			}
				
			sMny=df.format(dMny);
		}

		return sMny;
	}
	
	/**
	 * 格式化税率
	 * @param sRate
	 * @param nType 当nType==1时，sRate输入的值应该为17%;当nType!=1时，sRate输入的值应该为0.17;
	 * @return nType 当nType==1时，返回0.17;当nType!=1时，返回17%
	 */
	public static String formatRate(String sRate,int nType){
		
		if(nType == 1){
			Double dRate = Double.valueOf(sRate.substring(0,sRate.length() - 1));
			
			return new Double(dRate / 100).toString();
		}
		
		double dRate = new Double(sRate).doubleValue()*100;
		
		return formatMny(dRate,"##0.00")+"%";
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param sNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(String sNum){
		String sNumber = formatMny(sNum,"#####0.00");
		
		//查询小数点位置
		int nDecPos = 0;
		for(int i=0; i<sNumber.length(); i++){
			if(sNumber.charAt(i) == '.'){
				nDecPos = i;
				break;
			}
		}
		
		//整数位
		StringBuffer sbNumber = new StringBuffer();
		
		//小数位
		StringBuffer sbDecimal = new StringBuffer();
		
		//最终转换结果
		StringBuffer sbChinese = new StringBuffer();
		
		if(nDecPos > 0){
			sbNumber.append(sNumber.substring(0,nDecPos)).reverse();
			sbDecimal.append(sNumber.substring(nDecPos+1));
		}
		else{
			sbNumber.append(sNumber).reverse();
		}
		
		int nLen = sbNumber.length();
		for(int i=0; i<nLen; i++){
			String strNum = String.valueOf(sbNumber.charAt(i));
			int nNum = Integer.parseInt(strNum);
			int nPos = i % 4;
			int nZero = i / 4;
			
			if(nZero > 0){
				if(nPos > 0){
					if(nNum > 0)
						sbChinese.append(chineseIntBit[nPos]);
					
					sbChinese.append(chineseNumber[nNum]);
				}
				else{
					if(nNum > 0)
						sbChinese.append(chineseLongBit[nZero-1]);
					sbChinese.append(chineseNumber[nNum]);
				}
			}
			else{
				if(nNum > 0)
					sbChinese.append(chineseIntBit[nPos]);
				
				if(i <= (nLen - 1) && nNum != 0)
					sbChinese.append(chineseNumber[nNum]);
			}
		}
		sbChinese.reverse();
		
		if(nDecPos > 0){
			for(int i=0;i<sbDecimal.length(); i++){
				String strNum = String.valueOf(sbDecimal.charAt(i));
				int nNum = Integer.parseInt(strNum);
				int nPos = i % 4;

				sbChinese.append(chineseNumber[nNum]);
				if(nNum > 0)
					sbChinese.append(chineseDecBit[nPos]);
			}
		}
		else{
			sbChinese.append(chineseZero);
		}
		
		return sbChinese;
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param dNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(double dNum){
		String sNum = String.valueOf(dNum);
		
		return convertNumberToChinese(sNum);
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param nNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(long nNum){
		String sNum = String.valueOf(nNum);
		
		return convertNumberToChinese(sNum);
	}
	
	/**
	 * 阿拉伯数字转换为中文大写数字
	 * @param nNum
	 * @return
	 */
	public static StringBuffer convertNumberToChinese(int nNum){
		String sNum = String.valueOf(nNum);
		
		return convertNumberToChinese(sNum);
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
	public static String formatQty1(double dQty){
		DecimalFormat df = new DecimalFormat("##0.00");
		
		return df.format(dQty);
	}
	public static long getArrayMaxValue(long[] lCompareArray){
		if(lCompareArray.length<=0){
			return 0;
		}else{
			long lRtn=0;
			for(int i=0;i<lCompareArray.length;i++){
				if(lCompareArray[i]>lRtn){
					lRtn=lCompareArray[i];
				}
			}
			return lRtn;
		}	
	}
	
	/**
	 *摘要：
	 *@说明：
	 *@创建：作者:whj		创建时间：2007-12-19
	 *@param sParam
	 *@return 
	 *@修改历史：
	 *		[序号](whj	2007-12-19)<修改说明>
	 */
	public static String formatResult(String sParam){
		if(sParam == null || sParam.equals("0")){
			return "";
		}else{
			String sResult = "";
			DecimalFormat df = new DecimalFormat("###,###.00");
			sResult = df.format(Double.valueOf(sParam));
			return sResult;
		}
	}
	/**
	 *摘要：获得指定字符串的长度
	 *@说明：
	 *@创建：作者:YQH 	创建时间：2008-07-19
	 *@param sStr   指定字符串
	 *@return 
	 *@修改历史：
	 *		[序号](whj	2007-12-19)<修改说明>
	 */
	public static int valieFiledLength(String sStr){
		String regex="^\\w+$";
    	String regex1 = "&@!#$%^)(-+=_\\'?><,~`!/ {}";
    	int lengthg=0;
    	int j = 0;
    	String srr = "";
    	
    	for(int i =0;i<sStr.length();i++){
    			if(i<sStr.length()) j=i+1;
    			else j=i;
    			srr=sStr.substring(i,j);
       			if(!srr.matches(regex)&&regex1.indexOf(srr)==-1){
    				lengthg=lengthg+2;
    			}else{
    				lengthg= lengthg+1;
    			}
    		
    	
    	
    	}
    	return lengthg;
    }
	public static String getFiledValue(String sStr,int length){ 
		String regex="^\\w+$";
    	String regex1 = "&@!#$%^)(-+=_\\'?><,~`!/ {}";
    	int lengthg=0;
    	int j = 0;
    	String srr = "";
    	String  sRtn = "";
    	for(int i =0;i<sStr.length();i++){
    		if(lengthg>=length) break;
    			if(i<sStr.length()) j=i+1;
    			else j=i;
    			srr=sStr.substring(i,j);
    			if(!srr.matches(regex)&&regex1.indexOf(srr)==-1){
    				lengthg=lengthg+2;
    				
    			}else{
    				lengthg= lengthg+1;
    				
    			}
    			sRtn=sRtn+srr;
    	
    	
    	}
    	return sRtn;
    }
	
	
	public static void main(String[] argc){
	   try {
		   String en = java.net.URLEncoder.encode("我的编码", "UTF-8");
		   System.out.println("en="+en);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	   System.out.println( encrypt("admin123"));
	   System.out.println(convertProperty2Column("roleMakere"));
    }

	/**
	 *摘要：密码加密
	 *@说明：
	 *@创建：作者:Administrator		创建时间：Apr 9, 2009
	 *@param password
	 *@return 
	 *@修改历史：
	 *		[序号](Administrator	Apr 9, 2009)<修改说明>
	 */
	public static String encrypt(String password) {
	    String result = null;
	    String password1 = "";
	    if (password != null) {
	      try {
	        MessageDigest ca = MessageDigest.getInstance("SHA");
	        result = "";
	        char pass[] = password.toCharArray();
	        for(int i=0; i<pass.length; i++){
	        	password1 = (String)password1+pass[i]+"&^./&";
	        }
	        byte mess[] = password1.getBytes();
	        ca.reset();
	        byte[] hash = ca.digest(mess);
	        result = byte2hex(hash);
	      }
	      catch (Exception err) {
	        err.printStackTrace();
	      }
	    }
	    return result;
	  }

	private static String byte2hex(byte[] b) {
	    String hs = "";
	    String stmp = "";
	
	    for (int n = 0; n < b.length; n++) {
	      stmp = (Integer.toHexString(b[n] & 0XFF));
	      if (stmp.length() == 1) {
	        hs = hs + "0" + stmp;
	      }
	      else {
	        hs = hs + stmp;
	      }
	    }
	    return hs.toUpperCase();
	  }
	
	/**
	 * 无用了
	 * 将用逗号隔开的字符串，转换成List<String>
	* @Title: getList 
	* @author tw  2013-11-22
	* @Description: 
	* @param @param value
	* @param @return    
	* @return List<String>    
	* @throws
	 */
	public static List<String> generateList(String value) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNull(value))
			return list;
		String[] vl = value.split(",");
		for (String str : vl) {
			if (!StringUtils.isNull(str))
				list.add(str);
		}
		return list;
	}
	/**
	 * 替换指定的字符串
	* @Title: removeNoUseString 
	* @author xzm  2013-12-12
	* @Description: 
	* @param url
	* @param oldChar
	* @param newChar
	* @return    
	* @return String    
	* @throws
	 */
	public static String removeNoUseString(String url,String oldChar,String newChar)
	{
		if(url.contains(oldChar))
		{
			url=url.replace(oldChar, newChar);
			return removeNoUseString(url,oldChar,newChar);
		}
		return url;
	}
	/*public static Object getTrueField(Object field)
    {
		if(null == field){
			return null;
		}
		String trueField;
		String strField = field.toString();
		if (!strField.contains("_")) {
			int c = strField.length();
			trueField = String.valueOf(strField.charAt(0)).toUpperCase();
			for (int i = 1; i < c; i++) {
				Character cc = strField.charAt(i);
				if (Character.isUpperCase(cc)) {
					trueField = trueField + "_" + strField.charAt(i);
				} else {
					trueField += strField.charAt(i);
				}
			}
			return trueField;
		}
		return field;
	}*/
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
	 * 去掉double小数点后面全部的零（小数点后面有非0数字，则不处理）
	* @Title: removePointZero 
	* @author xql  2014-1-9
	* @Description: 
	* @param @param para
	* @param @return    
	* @return String    
	* @throws
	 */
	public static String removePointZero(double para) {
		String s= Double.toString(para);
		if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;
	}
	/**
	 * 对页面输入的字符串进行转义，将换行空格转换成<br/> &nbsp; 
	 * @param content
	 * @return
	 */
	public static String replaceNewline(String content)
	{
		if(content.indexOf("\n") != -1)
		{
			content = content.replaceAll("<br/>", "\n");
		}
		else if(content.indexOf(" ") != -1)
		{
			content = content.replaceAll("&nbsp;&nbsp;", " ");
		}
		return content;
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
	 * 把属性名转成字段名。
	 * 比如roleName -> role_name
	 * @param str
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年4月6日
	 */
	public static String convertProperty2Column(String propString){
		if(isNull(propString)){
			return null;
		}
		StringBuilder colString = new StringBuilder("");
		for(int i = 0; i < propString.length(); i++){
			char c = propString.charAt(i);
			if(Character.isLowerCase(c)){
				colString.append(c);
			}else{
				colString.append('_');
				colString.append(String.valueOf(c).toLowerCase());
			}
		}
		return colString.toString();
	}
	
	/**
	 * 序列化
	 * @param object
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月30日
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @author 13346450@qq.com 童晟 
	 * @date 2016年8月30日
	 */
	public static Object unserialize(byte[] bytes) {
		if (bytes == null)
			return null;
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
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
	       if(size == 2) {  
	            temp = new ArrayList<String>(2);  
	            temp.add(a.get(0));  
	           temp.add(a.get(1));  
	            res.add(temp);  
	              
	            temp = new ArrayList<String>(2);  
	            temp.add(a.get(1));  
	            temp.add(a.get(0));  
	            res.add(temp);  
	        } else if(size > 2) {  
	            List<List<String>> tempRes = null;  
	            String firstNum = null;  
	            List<String> tempPar = null;  
	            for(int i=0; i<size; i++) {  
	               firstNum = a.get(i);  
	                tempPar = new ArrayList<String>(a);  
	                tempPar.remove(i);  
	                tempRes = cal(tempPar);  
	                if(tempRes != null && tempRes.size() > 0) {  
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
		  * @param s 字符串
		  * @return
		  */
	  public static List<String>  sortCombine(String s){			
			if(s == null || s == "")
				return null;
			
			List<String> zz = new ArrayList<String>();
			List<String> a = new ArrayList<String>();
			String[] qwe = s.split("[+]");
			for (String string : qwe) {
				a.add(string);
			}
			
			if(a.size()==1){
				zz.add("%"+a.get(0)+"%");
			}else{
				int k = a.size()-1;
		        for(int i=a.size()-1;i>=0;i--){
		        	if(k == i){
		        	List<List<String>> resList = cal(a);  
			        for (List<String> intList : resList) {  
			        	StringBuilder sb = new StringBuilder("%");
			            for (String j : intList) {  
			                sb.append(j+"%");
			            }  
			            zz.add(sb.toString());
			        } 
		        	}else if(i==0){
		        		
		        		for (String string : a) {
							zz.add("%"+string+"%");
						}
		        	}else{
		        		for (String obj : combine(qwe, (i+1))) {
		        			String[] zzp = obj.split(" ");
		        			List<String> abc = new ArrayList<String>();
		        			for (String string : zzp) {
		        				abc.add(string);
							}
		        			List<List<String>> resList = cal(abc);  
					        for (List<String> intList : resList) {  
					        	StringBuilder sb = new StringBuilder("%");
					            for (String j : intList) {  
					                sb.append(j+"%");
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
		  * @param s 字符串
		  * @return
		  */
	  public static List<String>  sortCombine2(String s){
		if(s == null || s == "")
			return null;
		
		List<String> zz = new ArrayList<String>();
		List<String> a = new ArrayList<String>();
		String[] qwe = s.split("[+]");
		for (String string : qwe) {
			a.add(string);
		}
		
		if(a.size()==1){
			zz.add(a.get(0));
		}else{
			int k = a.size()-1;
	        for(int i=a.size()-1;i>=0;i--){
	        	if(k == i){
	        	List<List<String>> resList = cal(a);  
		        for (List<String> intList : resList) {  
		        	StringBuilder sb = new StringBuilder("");
		            for (String j : intList) {  
		                sb.append(j);
		            }  
		            zz.add(sb.toString());
		        } 
	        	}else if(i==0){
	        		
	        		for (String string : a) {
						zz.add(string);
					}
	        	}else{
	        		for (String obj : combine(qwe, (i+1))) {
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
