package com.jipengblog.webadmin.service.impl.weixin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.weixin.MpAccount;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.weixin.MpAccountService;

@Service("mpAccountService")
@Transactional
public class MpAccountServiceImpl implements MpAccountService {
	
	@Override
	public MpAccount findByMpAccountId(Long mpAccountId) {
		return baseRepository.getOneByHQL(
				"from MpAccount where mpAccountId = ?0", mpAccountId);
	}

	@Autowired
	private BaseRepository<MpAccount, Long> baseRepository;

	@Override
	public void save(MpAccount mpAccount) {
		baseRepository.save(mpAccount);
	}

	@Override
	public void update(MpAccount mpAccount) {
		baseRepository.update(mpAccount);
	}

	@Override
	public MpAccount findByAppId(String appId) {
		return baseRepository.getOneByHQL("from MpAccount where appId = ?0", appId);
	}

	@Override
	public List<MpAccount> findAll() {
		return baseRepository.getListByHQL("from MpAccount");
	}

	@Override
	public PageResults<MpAccount> findListByDetachedCriteria(
			DetachedCriteria dc, int pageNo, int pageSize) {
		return baseRepository.findPageByDetachedCriteria(dc, pageNo, pageSize);
	}
}
