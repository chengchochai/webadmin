package com.jipengblog.webadmin.service.impl.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.weixin.mp.MpAccount;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.service.weixin.MpAccountService;

@Service
@Transactional
public class MpAccountServiceImpl implements MpAccountService {

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
	public MpAccount findByMpAccountId(Long mpAccountId) {
		return baseRepository.getOneByHQL(
				"from MpAccount where accountId = ?0", mpAccountId);
	}

	@Override
	public List<MpAccount> findAll() {
		return baseRepository.getListByHQL("from MpAccount");
	}

}