package com.jipengblog.webadmin.service.system;

import java.util.List;

import com.jipengblog.webadmin.entity.system.SysModule;

public interface SysModuleService {

	void save(SysModule module);
	
	void update(SysModule module);

	void delete(SysModule module);
	
	List<SysModule> findAll();

	SysModule findByModuleId(Long moduleId);

	List<SysModule> findByModuleIds(Long[] ids);

}
