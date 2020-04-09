package cn.liaozhonghao.www.service.account.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.account.entity.Account;

/**
 *  账户service接口
 */
public interface AccountService{
	
	/**
	 * 保存
	 */
	void saveData(Account Account);

	/**
	 * 更新
	 */
	void updateData(Account Account);

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	Account getDataById(String id);
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, Account Account);
	
	/**
	 * 根据userNo获取数据
	 * 
	 * @param userNo
	 * @return
	 */
	Account getDataByUserNo(String userNo);
	
}