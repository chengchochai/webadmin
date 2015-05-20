package com.jipengblog.webadmin.service.weixin;

import java.util.List;

import com.jipengblog.webadmin.entity.weixin.MpMenu;

public interface MpMenuService {

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
	 * @param 查询所有一级菜单
	 * @return
	 */
	MpMenu findByMpMenuId(Long mpMenuId);

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

}
