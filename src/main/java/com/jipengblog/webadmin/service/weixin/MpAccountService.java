package com.jipengblog.webadmin.service.weixin;

import java.util.List;

import com.jipengblog.webadmin.entity.weixin.mp.MpAccount;

public interface MpAccountService {

	/**
	 * 添加MpAccount
	 * 
	 * @param mpAccount
	 */
	void save(MpAccount mpAccount);

	/**
	 * 更新MpAccount
	 * 
	 * @param logLogin
	 */
	void update(MpAccount mpAccount);
	
	/**
	 * 
	 * @param mpAccountId
	 * @return
	 */
	MpAccount findByMpAccountId(Long mpAccountId);

	/**
	 * 查询所有的登录日志
	 * 
	 * @return
	 */
	List<MpAccount> findAll();

	

}
