package com.jipengblog.webadmin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

/**
 * @desc Stream处理的工具类
 * @date 2013-06-27 12:23
 * @author penn
 */
public class StreamUtil {
	/**
	 * 将ServletInputStream转换成字符串
	 * 
	 * @param in
	 * @return
	 */
	public static String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}
