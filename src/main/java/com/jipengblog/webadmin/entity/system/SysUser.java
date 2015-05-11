package com.jipengblog.webadmin.entity.system;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.jipengblog.webadmin.entity.constant.Sex;

@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

	public SysUser() {
	}

	public SysUser(String loginName, String loginPass, String realName,
			Sex sex, String email, String mobile, Date createTime,
			Boolean enabled, Set<SysRole> roles) {
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.realName = realName;
		this.sex = sex;
		this.email = email;
		this.mobile = mobile;
		this.createTime = createTime;
		this.enabled = enabled;
		this.roles = roles;
	}

	private static final long serialVersionUID = 7093133452396641043L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;// 主键标识

	@Column(nullable = false, length = 50, unique = true)
	private String loginName;

	@Column(nullable = false, length = 32)
	private String loginPass;// 登录密码

	@Column(nullable = true, length = 50)
	private String realName;// 称呼

	@Enumerated(EnumType.STRING)
	private Sex sex;// 性别

	@Column(nullable = false, length = 50)
	private String email;// 邮箱

	@Column(nullable = true, length = 20)
	private String mobile;// 手机

	@Column(nullable = true)
	private String headImg;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createTime;// 登录时间

	/*
	 * 由于JPA本身是不支持Boolean类型,所以这里使用Hibernater自带的注解
	 * 
	 * @Type(type="yes_no") mysql中将对应char(1)的类型，入库的值为N或Y
	 */
	@Column(nullable = false)
	@Type(type = "yes_no")
	private Boolean enabled;// 账户状态

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "relation_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<SysRole> roles = new LinkedHashSet<SysRole>();// 用户关联的角色

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

}
