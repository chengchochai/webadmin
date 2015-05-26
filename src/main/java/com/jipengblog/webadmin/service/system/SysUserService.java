package com.jipengblog.webadmin.service.system;

import java.util.List;

import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.repository.PageResults;

public interface SysUserService {

	void save(SysUser user);

	void update(SysUser user);

	void delete(SysUser user);

	List<SysUser> findAll();

	PageResults<SysUser> findListByPageResults(String hql, String countHql,
			int pageNo, int pageSize);

	SysUser findByLoginName(String loginName);

	SysUser findByEmail(String email);

	SysUser findByMobile(String mobile);

	SysUser findByUserId(Long userId);

}
