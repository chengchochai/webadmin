package com.jipengblog.webadmin.weixin.bean;

import java.util.Date;

/**
 * @desc 微信服务器传来数据类型的公共数据，不同类型的数据继承该类
 * @author penn
 * @date 2013-06-27 14:55
 */
public class ReceiveMsgParent {
	protected String fromUsername;
	protected String toUsername;
	protected String msgType;
	protected Date createTime;
	protected String msgId;

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
