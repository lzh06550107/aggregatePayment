package cn.liaozhonghao.www.service.account.service.impl;

import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.common.core.utils.DateUtils;
import cn.liaozhonghao.www.service.account.dao.AccountDao;
import cn.liaozhonghao.www.service.account.dao.AccountHistoryDao;
import cn.liaozhonghao.www.service.account.entity.Account;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;
import cn.liaozhonghao.www.service.account.exception.AccountBizException;
import cn.liaozhonghao.www.service.account.service.AccountQueryService;
import cn.liaozhonghao.www.service.account.vo.DailyCollectAccountHistoryVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户查询service实现类
 */
@Service("rpAccountQueryService")
public class AccountQueryServiceImpl implements AccountQueryService {

	@Autowired
	private AccountDao rpAccountDao;
	@Autowired
	private AccountHistoryDao rpAccountHistoryDao;

	private static final Logger LOG = LoggerFactory.getLogger(AccountQueryServiceImpl.class);

	/**
	 * 根据账户编号获取账户信息
	 * 
	 * @param accountNo
	 *            账户编号
	 * @return
	 */
	public Account getAccountByAccountNo(String accountNo) {
		LOG.info("根据账户编号查询账户信息");
		Account account = this.rpAccountDao.getByAccountNo(accountNo);
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(account.getEditTime())) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setEditTime(new Date());
			rpAccountDao.update(account);
		}
		return account;
	}

	/**
	 * 根据用户编号编号获取账户信息
	 * 
	 * @param userNo
	 *            用户编号
	 * @return
	 */
	public Account getAccountByUserNo(String userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		LOG.info("根据用户编号查询账户信息");
		Account account = this.rpAccountDao.getBy(map);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(account.getEditTime())) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setEditTime(new Date());
			rpAccountDao.update(account);
		}
		return account;
	}

	/**
	 * 分页查询账户历史单用户
	 */
	public PageBean queryAccountHistoryListPage(PageParam pageParam, String accountNo){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		return rpAccountDao.listPage(pageParam, params);
	}

	/**
	 * 分页查询账户历史单角色
	 */
	public PageBean queryAccountHistoryListPageByRole(PageParam pageParam, Map<String, Object> params){
		String accountType = (String) params.get("accountType");
		if (StringUtils.isBlank(accountType)) {
			throw AccountBizException.ACCOUNT_TYPE_IS_NULL;
		}
		return rpAccountDao.listPage(pageParam, params);

	}

	/**
	 * 获取账户历史单角色
	 * 
	 * @param accountNo
	 *            账户编号
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @return AccountHistory
	 */
	public AccountHistory getAccountHistoryByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
		map.put("requestNo", requestNo);
		map.put("trxType", trxType);
		return rpAccountHistoryDao.getBy(map);
	}

	/**
	 * 日汇总账户待结算金额 .
	 * 
	 * @param accountNo
	 *            账户编号
	 * @param statDate
	 *            统计日期
	 * @param riskDay
	 *            风险预测期
	 * @param fundDirection
	 *            资金流向
	 * @return
	 */
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay, Integer fundDirection) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("statDate", statDate);
		params.put("riskDay", riskDay);
		params.put("fundDirection", fundDirection);
		return rpAccountHistoryDao.listDailyCollectAccountHistoryVo(params);

	}

	/**
	 * 根据参数分页查询账户.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountList.
	 */
	public PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) {

		return rpAccountDao.listPage(pageParam, params);
	}

	/**
	 * 根据参数分页查询账户历史.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountHistoryList.
	 */
	public PageBean queryAccountHistoryListPage(PageParam pageParam, Map<String, Object> params) {

		return rpAccountHistoryDao.listPage(pageParam, params);
	}
	
    /**
	 * 获取所有账户
	 * @return
	 */
    @Override
    public List<Account> listAll(){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpAccountDao.listBy(paramMap);
	}

}