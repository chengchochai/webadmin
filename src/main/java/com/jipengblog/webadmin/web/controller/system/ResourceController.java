package com.jipengblog.webadmin.web.controller.system;

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

import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.service.system.SysResourceService;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class ResourceController extends ParentController {

	@Autowired
	private SysResourceService resourceService;

	private String defaultPath = "/system/resource/list";

	/**
	 * 跳转到角色列表页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/resource/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model, String tip) {
		try{
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			}else{
				super.pageTip = null;
			}
			List<SysResource> resources = resourceService.findAll();
			model.addAttribute("resources", resources);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("资源列表错误:::" + e.getMessage());
			super.pageTip = "系统未知错误";
		}
		model.addAttribute("pageTip",super.pageTip);
		return defaultPath;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/resource/del/{resourceId}", method = { RequestMethod.GET })
	public String del(@PathVariable("resourceId") Long resourceId, Model model,RedirectAttributesModelMap modelMap) {
		try{
			SysResource resource = resourceService.findByResourceId(resourceId);// 查询当前模块信息
			if (resource != null && resource.getModules().size() == 0) {// 判断当前模块是否还有角色关联
				resourceService.delete(resource);
				modelMap.addAttribute("tip","资源删除成功");
			}else{
				modelMap.addAttribute("tip","该资源有模块使用，删除失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("资源删除错误:::" + e.getMessage());
			super.pageTip = "该资源有模块使用，删除失败";
		}
		return redirect + defaultPath;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/system/resource/edit/{resourceId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("resourceId") Long resourceId, Model model) {
		SysResource resource = resourceService.findByResourceId(resourceId);// 查询当前角色信息
		model.addAttribute("resource", resource);
		return "/system/resource/edit";
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
	@RequestMapping(value = "/system/resource/edit", method = { RequestMethod.POST })
	public String doEdit(
			Model model,RedirectAttributesModelMap modelMap,
			@RequestParam("resourceId") Long resourceId,// 资源Id
			@RequestParam(value = "resourceName") String resourceName, // 模块名称
			@RequestParam(value = "priority", defaultValue = "0") String priority, // 显示级别
			@RequestParam(value = "resourceUri", defaultValue = "") String resourceUri, // 显示级别
			@RequestParam(value = "enabled", defaultValue = "0") String enabled, // 是否可用
			@RequestParam("description") String description) throws Exception {// 资源
		String tip = null;
		try{
			if (resourceId != null && resourceId != 0) {
				// 再次检测用户名是否存在
				SysResource resource = resourceService.findByResourceId(resourceId);
				if (resource != null) {
					resource.setResourceName(resourceName);
					resource.setDescription(description);
					resource.setEnabled("Y".equals(enabled));
					resource.setPriority(Integer.parseInt(priority));
					resource.setResourceUri(resourceUri);
					resourceService.update(resource);
					tip = "资源更新成功";
				}else{
					tip = "没有找到要编辑的资源信息";
				}
			} else {
				SysResource resource = new SysResource(resourceName, description, resourceUri, Integer.parseInt(priority), "Y".equals(enabled));
				resourceService.save(resource);
				tip = "资源添加成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建模块错误:::" + e.getMessage());
			tip="系统未知错误";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}
}