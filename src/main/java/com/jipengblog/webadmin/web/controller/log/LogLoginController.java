package com.jipengblog.webadmin.web.controller.log;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.log.LogLoginService;
import com.jipengblog.webadmin.utils.time.DateUtils;
import com.jipengblog.webadmin.web.common.DataTablesPojo;
import com.jipengblog.webadmin.web.controller.ParentController;

@Controller
public class LogLoginController extends ParentController {

	@Autowired
	private LogLoginService loginService;

	private String defaultPath = "/log/login/list";

	@RequestMapping(value = "/log/login/list", method = { RequestMethod.GET })
	public String list(HttpSession session, Model model) {
		return defaultPath;
	}
	
	@RequestMapping(value = "/log/login/fillData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "loginIp", required = false) String loginIp,
			@RequestParam(value = "sEcho", required = true) Integer sEcho,
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit){
		DetachedCriteria dc = DetachedCriteria.forClass(LogLogin.class);
		if(null!=startTime && !"".equals(startTime)){
			dc.add(Restrictions.ge("loginTime", DateUtils.stringToDate(startTime)));
		}
		if(null!=endTime && !"".equals(endTime)){
			dc.add(Restrictions.le("loginTime", DateUtils.stringToDate(endTime)));
		}
		if(null!=loginName && !"".equals(loginName)){
			dc.add(Restrictions.eq("loginName", loginName));	
		}
		if(null!=loginIp && !"".equals(loginIp)){
			dc.add(Restrictions.eq("loginIp", loginIp));
		}
		dc.addOrder(Order.desc("loginTime"));//排序
		PageResults<LogLogin> pageResults = loginService.findListByDetachedCriteria(dc, (start/limit) + 1, limit);
		List<LogLogin> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(LogLogin logLogin : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(logLogin.getLogId());
			jsonObj.add(logLogin.getLoginName());
			jsonObj.add(logLogin.getLoginIp());
			jsonObj.add(logLogin.getLoginTime()==null?"":DateUtils.dateToString(logLogin.getLoginTime()));
			jsonObj.add(logLogin.getDescription());
			jsonObj.add(logLogin.getLogoutTime()==null?"":DateUtils.dateToString(logLogin.getLogoutTime()));
			jsonObj.add(logLogin.getLogoutType()==null?"":logLogin.getLogoutType().getValue());
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo(sEcho,pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}
}
