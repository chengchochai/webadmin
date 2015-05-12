package com.jipengblog.webadmin.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.service.log.LogLoginService;
import com.jipengblog.webadmin.web.utils.SpringContextUtil;

public class MyServletContextListener implements ServletContextListener {

	private final static Logger logger = Logger
			.getLogger(MyServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("webadmin contextDestroyed");
		LogLoginService logLoginService = (LogLoginService) SpringContextUtil
				.getBean("logLoginService");
		int logoutUserNum = logLoginService.updateAllLogLogout();
		logger.info("系统关闭使" + logoutUserNum + "个用户下线...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("webadmin 服务启动成功");
		long start = System.currentTimeMillis();
		logger.info("======================webadmin 服务启动成功===========");
		long end = System.currentTimeMillis();
		logger.info("=======================webadmin 服务耗时间:" + (end - start)
				+ " ms ===================");
	}
}