package com.jipengblog.webadmin.utils.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jipengblog.webadmin.utils.time.constant.TimeUnit;

public class DateUtils {
	
	private DateUtils(){
		throw new Error("工具类，不需要实例化，直接使用静态方法");
	}

	/**
	 * 以基准时间为标准计算加入偏移量后的时间
	 * 
	 * @param baseDate
	 *            基准时间
	 * @param timeUnit
	 *            偏移量的时间单位
	 * @param offset
	 *            偏移量
	 * @return 计算后的时间
	 */
	public static Date addTime(Date baseDate, TimeUnit timeUnit, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(timeUnit.getValue(), offset);
		return calendar.getTime();
	}

	/**
	 * 获得今天零点的时间
	 * 
	 * @return 今天的00:00:00.000
	 */
	public static Date getZeroOfToday() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 日期时间戳转换成yyyy-MM-dd HH:mm:ss
	 * @param timestap 1970-01-01 08:00:00距现在的秒数
	 * @return
	 */
	public static String timestapToString(int timestap) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp now = new Timestamp(timestap*1000);
		return df.format(now);
	}
	
	/**
	 * 日期时间戳转换成Date
	 * @param timestap 1970-01-01 08:00:00距现在的秒数
	 * @return
	 */
	public static Date timestapToDate(int timestap) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestap*1000);
		return calendar.getTime();
	}
	
	/**
	 * 日期时间戳转换成yyyy-MM-dd HH:mm:ss
	 * @param timestap 1970-01-01 08:00:00距现在的毫秒数
	 * @return
	 */
	public static String timestapToString(long timestap) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp now = new Timestamp(timestap);
		return df.format(now);
	}
	
	/**
	 * 日期时间戳转换成Date
	 * @param timestap 1970-01-01 08:00:00距现在的毫秒数
	 * @return
	 */
	public static Date timestapToDate(long timestap) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestap);
		return calendar.getTime();
	}
	
	/**
	 * 日期转换成yyyy-MM-dd HH:mm:ss
	 * @param java.util.Date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss转换成日期
	 * @param yyyy-MM-dd HH:mm:ss格式的字符串
	 * @return
	 */
	public static Date stringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		int time = 1;
		System.out.println(DateUtils.timestapToDate(time));
		Date now = new Date();
		System.out.println(DateUtils.dateToString(now));
	}

}
