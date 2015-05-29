package com.jipengblog.webadmin.web.controller.weixin;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class MpAccountController extends ParentController {

	@Autowired
	private MpAccountService mpAccountService;

	private String defaultPath = "/weixin/mpAccount/list";

	/**
	 * 跳转到MpAccount列表
	 * 
	 * @param session
	 * @param model
	 * @param tip
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpAccount/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model, String tip) {
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
			e.printStackTrace();
			logger.error("公众号账号列表错误:::" + e.getMessage());
			super.pageTip = "系统错误";
		}
		model.addAttribute("pageTip", pageTip);
		return defaultPath;
	}

	/**
	 * 跳转编辑／添加MpAccount页面
	 * 
	 * @param moduleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpAccount/edit/{mpAccountId}", method = { RequestMethod.GET })
	public String edit(@PathVariable("mpAccountId") Long mpAccountId,
			Model model) {
		MpAccount mpAccount = mpAccountService.findByMpAccountId(mpAccountId);// 查询当前角色信息
		model.addAttribute("mpAccount", mpAccount);
		return "/weixin/mpAccount/edit";
	}

	/**
	 * 编辑／添加module
	 * 
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
	@RequestMapping(value = "/weixin/mpAccount/edit", method = { RequestMethod.POST })
	public String doEdit(
			Model model,
			RedirectAttributesModelMap modelMap,
			@RequestParam(value = "mpAccountId") Long mpAccountId,
			@RequestParam(value = "mpAccountName") String mpAccountName,
			@RequestParam(value = "appId", required = false) String appId,
			@RequestParam(value = "appSecret") String appSecret,
			@RequestParam(value = "appToken") String appToken,
			@RequestParam(value = "encodingAESKey") String encodingAESKey) throws Exception {// 资源
		String tip = null;
		try {
			if (mpAccountId != null && mpAccountId != 0) {
				// 再次检测用户名是否存在
				MpAccount mpAccount = mpAccountService
						.findByMpAccountId(mpAccountId);
				if (mpAccount != null) {// 找到模块信息
					mpAccount.setMpAccountName(mpAccountName);
					//mpAccount.setAppId(appId);
					mpAccount.setAppSecret(appSecret);
					mpAccount.setAppToken(appToken);
					mpAccount.setEncodingAESKey(encodingAESKey);
					mpAccountService.update(mpAccount);
					tip = "公众号账号编辑成功";
				} else {
					tip = "没有找到要编辑的公众号账号信息";
				}
			} else {
				MpAccount mpAccount = new MpAccount(mpAccountName, appId,
						appSecret, appToken, encodingAESKey);
				mpAccountService.save(mpAccount);
				tip = "公众号账号添加成功";
			}
			// 写库
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("公众号账号错误:::" + e.getMessage());
			tip = "系统未知错误";
		}
		modelMap.addAttribute("tip", tip);
		return redirect + defaultPath;
	}
	
	/**
	 * 检测appId的方法
	 * 
	 * @param mobile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/weixin/mpAccount/edit/checkAppId", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkAppId(
			@RequestParam(value = "appId", required = true) String appId,
			Model model) {
		boolean isUsed = true;// 是否可以使用
		MpAccount apAccount = mpAccountService.findByAppId(appId);
		if (apAccount != null) {
			isUsed = false;
		}
		return String.valueOf(isUsed);
	}
	
	@RequestMapping(value = "/weixin/mpAccount/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			@RequestParam(value = "appId", required = false) String appId,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit){
		DetachedCriteria dc = DetachedCriteria.forClass(MpAccount.class);
		if(null!=appId && !"".equals(appId)){
			dc.add(Restrictions.like("appId", appId, MatchMode.ANYWHERE));
		}
		dc.addOrder(Order.desc("mpAccountId"));//排序
		PageResults<MpAccount> pageResults = mpAccountService.findListByDetachedCriteria(dc, (start/limit) + 1, limit);
		List<MpAccount> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(MpAccount mpAccount : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(mpAccount.getMpAccountId());
			jsonObj.add(mpAccount.getMpAccountName());
			jsonObj.add(mpAccount.getAppId());
			jsonObj.add(mpAccount.getAppSecret());
			jsonObj.add(mpAccount.getAppToken());
			jsonObj.add(mpAccount.getAccessToken());
			jsonObj.add(mpAccount.getAccessTokenDeadTime());
			String operator = "<a href=\"edit/"+mpAccount.getMpAccountId()+"\" class=\"btn btn-primary btn-xs\">编辑</a>";
			jsonObj.add(operator);
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}
}