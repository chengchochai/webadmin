package com.jipengblog.webadmin.weixin.factory;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;

import com.jipengblog.webadmin.utils.DocumentUtil;
import com.jipengblog.webadmin.utils.StreamUtil;
import com.jipengblog.webadmin.utils.time.TimeUtils;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgParent;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgSiteAccess;
import com.jipengblog.webadmin.weixin.bean.ReceiveMsgText;

public class MpReceiveMsgBeanFactory {
	
	public static ReceiveMsgParent packageReqInfo(HttpServletRequest request)
			throws IOException {
		String echostr = request.getParameter("echostr");
		if (null == echostr || echostr.equals("")) {
			String postStr = StreamUtil.readStreamParameter(request.getInputStream());
			Element root = DocumentUtil.stringToElement(postStr);
			String msgType = root.elementText("MsgType");
			if (msgType.equals("text")) {
				ReceiveMsgText txtMsg = new ReceiveMsgText();
				txtMsg.setFromUsername(root.elementText("FromUserName"));
				txtMsg.setToUsername(root.elementText("ToUserName"));
				txtMsg.setCreateTime(TimeUtils.timestapToDate(Integer.parseInt((root.elementText("CreateTime")))));
				txtMsg.setMsgType(msgType);
				txtMsg.setMsgId(root.elementText("MsgId"));
				txtMsg.setContent(root.elementText("Content"));
				return txtMsg;
			} else {
				ReceiveMsgParent msg = new ReceiveMsgParent();
				msg.setFromUsername(root.elementText("FromUserName"));
				msg.setToUsername(root.elementText("ToUserName"));
				msg.setCreateTime(TimeUtils.timestapToDate(Integer.parseInt(root.elementText("CreateTime"))));
				msg.setMsgType(msgType);
				msg.setMsgId(root.elementText("MsgId"));
				return msg;
			}
		} else {
			ReceiveMsgSiteAccess sa = new ReceiveMsgSiteAccess();
			sa.setEchostr(request.getParameter("echostr"));
			sa.setSignature(request.getParameter("signature"));
			sa.setTimestamp(request.getParameter("timestamp"));
			sa.setNonce(request.getParameter("nonce"));
			sa.setMsgType("site");
			return sa;
		}
	}
}
