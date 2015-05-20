package com.jipengblog.webadmin.web.controller.log;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.jipengblog.webadmin.utils.time.TimeUtils;
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
	
	@RequestMapping(value = "/log/login/tableData", method = { RequestMethod.POST }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tableData(
			@RequestParam(value = "start", required = true) Integer start,
			@RequestParam(value = "limit", required = true) Integer limit) {
		String hql = "from LogLogin order by loginTime desc";
		String countHql = "select count(*) from LogLogin";
		int a = start/limit;
		int b = limit;
		System.out.println(a);
		System.out.println(b);
		PageResults<LogLogin> pageResults = loginService.findListByPageResults(hql, countHql, (start/limit)+1, limit);
		List<LogLogin> results = pageResults.getResults();
		JSONArray jsonArr = new JSONArray();
		for(LogLogin logLogin : results){
			JSONArray jsonObj = new JSONArray();
			jsonObj.add(logLogin.getLogId());
			jsonObj.add(logLogin.getLoginName());
			jsonObj.add(logLogin.getLoginIp());
			jsonObj.add(logLogin.getLoginTime()==null?"":TimeUtils.timestapToString(logLogin.getLoginTime()));
			jsonObj.add(logLogin.getDescription());
			jsonObj.add(logLogin.getLogoutTime()==null?"":TimeUtils.timestapToString(logLogin.getLogoutTime()));
			jsonObj.add(logLogin.getLogoutType()==null?"":logLogin.getLogoutType().getValue());
			jsonArr.add(jsonObj);
		}
		DataTablesPojo dataTablesPojo = new DataTablesPojo((int)(System.currentTimeMillis()/1000),pageResults.getTotalCount(),pageResults.getTotalCount(),jsonArr);
		return JSONObject.fromObject(dataTablesPojo).toString();
	}
}
