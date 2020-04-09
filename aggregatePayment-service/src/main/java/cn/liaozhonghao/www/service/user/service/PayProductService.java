package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.PayProduct;
import cn.liaozhonghao.www.service.user.exception.PayBizException;

import java.util.List;

/**
 * 支付产品service接口
 */
public interface PayProductService {
	
	/**
	 * 保存
	 */
	void saveData(PayProduct rpPayProduct);

	/**
	 * 更新
	 */
	void updateData(PayProduct rpPayProduct);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	PayProduct getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, PayProduct rpPayProduct);
	
	/**
	 * 根据产品编号获取支付产品
	 * @param productCode
	 * @return
	 */
	PayProduct getByProductCode(String productCode, String auditStatus);
	
	/**
	 * 创建支付产品
	 * @param productCode
	 * @param productName
	 */
	void createPayProduct(String productCode, String productName) throws BizException;
	
	/**
	 * 删除支付产品
	 * @param productCode
	 */
	void deletePayProduct(String productCode) throws PayBizException;
	
	/**
	 * 获取所有支付产品
	 */
	List<PayProduct> listAll();

	/**
	 * 审核
	 * @param productCode
	 * @param auditStatus
	 */
	void audit(String productCode, String auditStatus) throws PayBizException;
	
}