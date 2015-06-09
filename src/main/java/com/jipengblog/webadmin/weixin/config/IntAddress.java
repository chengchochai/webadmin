package com.jipengblog.webadmin.weixin.config;

/**
 * 微信公众号的所有接口地址
 * @author penn
 *
 */
public interface IntAddress {
	String getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
}
