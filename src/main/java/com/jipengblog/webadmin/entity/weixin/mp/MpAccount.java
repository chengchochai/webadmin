package com.jipengblog.webadmin.entity.weixin.mp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 微信公众平台，标识公众号唯一性的重要标识，包括ID，secret，token，秘钥等非常重要的信息
 * 
 * @author penn
 *
 */
@Entity
@Table(name = "wx_mp_account")
public class MpAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2203776487490837148L;

	public MpAccount() {

	}

	public MpAccount(String accountName, String appId,
			String appSecret, String appToken, String accessToken,
			Date accessTokenDeadTime) {
		this.accountName = accountName;
		this.appId = appId;
		this.appSecret = appSecret;
		this.appToken = appToken;
		this.accessToken = accessToken;
		this.accessTokenDeadTime = accessTokenDeadTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;// 公众号在本系统的主键标识

	@Column(nullable = false, length = 50)
	private String accountName;// 公众号在本系统的名称标识

	@Column(nullable = false, length = 50)
	private String appId;// 微信分配给公众号的appId

	@Column(nullable = false, length = 50)
	private String appSecret;// 微信分配给公众号的appSecret

	@Column(nullable = false, length = 50)
	private String appToken;// 与微信服务器建立链接时使用的token

	@Column(nullable = false, length = 50)
	private String encodingAESKey;// 数据传输时对数据进行加密的秘钥

	@Column(nullable = false, length = 50)
	private String accessToken;// 获得微信高级接口权限时使用的token

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date accessTokenDeadTime;// 获得微信高级接口权限时使用的token的失效时间

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	public Date getAccessTokenDeadTime() {
		return accessTokenDeadTime;
	}

	public void setAccessTokenDeadTime(Date accessTokenDeadTime) {
		this.accessTokenDeadTime = accessTokenDeadTime;
	}

}
