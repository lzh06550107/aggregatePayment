package cn.liaozhonghao.www.service.user.service;

import cn.liaozhonghao.www.service.user.entity.UserBankAccount;

/**
 * 用户银行账户service接口
 */
public interface UserBankAccountService {
	
	/**
	 * 保存
	 */
	void saveData(UserBankAccount rpUserBankAccount);

	/**
	 * 更新
	 */
	void updateData(UserBankAccount rpUserBankAccount);

	/**
	 * 根据用户编号获取银行账户
	 */
	UserBankAccount getByUserNo(String userNo);

	/**
	 * 创建或更新
	 * @param rpUserBankAccount
	 */
	void createOrUpdate(UserBankAccount rpUserBankAccount);
}