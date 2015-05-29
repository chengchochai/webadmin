package com.jipengblog.webadmin.service.weixin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jipengblog.webadmin.entity.weixin.MpMenu;
import com.jipengblog.webadmin.repository.PageResults;

public interface MpMenuService {
	
	/**
	 * 
	 * @param 查询所有一级菜单
	 * @return
	 */
	MpMenu findByMpMenuId(Long mpMenuId);

	/**
	 * 添加MpMenu
	 * 
	 * @param MpMenu
	 */
	void save(MpMenu mpMenu);

	/**
	 * 更新MpMenu
	 * 
	 * @param mpMenu
	 */
	void update(MpMenu mpMenu);

	/**
	 * 
	 * @return
	 */
	List<MpMenu> findAllFirstLevelMenu(Long mpAccountId);

	/**
	 * 根据一级菜单的mpMenuId的二级菜单
	 * 
	 * @param parentMpMenuId
	 * @return
	 */
	List<MpMenu> findAllSecondLevelMenu(Integer mpMenuId);
	
	PageResults<MpMenu> findListByDetachedCriteria(DetachedCriteria dc,int pageNo,int pageSize);
}
