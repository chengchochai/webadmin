package com.jipengblog.webadmin.web.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.web.common.SessionCons;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String[] noInterceptURIs = new String[] { "/login", "/logout",
				"/index", "/error", "/static", "/interface" };
		boolean beIntercepted = true;// 判断是否需要拦截
		for (String noInterceptURI : noInterceptURIs) {
			logger.info("不需要拦截的URI:::" + noInterceptURI);
			if (uri.indexOf(noInterceptURI) != -1) {
				logger.info("当前访问URI:::" + uri + "不需要拦截");
				beIntercepted = false;
				break;
			}
		}

		if (beIntercepted) {
			logger.info("当前URI[" + uri + "]已被拦截,需要做进一步判断......");
			SysUser loginUser = (SysUser) request.getSession().getAttribute(
					SessionCons.LOGINED_USER);
			if (null == loginUser) {
				logger.info("session无效,需要重新登录");
				response.sendRedirect("/error/nosession");
				return false;
			} else {
				boolean hasAuthority = false; // 默认没有权限
				Map<SysModule, Set<SysResource>> menus = (Map<SysModule, Set<SysResource>>) request
						.getSession().getAttribute(
								SessionCons.LOGINED_AUTHORITY);
				for (Map.Entry<SysModule, Set<SysResource>> entry : menus
						.entrySet()) {
					//SysModule module = entry.getKey();
					Set<SysResource> resources = entry.getValue();
					for (SysResource resource : resources) {
						String validUri = resource.getResourceUri().substring(0, resource.getResourceUri().lastIndexOf("/"));
						if (uri.indexOf(validUri) != -1) {
							hasAuthority = true;
							break;
						}
					}
					if (hasAuthority) {
						break;
					}
				}
				if (!hasAuthority) {// 无权限
					logger.info("权限无效,非法访问!!!");
					response.sendRedirect("/error/403");
					return false;
				}
			}
		}
		logger.info("session和权限都已验证通过,放行......");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle方法");
		request.setAttribute("_contextPath", request.getContextPath());
		String serverName = "http://" + request.getServerName();
		String serverPort = ":" + request.getServerPort();
		String httpPath = serverName + serverPort;
		request.setAttribute("_serverPath", httpPath);

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("afterCompletion方法");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.info("afterConcurrentHandlingStarted方法");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
