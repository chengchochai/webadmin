package com.jipengblog.webadmin.service.log;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

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
	 * 分页查询登陆日志
	 * @param hql 查询HQL
	 * @param countHql 记录数HQL
	 * @param pageNo 当前页
	 * @param pageSize 每页大小
	 * @return
	 */
	PageResults<LogLogin> findListByPageResults(String hql, String countHql, int pageNo, int pageSize);
	
	/**
	 * 分页查询登陆日志
	 * @param dc DetachedCriteria
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageResults<LogLogin> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);
	
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
