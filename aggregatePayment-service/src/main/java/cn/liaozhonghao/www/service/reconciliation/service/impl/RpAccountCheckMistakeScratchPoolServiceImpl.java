package cn.liaozhonghao.www.service.reconciliation.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.reconciliation.dao.RpAccountCheckMistakeScratchPoolDao;
import cn.liaozhonghao.www.service.reconciliation.entity.RpAccountCheckMistakeScratchPool;
import cn.liaozhonghao.www.service.reconciliation.service.RpAccountCheckMistakeScratchPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账暂存池接口实现 .
 */
@Service("rpAccountCheckMistakeScratchPoolService")
public class RpAccountCheckMistakeScratchPoolServiceImpl implements RpAccountCheckMistakeScratchPoolService {

	@Autowired
	private RpAccountCheckMistakeScratchPoolDao rpAccountCheckMistakeScratchPoolDao;

	@Override
	public void saveData(RpAccountCheckMistakeScratchPool RpAccountCheckMistakeScratchPool) {
		rpAccountCheckMistakeScratchPoolDao.insert(RpAccountCheckMistakeScratchPool);
	}

	/**
	 * 批量保存记录
	 * 
	 * @param scratchPoolList
	 */
	public void savaListDate(List<RpAccountCheckMistakeScratchPool> scratchPoolList) {
		for (RpAccountCheckMistakeScratchPool record : scratchPoolList) {
			rpAccountCheckMistakeScratchPoolDao.insert(record);
		}
	}

	@Override
	public void updateData(RpAccountCheckMistakeScratchPool RpAccountCheckMistakeScratchPool) {
		rpAccountCheckMistakeScratchPoolDao.update(RpAccountCheckMistakeScratchPool);
	}

	@Override
	public RpAccountCheckMistakeScratchPool getDataById(String id) {
		return rpAccountCheckMistakeScratchPoolDao.getById(id);
	}

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, RpAccountCheckMistakeScratchPool rpAccountCheckMistakeScratchPool) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		return rpAccountCheckMistakeScratchPoolDao.listPage(pageParam, paramMap);
	}

	/**
	 * 从缓冲池中删除数据
	 * 
	 * @param scratchPoolList
	 */
	public void deleteFromPool(List<RpAccountCheckMistakeScratchPool> scratchPoolList) {
		for (RpAccountCheckMistakeScratchPool record : scratchPoolList) {
			rpAccountCheckMistakeScratchPoolDao.delete(record.getId());
		}

	}

	/**
	 * 查询出缓存池中所有的数据
	 * 
	 * @return
	 */
	public List<RpAccountCheckMistakeScratchPool> listScratchPoolRecord(Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return rpAccountCheckMistakeScratchPoolDao.listByColumn(paramMap);
	}
}