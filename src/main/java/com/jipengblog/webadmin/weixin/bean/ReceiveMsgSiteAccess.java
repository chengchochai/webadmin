package com.jipengblog.webadmin.weixin.bean;

/**
 * 
 * @desc 网站接入时封装数据的实体类 
 * 	加密/校验流程如下： 
 * 	1. 将token、timestamp、nonce三个参数进行字典序排序 
 * 	2. 将三个参数字符串拼接成一个字符串进行sha1加密 
 * 	3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 * @author penn
 * @date 2013-06-27 14:59
 */
public class ReceiveMsgSiteAccess extends ReceiveMsgParent {

	private String signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String nonce;// 随机数
	private String timestamp;// 时间戳
	private String echostr;// 随机字符串

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
}
