package com.jipengblog.webadmin.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class ErrorController extends ParentController {

	private String error400 = "/error/400";
	private String error403 = "/error/403";
	private String error404 = "/error/404";
	private String error500 = "/error/500";
	private String nosession = "/error/nosession";

	@RequestMapping(value = "/error/400", method = { RequestMethod.GET })
	public String error400() {
		return error400;
	}

	@RequestMapping(value = "/error/403", method = { RequestMethod.GET })
	public String error403() {
		return error403;
	}

	@RequestMapping(value = "/error/404", method = { RequestMethod.GET })
	public String error404() {
		return error404;
	}

	@RequestMapping(value = "/error/500", method = { RequestMethod.GET })
	public String error500() {
		return error500;
	}

	@RequestMapping(value = "/error/nosession", method = { RequestMethod.GET })
	public String nosession() {
		return nosession;
	}

}