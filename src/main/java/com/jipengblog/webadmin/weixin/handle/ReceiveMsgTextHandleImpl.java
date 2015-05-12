package com.jipengblog.webadmin.weixin.handle;

import org.apache.log4j.Logger;

import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgText;

/**
 * @desc 消息内容为text的handle
 * @author penn
 * @2013-06-27
 * 
 */
public class ReceiveMsgTextHandleImpl implements ReceiveMsgHandle {

	private static final Logger logger = Logger
			.getLogger(ReceiveMsgTextHandleImpl.class);
	private ReceiveMsgText txtMsg = null;

	public String handle(ReceiveMsgParent msg) {
		this.txtMsg = (ReceiveMsgText) msg;

		String replyContent = "";// 结果

		return replyContent;
	}

}
