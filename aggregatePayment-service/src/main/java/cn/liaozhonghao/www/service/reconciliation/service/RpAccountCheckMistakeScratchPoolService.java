package cn.liaozhonghao.www.service.reconciliation.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.reconciliation.entity.RpAccountCheckMistakeScratchPool;

import java.util.List;
import java.util.Map;

/**
 * 对账暂存池接口 .
 */
public interface RpAccountCheckMistakeScratchPoolService {

	/**
	 * 保存
	 */
	void saveData(RpAccountCheckMistakeScratchPool rpAccountCheckMistakeScratchPool);

	/**
	 * 批量保存记录
	 * 
	 * @param scratchPoolList
	 */
	public void savaListDate(List<RpAccountCheckMistakeScratchPool> scratchPoolList);

	/**
	 * 更新
	 */
	void updateData(RpAccountCheckMistakeScratchPool rpAccountCheckMistakeScratchPool);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpAccountCheckMistakeScratchPool getDataById(String id);

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpAccountCheckMistakeScratchPool rpAccountCheckMistakeScratchPool);

	/**
	 * 从缓冲池中删除数据
	 * 
	 * @param scratchPoolList
	 */
	void deleteFromPool(List<RpAccountCheckMistakeScratchPool> scratchPoolList);

	/**
	 * 查询出缓存池中所有的数据
	 * 
	 * @return
	 */
	List<RpAccountCheckMistakeScratchPool> listScratchPoolRecord(Map<String, Object> paramMap);

}