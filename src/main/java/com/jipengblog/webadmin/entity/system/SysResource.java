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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "sys_resource")
public class SysResource implements Serializable {

	public SysResource() {
	}

	public SysResource(String resourceName, String description,
			String resourceUri, Integer priority, Boolean enabled) {
		this.resourceName = resourceName;
		this.description = description;
		this.resourceUri = resourceUri;
		this.priority = priority;
		this.enabled = enabled;
	}

	private static final long serialVersionUID = -2445371099656262721L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long resourceId;// 主键标识

	@Column(nullable = false, length = 50)
	private String resourceName;// 资源名称

	@Column(nullable = true, length = 100)
	private String description;// 描述

	@Column(nullable = false, length = 100)
	private String resourceUri;// 资源对应的URI

	@Column(nullable = false)
	private Integer priority;// 优先级

	@Column(nullable = false)
	@Type(type = "yes_no")
	private Boolean enabled;// 是否可用

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<SysModule> modules = new LinkedHashSet<SysModule>();// 模块关联的角色

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<SysModule> getModules() {
		return modules;
	}

	public void setModules(Set<SysModule> modules) {
		this.modules = modules;
	}

}
