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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "sys_module")
public class SysModule implements Serializable {

	public SysModule() {
	}

	public SysModule(String moduleName, String description, Integer priority,
			String icon, Boolean enabled, Set<SysResource> resources) {
		this.moduleName = moduleName;
		this.description = description;
		this.priority = priority;
		this.icon = icon;
		this.enabled = enabled;
		this.resources = resources;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5387009972160937796L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long moduleId;// 主键标识

	@Column(nullable = false, length = 50)
	private String moduleName;// 模块名称

	@Column(nullable = true, length = 100)
	private String description;// 描述

	@Column(nullable = false)
	private Integer priority;// 优先级

	@Column(nullable = true, length = 50)
	private String icon;

	@Column(nullable = false)
	@Type(type = "yes_no")
	private Boolean enabled;// 是否可用

	@ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<SysRole> roles = new LinkedHashSet<SysRole>();// 模块关联的角色

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "relation_module_resource", joinColumns = @JoinColumn(name = "module_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
	private Set<SysResource> resources = new LinkedHashSet<SysResource>();// 模块关联的资源

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<SysResource> getResources() {
		return resources;
	}

	public void setResources(Set<SysResource> resources) {
		this.resources = resources;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

}
