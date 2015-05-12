package com.jipengblog.webadmin.weixin.handle;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.utils.security.SignatureUtils;
import com.jipengblog.webadmin.utils.security.enums.Algorithm;
import com.jipengblog.webadmin.utils.sort.SortUtil;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgSiteAccess;

/**
 * @desc 处理网站接入的handle
 * @author penn
 * @2013-06-27 14:50
 * 
 */
public class ReceiveMsgSiteAccessHandleImpl implements ReceiveMsgHandle {
	private static final Logger logger = Logger
			.getLogger(ReceiveMsgSiteAccessHandleImpl.class);

	public String handle(ReceiveMsgParent msg){
		logger.info("本次信息处理由SiteAccessHandleImpl进行......");
		String result = "";
		try {
			ReceiveMsgSiteAccess sa = (ReceiveMsgSiteAccess) msg;
			logger.info("echostr的值为:" + sa.getEchostr() + "，开始网址接入验证的处理......");
			if (vaildSiteAccess(sa)) {// 验证成功
				logger.info("网址接入验证成功.");
				result = sa.getEchostr();
			} else {// 验证失败
				logger.info("网址接入验证失败!");
				result = "网址接入验证失败";
			}
			logger.info("echostr的值为:" + sa.getEchostr() + "，开始网址接入验证的处理结束。");
		} catch (Exception e) {
			logger.error("错误!!!SiteAccessHandleImpl逻辑处理错误!!!");
			e.printStackTrace();
			result = "对不起，系统升级中，稍我们将为您提供更有趣的资讯，谢谢您的关注！";
		}
		logger.info("返回的处理结果:::" + result);
		return result;
	}

	/**
	 * 接入网址的验证逻辑
	 * 
	 * @param request
	 * @return
	 */
	private static boolean vaildSiteAccess(ReceiveMsgSiteAccess sa) {
		/*
		 * 根据验证规则首先对token, timestamp, nonce进行字典排序，然后对排序后的结果进行SHA1加密
		 * 最后对加密后的字符串和signature进行比较，相同则验证通过，否则失败。
		 */
		//String token = AppUtils.getProperty("dodoniuToken");
		String token = "";//数据库查询得到token
		String result_sort = SortUtil.dictSort(token, sa.getTimestamp(),sa.getNonce());
		SignatureUtils signatureUtils = new SignatureUtils(Algorithm.SHA1);
		String result_encode = signatureUtils.encrypt(result_sort, SignatureUtils.NO_SALT);
		if (sa.getSignature().equals(result_encode)) {
			return true;// 成功
		} else {
			return false;// 失败
		}
	}
	
}
