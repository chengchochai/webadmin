package com.jipengblog.webadmin.service.impl.weixin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jipengblog.webadmin.entity.weixin.MpMenu;
import com.jipengblog.webadmin.repository.BaseRepository;
import com.jipengblog.webadmin.repository.PageResults;
import com.jipengblog.webadmin.service.weixin.MpMenuService;

@Service("mpMenuService")
@Transactional
public class MpMenuServiceImpl implements MpMenuService {
	
	@Override
	public MpMenu findByMpMenuId(Long mpMenuId) {
		return baseRepository.getOneByHQL(
				"from MpMenu where mpMenuId = ?0", mpMenuId);
	}

	@Autowired
	private BaseRepository<MpMenu, Long> baseRepository;

	@Override
	public void save(MpMenu mpMenu) {
		baseRepository.save(mpMenu);
	}

	@Override
	public void update(MpMenu mpMenu) {
		baseRepository.update(mpMenu);
	}

	@Override
	public List<MpMenu> findAllFirstLevelMenu(Long mpAccountId) {
		if(mpAccountId == 0){//如果mpAccountId为0，查询所有的一级菜单
			return baseRepository.getListByHQL("from MpMenu where parentMpMenuId = 0 ");
		}else{
			return baseRepository.getListByHQL("from MpMenu where mpAccountId = ?0 and parentMpMenuId = 0 ",mpAccountId);
		}
	}

	@Override
	public List<MpMenu> findAllSecondLevelMenu(Long parentMpMenuId) {
		return baseRepository.getListByHQL("from MpMenu where parentMpMenuId = ?0", parentMpMenuId);
	}

	@Override
	public PageResults<MpMenu> findListByDetachedCriteria(DetachedCriteria dc,
			int pageNo, int pageSize) {
		return baseRepository.findPageByDetachedCriteria(dc, pageNo, pageSize);
	}

}
