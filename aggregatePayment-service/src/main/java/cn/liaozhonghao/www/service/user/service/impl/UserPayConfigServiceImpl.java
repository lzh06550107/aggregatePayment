package cn.liaozhonghao.www.service.user.service.impl;

import cn.liaozhonghao.www.common.core.enums.PayWayEnum;
import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.common.core.utils.StringUtil;
import cn.liaozhonghao.www.service.user.dao.UserPayConfigDao;
import cn.liaozhonghao.www.service.user.entity.PayProduct;
import cn.liaozhonghao.www.service.user.entity.PayWay;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;
import cn.liaozhonghao.www.service.user.entity.UserPayInfo;
import cn.liaozhonghao.www.service.user.exception.PayBizException;
import cn.liaozhonghao.www.service.user.service.PayProductService;
import cn.liaozhonghao.www.service.user.service.PayWayService;
import cn.liaozhonghao.www.service.user.service.UserPayConfigService;
import cn.liaozhonghao.www.service.user.service.UserPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户支付配置service实现类
 */
@Service("rpUserPayConfigService")
public class UserPayConfigServiceImpl implements UserPayConfigService {

	@Autowired
	private UserPayConfigDao rpUserPayConfigDao;
	@Autowired
	private PayProductService rpPayProductService;
	@Autowired
	private PayWayService rpPayWayService;
	@Autowired
	private UserPayInfoService rpUserPayInfoService;
	
	@Override
	public void saveData(UserPayConfig rpUserPayConfig) {
		rpUserPayConfigDao.insert(rpUserPayConfig);
	}

	@Override
	public void updateData(UserPayConfig rpUserPayConfig) {
		rpUserPayConfigDao.update(rpUserPayConfig);
	}

	@Override
	public UserPayConfig getDataById(String id) {
		return rpUserPayConfigDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, UserPayConfig rpUserPayConfig) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", rpUserPayConfig.getProductCode());
		paramMap.put("userNo", rpUserPayConfig.getUserNo());
		paramMap.put("userName", rpUserPayConfig.getUserName());
		paramMap.put("productName", rpUserPayConfig.getProductName());
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpUserPayConfigDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据商户编号获取已生效的支付配置
	 *
	 * @param userNo
	 * @return
	 */
	@Override
	public UserPayConfig getByUserNo(String userNo) {
		return rpUserPayConfigDao.getByUserNo(userNo, PublicEnum.YES.name());
	}
	
	/**
	 * 根据商户编号获取支付配置
	 * @param userNo
	 * @param auditStatus
	 * @return
	 */
	@Override
	public UserPayConfig getByUserNo(String userNo, String auditStatus){
		return rpUserPayConfigDao.getByUserNo(userNo, auditStatus);
	}
	
	
	/**
	 * 根据支付产品获取已生效数据
	 */
	@Override
	public List<UserPayConfig> listByProductCode(String productCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", PublicEnum.YES.name());
		return rpUserPayConfigDao.listBy(paramMap);
	}
	
	/**
	 * 根据支付产品获取数据
	 */
	@Override
	public List<UserPayConfig> listByProductCode(String productCode, String auditStatus){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", auditStatus);
		return rpUserPayConfigDao.listBy(paramMap);
	}
	
	/**
	 * 创建用户支付配置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay,
			String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey,
			String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException {

		createUserPayConfig( userNo,  userName,  productCode,  productName, riskDay,
				 fundIntoType,  isAutoSett,  appId,  merchantId,  partnerKey,
				 ali_partner,  ali_sellerId,  ali_key,  ali_appid,  ali_rsaPrivateKey,  ali_rsaPublicKey ,  null ,  null);
	}

	/**
	 * 创建用户支付配置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay,
			String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey,
			String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey , String securityRating , String merchantServerIp)  throws PayBizException{

		UserPayConfig payConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
		if(payConfig != null){
			throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_EXIST,"用户支付配置已存在");
		}

		UserPayConfig rpUserPayConfig = new UserPayConfig();
		rpUserPayConfig.setUserNo(userNo);
		rpUserPayConfig.setUserName(userName);
		rpUserPayConfig.setProductCode(productCode);
		rpUserPayConfig.setProductName(productName);
		rpUserPayConfig.setStatus(PublicStatusEnum.ACTIVE.name());
		rpUserPayConfig.setAuditStatus(PublicEnum.YES.name());
		rpUserPayConfig.setRiskDay(riskDay);
		rpUserPayConfig.setFundIntoType(fundIntoType);
		rpUserPayConfig.setIsAutoSett(isAutoSett);
		rpUserPayConfig.setPayKey(StringUtil.get32UUID());
		rpUserPayConfig.setPaySecret(StringUtil.get32UUID());
		rpUserPayConfig.setId(StringUtil.get32UUID());
		rpUserPayConfig.setSecurityRating(securityRating);//安全等级
		rpUserPayConfig.setMerchantServerIp(merchantServerIp);
		saveData(rpUserPayConfig);

		//查询支付产品下有哪些支付方式
		List<PayWay> payWayList = rpPayWayService.listByProductCode(productCode);
		Map<String, String> map = new HashMap<String, String>();
		//过滤重复数据
		for(PayWay payWay : payWayList){
	        map.put(payWay.getPayWayCode(), payWay.getPayWayName());
		}

		for (String key : map.keySet()) {
			if(key.equals(PayWayEnum.WEIXIN.name())){
				//创建用户第三方支付信息
				UserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.WEIXIN.name());
				if(rpUserPayInfo == null){
					rpUserPayInfo = new UserPayInfo();
					rpUserPayInfo.setId(StringUtil.get32UUID());
					rpUserPayInfo.setCreateTime(new Date());
					rpUserPayInfo.setAppId(appId);
					rpUserPayInfo.setMerchantId(merchantId);
					rpUserPayInfo.setPartnerKey(partnerKey);
					rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
					rpUserPayInfo.setUserNo(userNo);
					rpUserPayInfo.setUserName(userName);
					rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
					rpUserPayInfoService.saveData(rpUserPayInfo);
				}else{
					rpUserPayInfo.setEditTime(new Date());
					rpUserPayInfo.setAppId(appId);
					rpUserPayInfo.setMerchantId(merchantId);
					rpUserPayInfo.setPartnerKey(partnerKey);
					rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
					rpUserPayInfoService.updateData(rpUserPayInfo);
				}

			}else if(key.equals(PayWayEnum.ALIPAY.name())){
				//创建用户第三方支付信息
				UserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.ALIPAY.name());
				if(rpUserPayInfo == null){
					rpUserPayInfo = new UserPayInfo();
					rpUserPayInfo.setId(StringUtil.get32UUID());
					rpUserPayInfo.setCreateTime(new Date());
					rpUserPayInfo.setAppId(ali_partner);
					rpUserPayInfo.setMerchantId(ali_sellerId);
					rpUserPayInfo.setPartnerKey(ali_key);
					rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
					rpUserPayInfo.setUserNo(userNo);
					rpUserPayInfo.setUserName(userName);
					rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
					rpUserPayInfo.setOfflineAppId(ali_appid);
					rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
					rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
					rpUserPayInfoService.saveData(rpUserPayInfo);
				}else{
					rpUserPayInfo.setEditTime(new Date());
					rpUserPayInfo.setAppId(ali_partner);
					rpUserPayInfo.setMerchantId(ali_sellerId);
					rpUserPayInfo.setPartnerKey(ali_key);
					rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
					rpUserPayInfo.setOfflineAppId(ali_appid);
					rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
					rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
					rpUserPayInfoService.updateData(rpUserPayInfo);
				}
			}
		}



	}

	/**
	 * 删除支付产品
	 * @param userNo
	 */
	@Override
	public void deleteUserPayConfig(String userNo) throws PayBizException{
		
		UserPayConfig rpUserPayConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
		if(rpUserPayConfig == null){
			throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"用户支付配置不存在");
		}
		
		rpUserPayConfig.setStatus(PublicStatusEnum.UNACTIVE.name());
		rpUserPayConfig.setEditTime(new Date());
		updateData(rpUserPayConfig);
	}
	
	/**
	 * 修改用户支付配置
	 */
	@Override
	public void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
			String isAutoSett, String appId, String merchantId, String partnerKey,
			String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException{

			updateUserPayConfig( userNo,  productCode,  productName,  riskDay,  fundIntoType,
				 isAutoSett,  appId,  merchantId,  partnerKey,
				 ali_partner,  ali_sellerId,  ali_key,  ali_appid,  ali_rsaPrivateKey,  ali_rsaPublicKey  ,  null ,  null);
	}
	/**
	 * 修改用户支付配置
	 */
	@Override
	public void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
			String isAutoSett, String appId, String merchantId, String partnerKey,
			String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey  , String securityRating , String merchantServerIp)  throws PayBizException{
		UserPayConfig rpUserPayConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
		if(rpUserPayConfig == null){
			throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"用户支付配置不存在");
		}

		rpUserPayConfig.setProductCode(productCode);
		rpUserPayConfig.setProductName(productName);
		rpUserPayConfig.setRiskDay(riskDay);
		rpUserPayConfig.setFundIntoType(fundIntoType);
		rpUserPayConfig.setIsAutoSett(isAutoSett);
		rpUserPayConfig.setEditTime(new Date());
		rpUserPayConfig.setSecurityRating(securityRating);//安全等级
		rpUserPayConfig.setMerchantServerIp(merchantServerIp);
		updateData(rpUserPayConfig);

		//查询支付产品下有哪些支付方式
		List<PayWay> payWayList = rpPayWayService.listByProductCode(productCode);
		Map<String, String> map = new HashMap<String, String>();
		//过滤重复数据
		for(PayWay payWay : payWayList){
			map.put(payWay.getPayWayCode(), payWay.getPayWayName());
		}

		for (String key : map.keySet()) {
			if(key.equals(PayWayEnum.WEIXIN.name())){
				//创建用户第三方支付信息
				UserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.WEIXIN.name());
				if(rpUserPayInfo == null){
					rpUserPayInfo = new UserPayInfo();
					rpUserPayInfo.setId(StringUtil.get32UUID());
					rpUserPayInfo.setCreateTime(new Date());
					rpUserPayInfo.setAppId(appId);
					rpUserPayInfo.setMerchantId(merchantId);
					rpUserPayInfo.setPartnerKey(partnerKey);
					rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
					rpUserPayInfo.setUserNo(userNo);
					rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
					rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
					rpUserPayInfoService.saveData(rpUserPayInfo);
				}else{
					rpUserPayInfo.setEditTime(new Date());
					rpUserPayInfo.setAppId(appId);
					rpUserPayInfo.setMerchantId(merchantId);
					rpUserPayInfo.setPartnerKey(partnerKey);
					rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
					rpUserPayInfoService.updateData(rpUserPayInfo);
				}

			}else if(key.equals(PayWayEnum.ALIPAY.name())){
				//创建用户第三方支付信息
				UserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.ALIPAY.name());
				if(rpUserPayInfo == null){
					rpUserPayInfo = new UserPayInfo();
					rpUserPayInfo.setId(StringUtil.get32UUID());
					rpUserPayInfo.setCreateTime(new Date());
					rpUserPayInfo.setAppId(ali_partner);
					rpUserPayInfo.setMerchantId(ali_sellerId);
					rpUserPayInfo.setPartnerKey(ali_key);
					rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
					rpUserPayInfo.setUserNo(userNo);
					rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
					rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
					rpUserPayInfo.setOfflineAppId(ali_appid);
					rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
					rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
					rpUserPayInfoService.saveData(rpUserPayInfo);
				}else{
					rpUserPayInfo.setEditTime(new Date());
					rpUserPayInfo.setAppId(ali_partner);
					rpUserPayInfo.setMerchantId(ali_sellerId);
					rpUserPayInfo.setPartnerKey(ali_key);
					rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
					rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
					rpUserPayInfo.setOfflineAppId(ali_appid);
					rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
					rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
					rpUserPayInfoService.updateData(rpUserPayInfo);
				}
			}
		}
	}

	/**
	 * 审核
	 * @param userNo
	 * @param auditStatus
	 */
	@Override
	public void audit(String userNo, String auditStatus){
		UserPayConfig rpUserPayConfig = getByUserNo(userNo, null);
		if(rpUserPayConfig == null){
			throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"支付配置不存在！");
		}
		
		if(auditStatus.equals(PublicEnum.YES.name())){
			//检查是否已关联生效的支付产品
			PayProduct rpPayProduct = rpPayProductService.getByProductCode(rpUserPayConfig.getProductCode(), PublicEnum.YES.name());
			if(rpPayProduct == null){
				throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"未关联已生效的支付产品，无法操作！");
			}
			
			//检查是否已设置第三方支付信息
		}
		rpUserPayConfig.setAuditStatus(auditStatus);
		rpUserPayConfig.setEditTime(new Date());
		updateData(rpUserPayConfig);
	}
	
	/**
	 * 根据商户key获取已生效的支付配置
	 * @param payKey
	 * @return
	 */
	public UserPayConfig getByPayKey(String payKey){
	    Map<String , Object> paramMap = new HashMap<String , Object>();
	    paramMap.put("payKey", payKey);
	    paramMap.put("status", PublicStatusEnum.ACTIVE.name());
	    paramMap.put("auditStatus", PublicEnum.YES.name());
	    return rpUserPayConfigDao.getBy(paramMap);
	}
}