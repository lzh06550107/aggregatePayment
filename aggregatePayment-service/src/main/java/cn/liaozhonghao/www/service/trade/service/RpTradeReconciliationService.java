package cn.liaozhonghao.www.service.trade.service;

import cn.liaozhonghao.www.service.reconciliation.entity.RpAccountCheckMistake;

/**
 * 交易模块对账差错处理接口
 */
public interface RpTradeReconciliationService {

	/**
	 * 平台成功，银行记录不存在，或者银行失败，以银行为准
	 * 
	 * @param trxNo
	 *            平台交易流水
	 */
	public void bankMissOrBankFailBaseBank(String trxNo);

	/**
	 * 银行成功，平台失败。
	 * 
	 * @param trxNo
	 *            平台交易流水
	 * @param bankTrxNo
	 *            银行返回流水
	 */
	public void platFailBankSuccess(String trxNo, String bankTrxNo);

	/**
	 * 处理金额不匹配异常(都是以银行数据为准才需要调整)
	 * 
	 * @param mistake
	 *            差错记录
	 * @param isBankMore
	 *            是否是银行金额多
	 */
	public void handleAmountMistake(RpAccountCheckMistake mistake, boolean isBankMore) ;

	/**
	 * 处理手续费不匹配差错（默认以银行为准）
	 * 
	 * @param mistake
	 */
	public void handleFeeMistake(RpAccountCheckMistake mistake);

}
