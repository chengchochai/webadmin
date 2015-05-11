package com.jipengblog.webadmin.web.controller.log;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.service.LogLoginService;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class LogLoginController extends ParentController {

	@Autowired
	private LogLoginService loginService;

	private String defaultPath = "/log/login/list";

	@RequestMapping(value = "/log/login/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model) {
		try {
			List<LogLogin> logs = loginService.findAll();
			model.addAttribute("logs", logs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录日志列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		return defaultPath;
	}
}
