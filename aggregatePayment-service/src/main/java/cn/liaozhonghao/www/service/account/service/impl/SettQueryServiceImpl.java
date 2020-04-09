package cn.liaozhonghao.www.service.account.service.impl;

import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.account.dao.SettDailyCollectDao;
import cn.liaozhonghao.www.service.account.dao.SettRecordDao;
import cn.liaozhonghao.www.service.account.entity.SettRecord;
import cn.liaozhonghao.www.service.account.service.SettQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 结算查询service实现类
 */
@Service("rpSettQueryService")
public class SettQueryServiceImpl implements SettQueryService {

	@Autowired
	private SettRecordDao rpSettRecordDao;
	@Autowired
	private SettDailyCollectDao settDailyCollectMapper;

	/**
	 * 根据参数分页查询结算记录
	 * 
	 * @param pageParam
	 * @param params
	 *            ：map的key为 ：accountNo、userNo、settStatus...可以参考实体
	 * 
	 * @return
	 */
	public PageBean querySettRecordListPage(PageParam pageParam, Map<String, Object> params) throws BizException {
		return rpSettRecordDao.listPage(pageParam, params);
	}

	/**
	 * 根据参数分页查询结算日汇总记录
	 * 
	 * @param pageParam
	 * @param params
	 *            ：map的key为 ：accountNo...可以参考实体
	 * 
	 * @return
	 * @throws BizException
	 */
	public PageBean querySettDailyCollectListPage(PageParam pageParam, Map<String, Object> params) throws BizException {
		return settDailyCollectMapper.listPage(pageParam, params);
	}
	
	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	public SettRecord getDataById(String id){
		return rpSettRecordDao.getById(id);
	}
}
