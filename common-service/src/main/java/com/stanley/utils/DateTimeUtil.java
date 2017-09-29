package com.stanley.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 说明：时间工具类
 * 
 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
 */
public class DateTimeUtil {
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.CHINA);
	private static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
			"yyyy-MM", Locale.CHINA);
	private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"yyyyMMdd", Locale.CHINA);
	private static final SimpleDateFormat dateFormat3 = new SimpleDateFormat(
			"yyyyMMddHHmmss", Locale.CHINA);
	private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static Calendar calendar = GregorianCalendar
			.getInstance(Locale.CHINA);
	/** 日前格式字符串1 DATE_FORMAT_STR1="yyyy-mm-dd" */
	public static final String DATE_FORMAT_STR1 = "yyyy-mm-dd";
	/** 日前格式字符串2 DATE_FORMAT_STR2="yyyymmdd" */
	public static final String DATE_FORMAT_STR2 = "yyyymmdd";

	/**
	 *
	 * 摘要：格式化日期
	 *
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @param date
	 * @return 返回 yyyy-mm-dd的格式
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static String formatDateToStr(Date date, String formatStr) {
		if (formatStr != null) {
			formatStr = formatStr.toLowerCase();

			if (formatStr.equals("yyyy-mm-dd"))
				return dateToStr(date, simpleDateFormat);
			else if (formatStr.equals("yyyy-mm"))
				return dateToStr(date, simpleDateFormat2);
			else if (formatStr.equals("yyyy-mm-dd hh:mm:ss"))
				return dateToStr(date, fullDateFormat);
			else if (formatStr.equals("yyyymmdd"))
				return dateToStr(date, dateFormat2);
			else if(formatStr.equals("number"))
				return dateToStr(date, dateFormat3);
		}
		return null;
	}

	private static String dateToStr(Date date, SimpleDateFormat format) {
		return format.format(date);
	}
	/***
	 * 时间戳转化成时间Date格式
	 */
	public static Date stamp2Date(long time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }

	/***
	 * 得到时间戳:从1970开始的毫秒数
	 */
	public static long  date2Stamp(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    long currentMs = 0;
		try {
			currentMs = sdf.parse(timeString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentMs;
	}

	/**
	 * 得到全格式的 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateToStrFullFormat(Date date) {
		return fullDateFormat.format(date);
	}

	/***
	 * 得到全格式的，烟草公司用 yyyy-MM-ddTHH:mm:ss
	 */
	public static String getDateToStrFullFormatTobacco(Date date) {
		String fullDate = fullDateFormat.format(date);
		return fullDate.substring(0, 10).concat("T")
				.concat(fullDate.substring(11));
	}

	/***
	 * 得到时间，烟草公司用 HH:mm:ss
	 */
	public static String getTimeToStrTobacco(Date date) {
		return fullDateFormat.format(date).substring(11);
	}

	/**
	 * 摘要：字符转成date
	 *
	 * @说明：
	 * @创建：作者:Administrator 创建时间：Jun 2, 2009
	 * @param str
	 * @param
	 * @return
	 * @修改历史： [序号](Administrator Jun 2, 2009)<修改说明>
	 */
	public static Date formatStrToDate(String str) {
		return StringToDate(str, simpleDateFormat);
	}

	public static Date formatStrToDate1(String str)
	{
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return StringToDate(str, simpleDateFormat1);
	}
	private static Date StringToDate(String str, SimpleDateFormat format) {
		Date result = null;
		try {
			result = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *
	 * 摘要：
	 *
	 * @说明：返回年份
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 *
	 * 摘要：根据指定的时间返回年份
	 *
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：Jun 23, 2009
	 * @param date
	 * @return
	 * @修改历史： [序号](zbs Jun 23, 2009)<修改说明>
	 */
	public static int getYear(Date date) {
		Calendar c = GregorianCalendar.getInstance(Locale.CHINA);
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 *
	 * 摘要：返回月份
	 *
	 * @说明： 比正常的月份小1
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	/**
	 *
	 * 摘要：根据指定的时间返回月份
	 *
	 * @说明： 比正常的月份小1
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static int getMonth(Date date) {
		Calendar c = GregorianCalendar.getInstance(Locale.CHINA);
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	/**
	 *
	 * 摘要：返回当前的 日期
	 *
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static int getDay() {
		return calendar.get(Calendar.DATE);
	}

	/**
	 *
	 * 摘要：返回当前的 日期
	 *
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static int getDay(Date date) {
		Calendar c = GregorianCalendar.getInstance(Locale.CHINA);
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 *
	 * 摘要：返回自定义的时间
	 *
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：May 8, 2009
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @修改历史： [序号](zbs May 8, 2009)<修改说明>
	 */
	public static Date getDateTime(int year, int month, int date) {
		Calendar c = GregorianCalendar.getInstance(Locale.CHINA);
		c.set(year, month, date);
		return c.getTime();
	}

	/**
	 * 给制单日期加上当前时间的时分秒
	 *
	 * @param loginDt
	 *            登录时间
	 * @return 制单时间
	 */
	public static Date voucherDtFillHMS(Date loginDt) {
		Calendar rightNow = Calendar.getInstance();
		int h = rightNow.get(Calendar.HOUR_OF_DAY);
		int m = rightNow.get(Calendar.MINUTE);
		int s = rightNow.get(Calendar.SECOND);
		rightNow.setTime(loginDt);
		rightNow.set(Calendar.HOUR_OF_DAY, h);
		rightNow.set(Calendar.MINUTE, m);
		rightNow.set(Calendar.SECOND, s);
		return rightNow.getTime();
	}

	/**
	 * 给日期加上当前时间的时分秒
	 *
	 * @Title: DateAddTime
	 * @author tw 2013-11-22
	 * @Description:
	 * @param @param d
	 * @param @param hour
	 * @param @param minute
	 * @param @param second
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date DateAddTime(Date d, Integer hour, Integer minute, Integer second) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(d);
		if(hour!=null){
			cd.set(Calendar.HOUR_OF_DAY, hour);
		}
		if(minute!=null){
			cd.set(Calendar.MINUTE, minute);
		}
		if(second!=null){
			cd.set(Calendar.SECOND, second);
		}
		return cd.getTime();
	}
	/**
	 * 当前时间前进后退
	 * @param d
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 *@author cqg
	 *2015年9月23日
	 */
	public static Date nowAddTime(Date d,int day, int hour, int minute, int second) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(d);
		cd.add(Calendar.DAY_OF_YEAR, day);
		cd.add(Calendar.HOUR_OF_DAY, hour);
		cd.add(Calendar.MINUTE, minute);
		cd.add(Calendar.SECOND, second);
		return cd.getTime();
	}

	/**
	 *
	 * 摘要：
	 *
	 * @说明：得到当前的完整格式的时间：yyyy-MM-dd HH:mm:ss
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：Nov 16, 2009
	 * @return
	 * @修改历史： [序号](zbs Nov 16, 2009)<修改说明>
	 */
	public static Date getFullDateHMS() {
		Date result = null;
		Calendar c2 = GregorianCalendar.getInstance(Locale.CHINA);
		String value = getYear() + "-" + (getMonth() + 1) + "-" + getDay();
		if (value instanceof String) {
			try {

				String str = value + " " + c2.get(Calendar.HOUR_OF_DAY) + ":"
						+ c2.get(Calendar.MINUTE) + ":"
						+ c2.get(Calendar.SECOND);
				result = fullDateFormat.parse(str);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/***
	 * 
	 * 摘要：计算2个日期的差
	 * 
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：Oct 22, 2009
	 * @param date1
	 *            ：比较早的日期
	 * @param date2
	 *            ：比较晚的日期
	 * @return
	 * @修改历史： [序号](zbs Oct 22, 2009)<修改说明>
	 */
	public static int twoDateDistance(String date1, String date2) {
		Date d1 = DateTimeUtil.formatStrToDate(date1);
		Date d2 = DateTimeUtil.formatStrToDate(date2);
		return DateTimeUtil.twoDateDistance(d1, d2);
	}

	/***
	 * 
	 * 摘要：计算2个日期的差
	 * 
	 * @说明：
	 * @创建：作者:詹保山(zbs),softzbs@126.com 创建时间：Oct 22, 2009
	 * @param date1
	 *            ：比较早的日期
	 * @param date2
	 *            ：比较晚的日期
	 * @return
	 * @修改历史： [序号](zbs Oct 22, 2009)<修改说明>
	 */
	public static int twoDateDistance(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
	}


	/***
	 * 
	 * 摘要：计算2个日期的毫秒差
	 * 
	 * @说明：
	 * @创建：作者:xql
	 * @param date1
	 *            ：比较早的日期
	 * @param date2
	 *            ：比较晚的日期
	 * @return
	 * @修改历史： [序号](zbs Oct 22, 2009)<修改说明>
	 */
	public static int twoDateDistanceMilliSeconds(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()));
	}
	
	
	
	/**
	 * 
	 * 摘要：得到几天后的日期
	 * 
	 * @说明：
	 * @创建：作者:ts 创建时间：2010-9-13
	 * @param d
	 * @param day
	 * @return
	 * @修改历史： [序号](ts 2010-9-13)<修改说明>
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static void main(String[] args) {
		//System.out.println(getDateMillionSeconds("2016-12-10 16:50:33"));
		//Date d = getDateToTime(1481359833000l);
		//System.out.println(d);
	}

	/**
	 * 
	 * 摘要：获得指定月份的天数
	 * 
	 * @说明：
	 * @创建：作者:Hzj 2013-11-22
	 * @param @param d
	 * @param @return
	 * @return int
	 * @修改历史： [序号](Hzj 2013-11-22)<修改说明>
	 */
	public static int getDayOfMonth(Date d) {
		int dayCount = 28;
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		dayCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return dayCount;
	}

	/**
	 * 
	 * 摘要：获得指定月份的剩余天数（不包括当天）
	 * 
	 * @说明：
	 * @创建：作者:Hzj 2013-11-22
	 * @param @param d
	 * @param @return
	 * @return int
	 * @修改历史： [序号](Hzj 2013-11-22)<修改说明>
	 */
	public static int getSurplusDayOfMonth(Date d) {
		int dayCount = 28;
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		dayCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
				- cal.get(Calendar.DATE);
		return dayCount;
	}

	/**
	 * 获取指定月份第一天
	 * @author zgj  2015-5-12
	 * @param date
	 * @param format
	 * @return
	 */
	public static String firstDayOfMonth(String date,String format)
	{
		Calendar cal = Calendar.getInstance();
        cal.setTime(formatStrToDate(date));
        cal.add(Calendar.MONTH, -1);
        return formatDateToStr(cal.getTime(),format);
	}
	/**
	 * 
	 * 获取指定月份第一天
	 * @author zgj  2015-5-12
	 * @param date
	 * @param format
	 * @return
	 */
	public static String firstDayOfMonth(Date date,String format)
	{
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return formatDateToStr(cal.getTime(),format);
	}
	/**
	 * 取得当月最后一天
	* @Title: lastDayOfMonth 
	* @author xzm  2013-11-26
	* @Description: 
	* @param date
	* @return    
	* @return Date    
	* @throws
	 */
	public static String lastDayOfMonth(String date,String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatStrToDate(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return formatDateToStr(cal.getTime(),format);
		}
	/**
	 * 取得当月最后一天
	* @Title: lastDayOfMonth 
	* @author xzm  2013-11-26
	* @Description: 
	* @param date
	* @param format
	* @return    
	* @return String    
	* @throws
	 */
	public static String lastDayOfMonth(Date date,String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return formatDateToStr(cal.getTime(),format);
		}
    /**
     * 
    * @Title: formatStringToDate 
    * @author xzm  2013-12-2
    * @Description: 
    * @param
    * @param formatStr
    * @return    
    * @return Date    
    * @throws
     */
	public static String formatStringToDate(String str, String formatStr) {
		DateFormat df = new SimpleDateFormat(formatStr);
		try {
			return df.format(df.parse(str));
		} catch (Exception ex) {
			return "";
		}
	}
	/**
	 * 获取指定格式的日期字符串
	 *  @author zgj
	 *	日期：2015年9月21日下午7:26:50
	 *  描述：@param date
	 *  描述：@param formatStr
	 *  描述：@return
	 */
	public static String formatDateToString(Date date, String formatStr){
		DateFormat df = new SimpleDateFormat(formatStr);
		return df.format(date);
	}
	/**
	 * 根据日期获取星期
	 * @param date
	 * @return
	 * @author 1447638927@qq.com 周振平
	 * @date 2016年10月25日
	 */
	public static String getWeekOfDate(Date date) {
    	String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    	//String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    	return weekDaysName[intWeek];
	} 
}
