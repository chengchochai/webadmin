package com.jipengblog.webadmin.service.impl.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.system.SysRole;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.system.SysRoleService;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Override
	public SysRole findByRoleId(Long id) {
		return baseRepository.getOneByHQL("from SysRole where roleId = ?0", id);
	}

	@Autowired
	private BaseRepository<SysRole, Long> baseRepository;

	@Override
	public void save(SysRole role) {
		baseRepository.save(role);
	}

	@Override
	public void update(SysRole role) {
		baseRepository.update(role);
	}

	@Override
	public void delete(SysRole role) {
		baseRepository.delete(role);
	}

	@Override
	public List<SysRole> findAll() {
		return baseRepository.getListByHQL("from SysRole order by roleId desc");
	}

	@Override
	public List<SysRole> findByRoleIds(Long[] ids) {
		StringBuffer paramSB = new StringBuffer();
		String paramString = "";
		if (ids != null && ids.length > 0) {
			for (Long id : ids) {
				paramSB.append(id + ",");
			}
			paramString = paramSB.substring(0, paramSB.length() - 1);
		}
		String hql = "from SysRole where roleId in (" + paramString + ")";
		return baseRepository.getListByHQL(hql);
	}

	@Override
	public PageResults<SysRole> findListByDetachedCriteria(DetachedCriteria dc,
			int pageNo, int pageSize) {
		return baseRepository.findPageByDetachedCriteria(dc, pageNo, pageSize);
	}

}
