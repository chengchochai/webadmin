package com.jipengblog.webadmin.utils.time.constant;

public enum DateFormat {
	
	FORMAT1("yyyy-MM-dd HH:mm:ss");

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	private final String value;

	DateFormat(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
