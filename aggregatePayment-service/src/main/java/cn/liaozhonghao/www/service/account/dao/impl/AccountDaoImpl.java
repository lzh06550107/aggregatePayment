package cn.liaozhonghao.www.service.account.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.service.account.dao.AccountDao;
import cn.liaozhonghao.www.service.account.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * 账户dao实现类
 */
@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

	public Account getByAccountNo(String accountNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", accountNo);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return this.getBy(paramMap);
	}

	public Account getByUserNo(Map<String, Object> map){
		return this.getSessionTemplate().selectOne(getStatement("getByUserNo"), map);
	}
}