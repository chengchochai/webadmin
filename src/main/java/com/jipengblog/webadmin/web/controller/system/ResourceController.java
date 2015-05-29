package com.jipengblog.webadmin.web.controller.system;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.system.SysResourceService;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
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
	public String list(Model model, String tip) {
		try{
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			}else{
				super.pageTip = null;
			}
		}catch(Exception e){
			e.printStackTrace();
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
	
	@RequestMapping(value = "/system/resource/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			@RequestParam(value = "resourceName", required = false) String resourceName,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit){
		DetachedCriteria dc = DetachedCriteria.forClass(SysResource.class);
		if(null!=resourceName && !"".equals(resourceName)){
			dc.add(Restrictions.like("resourceName", resourceName, MatchMode.ANYWHERE));
		}
		dc.addOrder(Order.desc("priority"));//排序
		PageResults<SysResource> pageResults = resourceService.findListByDetachedCriteria(dc, (start/limit) + 1, limit);
		List<SysResource> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(SysResource sysResource : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(sysResource.getResourceId());
			jsonObj.add(sysResource.getResourceName());
			jsonObj.add(sysResource.getEnabled()?"可用":"禁用");
			jsonObj.add(sysResource.getPriority());
			jsonObj.add(sysResource.getResourceUri());
			jsonObj.add(sysResource.getDescription());
			String operator = "<a href=\"edit/"+sysResource.getResourceId()+"\" class=\"btn btn-primary btn-xs\">编辑</a>&nbsp;" + "<a onclick=\"if(!confirm('确定要删除吗?'))return false;\" href=\"del/"+sysResource.getResourceId()+"\" class=\"btn btn-danger btn-xs\">删除</a>";
			jsonObj.add(operator);
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}
}