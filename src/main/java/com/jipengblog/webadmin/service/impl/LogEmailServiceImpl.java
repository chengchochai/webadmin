package com.jipengblog.webadmin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.log.LogEmail;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.service.LogEmailService;

@Service
@Transactional
public class LogEmailServiceImpl implements LogEmailService {

	@Autowired
	private BaseRepository<LogEmail, Long> baseRepository;

	@Override
	public void saveLogEmail(LogEmail logEmail) {
		baseRepository.save(logEmail);
	}

	@Override
	public LogEmail findLogEmailByKeyword(String keyword, Date deadline) {
		LogEmail logEmail = baseRepository.getOneByHQL(
				"from LogEmail where keyword = ?0 and deadline >= ?1", keyword,
				deadline);
		return logEmail;
	}

}
