package com.jipengblog.webadmin.utils.email.constant;

/**
 * 发送Email的模板的名称
 * 
 * @author penn
 *
 */
public enum EmailTemplate {
	
	RESPONSE_URL("/com/jipengblog/webadmin/utils/email/tpl/email_system.vm");// 响应式模板，用户点击操作，如忘记密码和注册用户

	private final String value;

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	EmailTemplate(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
