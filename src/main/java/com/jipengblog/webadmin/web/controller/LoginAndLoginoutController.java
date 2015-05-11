package com.jipengblog.webadmin.web.controller;

import static com.jipengblog.webadmin.web.common.WebCons.LOGIN_URI;
import static com.jipengblog.webadmin.web.common.WebCons.MAIN_URI;
import static com.jipengblog.webadmin.web.common.WebCons.INDEX_URI;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jipengblog.webadmin.entity.constant.LogoutType;
import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.service.LogLoginService;
import com.jipengblog.webadmin.service.SysUserService;
import com.jipengblog.webadmin.utils.security.SignatureUtils;
import com.jipengblog.webadmin.web.common.CookieCons;
import com.jipengblog.webadmin.web.common.SessionCons;

@Controller
public class LoginAndLoginoutController extends ParentController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private LogLoginService logLoginService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(
			@RequestParam(value = "loginname", required = true) String loginName,
			@RequestParam(value = "password", required = true) String loginPass,
			@RequestParam(value = "remember", required = true, defaultValue = "0") String remember,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {

		SysUser loginUser = userService.findByLoginName(loginName);
		SignatureUtils signatureUtils = new SignatureUtils();
		String cipherPassword = signatureUtils.encrypt(loginPass, SignatureUtils.DEFAULT_SALT);
		if (loginUser != null) {

			// condition1, 账号已禁用
			if (loginUser.getEnabled() == Boolean.FALSE) {
				LogLogin logLogin = new LogLogin(loginName, loginUser.getLoginPass(), Boolean.FALSE, new Date(), request.getRemoteAddr(), "账号已禁用",session.getId());
				logLoginService.save(logLogin);// 记录登录日志
				model.addAttribute("tip", "抱歉,您的账号已禁用,请联系管理员");
				return LOGIN_URI;
			}

			// condition2, 登录次数超过设置的最大次数
			int remainLoginTimes = logLoginService.allowLoginTimes(loginName);// 今天剩余登录次数
			if (remainLoginTimes <= 0) {
				LogLogin logLogin = new LogLogin(loginName, cipherPassword, Boolean.FALSE, new Date(), request.getRemoteAddr(), "登录次数过多",session.getId());
				logLoginService.save(logLogin);// 记录登录日志
				model.addAttribute("tip", "抱歉,您登录次数过多,请联系管理员");
				return LOGIN_URI;
			}

			// condition3, 密码错误,记录登录日志
			if (!loginUser.getLoginPass().equalsIgnoreCase(cipherPassword)) {
				LogLogin logLogin = new LogLogin(loginName, cipherPassword, Boolean.FALSE, new Date(), request.getRemoteAddr(), "密码错误",session.getId());
				logLoginService.save(logLogin);// 记录登录日志
				model.addAttribute("tip", "抱歉,登录密码错误,您今日还尝试" + (remainLoginTimes - 1) + "次");
				return LOGIN_URI;
			}

			// condition4, 登录成功
			// 加载权限信息
			Map<SysModule, Set<SysResource>> menus = super.getSysUserAuthority(loginUser);

			// 将用户和用户权限信息放入session
			session.setAttribute(SessionCons.LOGINED_USER, loginUser);
			session.setAttribute(SessionCons.LOGINED_AUTHORITY, menus);

			// 记住我，将登录名和签名放入Cookies,
			if ("1".equals(remember)) {
				
				// cookie保存登录名
				Cookie cookie_loginName = new Cookie(CookieCons.LOGINNAME, loginName);
				cookie_loginName.setMaxAge(CookieCons.VALIDITY);
				response.addCookie(cookie_loginName);

				// cookie保存登录签名
				String loginSign = signatureUtils.encrypt( loginUser.getLoginPass(), SignatureUtils.NO_SALT);
				Cookie cookie_loginSign = new Cookie(CookieCons.LOGINSIGN, loginSign);
				cookie_loginSign.setMaxAge(CookieCons.VALIDITY);
				response.addCookie(cookie_loginSign);
			}

			// 记录登录日志
			LogLogin logLogin = new LogLogin(loginName, cipherPassword, Boolean.TRUE, new Date(), request.getRemoteAddr(), "登录成功",session.getId());
			logLoginService.save(logLogin);

			// 跳转页面
			return MAIN_URI;
		}

		model.addAttribute("tip", "抱歉,登录账号不存在");
		return LOGIN_URI;
	}

	@RequestMapping(value = "/loginout", method = { RequestMethod.GET })
	public String loginout(Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		
		LogLogin logLogin = logLoginService.findBySessionId(session.getId());
		if(logLogin != null && logLogin.getLogoutTime() == null){
			logLogin.setLogoutTime(new Date());
			logLogin.setLogoutType(LogoutType.ACTIVELOGINOUT);
			logLoginService.update(logLogin);
		}

		// 清除session
		Enumeration<String> allSessionNames = session.getAttributeNames();
		while (allSessionNames.hasMoreElements()) {
			String sessionName = allSessionNames.nextElement();
			session.removeAttribute(sessionName);
		}
		session.invalidate();

		// 清除cookie登录名
		Cookie cookie_loginName = new Cookie(CookieCons.LOGINNAME, null);
		cookie_loginName.setMaxAge(0);
		response.addCookie(cookie_loginName);

		// 清除cookie登录名签名
		Cookie cookie_loginSign = new Cookie(CookieCons.LOGINSIGN, null);
		cookie_loginSign.setMaxAge(0);
		response.addCookie(cookie_loginSign);

		return "redirect:" + INDEX_URI;
	}
}