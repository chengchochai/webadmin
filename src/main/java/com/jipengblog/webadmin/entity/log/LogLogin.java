package com.jipengblog.webadmin.entity.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.json.JSONObject;

import org.hibernate.annotations.Type;

import com.jipengblog.webadmin.entity.constant.LogoutType;
import com.jipengblog.webadmin.utils.http.HttpUtils;
import com.jipengblog.webadmin.utils.http.constants.APIs;

@Entity
@Table(name = "log_login")
public class LogLogin implements Serializable {

	public LogLogin() {
	}

	/**
	 * 记录的日志不包括详细的IP信息
	 * 
	 * @param loginName
	 *            登录账号
	 * @param loginPass
	 *            登录密码
	 * @param successful
	 *            是否成功
	 * @param loginTime
	 *            登录时间
	 * @param loginIp
	 *            登录IP
	 * @param description
	 *            描述
	 */
	public LogLogin(String loginName, String loginPass, Boolean successful,
			Date loginTime, String loginIp, String description, String sessionId) {
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.successful = successful;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.description = description;
		this.sessionId = sessionId;
		try {
			String content = HttpUtils.sendGet(APIs.TAOBAO_IP + loginIp);
			JSONObject baseObj = JSONObject.fromObject(content);
			if (0 == (baseObj.getInt("code"))) {
				JSONObject dataObj = JSONObject.fromObject(baseObj.get("data"));
				this.loginState = dataObj.getString("country");
				this.loginProvince = dataObj.getString("region");
				this.loginCity = dataObj.getString("city");
				this.netType = dataObj.getString("isp");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2980869051914503248L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long logId;// 主键标识

	@Column(nullable = false, length = 100)
	private String loginName;// 登录账号

	@Column(nullable = false, length = 32)
	private String loginPass;// 登录密码

	@Column(nullable = false)
	@Type(type = "yes_no")
	private Boolean successful;// 是否成功

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date loginTime;// 登录时间

	@Column(nullable = false, length = 40)
	private String loginIp;// 登陆IP

	@Column(nullable = true, length = 10)
	private String netType;// 网络类型

	@Column(nullable = true, length = 50)
	private String loginState;// 登录所在国家

	@Column(nullable = true, length = 50)
	private String loginProvince;// 登录所在省份

	@Column(nullable = true, length = 50)
	private String loginCity;// 登录所在城市

	@Column(nullable = true)
	private String description;// 描述

	@Column(nullable = false, length = 32)
	private String sessionId;// 登陆的sessionID

	@Enumerated(EnumType.STRING)
	private LogoutType logoutType; // 登出方式

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date logoutTime;// 登出时间

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getLoginProvince() {
		return loginProvince;
	}

	public void setLoginProvince(String loginProvince) {
		this.loginProvince = loginProvince;
	}

	public String getDescription() {
		return description;
	}

	public String getLoginCity() {
		return loginCity;
	}

	public void setLoginCity(String loginCity) {
		this.loginCity = loginCity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public LogoutType getLogoutType() {
		return logoutType;
	}

	public void setLogoutType(LogoutType logoutType) {
		this.logoutType = logoutType;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

}