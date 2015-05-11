package com.jipengblog.webadmin.web.common;

/**
 * 配置Web常量
 * 
 * @author penn
 *
 */
public interface WebCons {
	String INDEX_URI = "/index";// 系统初始的路径
	String LOGIN_URI = "/login";// 系统登录的路径
	String MAIN_URI = "/main";// 系统登录后的主页面
	String DOMAIN_BASE_URL = "http://127.0.0.1:8887/webadmin";// 项目根目录
	String APP_NAME = "Penn Admin";// 系统名称
	int PASSWORD_ERROR_TIMES = 3;// 一天之内允许错误密码输入的次数

}
