package com.jipengblog.webadmin.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.system.SysRole;
import com.jipengblog.webadmin.repository.PageResults;

public interface SysRoleService {
	
	SysRole findByRoleId(Long id);

	void save(SysRole role);

	void update(SysRole role);

	void delete(SysRole role);

	List<SysRole> findAll();
	
	List<SysRole> findByRoleIds(Long[] ids);
	
	PageResults<SysRole> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);

}
