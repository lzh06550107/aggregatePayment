package cn.liaozhonghao.www.service.trade.dao;


import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.trade.entity.TradePaymentOrder;

/**
 * 商户支付订单,dao层接口
 */
public interface RpTradePaymentOrderDao  extends BaseDao<TradePaymentOrder> {

    /**
     * 根据商户编号及商户订单号获取支付订单信息
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    TradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo);

}
