package com.jipengblog.webadmin.service.impl.log;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.constant.LogoutType;
import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.log.LogLoginService;
import com.jipengblog.webadmin.utils.time.DateUtils;
import com.jipengblog.webadmin.web.common.WebCons;

@Service("logLoginService")
@Transactional
public class LogLoginServiceImpl implements LogLoginService {

	@Autowired
	private BaseRepository<LogLogin, Long> baseRepository;

	@Override
	public void save(LogLogin logLogin) {
		baseRepository.save(logLogin);
	}

	@Override
	public void update(LogLogin logLogin) {
		baseRepository.update(logLogin);
	}

	@Override
	public LogLogin findBySessionId(String sessionId) {
		return baseRepository.getOneByHQL("from LogLogin where sessionId = ?0",
				sessionId);
	}

	@Override
	public PageResults<LogLogin> findListByPageResults(String hql,
			String countHql, int pageNo, int pageSize) {
		PageResults<LogLogin> pageResults = baseRepository
				.findPageByFetchedHql(hql, countHql, pageNo, pageSize, null);
		return pageResults;
	}

	@Override
	public PageResults<LogLogin> findListByDetachedCriteria(
			DetachedCriteria dc, int pageNo, int pageSize) {
		PageResults<LogLogin> pageResults = baseRepository
				.findPageByDetachedCriteria(dc, pageNo, pageSize);
		return pageResults;
	}

	@Override
	public List<LogLogin> findAll() {
		return baseRepository
				.getListByHQL("from LogLogin order by loginTime desc");
	}

	@Override
	public int allowLoginTimes(String loginName) {
		Long errorTimes = baseRepository
				.countByHql(
						"select count(*) from LogLogin where successful = ?0 and loginTime >= ?1 and loginName = ?2",
						Boolean.FALSE, DateUtils.getZeroOfToday(), loginName);
		if (errorTimes == null) {
			errorTimes = 0l;
		}
		return WebCons.PASSWORD_ERROR_TIMES - errorTimes.intValue();
	}

	@Override
	public int updateAllLogLogout() {
		return baseRepository
				.queryHql(
						"update LogLogin set logoutTime = ?0, logoutType=?1 where logoutTime is null and logoutType is null",
						new Date(), LogoutType.SYSTEMSHUTDOWN);

	}

}
