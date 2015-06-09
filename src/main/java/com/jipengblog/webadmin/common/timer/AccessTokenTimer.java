package com.jipengblog.webadmin.common.timer;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.service.weixin.MpAccountService;
import com.jipengblog.webadmin.utils.http.HttpUtils;
import com.jipengblog.webadmin.utils.time.DateUtils;
import com.jipengblog.webadmin.utils.time.constant.TimeUnit;
import com.jipengblog.webadmin.web.utils.SpringContextUtil;
import com.jipengblog.webadmin.weixin.config.IntAddress;

/**
 * 根据微信公众号的要求，每个公众号的access_token有效时间为两个小时。
 * 所以每隔一个小时调用一次微信的获取access token接口，用来更新
 * 使用springmvc定时器
 * 
 * @author penn
 *
 */
@Component
public class AccessTokenTimer {
	
	private static Logger logger = Logger.getLogger(AccessTokenTimer.class);
	
	@Scheduled(cron="0 0 0/1 * * ?")  
	public void run() {
		logger.info("定时任务启动，AccessTokenTimer，更新每个公众账号的access_token");
		try {
			MpAccountService mpAccountService = (MpAccountService) SpringContextUtil.getBean("mpAccountService");
			List<MpAccount> mpAccounts = mpAccountService.findAll();
			logger.info("查询得到"+(mpAccounts==null?0:mpAccounts.size())+"个公众账号");
			for (MpAccount mpAccount : mpAccounts) {
				logger.info("更新第1个微信公众号的access_token:");
				String finalAddr = String.format(IntAddress.getAccessToken,mpAccount.getAppId(), mpAccount.getAppSecret());
				logger.info("接口地址:"+finalAddr);
				JSONObject json = JSONObject.fromObject(HttpUtils.sendGet(finalAddr));
				logger.info("请求结果:"+json.toString());
				mpAccount.setAccessToken(json.getString("access_token"));
				mpAccount.setAccessTokenDeadTime(DateUtils.addTime(new Date(),TimeUnit.SECOND, json.getInt("expires_in")));
				mpAccountService.update(mpAccount);
				logger.info("更新完成");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("定时任务结束，AccessTokenTimer");
	}
}
