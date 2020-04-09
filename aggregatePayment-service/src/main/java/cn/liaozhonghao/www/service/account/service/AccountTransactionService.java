package cn.liaozhonghao.www.service.account.service;

import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.service.account.entity.Account;

import java.math.BigDecimal;

/**
 *  账户交易操作service接口
 */
public interface AccountTransactionService {

	/** 加款:有银行流水 **/
	Account creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException;

	/** 减款 :有银行流水**/
	Account debitToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException;
	
	/** 加款 **/
	Account creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;

	/** 减款 **/
	Account debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;


	/** 冻结 **/
	Account freezeAmount(String userNo, BigDecimal freezeAmount) throws BizException;

	/** 结算成功：解冻+减款 **/
	Account unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;
	
	/** 结算失败：解冻 **/
	Account unFreezeSettAmount(String userNo, BigDecimal amount) throws BizException;

	/** 更新账户历史中的结算状态，并且累加可结算金额 **/
	void settCollectSuccess(String accountNo, String collectDate, int riskDay, BigDecimal totalAmount) throws BizException;

}