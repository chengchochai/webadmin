package com.jipengblog.webadmin.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.entity.constant.LogoutType;
import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.service.LogLoginService;
import com.jipengblog.webadmin.web.common.SessionCons;
import com.jipengblog.webadmin.web.utils.SpringContextUtil;

public class MyHttpSessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	private final static Logger logger = Logger
			.getLogger(MyHttpSessionListener.class);

	public void sessionCreated(HttpSessionEvent event) {
		logger.info("SessionCreated：" + event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		
		LogLoginService logLoginService = (LogLoginService) SpringContextUtil.getBean("logLoginService");
		LogLogin logLogin = logLoginService.findBySessionId(event.getSession().getId());
		if(logLogin != null && logLogin.getLogoutTime() == null){
			logLogin.setLogoutTime(new Date());
			logLogin.setLogoutType(LogoutType.SESSIONFAILURE);
			logLoginService.update(logLogin);
		}
		
		logger.info("SessionDestroyed：" + event.getSession().getId());
		SysUser loginUser = (SysUser) event.getSession().getAttribute(SessionCons.LOGINED_USER);
		logger.info("有人登出：" + loginUser.getLoginName());
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		logger.info("SessionAdded：" + event.getSession().getId());
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		logger.info("SessionRemoved：" + event.getSession().getId());
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		logger.info("SessionReplaced：" + event.getSession().getId());
	}

}