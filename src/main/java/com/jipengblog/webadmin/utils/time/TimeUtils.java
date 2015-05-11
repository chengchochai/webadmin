package com.jipengblog.webadmin.utils.time;

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
	public static Date getTodayZeroPoint() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		Date now = new Date();
		Date later = TimeUtils.addTime(now, TimeUnit.DATE, -1);
		System.out.println(now);
		System.out.println(later);
	}

}
