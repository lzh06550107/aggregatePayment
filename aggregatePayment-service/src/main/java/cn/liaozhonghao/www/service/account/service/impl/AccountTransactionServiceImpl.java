package cn.liaozhonghao.www.service.account.service.impl;

import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.utils.DateUtils;
import cn.liaozhonghao.www.common.core.utils.StringUtil;
import cn.liaozhonghao.www.service.account.dao.AccountDao;
import cn.liaozhonghao.www.service.account.dao.AccountHistoryDao;
import cn.liaozhonghao.www.service.account.entity.Account;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;
import cn.liaozhonghao.www.service.account.enums.AccountFundDirectionEnum;
import cn.liaozhonghao.www.service.account.exception.AccountBizException;
import cn.liaozhonghao.www.service.account.service.AccountTransactionService;
import cn.liaozhonghao.www.service.trade.enums.TrxTypeEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户操作service实现类
 */
@Service("rpAccountTransactionService")
public class AccountTransactionServiceImpl implements AccountTransactionService {
	
	private static final Log LOG = LogFactory.getLog(AccountTransactionServiceImpl.class);
	
	@Autowired
	private AccountDao rpAccountDao;
	@Autowired
	private AccountHistoryDao rpAccountHistoryDao;
	
	

	/**
	 * 根据用户编号编号获取账户信息
	 * 
	 * @param userNo
	 *            用户编号
	 * @param isPessimist
	 *            是否加行锁
	 * @return
	 */
	private Account getByUserNo_IsPessimist(String userNo, boolean isPessimist) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("isPessimist", isPessimist);
		return rpAccountDao.getByUserNo(map);
	}

	/**
	 * 加款
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            加款金额
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public Account creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark){

		return this.creditToAccount(userNo, amount, requestNo, null, trxType, remark);
	}

	/**
	 * 加款:有银行流水
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            加款金额
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public Account creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) {
		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
		}

		// 总收益累加和今日收益
		if (TrxTypeEnum.EXPENSE.name().equals(trxType)) {// 业务类型是交易
			account.setTotalIncome(account.getTotalIncome().add(amount));

			/***** 根据上次修改时间，统计今日收益 *******/
			if (DateUtils.isSameDayWithToday(lastModifyDate)) {
				// 如果是同一天
				account.setTodayIncome(account.getTodayIncome().add(amount));
			} else {
				// 不是同一天
				account.setTodayIncome(amount);
			}
			/************************************/
		}

		String completeSett = PublicEnum.NO.name();
		String isAllowSett = PublicEnum.YES.name();

		/** 设置余额的值 **/
		account.setBalance(account.getBalance().add(amount));
		account.setEditTime(new Date());

		// 记录账户历史
		AccountHistory accountHistoryEntity = new AccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setBankTrxNo(bankTrxNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setId(StringUtil.get32UUID());
		accountHistoryEntity.setUserNo(userNo);

		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		LOG.info("账户加款成功，并记录了账户历史");
		return account;
	}

	/**
	 * 减款
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            减款金额
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public Account debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark){
		return this.debitToAccount(userNo, amount, requestNo, null, trxType, remark);
	}

	/**
	 * 减款:有银行流水
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            减款金额
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public Account debitToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) {
		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		// 获取可用余额
		BigDecimal availableBalance = account.getAvailableBalance();

		String isAllowSett = PublicEnum.YES.name();
		String completeSett = PublicEnum.NO.name();

		if (availableBalance.compareTo(amount) == -1) {
			throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
		}

		/** 减少总余额 **/
		account.setBalance(account.getBalance().subtract(amount));

		Date lastModifyDate = account.getEditTime();
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setTodayExpend(amount);
		}else{
			account.setTodayExpend(account.getTodayExpend().add(amount));
		}
		account.setTotalExpend(account.getTodayExpend().add(amount));
		account.setEditTime(new Date());

		// 记录账户历史
		AccountHistory accountHistoryEntity = new AccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setBankTrxNo(bankTrxNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setId(StringUtil.get32UUID());
		accountHistoryEntity.setUserNo(userNo);
		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 冻结账户资金
	 * 
	 * @param userNo
	 *            用户编号
	 * @param freezeAmount
	 *            冻结金额
	 **/

	@Transactional(rollbackFor = Exception.class)
	public Account freezeAmount(String userNo, BigDecimal freezeAmount) {
		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		account.setEditTime(new Date());
		// 比较可用余额和冻结金额
		if (!account.availableBalanceIsEnough(freezeAmount)) {
			// 可用余额不足
			throw AccountBizException.ACCOUNT_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setUnbalance(account.getUnbalance().add(freezeAmount));
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 结算成功 解冻金额+减款
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            解冻和减款金额
	 * @param requestNo
	 *            流水号
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */

	@Transactional(rollbackFor = Exception.class)
	public Account unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) {
		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setTodayExpend(amount);
		}else{
			account.setTodayExpend(account.getTodayExpend().add(amount));
		}
		account.setTotalExpend(account.getTodayExpend().add(amount));

		// 判断解冻金额是否充足
		if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
			// 解冻金额超限
			throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setEditTime(new Date());
		account.setBalance(account.getBalance().subtract(amount));// 减款
		account.setUnbalance(account.getUnbalance().subtract(amount));// 解冻
		account.setSettAmount(account.getSettAmount().subtract(amount));// 减少可结算金额

		String isAllowSett = PublicEnum.NO.name();
		String completeSett = PublicEnum.NO.name();
		// 记录账户历史
		AccountHistory accountHistoryEntity = new AccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setUserNo(userNo);
		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 结算失败 解冻金额
	 * 
	 * @param userNo
	 *            用户编号
	 * @param amount
	 *            解冻和减款金额
	 */

	@Transactional(rollbackFor = Exception.class)
	public Account unFreezeSettAmount(String userNo, BigDecimal amount) {
		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
		}

		// 判断解冻金额是否充足
		if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
			// 解冻金额超限
			throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setEditTime(new Date());
		account.setUnbalance(account.getUnbalance().subtract(amount));// 解冻

		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 更新账户历史中的结算状态，并且累加可结算金额
	 * 
	 * @param userNo
	 *            用户编号
	 * @param collectDate
	 *            汇总日期
	 * @param riskDay
	 *            风险预存期
	 * @param totalAmount
	 *            可结算金额累计
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void settCollectSuccess(String userNo, String collectDate, int riskDay, BigDecimal totalAmount) {

		LOG.info("==>settCollectSuccess");
		LOG.info(String.format("==>userNo:%s, collectDate:%s, riskDay:%s", userNo, collectDate, riskDay));

		Account account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户不存在,用户编号{%s}", userNo).print();
		}
		// 更新账户历史状态
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", account.getAccountNo());
		params.put("statDate", collectDate);
		params.put("riskDay", riskDay);
		rpAccountHistoryDao.updateCompleteSettTo100(params);

		// 账户可结算金额的累加
		account.setSettAmount(account.getSettAmount().add(totalAmount));
		rpAccountDao.update(account);
		LOG.info("==>settCollectSuccess<==");
	}
}