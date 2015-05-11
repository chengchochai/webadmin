package com.jipengblog.webadmin.web.tag;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * EL方法用于urldecode字符串
 * 
 * @author penn
 *
 */
public class UrlUtils {

	public static String decode(String value) {
		try {
			return URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
