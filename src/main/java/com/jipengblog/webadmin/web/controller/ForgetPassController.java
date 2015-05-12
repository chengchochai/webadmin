package com.jipengblog.webadmin.web.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jipengblog.webadmin.entity.constant.EmailType;
import com.jipengblog.webadmin.entity.log.LogEmail;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.exception.UtilsException;
import com.jipengblog.webadmin.service.log.LogEmailService;
import com.jipengblog.webadmin.service.system.SysUserService;
import com.jipengblog.webadmin.utils.email.EmailUtils;
import com.jipengblog.webadmin.utils.email.constant.EmailTemplate;
import com.jipengblog.webadmin.utils.security.SignatureUtils;
import com.jipengblog.webadmin.utils.security.enums.Algorithm;
import com.jipengblog.webadmin.utils.time.TimeUtils;
import com.jipengblog.webadmin.utils.time.constant.TimeUnit;

import static com.jipengblog.webadmin.web.common.WebCons.*;

@Controller
public class ForgetPassController extends ParentController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private LogEmailService logEmailService;

	@RequestMapping(value = "/forgetpassword", method = { RequestMethod.POST })
	public String forgetpassword(
			@RequestParam(value = "email", required = true) String email,
			Model model) {

		String tip = "邮件已发送，请根据提示操作";// 默认页面提示tip

		SysUser sysUser = userService.findByEmail(email);

		if (sysUser != null) {

			// 初始化参数
			Date now = new Date();// 当前时间
			Date deadline = TimeUtils.addTime(now, TimeUnit.DATE, 1);// 截止时间
			EmailTemplate emailTpl = EmailTemplate.RESPONSE_URL;// 邮件模板
			String emailTplPath = emailTpl.getValue();// 邮件模板的路径
			EmailType emailType = EmailType.FORGETPASSWORD;// 邮件类型
			String subject = APP_NAME + "密码找回";// 邮件主题

			// 生成关键字
			SignatureUtils signatureUtils = new SignatureUtils(Algorithm.SHA1);
			String keywordSeed = email + now.toString();
			String keyword = signatureUtils.encrypt(keywordSeed,
					"forgetpassword");

			// 生成响应的URL
			String responseUrl = DOMAIN_BASE_URL + "/changepassword/" + keyword;

			// 保存邮件发送日志
			LogEmail logEmail = new LogEmail(email, keyword, emailType,
					String.valueOf(emailTpl), now, deadline);
			logEmailService.saveLogEmail(logEmail);

			// Velocity模板内容设置
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("responseUrl", responseUrl);
			map.put("baseUrl", DOMAIN_BASE_URL);

			// 发送邮件
			EmailUtils mailUtils = new EmailUtils();
			try {
				mailUtils.sendVelocityTpl(subject, email, emailTplPath, map);
			} catch (UtilsException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		model.addAttribute("tip", tip);
		model.addAttribute("title", APP_NAME);
		return LOGIN_URI;
	}

	@RequestMapping(value = "/changepassword/{keyword}", method = { RequestMethod.GET })
	public String changepassword(@PathVariable String keyword, Model model) {
		logger.info("keyword:" + keyword);
		logger.info("deadline:" + new Date());
		LogEmail logEmail = logEmailService.findLogEmailByKeyword(keyword,
				new Date());
		if (logEmail != null) {
			model.addAttribute("tip", "keyword已找到!!!");
		} else {
			model.addAttribute("tip", "keyword没有找到!!!");
		}
		model.addAttribute("title", APP_NAME);
		return LOGIN_URI;

	}
}