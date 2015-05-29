package com.jipengblog.webadmin.service.system;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.system.SysUser;
import com.jipengblog.webadmin.repository.PageResults;

public interface SysUserService {
	
	SysUser findByUserId(Long userId);
	
	SysUser findByLoginName(String loginName);

	SysUser findByEmail(String email);

	SysUser findByMobile(String mobile);

	void save(SysUser user);

	void update(SysUser user);

	void delete(SysUser user);

	List<SysUser> findAll();

	PageResults<SysUser> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);

	

	

}
