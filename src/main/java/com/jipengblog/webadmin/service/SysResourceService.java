package com.jipengblog.webadmin.service;

import java.util.List;

import com.jipengblog.webadmin.entity.system.SysResource;

public interface SysResourceService {

	void save(SysResource resource);

	void update(SysResource resource);

	void delete(SysResource resource);

	List<SysResource> findAll();

	List<SysResource> findByResourceIds(Long[] ids);

	SysResource findByResourceId(Long resourceId);

}
