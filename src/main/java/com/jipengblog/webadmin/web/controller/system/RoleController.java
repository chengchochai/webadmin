package com.jipengblog.webadmin.web.controller.system;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.entity.system.SysRole;
import com.jipengblog.webadmin.service.SysModuleService;
import com.jipengblog.webadmin.service.SysRoleService;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class RoleController extends ParentController {

	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysModuleService moduleService;

	private String defaultPath = "/system/role/list";

	/**
	 * 跳转到角色列表页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/role/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model, String tip) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			}else{
				super.pageTip = null;
			}
			List<SysRole> roles = roleService.findAll();
			model.addAttribute("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色列表错误:::" + e.getMessage());
			super.pageTip = "系统未知错误";
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
	@RequestMapping(value = "/system/role/del/{roleId}", method = { RequestMethod.GET })
	public String del(@PathVariable("roleId") Long roleId, Model model,
			RedirectAttributesModelMap modelMap) {
		String tip = "";
		SysRole role = roleService.findByRoleId(roleId);// 查询当前角色信息
		if (role != null && role.getUsers().size() == 0) {// 判断当前角色是否还有用户关联
			roleService.delete(role);
			tip = "角色删除成功";
		} else {
			tip = "该角色有用户使用，删除失败";
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
	@RequestMapping(value = "/system/role/edit/{roleId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("roleId") Long roleId, Model model) {
		SysRole role = roleService.findByRoleId(roleId);// 查询当前角色信息
		List<SysModule> allModules = moduleService.findAll();// 查询所有模块信息
		model.addAttribute("role", role);
		model.addAttribute("allModules", allModules);
		return "/system/role/edit";
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
	@RequestMapping(value = "/system/role/edit", method = { RequestMethod.POST })
	public String doEdit(Model model, RedirectAttributesModelMap modelMap,
			@RequestParam("roleId") Long roleId,// 角色Id
			@RequestParam(value = "roleName") String roleName, // 角色名称
			@RequestParam("description") String description,// 角色描述
			@RequestParam("modules") String moduleIdsString) throws Exception {// 权限模块
		String tip = null;
		try {
			// 页面传入的模块ID转换成Long[]类型
			String[] moduleIdsStrings = moduleIdsString.split(",");
			Long[] moduleIdsLongs = new Long[moduleIdsStrings.length];
			for (int i = 0; i < moduleIdsStrings.length; i++) {
				moduleIdsLongs[i] = Long.parseLong(moduleIdsStrings[i]);
			}
			// 根据ID查询权限模块
			List<SysModule> modulesList = moduleService
					.findByModuleIds(moduleIdsLongs);
			if (roleId != null && roleId != 0) {
				// 再次检测用户名是否存在
				SysRole sysRole = roleService.findByRoleId(roleId);
				if (sysRole != null) {
					sysRole.setModules(new HashSet<SysModule>(modulesList));
					sysRole.setRoleName(roleName);
					sysRole.setDescription(description);
					roleService.update(sysRole);
					tip = "角色编辑成功";
				} else {
					tip = "没有找到要编辑的模块信息";
				}
			} else {
				SysRole sysRole = new SysRole(roleName, description,
						new HashSet<SysModule>(modulesList));
				roleService.save(sysRole);
				tip = "角色添加成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("编辑角色错误:::" + e.getMessage());
			tip = "系统未知错误";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}
}