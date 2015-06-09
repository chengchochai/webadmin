package com.jipengblog.webadmin.web.listener;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.service.log.LogLoginService;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.utils.http.HttpUtils;
import com.jipengblog.webadmin.utils.time.DateUtils;
import com.jipengblog.webadmin.utils.time.constant.TimeUnit;
import com.jipengblog.webadmin.web.utils.SpringContextUtil;
import com.jipengblog.webadmin.weixin.config.IntAddress;

public class MyServletContextListener implements ServletContextListener {

	private final static Logger logger = Logger
			.getLogger(MyServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("webadmin contextDestroyed");
		LogLoginService logLoginService = (LogLoginService) SpringContextUtil.getBean("logLoginService");
		int logoutUserNum = logLoginService.updateAllLogLogout();
		logger.info("系统关闭使" + logoutUserNum + "个用户下线...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("webadmin 服务启动成功");
		long start = System.currentTimeMillis();
//		logger.info("更新微信公众号的access_token和有效时间");
//		try {
//			MpAccountService mpAccountService = (MpAccountService) SpringContextUtil.getBean("mpAccountService");
//			List<MpAccount> mpAccounts = mpAccountService.findAll();
//			logger.info("查询得到"+(mpAccounts==null?0:mpAccounts.size())+"个公众账号");
//			for (MpAccount mpAccount : mpAccounts) {
//				logger.info("更新第1个微信公众号的access_token:");
//				String finalAddr = String.format(IntAddress.getAccessToken,mpAccount.getAppId(), mpAccount.getAppSecret());
//				logger.info("接口地址:"+finalAddr);
//				JSONObject json = JSONObject.fromObject(HttpUtils.sendGet(finalAddr));
//				logger.info("请求结果:"+json.toString());
//				mpAccount.setAccessToken(json.getString("access_token"));
//				mpAccount.setAccessTokenDeadTime(DateUtils.addTime(new Date(),TimeUnit.SECOND, json.getInt("expires_in")));
//				mpAccountService.update(mpAccount);
//				logger.info("更新完成");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		logger.info("======================webadmin 服务启动成功===========");
		long end = System.currentTimeMillis();
		logger.info("=======================webadmin 服务耗时间:" + (end - start)
				+ " ms ===================");
	}
}