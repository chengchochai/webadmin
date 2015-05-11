package com.jipengblog.webadmin.web.common;

/**
 * 配置Cookie常量
 * 
 * @author penn
 *
 */
public interface CookieCons {
	int VALIDITY = 604800;//保存在cookie里数据的有效期
	String APPNAME = "cookie_appName";// 在cookie里保存的项目名称
	String LOGINNAME = "cookie_loginName";// 在cookie里保存的登录名
	String LOGINSIGN = "cookie_loginNameSign";// 在cookie里保存的登录名的签名
}
