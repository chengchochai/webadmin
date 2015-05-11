package com.jipengblog.webadmin.entity.constant;

/**
 * 发送Email的类型
 * 
 * @author penn
 *
 */
public enum EmailType {

	FORGETPASSWORD(1), // 忘记密码
	REGISTER(2); // 账号注册

	private final int value;

	EmailType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
