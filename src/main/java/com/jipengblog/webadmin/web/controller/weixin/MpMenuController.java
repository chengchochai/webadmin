package com.jipengblog.webadmin.web.controller.weixin;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
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

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.entity.weixin.MpMenu;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.service.weixin.MpMenuService;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class MpMenuController extends ParentController {

	@Autowired
	private MpMenuService mpMenuService;
	@Autowired
	private MpAccountService mpAccountService;

	private String defaultPath = "/weixin/mpMenu/list";

	/**
	 * 跳转到mpMenu列表
	 * 
	 * @param session
	 * @param model
	 * @param tip
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/list", method = { RequestMethod.GET })
	public String list(Model model, String tip,@RequestParam(value = "mpAccountId", defaultValue = "0") Long mpAccountId) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			} else {
				super.pageTip = null;
			}
			List<MpAccount> mpAccounts = mpAccountService.findAll();
			model.addAttribute("mpAccounts", mpAccounts);
			model.addAttribute("mpAccountId", mpAccountId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("自定义菜单列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath;
	}

	/**
	 * 跳转到第一级菜单
	 * 
	 * @param moduleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/edit/{mpMenuId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("mpMenuId") Long mpMenuId, Model model) {
		MpMenu mpMenu = mpMenuService.findByMpMenuId(mpMenuId);
		model.addAttribute("mpMenu", mpMenu);
		return "/weixin/mpMenu/edit";
	}
	
	@RequestMapping(value = "/weixin/mpMenu/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			@RequestParam(value = "mpAccountId", required = false) String mpAccountId,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit){
		DetachedCriteria dc = DetachedCriteria.forClass(MpMenu.class);
		if(null!=mpAccountId && !"".equals(mpAccountId)){
			dc.add(Restrictions.eq("parentMpMenuId", Integer.parseInt(mpAccountId)));
		}
		dc.addOrder(Order.desc("mpMenuId"));//排序
		PageResults<MpMenu> pageResults = mpMenuService.findListByDetachedCriteria(dc, (start/limit) + 1, limit);
		List<MpMenu> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(MpMenu mpMenu : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(mpMenu.getMpMenuId());
			jsonObj.add(mpMenu.getMpMenuType().toString());
			jsonObj.add(mpMenu.getMpMenuKey());
			jsonObj.add(mpMenu.getMpMenuUrl());
			jsonObj.add(mpMenu.getMpMenuMediaId());
			String operator = "<a href=\"edit/"+mpMenu.getMpMenuId()+"\" class=\"btn btn-primary btn-xs\">编辑</a>";
			jsonObj.add(operator);
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}

}