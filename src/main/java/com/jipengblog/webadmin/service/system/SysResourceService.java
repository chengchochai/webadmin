package com.jipengblog.webadmin.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.repository.PageResults;

public interface SysResourceService {
	
	SysResource findByResourceId(Long resourceId);

	void save(SysResource resource);

	void update(SysResource resource);

	void delete(SysResource resource);

	List<SysResource> findAll();
	
	List<SysResource> findByResourceIds(Long[] ids);

	PageResults<SysResource> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);

}
