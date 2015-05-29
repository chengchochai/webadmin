package com.jipengblog.webadmin.service.weixin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.repository.PageResults;

public interface MpAccountService {
	
	/**
	 * 
	 * @param mpAccountId
	 * @return
	 */
	MpAccount findByMpAccountId(Long mpAccountId);

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
	 * @param appId
	 * @return
	 */
	MpAccount findByAppId(String appId);

	List<MpAccount> findAll();
	
	PageResults<MpAccount> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);

}
