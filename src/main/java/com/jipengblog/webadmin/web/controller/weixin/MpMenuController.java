package com.jipengblog.webadmin.web.controller.weixin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.jipengblog.webadmin.entity.constant.MpMenuType;
import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.entity.weixin.MpMenu;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.service.weixin.MpMenuService;
import com.jipengblog.webadmin.utils.http.HttpUtils;
import com.jipengblog.webadmin.utils.time.DateUtils;
import com.jipengblog.webadmin.utils.time.constant.TimeUnit;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
import com.jipengblog.webadmin.web.controller.ParentController;
import com.jipengblog.webadmin.weixin.config.IntAddress;

@Controller
public class MpMenuController extends ParentController {

	@Autowired
	private MpMenuService mpMenuService;
	@Autowired
	private MpAccountService mpAccountService;

	private String defaultPath1 = "/weixin/mpMenu/list1";
	private String defaultPath2 = "/weixin/mpMenu/list2";

	/**
	 * 跳转到自定义(一级)菜单列表
	 * 
	 * @param model
	 * @param tip
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/list1", method = { RequestMethod.GET })
	public String list1(Model model, String tip) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"),
						"utf-8");
			} else {
				super.pageTip = null;
			}
			List<MpAccount> mpAccounts = mpAccountService.findAll();
			model.addAttribute("mpAccounts", mpAccounts);
		} catch (Exception e) {
			logger.error("自定义菜单列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath1;
	}

	/**
	 * 跳转到自定义(二级)菜单列表
	 * 
	 * @param model
	 * @param tip
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/list2/{mpAccountId}/{parentMpMenuId}", method = { RequestMethod.GET })
	public String list2(Model model, String tip,
			@PathVariable("mpAccountId") Long mpAccountId,
			@PathVariable("parentMpMenuId") Long parentMpMenuId) {
		try {
			if (tip != null) {
				super.pageTip = new String(tip.trim().getBytes("ISO-8859-1"),
						"utf-8");
			} else {
				super.pageTip = null;
			}
			MpAccount mpAccount = mpAccountService
					.findByMpAccountId(mpAccountId);
			List<MpMenu> parentMpMenus = mpMenuService
					.findAllFirstLevelMenu(mpAccountId);
			MpMenu parentMpMenu = mpMenuService.findByMpMenuId(parentMpMenuId);
			model.addAttribute("mpAccount", mpAccount);
			model.addAttribute("parentMpMenu", parentMpMenu);
			model.addAttribute("parentMpMenus", parentMpMenus);
		} catch (Exception e) {
			logger.error("自定义菜单列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath2;
	}

	/**
	 * 跳转到新增/编辑自定义(一级)菜单
	 * 
	 * @param model
	 * @param mpAccountId
	 * @param mpMenuId
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/edit1/{mpAccountId}/{mpMenuId}", method = { RequestMethod.GET })
	public String edit1(Model model, RedirectAttributesModelMap modelMap,
			@PathVariable("mpAccountId") Long mpAccountId,// 表示当前操作的公众号
			@PathVariable("mpMenuId") Long mpMenuId) {// 表示当前操作的菜单，如果为0，则表示当前操作自定义(一级)菜单
		MpAccount mpAccount = mpAccountService.findByMpAccountId(mpAccountId);
		if (mpAccount == null) {// 首先判断公众号信息是否正确存在
			modelMap.addAttribute("tip", "公众号信息错误");
			return redirect + defaultPath1;
		}
		model.addAttribute("mpAccount", mpAccount);
		if (mpMenuId == 0) {// 新增时判断自定义(一级)菜单的数量
			List<MpMenu> list = mpMenuService
					.findAllFirstLevelMenu(mpAccountId);
			// 判断当前公众号如果已经有了3个自定义(一级)菜单，则无法添加第4个自定义(一级)菜单
			if (list.size() >= 3) {
				modelMap.addAttribute("tip", "不能添加第四个自定义(一级)菜单");
				return redirect + defaultPath1;
			}
		}
		if (mpMenuId != 0) {// 编辑时判断自定义(一级)菜单与公众号的关系是否正确
			MpMenu mpMenu = mpMenuService.findByMpMenuId(mpMenuId);
			if (mpMenu == null) {// 先判断自定义(一级)菜单的信息是否正确
				modelMap.addAttribute("tip", "自定义(一级)菜单信息错误");
				return redirect + defaultPath1;
			}
			// 判断该自定义(一级)菜单对应的mpAccountId与传入的mpAccountId是否相同，如果不同说明该自定义(一级)菜单与传入的公众号关系错误
			if (mpMenu.getMpAccountId() != mpAccountId) {
				modelMap.addAttribute("tip", "公众号与自定义(一级)菜单关系错误");
				return redirect + defaultPath1;
			}
			model.addAttribute("mpMenu", mpMenu);
		}
		return "/weixin/mpMenu/edit1";
	}

	/**
	 * 跳转到新增/编辑自定义(二级)菜单
	 * 
	 * @param model
	 * @param mpAccountId
	 * @param parentMpMenuId
	 * @param mpMenuId
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpMenu/edit2/{mpAccountId}/{parentMpMenuId}/{mpMenuId}", method = { RequestMethod.GET })
	public String edit2(Model model, RedirectAttributesModelMap modelMap,
			@PathVariable("mpAccountId") Long mpAccountId,// 表示当前操作的公众号
			@PathVariable("parentMpMenuId") Long parentMpMenuId,// 表示当前操作的自定义(一级)菜单
			@PathVariable("mpMenuId") Long mpMenuId) {// 表示当前操作的菜单，如果为0，则表示当前操作自定义(一级)菜单
		MpAccount mpAccount = mpAccountService.findByMpAccountId(mpAccountId);
		if (mpAccount == null) {// 首先判断公众号信息是否正确存在
			modelMap.addAttribute("tip", "公众号信息错误");
			return redirect + defaultPath2 + "/" + mpAccountId + "/"
					+ parentMpMenuId;
		}
		model.addAttribute("mpAccount", mpAccount);

		MpMenu parentMpMenu = mpMenuService.findByMpMenuId(parentMpMenuId);
		if (parentMpMenu == null) {// 判断对应的自定义(一级)菜单信息是否正确存在
			modelMap.addAttribute("tip", "自定义(一级)菜单信息错误");
			return redirect + defaultPath2 + "/" + mpAccountId + "/"
					+ parentMpMenuId;
		}
		model.addAttribute("parentMpMenu", parentMpMenu);

		if (parentMpMenu.getMpAccountId() != mpAccountId) {// 判断公众号和自定义(一级)菜单的关系是否正确
			modelMap.addAttribute("tip", "公众号与自定义(一级)菜单关系错误");
			return redirect + defaultPath2 + "/" + mpAccountId + "/"
					+ parentMpMenuId;
		}

		if (mpMenuId == 0) {// 新增时判断自定义(二级)菜单的数量
			List<MpMenu> list = mpMenuService
					.findAllSecondLevelMenu(parentMpMenuId);
			// 判断当前公众号如果已经有了5个自定义(二级)菜单，则无法添加第6个自定义(二级)菜单
			if (list.size() >= 5) {
				modelMap.addAttribute("tip", "不能添加第六个自定义(二级)菜单");
				return redirect + defaultPath2 + "/" + mpAccountId + "/"
						+ parentMpMenuId;
			}
		}

		if (mpMenuId != 0) {// 编辑时判断自定义(二级)菜单与公众号的关系是否正确
			MpMenu mpMenu = mpMenuService.findByMpMenuId(mpMenuId);
			if (mpMenu == null) {// 先判断自定义(二级)菜单的信息是否正确
				modelMap.addAttribute("tip", "自定义(二级)菜单信息错误");
				return redirect + defaultPath1;
			}
			// 判断该自定义(二级)菜单对应的mpAccountId与传入的mpAccountId是否相同，如果不同说明该自定义(二级)菜单与传入的公众号关系错误
			if (mpMenu.getMpAccountId() != parentMpMenu.getMpAccountId()) {
				modelMap.addAttribute("tip", "公众号与自定义(二级)菜单关系错误");
				return redirect + defaultPath1;
			}
			model.addAttribute("mpMenu", mpMenu);
		}
		return "/weixin/mpMenu/edit2";
	}

	@RequestMapping(value = "/weixin/mpMenu/edit", method = { RequestMethod.POST })
	public String edit(
			Model model,
			RedirectAttributesModelMap modelMap,
			@RequestParam(value = "mpMenuId") Long mpMenuId,
			@RequestParam(value = "parentMpMenuId", required = false, defaultValue = "0") Long parentMpMenuId,
			@RequestParam(value = "mpAccountId", required = false) Long mpAccountId,
			@RequestParam(value = "mpMenuName", required = false) String mpMenuName,
			@RequestParam(value = "mpMenuType", required = false) String mpMenuType,
			@RequestParam(value = "priority", required = false) Integer priority,
			@RequestParam(value = "mpMenuUrl") String mpMenuUrl,
			@RequestParam(value = "mpMenuKey") String mpMenuKey,
			@RequestParam(value = "mpMenuMediaId") String mpMenuMediaId) {
		String tip = null;
		String finalPath = redirect + defaultPath1;// 最后跳转的地址
		if (parentMpMenuId != 0) {
			finalPath = redirect + defaultPath2 + "/" + mpAccountId + "/"
					+ parentMpMenuId;
		}
		try {
			MpAccount mpAccount = mpAccountService
					.findByMpAccountId(mpAccountId);
			if (mpAccount == null) {// 首先判断公众号信息是否正确存在
				modelMap.addAttribute("tip", "公众号信息错误");
				return finalPath;
			}

			if (parentMpMenuId != 0) {
				MpMenu parentMpMenu = mpMenuService
						.findByMpMenuId(parentMpMenuId);
				if (parentMpMenu == null) {
					modelMap.addAttribute("tip", "自定义(一级)菜单信息错误");
					return finalPath;
				}

				if (parentMpMenu.getMpAccountId() != mpAccountId) {
					modelMap.addAttribute("tip", "公众号与自定义(一级)菜单关系错误");
					return finalPath;

				}
			}
			MpMenu mpMenu = mpMenuService.findByMpMenuId(mpMenuId);
			if (mpMenu != null) {// 找到自定义菜单信息
				if (mpMenu.getMpAccountId() != mpAccountId
						|| mpMenu.getParentMpMenuId() != parentMpMenuId) {
					modelMap.addAttribute("tip", "公众号与自定义(二级)菜单关系错误");
					return finalPath;
				}
				mpMenu.setMpMenuName(mpMenuName);
				mpMenu.setMpMenuType(MpMenuType.valueOf(mpMenuType));
				mpMenu.setPriority(priority);
				mpMenu.setMpMenuUrl(mpMenuUrl);
				mpMenu.setMpMenuKey(mpMenuKey);
				mpMenu.setMpMenuMediaId(mpMenuMediaId);
				mpMenuService.update(mpMenu);
				tip = "自定义菜单编辑成功";
			} else {

				mpMenu = new MpMenu(mpMenuName, MpMenuType.valueOf(mpMenuType),
						mpMenuKey, mpMenuUrl, mpMenuMediaId, mpAccountId,
						priority, parentMpMenuId);
				mpMenuService.save(mpMenu);
				tip = "自定义菜单添加成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("公众号账号错误:::" + e.getMessage());
			tip = "系统未知错误";
		}
		modelMap.addAttribute("tip", tip);
		return finalPath;
	}

	@RequestMapping(value = "/weixin/mpMenu/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			HttpServletRequest request,
			@RequestParam(value = "mpAccountId", required = false) Long mpAccountId,
			@RequestParam(value = "parentMpMenuId", required = false, defaultValue = "0") Long parentMpMenuId,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit) {
		DetachedCriteria dc = DetachedCriteria.forClass(MpMenu.class);
		if (null != mpAccountId && !"".equals(mpAccountId)) {
			dc.add(Restrictions.eq("mpAccountId", mpAccountId));
		} else {// 如果不传mpAccountId，则不查询
			return JSONObject.fromObject(
					new DataTablesPojo(sEcho, 0, 0, new JSONArray()))
					.toString();
		}
		if (null != parentMpMenuId && !"".equals(parentMpMenuId)) {// 查询自定义(二级)菜单
			dc.add(Restrictions.eq("parentMpMenuId", parentMpMenuId));
		} else {// 查询自定义(一级)菜单
			dc.add(Restrictions.eq("parentMpMenuId", 0L));
		}
		dc.addOrder(Order.desc("priority"));// 排序
		PageResults<MpMenu> pageResults = mpMenuService
				.findListByDetachedCriteria(dc, (start / limit) + 1, limit);
		List<MpMenu> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for (MpMenu mpMenu : results) {
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(mpMenu.getMpMenuId());
			jsonObj.add(mpMenu.getMpMenuName());
			jsonObj.add(mpMenu.getMpMenuType().toString());
			jsonObj.add(mpMenu.getMpMenuKey());
			jsonObj.add(mpMenu.getMpMenuUrl());
			jsonObj.add(mpMenu.getMpMenuMediaId());
			String operator = "<a href=\"edit1/" + mpAccountId + "/"
					+ mpMenu.getMpMenuId()
					+ "\" class=\"btn btn-primary btn-xs\">编辑</a> ";
			if (parentMpMenuId == 0) {// 自定义(一级)菜单时添加查看二级菜单的按钮
				operator += "<a href=\"list2/" + mpAccountId + "/"
						+ mpMenu.getMpMenuId()
						+ "\" class=\"btn btn-primary btn-xs\">查看二级菜单</a> ";
			}
			jsonObj.add(operator);
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,
				pageResults.getTotalCount(), pageResults.getTotalCount(),
				jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}

	@RequestMapping(value = "/weixin/mpMenu/releaseMenu/{mpAccountId}", method = { RequestMethod.PUT }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String releaseMenu(HttpServletRequest request, HttpSession session,
			@PathVariable("mpAccountId") Long mpAccountId) throws Exception {
		MpAccount mpAccount = mpAccountService.findByMpAccountId(mpAccountId);
		if (mpAccount != null) {
			JSONArray jsonArray = new JSONArray();
			List<MpMenu> mpMenus = mpMenuService.findAllFirstLevelMenu(mpAccountId);
			for(MpMenu mpMenu : mpMenus){
				List<MpMenu> subMpMenu = mpMenuService.findAllSecondLevelMenu(mpMenu.getMpMenuId());
				if(null == subMpMenu || subMpMenu.size() == 0){//没有子菜单
					
				}else{//有子菜单
					JSONObject json = new JSONObject();
					json.put("name", mpMenu.getMpMenuName());
					json.put("type", mpMenu.getMpMenuType());
//					if(mpMenu.getType().equals("view")){
//						json.put("url", mpMenu.getUrl());
//					}else if(menu.getType().equals("click")){
//						json.put("key", mpMenu.getKey());
//					}
				}
			}
		}
		return "";
	}
}