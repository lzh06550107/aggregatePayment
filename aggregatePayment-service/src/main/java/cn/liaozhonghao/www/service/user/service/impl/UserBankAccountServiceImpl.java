package cn.liaozhonghao.www.service.user.service.impl;

import cn.liaozhonghao.www.common.core.enums.PublicEnum;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.common.core.utils.StringUtil;
import cn.liaozhonghao.www.service.user.dao.UserBankAccountDao;
import cn.liaozhonghao.www.service.user.entity.UserBankAccount;
import cn.liaozhonghao.www.service.user.enums.BankCodeEnum;
import cn.liaozhonghao.www.service.user.service.UserBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户银行账户service实现类
 */
@Service("rpUserBankAccountService")
public class UserBankAccountServiceImpl implements UserBankAccountService {

	@Autowired
	private UserBankAccountDao userBankAccountDao;
	
	@Override
	public void saveData(UserBankAccount rpUserBankAccount) {
		userBankAccountDao.insert(rpUserBankAccount);
	}

	@Override
	public void updateData(UserBankAccount rpUserBankAccount) {
		userBankAccountDao.update(rpUserBankAccount);
	}

	/**
	 * 根据用户编号获取银行账户
	 */
	@Override
	public UserBankAccount getByUserNo(String userNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo", userNo);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return userBankAccountDao.getBy(paramMap);
	}
	
	/**
	 * 创建或更新
	 * @param rpUserBankAccount
	 */
	@Override
	public void createOrUpdate(UserBankAccount rpUserBankAccount){
		UserBankAccount bankAccount = getByUserNo(rpUserBankAccount.getUserNo());
		if(bankAccount == null){
			bankAccount = new UserBankAccount();
			bankAccount.setId(StringUtil.get32UUID());
			bankAccount.setCreateTime(new Date());
			bankAccount.setEditTime(new Date());
			bankAccount.setAreas(rpUserBankAccount.getAreas());
			bankAccount.setBankAccountName(rpUserBankAccount.getBankAccountName());
			bankAccount.setBankAccountNo(rpUserBankAccount.getBankAccountNo());
			bankAccount.setBankAccountType(rpUserBankAccount.getBankAccountType());
			bankAccount.setBankCode(rpUserBankAccount.getBankCode());
			bankAccount.setBankName(BankCodeEnum.getEnum(rpUserBankAccount.getBankCode()).getDesc());
			bankAccount.setCardNo(rpUserBankAccount.getCardNo());
			bankAccount.setCardType(rpUserBankAccount.getCardType());
			bankAccount.setCity(rpUserBankAccount.getCity());
			bankAccount.setIsDefault(PublicEnum.YES.name());
			bankAccount.setMobileNo(rpUserBankAccount.getMobileNo());
			bankAccount.setProvince(rpUserBankAccount.getProvince());
			bankAccount.setRemark(rpUserBankAccount.getRemark());
			bankAccount.setStatus(PublicStatusEnum.ACTIVE.name());
			bankAccount.setUserNo(rpUserBankAccount.getUserNo());
			bankAccount.setStreet(rpUserBankAccount.getStreet());
			userBankAccountDao.insert(bankAccount);
		}else{
			bankAccount.setEditTime(new Date());
			bankAccount.setAreas(rpUserBankAccount.getAreas());
			bankAccount.setBankAccountName(rpUserBankAccount.getBankAccountName());
			bankAccount.setBankAccountNo(rpUserBankAccount.getBankAccountNo());
			bankAccount.setBankAccountType(rpUserBankAccount.getBankAccountType());
			bankAccount.setBankCode(rpUserBankAccount.getBankCode());
			bankAccount.setBankName(BankCodeEnum.getEnum(rpUserBankAccount.getBankCode()).getDesc());
			bankAccount.setCardNo(rpUserBankAccount.getCardNo());
			bankAccount.setCardType(rpUserBankAccount.getCardType());
			bankAccount.setCity(rpUserBankAccount.getCity());
			bankAccount.setIsDefault(PublicEnum.YES.name());
			bankAccount.setMobileNo(rpUserBankAccount.getMobileNo());
			bankAccount.setProvince(rpUserBankAccount.getProvince());
			bankAccount.setRemark(rpUserBankAccount.getRemark());
			bankAccount.setStatus(PublicStatusEnum.ACTIVE.name());
			bankAccount.setUserNo(rpUserBankAccount.getUserNo());
			bankAccount.setStreet(rpUserBankAccount.getStreet());
			userBankAccountDao.update(bankAccount);
		}
	}
}