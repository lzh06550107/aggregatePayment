package cn.liaozhonghao.www.service.trade.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.trade.entity.TradePaymentRecord;

import java.util.List;
import java.util.Map;

/**
 * 商户支付记录,dao层接口
 */
public interface RpTradePaymentRecordDao extends BaseDao<TradePaymentRecord> {

    /**
     * 根据银行订单号获取支付信息
     * @param bankOrderNo
     * @return
     */
    TradePaymentRecord getByBankOrderNo(String bankOrderNo);

    /**
     * 根据商户编号及商户订单号获取支付成功的结果
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    TradePaymentRecord getSuccessRecordByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo);

    /**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	TradePaymentRecord getByTrxNo(String trxNo);

	List<Map<String, String>> getPaymentReport(String merchantNo);

	List<Map<String, String>> getPayWayReport(String merchantNo);

}
