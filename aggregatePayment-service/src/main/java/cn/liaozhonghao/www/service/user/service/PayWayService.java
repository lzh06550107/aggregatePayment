package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.common.core.exception.BizException;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.PayWay;

import java.util.List;

/**
 * 支付方式service接口
 */
public interface PayWayService {
	
	/**
	 * 保存
	 */
	void saveData(PayWay rpPayWay);

	/**
	 * 更新
	 */
	void updateData(PayWay rpPayWay);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	PayWay getDataById(String id);
	
	/**
	 * 根据支付方式、渠道编码获取数据
	 * @param rpTypeCode
	 * @return
	 */
	PayWay getByPayWayTypeCode(String payProductCode, String payWayCode, String rpTypeCode);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, PayWay rpPayWay);
	
	/**
	 * 绑定支付费率
	 * @param payWayCode
	 * @param payTypeCode
	 * @param payRate
	 */
	void createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate)  throws BizException;
	
	/**
	 * 根据支付产品获取支付方式
	 * @param payProductCode
	 */
	List<PayWay> listByProductCode(String payProductCode);
	
	/**
	 * 获取所有支付方式
	 */
	List<PayWay> listAll();
	
}