package com.jipengblog.webadmin.entity.constant;

public enum LogoutType {
	ACTIVELOGINOUT("用户登出"),  // 主动登出
	PASSIVELOGOUT("被动登出"),   // 被动登出
	SESSIONFAILURE("会话失效"),  // session失效
	BROWSERCLOSED("浏览器关闭"), // 浏览器关闭
	SYSTEMSHUTDOWN("系统关闭"); // 系统关闭

	private final String value;

	LogoutType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
