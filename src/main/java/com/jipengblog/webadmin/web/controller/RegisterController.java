package com.jipengblog.webadmin.web.controller;

import static com.jipengblog.webadmin.web.common.WebCons.LOGIN_URI;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jipengblog.webadmin.entity.constant.Sex;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.service.system.SysRoleService;
import com.jipengblog.webadmin.service.system.SysUserService;
import com.jipengblog.webadmin.utils.security.SignatureUtils;

@Controller
public class RegisterController extends ParentController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleSerivce;

	@RequestMapping("/register")
	public String register(SysUser sysUser, Model model) {
		SignatureUtils signatureUtils = new SignatureUtils();
		String pass = signatureUtils.encrypt(sysUser.getLoginPass(), null);
		sysUser.setCreateTime(new Date());
		sysUser.setEnabled(Boolean.FALSE);
		sysUser.setLoginName(sysUser.getEmail());
		sysUser.setSex(Sex.UNKNOWN);
		sysUser.setLoginPass(pass);
		userService.save(sysUser);
		model.addAttribute("tip","已经注册完成");
		return LOGIN_URI;
	}

	@RequestMapping(value = "/checkEmail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkEmail(
			@RequestParam(value = "email", required = true) String email,
			Model model) {
		boolean canUse = true;// 是否可以使用
		SysUser sysUser = userService.findByEmail(email);
		if (sysUser != null) {
			canUse = false;
		}
		return String.valueOf(canUse);
	}

}