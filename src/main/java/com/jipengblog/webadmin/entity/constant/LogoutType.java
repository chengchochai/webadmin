package com.jipengblog.webadmin.entity.constant;

public enum LogoutType {
	ACTIVELOGINOUT,	// 主动登出
	PASSIVELOGOUT,	// 被动登出
	SESSIONFAILURE,	// session失效
	BROWSERCLOSED,	// 浏览器关闭
	SYSTEMSHUTDOWN	// 系统关闭
	
}
