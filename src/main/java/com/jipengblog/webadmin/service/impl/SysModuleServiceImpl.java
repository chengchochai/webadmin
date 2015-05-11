package com.jipengblog.webadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.service.SysModuleService;

@Service
@Transactional
public class SysModuleServiceImpl implements SysModuleService {

	@Autowired
	private BaseRepository<SysModule, Long> baseRepository;

	@Override
	public void save(SysModule module) {
		baseRepository.save(module);
	}

	@Override
	public void update(SysModule module) {
		baseRepository.update(module);
	}

	@Override
	public void delete(SysModule sysModule) {
		baseRepository.delete(sysModule);
	}

	@Override
	public SysModule findByModuleId(Long moduleId) {
		return baseRepository.getOneByHQL("from SysModule where moduleId=?0",
				moduleId);
	}

	@Override
	public List<SysModule> findAll() {
		return baseRepository
				.getListByHQL("from SysModule order by priority desc");
	}

	@Override
	public List<SysModule> findByModuleIds(Long[] ids) {
		StringBuffer paramSB = new StringBuffer();
		String paramString = "";
		if (ids != null && ids.length > 0) {
			for (Long id : ids) {
				paramSB.append(id + ",");
			}
			paramString = paramSB.substring(0, paramSB.length() - 1);
		}
		String hql = "from SysModule where moduleId in (" + paramString
				+ ") order by priority desc";
		return baseRepository.getListByHQL(hql);
	}
}
