package com.jipengblog.webadmin.web.controller.system;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.jipengblog.webadmin.entity.constant.Sex;
import com.jipengblog.webadmin.entity.system.SysRole;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.system.SysRoleService;
import com.jipengblog.webadmin.service.system.SysUserService;
import com.jipengblog.webadmin.utils.security.SignatureUtils;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
import com.jipengblog.webadmin.web.common.SessionCons;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class UserController extends ParentController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;

	private String defaultPath = "/system/user/list";

	/**
	 * 跳转到角色列表页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model, String tip) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"),
						"utf-8");
			} else {
				super.pageTip = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/del/{userId}", method = { RequestMethod.GET })
	public String del(@PathVariable("userId") Long userId, Model model,
			HttpSession session, RedirectAttributesModelMap modelMap) {
		String tip = null;
		SysUser user = userService.findByUserId(userId);// 查询当前角色信息
		if (user != null) {// 判断当前角色是否还有用户关联
			SysUser userInSession = (SysUser) session
					.getAttribute(SessionCons.LOGINED_USER);
			if (userInSession.getUserId() != userId) {
				userService.delete(user);
				tip = "用户删除成功";
			} else {
				tip = "用户不能删除自己，删除失败";
			}
		} else {
			tip = "用户信息不存在，删除失败";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/edit/{userId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("userId") Long userId, Model model) {
		SysUser user = userService.findByUserId(userId);// 查询当前用户信息
		List<SysRole> allRoles = roleService.findAll();// 查询所有模块信息
		model.addAttribute("user", user);
		model.addAttribute("allRoles", allRoles);
		return "/system/user/edit";
	}

	/**
	 * 执行添加动作
	 * 
	 * @param model
	 * @param roleName
	 * @param description
	 * @param moduleIdsString
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/system/user/edit", method = { RequestMethod.POST })
	public String doEdit(
			Model model, RedirectAttributesModelMap modelMap,
			@RequestParam(value = "userId") Long userId,// 用户Id
			@RequestParam(value = "loginName", required = false) String loginName, // 登录名称
			@RequestParam(value = "loginPass") String loginPass, // 登录密码
			@RequestParam(value = "realName") String realName, // 真实名称
			@RequestParam(value = "email") String email, // 邮箱
			@RequestParam(value = "mobile") String mobile, // 手机
			@RequestParam(value = "sex") String sex, // 手机
			@RequestParam(value = "enabled", defaultValue = "N") String enabled, // 手机
			@RequestParam("roles") String rolesIdsString) throws Exception {// 权限模块
		String tip = "";
		try {

			SignatureUtils signatureUtils = new SignatureUtils();
			String[] rolesIdsStrings = rolesIdsString.split(",");
			Long[] rolesIdsLongs = new Long[rolesIdsStrings.length];
			for (int i = 0; i < rolesIdsStrings.length; i++) {
				rolesIdsLongs[i] = Long.parseLong(rolesIdsStrings[i]);
			}
			// 根据ID查询权限模块
			List<SysRole> roles = roleService.findByRoleIds(rolesIdsLongs);
			if (userId != null && userId != 0) {
				// 再次检测用户名是否存在
				SysUser user = userService.findByUserId(userId);
				if (user != null) {
					if (!user.getLoginPass().equals(loginPass)) {
						user.setLoginPass(signatureUtils.encrypt(loginPass, null));
					}
					user.setRealName(realName);
					user.setMobile(mobile);
					user.setEmail(email);
					user.setSex(Sex.valueOf(sex));
					user.setEnabled("Y".equals(enabled));
					user.setRoles(new HashSet<SysRole>(roles));
					userService.update(user);
					tip = "用户编辑成功";
				} else {
					tip = "没有找到要编辑的用户信息";
				}
			} else {
				SysUser user = new SysUser(loginName, signatureUtils.encrypt(
						loginPass, null), realName, Sex.valueOf(sex), email,
						mobile, new Date(), "Y".equals(enabled),
						new HashSet<SysRole>(roles));
				userService.save(user);
				tip = "用户添加成功";
			}
		} catch (Exception e) {
			logger.error("编辑用户错误:::" + e.getMessage());
			e.printStackTrace();
			tip = "系统错误";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}

	/**
	 * 检测登录名称的方法
	 * 
	 * @param loginName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/edit/checkLoginName", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkLoginName(
			@RequestParam(value = "loginName", required = true) String loginName,
			Model model) {
		boolean isUsed = true;// 是否可以使用
		SysUser user = userService.findByLoginName(loginName);
		if (user != null) {
			isUsed = false;
		}
		return String.valueOf(isUsed);
	}
	
	@RequestMapping(value = "/system/user/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			HttpSession session,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit){
		String hql = "from SysUser order by createTime desc";
		PageResults<SysUser> pageResults = userService.findListByPageResults(hql, "select count(*) " + hql, (start/limit) + 1, limit);
		List<SysUser> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(SysUser sysUser : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(sysUser.getUserId());
			jsonObj.add(sysUser.getLoginName());
			jsonObj.add(sysUser.getRealName());
			jsonObj.add(sysUser.getEmail());
			jsonObj.add(sysUser.getMobile());
			jsonObj.add(sysUser.getEnabled()?"可用":"禁用");
			String operator = "<a href=\"edit/"+sysUser.getUserId()+"\" class=\"btn btn-primary btn-xs\">编辑</a>&nbsp;" + "<a onclick=\"if(!confirm('确定要删除吗?'))return false;\" href=\"del/"+sysUser.getUserId()+"\" class=\"btn btn-danger btn-xs\">删除</a>";
			jsonObj.add(operator);
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}

	/**
	 * 检测登录邮箱的方法
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/edit/checkEmail", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkEmail(
			@RequestParam(value = "email", required = true) String email,
			Model model) {
		boolean isUsed = true;// 是否可以使用
		SysUser user = userService.findByEmail(email);
		if (user != null) {
			isUsed = false;
		}
		return String.valueOf(isUsed);
	}

	/**
	 * 检测手机的方法
	 * 
	 * @param mobile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/user/edit/checkMobile", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkMobile(
			@RequestParam(value = "mobile", required = true) String mobile,
			Model model) {
		boolean isUsed = true;// 是否可以使用
		SysUser user = userService.findByMobile(mobile);
		if (user != null) {
			isUsed = false;
		}
		return String.valueOf(isUsed);
	}
}