package cn.liaozhonghao.www.service.account.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;

/**
 * 账户历史service接口
 */
public interface AccountHistoryService {

	/**
	 * 保存
	 */
	void saveData(AccountHistory rpAccountHistory);

	/**
	 * 更新
	 */
	void updateData(AccountHistory rpAccountHistory);

	/**
	 * 根据id获取数据
	 *
	 * @param id
	 * @return
	 */
	AccountHistory getDataById(String id);


	/**
	 * 获取分页数据
	 *
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, AccountHistory rpAccountHistory);
	
}