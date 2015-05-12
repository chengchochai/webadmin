package com.jipengblog.webadmin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @desc document处理的工具类
 * @author penn
 * @date 2013-06-27 11:47
 */
public class DocumentUtil {
	public static Element stringToElement(String sourceStr) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(sourceStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == document) {
			return null;
		} else {
			Element root = document.getRootElement();
			return root;
		}
	}
}
