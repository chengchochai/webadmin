package com.jipengblog.webadmin.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.service.LogLoginService;
import com.jipengblog.webadmin.service.SysUserService;
import com.jipengblog.webadmin.utils.security.SignatureUtils;
import com.jipengblog.webadmin.web.common.CookieCons;
import com.jipengblog.webadmin.web.common.SessionCons;
import com.jipengblog.webadmin.web.common.WebCons;

@Controller
public class IndexController extends ParentController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private LogLoginService logLoginService;

	@RequestMapping("/index")
	public String index(
			Model model,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue(value = CookieCons.LOGINNAME, defaultValue = "") String loginName,// 登录名称
			@CookieValue(value = CookieCons.LOGINSIGN, defaultValue = "") String loginSign) {// 防止cookie被篡改

		
		try {
			Cookie cookie_appName = new Cookie(CookieCons.APPNAME, URLEncoder.encode(WebCons.APP_NAME,"utf-8"));
			cookie_appName.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie_appName);
		} catch (UnsupportedEncodingException e) {
		}
		
		SysUser loginUser = (SysUser) session.getAttribute(SessionCons.LOGINED_USER);
		@SuppressWarnings("unchecked")
		Map<SysModule, Set<SysResource>> menus = (Map<SysModule, Set<SysResource>>) session.getAttribute(SessionCons.LOGINED_AUTHORITY);

		if(loginUser!=null && menus!=null){
			return WebCons.MAIN_URI;
		}
		
		// 查询cookie中保存的loginName
		if (null != loginName && !"".equals(loginName)) {
			loginUser = userService.findByLoginName(loginName);
		}

		// loginName 存在
		if (loginUser != null) {

			// 账号状态正常
			if (loginUser.getEnabled() == Boolean.TRUE) {

				// 检测用户保存的session值是否被篡改，同时检测密码是否已经修改
				SignatureUtils signatureUtils = new SignatureUtils();
				String _toLoginSign = signatureUtils.encrypt(
						loginUser.getLoginPass(), SignatureUtils.NO_SALT);
				if (_toLoginSign.equalsIgnoreCase(loginSign)) {

					// 加载权限信息
					menus = super.getSysUserAuthority(loginUser);

					// 将用户信息和权限信息保存在session中
					session.setAttribute(SessionCons.LOGINED_USER, loginUser);
					session.setAttribute(SessionCons.LOGINED_AUTHORITY, menus);

					// 跳转页面
					return WebCons.MAIN_URI;
				}
			}
		}
		return WebCons.LOGIN_URI;
	}
}