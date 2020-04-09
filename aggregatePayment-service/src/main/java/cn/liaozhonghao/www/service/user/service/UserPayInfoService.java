package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.UserPayInfo;

/**
 * 用户第三方支付信息service接口
 */
public interface UserPayInfoService {
	
	/**
	 * 保存
	 */
	void saveData(UserPayInfo rpUserPayInfo);

	/**
	 * 更新
	 */
	void updateData(UserPayInfo rpUserPayInfo);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	UserPayInfo getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, UserPayInfo rpUserPayInfo);

	/**
	 * 通过商户编号获取商户支付配置信息
	 * @param userNo
	 * @param payWayCode
	 * @return
	 */
	public UserPayInfo getByUserNo(String userNo, String payWayCode);
	
}