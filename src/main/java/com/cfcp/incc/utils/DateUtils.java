package com.cfcp.incc.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 日期工具类
 * <p>
 *
 * @author zyj
 * @date 12/15/2014
 * @since 0.1
 */
public class DateUtils {

	/**
	 * 全局默认日期格式
	 */
	public static final String Format_Date = "yyyy-MM-dd";

	/**
	 * 全局默认时间格式
	 */
	public static final String Format_Time = "HH:mm:ss";

	public static final String Format_Date_CN = "yyyy年MM月dd日";

	/**
	 * 全局默认日期时间格式
	 */
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 得到以yyyy-MM-dd格式表示的当前日期字符串
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat(Format_Date).format(new Date());
	}

	/**
	 * 得到以yyyy-MM-dd格式表示的当前日期字符串目录路径
	 */
	public static String getCurrentDateDirPath() {
		return new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
	}

	/**
	 * 得到以format格式表示的当前日期字符串
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}
	/**
	 * 得到以format格式表示的当前日期字符串
	 */
	public static String format(Date date) {
		SimpleDateFormat t = new SimpleDateFormat(Format_Date);
		return t.format(date);
	}
	/**
	 * 得到以format格式表示的当前日期字符串
	 */
	public static String formatCn(Date date) {
		SimpleDateFormat t = new SimpleDateFormat(Format_Date_CN);
		return t.format(date);
	}

	/**
	 * 得到以HH:mm:ss表示的当前时间字符串
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(Format_Time).format(new Date());
	}

	/**
	 * 得到以format格式表示的当前时间字符串
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 得到以yyyy-MM-dd HH:mm:ss表示的当前时间字符串
	 */
	public static String getCurrentDateTime() {
		String format = DateUtils.Format_Date + " " + DateUtils.Format_Time;
		return getCurrentDateTime(format);
	}

	/**
	 * 今天是星期几
	 *
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 指定日期是星期几
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是本月的第几天
	 *
	 * @return
	 */
	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 指定日期是当月的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取某一个月的天数
	 *
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 以yyyy-MM-dd格式获取某月的第一天
	 *
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfMonth(String date) {
		return getFirstDayOfMonth(parse(date));
	}

	public static String getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(Format_Date).format(cal.getTime());
	}

	/**
	 * 以yyyy-MM-dd格式获取某月的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static String getLastDayOfMonth(String date) {
		return getLastDayOfMonth(parse(date));
	}

	public static String getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new SimpleDateFormat(Format_Date).format(cal.getTime());
	}

	/**
	 * 今天是本年的第几天
	 *
	 * @return
	 */
	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 指定日期是当年的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 以yyyy-MM-dd解析字符串date，并返回其表示的日期是周几
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 以yyyy-MM-dd解析字符串date，并返回其表示的日期是当月第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 以yyyy-MM-dd解析字符串date，并返回其表示的日期是当年第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 以指定的格式返回当前日期时间的字符串
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 以yyyy-MM-dd格式输出只带日期的字符串
	 */
	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Date).format(date);
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss输出带有日期和时间的字符串
	 */
	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_DateTime).format(date);
	}

	/**
	 * 按指定的format输出日期字符串
	 */
	public static String toString(Date date, String format) {
		if (date == null) return "";
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}

	/**
	 * 以HH:mm:ss输出只带时间的字符串
	 */
	public static String toTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Time).format(date);
	}

	/**
	 * 以yyyy-MM-dd解析两个字符串，并比较得到的两个日期的大小
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(String date1, String date2) {
		return compare(date1, date2, Format_Date);
	}

	/**
	 * 以HH:mm:ss解析两个字符串，并比较得到的两个时间的大小
	 *
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int compareTime(String time1, String time2) {
		return compareTime(time1, time2, Format_Time);
	}

	/**
	 * 以指定格式解析两个字符串，并比较得到的两个日期的大小
	 *
	 * @param date1
	 * @param date2
	 * @param format
	 * @return
	 */
	public static int compare(String date1, String date2, String format) {
		Date d1 = parse(date1, format);
		Date d2 = parse(date2, format);
		return d1.compareTo(d2);
	}

	/**
	 * 以指定解析两个字符串，并比较得到的两个时间的大小
	 *
	 * @param time1
	 * @param time2
	 * @param format
	 * @return
	 */
	public static int compareTime(String time1, String time2, String format) {
		String[] arr1 = time1.split(":");
		String[] arr2 = time2.split(":");
		if (arr1.length < 2) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if (arr2.length < 2) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		int h1 = Integer.parseInt(arr1[0]);
		int m1 = Integer.parseInt(arr1[1]);
		int h2 = Integer.parseInt(arr2[0]);
		int m2 = Integer.parseInt(arr2[1]);
		int s1 = 0, s2 = 0;
		if (arr1.length == 3) {
			s1 = Integer.parseInt(arr1[2]);
		}
		if (arr2.length == 3) {
			s2 = Integer.parseInt(arr2[2]);
		}
		if (h1 < 0 || h1 > 23 || m1 < 0 || m1 > 59 || s1 < 0 || s1 > 59) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if (h2 < 0 || h2 > 23 || m2 < 0 || m2 > 59 || s2 < 0 || s2 > 59) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		if (h1 != h2) {
			return h1 > h2 ? 1 : -1;
		} else {
			if (m1 == m2) {
				if (s1 == s2) {
					return 0;
				} else {
					return s1 > s2 ? 1 : -1;
				}
			} else {
				return m1 > m2 ? 1 : -1;
			}
		}
	}

	/**
	 * 判断指定的字符串是否符合HH:mm:ss格式，并判断其数值是否在正常范围
	 *
	 * @param time
	 * @return
	 */
	public static boolean isTime(String time) {
		String[] arr = time.split(":");
		if (arr.length < 2) {
			return false;
		}
		try {
			int h = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int s = 0;
			if (arr.length == 3) {
				s = Integer.parseInt(arr[2]);
			}
			if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断指定的字符串是否符合yyyy:MM:ss格式，但判断其数据值范围是否正常
	 *
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {
		String[] arr = date.split("-");
		if (arr.length < 3) {
			return false;
		}
		try {
			int y = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int d = Integer.parseInt(arr[2]);
			if (y < 0 || m > 12 || m < 0 || d < 0 || d > 31) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 判断指定日期是否是周末
	 *
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int t = cal.get(Calendar.DAY_OF_WEEK);
		if (t == Calendar.SATURDAY || t == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	/**
	 * 以yyyy-MM-dd解析指定字符串，并判断相应的日期是否是周末
	 *
	 * @param str
	 * @return
	 */
	public static boolean isWeekend(String str) {
		return isWeekend(parse(str));
	}

	/**
	 * 以yyyy-MM-dd解析指定字符串，返回相应java.util.Date对象
	 *
	 * @param str
	 * @return
	 */
	public static Date parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat(Format_Date).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按指定格式解析字符串，并返回相应的java.util.Date对象
	 *
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date parse(String str, String format) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss格式解析字符串，并返回相应的java.util.Date对象
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDateTime(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if (str.length() <= 10) {
			return parse(str);
		}
		try {
			return new SimpleDateFormat(Format_DateTime).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 以指定格式解析字符串，并返回相应的java.util.Date对象
	 *
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date parseDateTime(String str, String format) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期date上加count分钟，count为负表示减
	 */
	public static Date addMinute(Date date, int count) {
		return new Date(date.getTime() + 60000L * count);
	}

	/**
	 * 日期date上加count秒，count为负表示减
	 */
	public static Date addSecond(Date date, int count) {
		return new Date(date.getTime() + 1000L * count);
	}

	/**
	 * 日期date上加count小时，count为负表示减
	 */
	public static Date addHour(Date date, int count) {
		return new Date(date.getTime() + 3600000L * count);
	}

	/**
	 * 日期date上加count天，count为负表示减
	 */
	public static Date addDay(Date date, int count) {
		return new Date(date.getTime() + 86400000L * count);
	}

	/**
	 * 日期date上加count星期，count为负表示减
	 */
	public static Date addWeek(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_YEAR, count);
		return c.getTime();
	}

	/**
	 * 日期date上加count月，count为负表示减
	 */
	public static Date addMonth(Date date, int count) {
		/* ${_ZVING_LICENSE_CODE_} */

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, count);
		return c.getTime();
	}

	/**
	 * 日期date上加count年，count为负表示减
	 */
	public static Date addYear(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, count);
		return c.getTime();
	}

	/**
	 * 人性化显示时间日期,date格式为：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String toDisplayDateTime(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			if (isDate(date)) {
				return toDisplayDateTime(parse(date));
			} else {
				SimpleDateFormat t = new SimpleDateFormat(Format_DateTime);
				Date d = t.parse(date);
				return toDisplayDateTime(d);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return "不是标准格式时间!";
		}
	}

	/**
	 * 人性化显示时间日期
	 *
	 * @param date
	 * @return
	 */
	public static String toDisplayDateTime(Date date) {
		long minite = (System.currentTimeMillis() - date.getTime()) / 60000L;
		if (minite < 60) {
			return toString(date, "MM-dd") + " " + minite + "分钟前";
		}
		if (minite < 60 * 24) {
			return toString(date, "MM-dd") + " " + minite / 60L + "小时前";
		} else {
			return toString(date, "MM-dd") + " " + minite / 1440L + "天前";
		}
	}

	/**
	 * 计算时差
	 * @param dateStr
	 * @param type 计算类型（dd=按天计算;HH=按小时计算；mm=按分钟计算;）
	 * @return
	 */
	public static long differenceTime(String dateStr,String type) {
		String nowStr = getCurrentDate("yyyy-MM-dd HH:mm:ss");
//		java.sql.Date now = parseSQLDate(nowStr,"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		//long minite = (date.getTime() - System.currentTimeMillis()) / 60000L;
		Date date = parseSQLDate(dateStr,"yyyy-MM-dd HH:mm:ss");
		long minite = (date.getTime() - now.getTime()) / 60000L;
		long returnMinite = 0;
		System.err.println("minite : "+minite);
		System.err.println("System.currentTimeMillis() : "+System.currentTimeMillis());
		System.err.println("date.getTime() : "+date.getTime());
		System.err.println("now.getTime() : "+now.getTime());
		System.out.println("******************* "+toDateTimeString(now));
		System.out.println("*******************date.toString() "+date.toString());
		if ("ss".equals(type)) {
			returnMinite = minite*60;
		}
		if ("mm".equals(type)) {
			returnMinite = minite;
		}
		if ("HH".equals(type)) {
			returnMinite = minite / 60L;
		}
		if("dd".equals(type)){
			returnMinite = minite / 1440L;
		}
		return returnMinite;
	}

	/**
	 * 获取当前年份
	 * @return
	 */
	public static int getCurrentYear() {
		return getYearOfDate(new Date());
	}

	/**
	 * 获取指定日期所在的年份
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 *  获取指定日期所在的月份
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	/**
	 * 格式化获取一个java.sql.Timestamp对象；以yyyy-MM-dd HH:mm:ss格式解析字符串
	 * @param str
	 * @return
	 */
	public static java.sql.Timestamp parseTimeStamp(String str) {
		Date date = parseDateTime(str);
		return getSQLTimestamp(date);
	}

	/**
	 * 格式化获取一个java.sql.Timestamp对象；以format格式解析字符串
	 * @param str
	 * @param format
	 * @return
	 */
	public static java.sql.Timestamp parseTimeStamp(String str, String format) {
		Date date = parseDateTime(str, format);
		return getSQLTimestamp(date);
	}

	/**
	 *  格式化获取一个java.sql.Date对象；以yyyy-MM-dd HH:mm:ss格式解析字符串
	 * @param str
	 * @return
	 */
	public static java.sql.Date parseSQLDate(String str) {
		Date date = parse(str);
		return getSQLDate(date);
	}

	/**
	 *  格式化获取一个java.sql.Date对象；以yyyy-MM-dd HH:mm:ss格式解析字符串
	 * @param str
	 * @param format 格式化规则
	 * @return
	 */
	public static java.sql.Date parseSQLDate(String str, String format) {
		Date date = parse(str, format);
		return getSQLDate(date);
	}

	/**
	 * 获取一个当前日期的sql date对象
	 * @return
	 */
	public static java.sql.Date getSQLDate() {
		return getSQLDate(new Date());
	}

	/**
	 * 根据日期装换成sql date
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取一个SQL的时间戳
	 * @return
	 */
	public static java.sql.Timestamp getSQLTimestamp() {
		return getSQLTimestamp(new Date());
	}

	/**
	 * 获取第二天的零点
	 * @param date
	 * @return
	 */
	public static Date getTomorrowZero(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取一天最后一秒或开始一秒
	 * @param date
	 * @param isfirst
	 * @return
	 * @throws ParseException
	 */
	public static Date dateFirstOrLastSecond(Date date, boolean isfirst) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		Date first= null;
		try {
			first = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(isfirst) {
			return first;
		}
		int dayMis = 1000*60*60*24;
		long curMillisecond = first.getTime();
		long resultMis = curMillisecond + (dayMis - 1);
		Date last = new Date(resultMis);
		return last;
	}

	/**
	 * 获取一个SQL的时间戳，根据Date生成
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp getSQLTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 获取指定时间的时间戳
	 * @param s
	 * @return
	 * @throws ParseException
     */
	public static String dateToStamp(String s) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/**
	 * 获取当前时间 秒级 时间戳
	 * @return
     */
	public static String getNowSSStamp(){
		String res;
		Date date = new Date();
		long ts = date.getTime();
		ts = ts/1000;
		res = String.valueOf(ts);
		return res;
	}

	public static void main(String[] args) {
////		java.sql.Timestamp timestamp = parseTimeStamp("2014/12/20 12:34:32", "yyyy/MM/dd HH:mm:ss");
//		java.sql.Date d1 =  getSQLDate(parseSQLDate("2016-12-21 22:00:00"));
//		String d1Str = toDateTimeString(d1);
//		System.err.println(d1Str);
////		java.sql.Date d2 =  getSQLDate(parseSQLDate("2015-10-15 8:00:00"));
////		System.out.println(d1.compareTo(d2));
//
////		long d = differenceTime("2016-12-21 22:00:00","dd");
//		String str = "2016-12-21 22:00:00";
//		Date da = DateUtils.parseSQLDate(str,"yyyy-MM-dd HH:mm:ss");
//		DateUtils.addHour(da,3);
//		String daStr = DateUtils.toString(da,"yyyy-MM-dd HH:mm:ss");
//		long h = differenceTime(str,"HH");
//		long h2 = differenceTime(daStr,"HH");
////		long m = differenceTime("2016-12-21 22:00:00","mm");
////		System.out.println("相差天数 : "+d);
//		System.out.println("相差小时数 : "+h);
//		System.out.println("相差小时数h2 : "+h2);
//		boolean id = h2>3;
//		System.out.println("相差小时数h2判断 : "+id);
////		System.out.println("相差分钟数 : "+m);
//
//		Date tomorrowZero=getTomorrowZero(new Date());
//		System.err.println(DateUtils.toString(tomorrowZero,"yyyy-MM-dd HH:mm:ss"));

		java.sql.Timestamp timestamp = getSQLTimestamp();
		System.err.println("timestamp "+timestamp);
		String timestampStr = null;
		try {
			timestampStr = dateToStamp("2016-12-29 10:10:29");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.err.println("timestampStr "+timestampStr);
		Date now = new Date();
		System.err.println("now.getTime() "+now.getTime()/1000);
String ads = "adsf+ds324oiio";
		ads = ads.replaceAll("\\+","%20");
		System.err.println("ads ： "+ads);

		long s2 = differenceTime("2017-06-14 10:16:10","ss");
		System.err.println("相差 ： "+s2);
	}

}
