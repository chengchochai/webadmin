package com.jipengblog.webadmin.service;

import java.util.Date;

import com.jipengblog.webadmin.entity.log.LogEmail;

public interface LogEmailService {

	/**
	 * 添加邮件日志
	 * 
	 * @param logLogin
	 */
	void saveLogEmail(LogEmail logEmail);

	/**
	 * 根据关键字查找日志
	 * 
	 * @param keyword
	 * @return
	 */
	LogEmail findLogEmailByKeyword(String keyword, Date deadline);

}
