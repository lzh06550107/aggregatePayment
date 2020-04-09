package cn.liaozhonghao.www.service.trade.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.trade.dao.RpTradePaymentRecordDao;
import cn.liaozhonghao.www.service.trade.entity.TradePaymentRecord;
import cn.liaozhonghao.www.service.trade.enums.TradeStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商户支付记录,dao层实现类
 */
@Repository("rpTradePaymentRecordDao")
public class RpTradePaymentRecordDaoImpl extends BaseDaoImpl<TradePaymentRecord> implements RpTradePaymentRecordDao {

	/**
	 * 根据银行订单号获取支付信息
	 *
	 * @param bankOrderNo
	 * @return
	 */
	@Override
	public TradePaymentRecord getByBankOrderNo(String bankOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bankOrderNo", bankOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * 根据商户编号及商户订单号获取支付结果
	 *
	 * @param merchantNo
	 * @param merchantOrderNo
	 * @return
	 */
	@Override
	public TradePaymentRecord getSuccessRecordByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		paramMap.put("merchantOrderNo", merchantOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	public TradePaymentRecord getByTrxNo(String trxNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trxNo", trxNo);
		return super.getBy(paramMap);
	}
	
	public List<Map<String, String>> getPaymentReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSessionTemplate().selectList(getStatement("getPaymentReport"),paramMap);
	}

	public List<Map<String, String>> getPayWayReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSessionTemplate().selectList(getStatement("getPayWayReport"),paramMap);
	}

}
