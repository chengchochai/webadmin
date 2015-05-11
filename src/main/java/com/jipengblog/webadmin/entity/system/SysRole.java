package com.jipengblog.webadmin.entity.system;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {

	public SysRole() {
	}

	public SysRole(String roleName, String description, Set<SysModule> modules) {
		this.roleName = roleName;
		this.description = description;
		this.modules = modules;
	}

	private static final long serialVersionUID = 810764262328768993L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;// 主键标识

	@Column(nullable = false, length = 50)
	private String roleName;// 角色名称

	@Column(nullable = true)
	private String description;// 角色描述

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<SysUser> users = new LinkedHashSet<SysUser>();;// 角色关联的用户

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "relation_role_module", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "module_id"))	
	private Set<SysModule> modules = new LinkedHashSet<SysModule>();// 角色关联的权限

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}

	public Set<SysModule> getModules() {
		return modules;
	}

	public void setModules(Set<SysModule> modules) {
		this.modules = modules;
	}

}
