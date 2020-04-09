package cn.liaozhonghao.www.service.account.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.account.dao.AccountHistoryDao;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;
import cn.liaozhonghao.www.service.account.vo.DailyCollectAccountHistoryVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 账户历史dao实现类
 */
@Repository
public class AccountHistoryDaoImpl extends BaseDaoImpl<AccountHistory> implements AccountHistoryDao {
	
	public List<AccountHistory> listPageByParams(Map<String, Object> params){
		return this.listBy(params);
	}
	
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params){
		return this.getSessionTemplate().selectList(getStatement("listDailyCollectAccountHistoryVo"), params);
	}

	/** 更新账户风险预存期外的账户历史记录记为结算完成 **/
	public void updateCompleteSettTo100(Map<String, Object> params){
		this.getSessionTemplate().update(getStatement("updateCompleteSettTo100"), params);
	}
}