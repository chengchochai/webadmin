package com.jipengblog.webadmin.web.controller.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.entity.weixin.MpMenu;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.service.weixin.MpMenuService;
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
	public String list(Model model, String tip,
			@RequestParam(value = "mpAccountId", defaultValue = "0") Long mpAccountId) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"), "utf-8");
			} else {
				super.pageTip = null;
			}
			List<MpAccount> mpAccounts = mpAccountService.findAll();
			List<MpMenu> mpMenus = mpMenuService.findAllFirstLevelMenu(mpAccountId);
			model.addAttribute("mpMenus", mpMenus);
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

}