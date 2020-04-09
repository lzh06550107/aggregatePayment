package cn.liaozhonghao.www.service.trade.vo;

import cn.liaozhonghao.www.common.core.enums.PayTypeEnum;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 龙果支付网关页面展示实体
 */
public class RpPayGateWayPageShowVo {

    /** 订单金额 **/
    private BigDecimal orderAmount;

    /** 产品名称 **/
    private String productName;

    /** 商户名称 **/
    private String merchantName;

    /** 商户订单号 **/
    private String merchantOrderNo;

    /** 商户支付key **/
    private String payKey;

    /** 支付方式列表 **/
    private Map<String , PayTypeEnum> payTypeEnumMap;

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public Map<String, PayTypeEnum> getPayTypeEnumMap() {
        return payTypeEnumMap;
    }

    public void setPayTypeEnumMap(Map<String, PayTypeEnum> payTypeEnumMap) {
        this.payTypeEnumMap = payTypeEnumMap;
    }
}
