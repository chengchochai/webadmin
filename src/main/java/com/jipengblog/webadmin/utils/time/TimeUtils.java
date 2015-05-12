package com.jipengblog.webadmin.utils.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jipengblog.webadmin.utils.time.constant.TimeUnit;

public class TimeUtils {

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

	public static void main(String[] args) {
		int time = 1;
		System.out.println(TimeUtils.timestapToDate(time));
//		Date now = new Date();
//		Date later = TimeUtils.addTime(now, TimeUnit.DATE, -1);
//		System.out.println(now);
//		System.out.println(later);
	}

}
