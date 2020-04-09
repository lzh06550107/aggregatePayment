package cn.liaozhonghao.www.service.trade.service;

import cn.liaozhonghao.www.service.trade.bo.F2FPayRequestBo;
import cn.liaozhonghao.www.service.trade.bo.ProgramPayRequestBo;
import cn.liaozhonghao.www.service.trade.bo.ScanPayRequestBo;
import cn.liaozhonghao.www.service.trade.vo.*;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 交易模块管理接口
 */
public interface RpTradePaymentManagerService {

    /**
     * 初始化直连扫码支付数据,直连扫码支付初始化方法规则
     * 1:根据(商户编号 + 商户订单号)确定订单是否存在
     * 1.1:如果订单不存在,创建支付订单
     * 2:创建支付记录
     * 3:根据相应渠道方法
     * 4:调转到相应支付渠道扫码界面
     **/

    public ScanPayResultVo initDirectScanPay(UserPayConfig rpUserPayConfig, ScanPayRequestBo scanPayRequestBo);


    /**
     * 条码支付,对应的是支付宝的条码支付或者微信的刷卡支付
     *
     * @return
     */
    public F2FPayResultVo f2fPay(UserPayConfig rpUserPayConfig, F2FPayRequestBo f2FPayRequestBo);

    /**
     * 完成扫码支付(支付宝即时到账支付)
     *
     * @param payWayCode
     * @param notifyMap
     * @return
     */
    public String completeScanPay(String payWayCode, Map<String, String> notifyMap);

    /**
     * @param payWayCode
     * @param resultMap
     * @return
     */
    public OrderPayResultVo completeScanPayByResult(String payWayCode, Map<String, String> resultMap);


    /**
     * 初始化非直连扫码支付数据,非直连扫码支付初始化方法规则
     *      1:根据(商户编号 + 商户订单号)确定订单是否存在
     *       1.1:如果订单不存在,创建支付订单
     *      2:获取商户支付配置,跳转到支付网关,选择支付方式
     * @param rpUserPayConfig
     * @param scanPayRequestBo
     * @return
     */
    public RpPayGateWayPageShowVo initNonDirectScanPay(UserPayConfig rpUserPayConfig, ScanPayRequestBo scanPayRequestBo);

    /**
     * 非直连扫码支付,选择支付方式后,去支付
     *
     * @param payKey
     * @param orderNo
     * @param payType
     * @param numberOfStages
     * @return
     */
    public ScanPayResultVo toNonDirectScanPay(String payKey, String orderNo, String payType, Integer numberOfStages);

    /**
     * 处理交易记录
     * 如果交易记录是成功或者本地未支付,查询上游已支付,返回TRUE
     * 如果上游支付结果为未支付,返回FALSE
     *
     * @param bankOrderNo 银行订单号
     * @return
     */
    public boolean processingTradeRecord(String bankOrderNo);


    /** 小程序支付
     * @return
     */
    ProgramPayResultVo programPay(UserPayConfig rpUserPayConfig, ProgramPayRequestBo programPayRequestBo);

    /**
     * 初始化鉴权
     *
     * @param productName
     * @param orderPrice
     * @param orderIp
     * @param paramVo
     * @return
     */
    AuthInitResultVo initDirectAuth(String productName, BigDecimal orderPrice, String orderIp, AuthParamVo paramVo, UserPayConfig userPayConfig);

    /**
     * 初始化小程序鉴权
     *
     * @param productName
     * @param orderPrice
     * @param orderIp
     * @param paramVo
     * @return
     */
    AuthProgramInitResultVo initProgramDirectAuth(String productName, BigDecimal orderPrice, String orderIp, AuthProgramInitParamVo paramVo, UserPayConfig userPayConfig);

    /**
     * 用户鉴权
     *
     * @param merchantNo
     * @param orderNo
     * @return
     */
    AuthResultVo userAuth(String merchantNo, String orderNo);
}
