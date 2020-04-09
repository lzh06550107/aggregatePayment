package cn.liaozhonghao.www.service.account.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;
import cn.liaozhonghao.www.service.account.vo.DailyCollectAccountHistoryVo;

import java.util.List;
import java.util.Map;

/**
 * 账户历史dao
 */
public interface AccountHistoryDao extends BaseDao<AccountHistory> {

	List<AccountHistory> listPageByParams(Map<String, Object> params);
	
	List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params);

	/** 更新账户风险预存期外的账户历史记录记为结算完成 **/
	void updateCompleteSettTo100(Map<String, Object> params);
}