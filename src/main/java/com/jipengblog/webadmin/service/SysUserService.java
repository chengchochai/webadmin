package com.jipengblog.webadmin.service;

import java.util.List;

import com.jipengblog.webadmin.entity.system.SysUser;

public interface SysUserService {

	void save(SysUser user);

	void update(SysUser user);
	
	void delete(SysUser user);
	
	List<SysUser> findAll();

	SysUser findByLoginName(String loginName);

	SysUser findByEmail(String email);
	
	SysUser findByMobile(String mobile);

	SysUser findByUserId(Long userId);

}
