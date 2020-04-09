package cn.liaozhonghao.www.service.account.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.account.dao.AccountHistoryDao;
import cn.liaozhonghao.www.service.account.entity.AccountHistory;
import cn.liaozhonghao.www.service.account.service.AccountHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户历史service实现类
 */
@Service("rpAccountHistoryService")
public class AccountHistoryServiceImpl implements AccountHistoryService {

	@Autowired
	private AccountHistoryDao rpAccountHistoryDao;
	
	@Override
	public void saveData(AccountHistory rpAccountHistory) {
		rpAccountHistoryDao.insert(rpAccountHistory);
	}

	@Override
	public void updateData(AccountHistory rpAccountHistory) {
		rpAccountHistoryDao.update(rpAccountHistory);
	}

	@Override
	public AccountHistory getDataById(String id) {
		return rpAccountHistoryDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, AccountHistory rpAccountHistory) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", rpAccountHistory.getAccountNo());
		return rpAccountHistoryDao.listPage(pageParam, paramMap);
	}
}