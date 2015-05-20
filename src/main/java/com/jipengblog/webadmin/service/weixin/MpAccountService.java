package com.jipengblog.webadmin.service.weixin;

import java.util.List;

import com.jipengblog.webadmin.entity.weixin.MpAccount;

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
	 * 
	 * @param appId
	 * @return
	 */
	MpAccount findByAppId(String appId);

	List<MpAccount> findAll();

}
