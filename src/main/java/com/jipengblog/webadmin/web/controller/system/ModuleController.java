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
import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.service.system.SysModuleService;
import com.jipengblog.webadmin.service.system.SysResourceService;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class ModuleController extends ParentController {

	@Autowired
	private SysModuleService moduleService;
	@Autowired
	private SysResourceService resourceService;

	private String defaultPath = "/system/module/list";

	/**
	 * 跳转到module列表
	 * @param session
	 * @param model
	 * @param tip
	 * @return
	 */
	@RequestMapping(value = "/system/module/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model, String tip) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			}else{
				super.pageTip = null;
			}
			List<SysModule> modules = moduleService.findAll();
			model.addAttribute("modules", modules);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模块列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath;
	}

	/**
	 * 删除module
	 * @param moduleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/system/module/del/{moduleId}", method = { RequestMethod.GET })
	public String del(@PathVariable("moduleId") Long moduleId,
			RedirectAttributesModelMap modelMap) {
		String tip = "";
		SysModule module = moduleService.findByModuleId(moduleId);// 查询当前模块信息
		if (module != null && module.getRoles().size() == 0) {// 判断当前模块是否还有角色关联
			moduleService.delete(module);
			tip = "模块删除成功";
		} else {
			tip = "该模块有角色使用，删除失败";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}

	/**
	 * 跳转编辑／添加module页面
	 * @param moduleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/module/edit/{moduleId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("moduleId") Long moduleId, Model model) {
		SysModule module = moduleService.findByModuleId(moduleId);// 查询当前角色信息
		List<SysResource> resources = resourceService.findAll();// 查询所有模块信息
		model.addAttribute("module", module);
		model.addAttribute("allResources", resources);
		return "/system/module/edit";
	}

	/**
	 * 编辑／添加module
	 * @param model
	 * @param modelMap
	 * @param moduleId
	 * @param moduleName
	 * @param priority
	 * @param enabled
	 * @param icon
	 * @param description
	 * @param resourceIdsString
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/system/module/edit", method = { RequestMethod.POST })
	public String doEdit(
			Model model, RedirectAttributesModelMap modelMap,
			@RequestParam("moduleId") Long moduleId,// 模块Id
			@RequestParam(value = "moduleName") String moduleName, // 模块名称
			@RequestParam(value = "priority", defaultValue = "0") String priority, // 显示级别
			@RequestParam(value = "enabled", defaultValue = "0") String enabled, // 是否可用
			@RequestParam(value = "icon", defaultValue = "") String icon, // 是否可用
			@RequestParam("description") String description,// 模块描述
			@RequestParam("resources") String resourceIdsString)
			throws Exception {// 资源
		String tip = null;
		try {
			String[] resourceIdsStrings = resourceIdsString.split(",");
			Long[] resourceIdsLongs = new Long[resourceIdsStrings.length];
			for (int i = 0; i < resourceIdsStrings.length; i++) {
				resourceIdsLongs[i] = Long.parseLong(resourceIdsStrings[i]);
			}
			//查询resource
			List<SysResource> resourceList = resourceService.findByResourceIds(resourceIdsLongs);
			if (moduleId != null && moduleId != 0) {
				// 再次检测用户名是否存在
				SysModule module = moduleService.findByModuleId(moduleId);
				if (module != null) {//找到模块信息
					module.setResources(new HashSet<SysResource>(resourceList));
					module.setModuleName(moduleName);
					module.setDescription(description);
					module.setEnabled("Y".equals(enabled));
					module.setIcon(icon);
					module.setPriority(Integer.parseInt(priority));
					moduleService.update(module);
					tip="模块编辑成功";
				} else {
					tip="没有找到要编辑的模块信息";
				}
			} else {
				SysModule module = new SysModule(moduleName, description,
						Integer.parseInt(priority), icon, "Y".equals(enabled),
						new HashSet<SysResource>(resourceList));
				moduleService.save(module);
				tip="模块添加成功";
			}
			// 写库
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("编辑模块错误:::" + e.getMessage());
			tip="系统未知错误";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}
}