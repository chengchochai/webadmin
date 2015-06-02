package com.jipengblog.webadmin.utils.sort;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

/**
 * @date 2013-06-26 18:14
 * @author penn
 * @desc 对字符排序的工具类
 * 
 */
public class SortUtil {
	
	private SortUtil(){
		throw new Error("工具类，不需要实例化，直接使用静态方法");
	}

	/**
	 * 字典排序
	 * 
	 * @param strings
	 * @return
	 */
	public static String dictSort(String... strings) {
		if (null == strings) {
			return StringUtils.EMPTY;
		}
		Arrays.sort(strings);
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			bf.append(strings[i]);
		}
		return bf.toString();
	}
}
