package com.jipengblog.webadmin.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.system.SysUserService;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private BaseRepository<SysUser, Long> baseRepository;

	@Override
	public void save(SysUser user) {
		baseRepository.save(user);
	}

	@Override
	public void update(SysUser user) {
		baseRepository.update(user);
	}

	@Override
	public void delete(SysUser user) {
		baseRepository.delete(user);
	}

	@Override
	public List<SysUser> findAll() {
		return baseRepository.getListByHQL("from SysUser order by userId desc");
	}

	@Override
	public PageResults<SysUser> findListByPageResults(String hql,
			String countHql, int pageNo, int pageSize) {
		PageResults<SysUser> pageResults = baseRepository.findPageByFetchedHql(
				hql, countHql, pageNo, pageSize, null);
		return pageResults;
	}

	@Override
	public SysUser findByLoginName(String loginName) {
		return baseRepository.getOneByHQL("from SysUser where loginName = ?0",
				loginName);
	}

	@Override
	public SysUser findByEmail(String email) {
		return baseRepository.getOneByHQL("from SysUser where email = ?0",
				email);
	}

	@Override
	public SysUser findByMobile(String mobile) {
		return baseRepository.getOneByHQL("from SysUser where mobile = ?0",
				mobile);
	}

	@Override
	public SysUser findByUserId(Long userId) {
		return baseRepository.getOneByHQL("from SysUser where userId = ?0",
				userId);
	}
}
