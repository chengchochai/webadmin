package com.jipengblog.webadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.system.SysResource;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.service.SysResourceService;

@Service
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private BaseRepository<SysResource, Long> baseRepository;

	@Override
	public void save(SysResource resource) {
		baseRepository.save(resource);
	}

	@Override
	public void update(SysResource resource) {
		baseRepository.update(resource);
	}

	@Override
	public List<SysResource> findAll() {
		return baseRepository
				.getListByHQL("from SysResource order by priority desc");
	}

	@Override
	public List<SysResource> findByResourceIds(Long[] ids) {
		StringBuffer paramSB = new StringBuffer();
		String paramString = "";
		if (ids != null && ids.length > 0) {
			for (Long id : ids) {
				paramSB.append(id + ",");
			}
			paramString = paramSB.substring(0, paramSB.length() - 1);
		}
		String hql = "from SysResource where resourceId in (" + paramString
				+ ") order by priority desc";
		return baseRepository.getListByHQL(hql);
	}

	@Override
	public SysResource findByResourceId(Long resourceId) {
		return baseRepository.getOneByHQL(
				"from SysResource where resourceId = ?0", resourceId);
	}

	@Override
	public void delete(SysResource resource) {
		baseRepository.delete(resource);
	}

}
