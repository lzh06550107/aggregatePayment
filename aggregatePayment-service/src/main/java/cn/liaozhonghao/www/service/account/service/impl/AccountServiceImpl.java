package cn.liaozhonghao.www.service.account.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.account.dao.AccountDao;
import cn.liaozhonghao.www.service.account.entity.Account;
import cn.liaozhonghao.www.service.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户service实现类
 */
@Service("rpAccountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao rpAccountDao;
	
	@Override
	public void saveData(Account rpAccount) {
		rpAccountDao.insert(rpAccount);
	}

	@Override
	public void updateData(Account rpAccount) {
		rpAccountDao.update(rpAccount);
	}

	@Override
	public Account getDataById(String id) {
		return rpAccountDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, Account rpAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", rpAccount.getAccountNo());
		return rpAccountDao.listPage(pageParam, paramMap);
	}
	
	@Override
	public Account getDataByUserNo(String userNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo", userNo);
		return rpAccountDao.getBy(paramMap);
	}
}