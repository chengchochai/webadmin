package com.jipengblog.webadmin.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.repository.PageResults;

public interface SysModuleService {

	void save(SysModule module);
	
	void update(SysModule module);

	void delete(SysModule module);
	
	List<SysModule> findAll();
	
	PageResults<SysModule> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);

	SysModule findByModuleId(Long moduleId);

	List<SysModule> findByModuleIds(Long[] ids);

}
