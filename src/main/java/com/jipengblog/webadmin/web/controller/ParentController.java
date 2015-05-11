package com.jipengblog.webadmin.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.entity.system.SysRole;
import com.jipengblog.webadmin.entity.system.SysUser;

public class ParentController {
	
	protected final Logger logger = Logger.getLogger(ParentController.class);
	
	protected String redirect = "redirect:"; // 执行redirect操作的前缀
	
	protected String pageTip = null; // 页面提示
	
	/**
	 * 查询用户对应的权限
	 * 
	 * @param sysUser
	 *            用户
	 * @return 权限列表
	 */
	protected Map<SysModule, Set<SysResource>> getSysUserAuthority(SysUser sysUser) {
		Map<SysModule, Set<SysResource>> menus = new HashMap<SysModule, Set<SysResource>>();
		Set<SysRole> roles = sysUser.getRoles();// 用户所属角色
		for (SysRole role : roles) {
			logger.info("用户角色:" + role.getRoleName());
			Set<SysModule> modules = role.getModules();
			for (SysModule module : modules) {
				logger.info("	用户模块:" + module.getModuleName());
				Set<SysResource> resources = module.getResources();
				for (SysResource resource : resources) {
					logger.info("		用户资源:" + resource.getResourceUri());
				}
				menus.put(module, resources);
			}
		}
		return menus;
	}

}
