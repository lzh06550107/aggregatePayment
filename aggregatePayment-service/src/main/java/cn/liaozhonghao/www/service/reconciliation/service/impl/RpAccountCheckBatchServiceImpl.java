package cn.liaozhonghao.www.service.reconciliation.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.reconciliation.dao.RpAccountCheckBatchDao;
import cn.liaozhonghao.www.service.reconciliation.entity.RpAccountCheckBatch;
import cn.liaozhonghao.www.service.reconciliation.service.RpAccountCheckBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 对账批次接口实现 .
 */
@Service("rpAccountCheckBatchService")
public class RpAccountCheckBatchServiceImpl implements RpAccountCheckBatchService {

	@Autowired
	private RpAccountCheckBatchDao rpAccountCheckBatchDao;

	@Override
	public void saveData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.insert(rpAccountCheckBatch);
	}

	@Override
	public void updateData(RpAccountCheckBatch rpAccountCheckBatch) {
		rpAccountCheckBatchDao.update(rpAccountCheckBatch);
	}

	@Override
	public RpAccountCheckBatch getDataById(String id) {
		return rpAccountCheckBatchDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {

		return rpAccountCheckBatchDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据条件查询实体
	 * 
	 * @param paramMap
	 */
	public List<RpAccountCheckBatch> listBy(Map<String, Object> paramMap) {
		return rpAccountCheckBatchDao.listBy(paramMap);
	}

}