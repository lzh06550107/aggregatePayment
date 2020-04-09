package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;
import cn.liaozhonghao.www.service.user.exception.PayBizException;

import java.util.List;

/**
 *  用户支付配置service接口
 */
public interface UserPayConfigService {
	
	/**
	 * 保存
	 */
	void saveData(UserPayConfig rpUserPayConfig);

	/**
	 * 更新
	 */
	void updateData(UserPayConfig rpUserPayConfig);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	UserPayConfig getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, UserPayConfig rpUserPayConfig);

	/**
	 * 根据商户编号获取已生效的支付配置
	 * @param userNo
	 * @return
	 */
	UserPayConfig getByUserNo(String userNo);
	
	/**
	 * 根据商户编号获取支付配置
	 * @param userNo
	 * @param auditStatus
	 * @return
	 */
	UserPayConfig getByUserNo(String userNo, String auditStatus);
	
	/**
	 * 根据支付产品获取已生效数据
	 */
	List<UserPayConfig> listByProductCode(String productCode);
	
	/**
	 * 根据支付产品获取数据
	 */
	List<UserPayConfig> listByProductCode(String productCode, String auditStatus);
	
	/**
	 * 创建用户支付配置
	 */
	void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws BizException;

	/**
	 * 创建用户支付配置
	 */
	void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp)  throws PayBizException;

	/**
	 * 删除支付产品
	 * @param userNo
	 */
	void deleteUserPayConfig(String userNo) throws PayBizException;

	/**
	 * 修改用户支付配置
	 */
	void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException;

	/**
	 * 修改用户支付配置
	 */
	void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp)  throws PayBizException;

	/**
	 * 审核
	 * @param userNo
	 * @param auditStatus
	 */
	void audit(String userNo, String auditStatus);
	
	/**
	 * 根据商户key获取已生效的支付配置
	 * @param payKey
	 * @return
	 */
	UserPayConfig getByPayKey(String payKey);
	
}