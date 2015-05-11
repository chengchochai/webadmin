package com.jipengblog.webadmin.utils.time.constant;

import java.util.Calendar;

public enum TimeUnit {

	YEAR(Calendar.YEAR), 		// 年
	MONTH(Calendar.MONTH), 		// 月
	DATE(Calendar.DATE), 		// 日
	HOUR(Calendar.HOUR), 		// 时
	MINUTE(Calendar.MINUTE), 	// 分
	SECOND(Calendar.SECOND);	// 秒

	private final int value;

	TimeUnit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static void main(String[] args) {
		System.out.println(TimeUnit.YEAR.value);
		System.out.println(TimeUnit.MONTH.value);
		System.out.println(TimeUnit.DATE.value);
		System.out.println(TimeUnit.HOUR.value);
		System.out.println(TimeUnit.MINUTE.value);
		System.out.println(TimeUnit.SECOND.value);
	}
}
