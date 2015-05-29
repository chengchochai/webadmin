package com.jipengblog.webadmin.service.impl.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.system.SysModule;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.system.SysModuleService;

@Service
@Transactional
public class SysModuleServiceImpl implements SysModuleService {

	@Autowired
	private BaseRepository<SysModule, Long> baseRepository;

	@Override
	public SysModule findByModuleId(Long moduleId) {
		return baseRepository.getOneByHQL("from SysModule where moduleId=?0",
				moduleId);
	}

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

	@Override
	public PageResults<SysModule> findListByDetachedCriteria(
			DetachedCriteria dc, int pageNo, int pageSize) {
		return baseRepository.findPageByDetachedCriteria(dc, pageNo, pageSize);
	}

}
