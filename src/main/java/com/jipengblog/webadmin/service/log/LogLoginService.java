package com.jipengblog.webadmin.service.log;

import java.util.List;

import com.jipengblog.webadmin.entity.log.LogLogin;
import com.jipengblog.webadmin.repository.PageResults;

public interface LogLoginService {

	/**
	 * 添加登录日志
	 * 
	 * @param logLogin
	 */
	void save(LogLogin logLogin);
	
	/**
	 * 更新登录日志
	 * 
	 * @param logLogin
	 */
	void update(LogLogin logLogin);
	
	/**
	 * 通过sessionId查询登入日志
	 * @param sessionId
	 * @return
	 */
	LogLogin findBySessionId(String sessionId);

	/**
	 * 分页查询登录日志
	 * 
	 * @return
	 */
	PageResults<LogLogin> findListByPageResults(int pageNo, int pageSize);
	
	/**
	 * 查询所有的登录日志
	 * @return
	 */
	List<LogLogin> findAll();

	/**
	 * 该账户允许当天允许登录的次数
	 * 
	 * @param loginName
	 * @return
	 */
	int allowLoginTimes(String loginName);
	
	/**
	 * 系统结束时将所有用户下线
	 * @return
	 */
	int updateAllLogLogout();

}
