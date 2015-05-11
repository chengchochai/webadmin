package com.jipengblog.webadmin.entity.log;

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

import com.jipengblog.webadmin.entity.constant.EmailType;

@Entity
@Table(name = "log_email")
public class LogEmail implements Serializable {

	public LogEmail() {
	}

	public LogEmail(String receiver, String keyword, EmailType emailType,
			String templateName, Date createTime, Date deadline) {
		this.receiver = receiver;
		this.keyword = keyword;
		this.emailType = emailType.getValue();
		this.templateName = templateName;
		this.createTime = createTime;
		this.deadline = deadline;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8211454851497854470L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long logId;// 主键标识

	@Column(nullable = false, length = 50)
	private String receiver;// 接收人

	@Column(nullable = false, length = 50, unique = true)
	private String keyword;// 发送邮件的关键字，用作反查信息

	@Column(nullable = false, length = 50)
	private Integer emailType;// 发送邮件的类型，找回密码或者账户注册等

	@Column(nullable = false, length = 50)
	private String templateName;// 模板名称

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createTime;// 创建时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date deadline;// 记录查询的失效时间

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}