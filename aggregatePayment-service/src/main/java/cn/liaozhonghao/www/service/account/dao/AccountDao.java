package cn.liaozhonghao.www.service.account.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.account.entity.Account;

import java.util.Map;

/**
 * 账户dao
 */
public interface AccountDao extends BaseDao<Account> {

	Account getByAccountNo(String accountNo);

	Account getByUserNo(Map<String, Object> map);
}