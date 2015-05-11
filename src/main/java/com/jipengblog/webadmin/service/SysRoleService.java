package com.jipengblog.webadmin.service;

import java.util.List;

import com.jipengblog.webadmin.entity.system.SysRole;

public interface SysRoleService {

	void save(SysRole role);

	void update(SysRole role);

	void delete(SysRole role);

	List<SysRole> findAll();

	SysRole findByRoleId(Long id);

	List<SysRole> findByRoleIds(Long[] ids);

}
