package cn.liaozhonghao.www.service.trade.service.impl;

import cn.liaozhonghao.www.service.trade.dao.RpUserBankAuthDao;
import cn.liaozhonghao.www.service.trade.entity.UserBankAuth;
import cn.liaozhonghao.www.service.trade.service.RpUserBankAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rpUserBankAuthServiceImpl")
public class RpUserBankAuthServiceImpl implements RpUserBankAuthService {

    @Autowired
    private RpUserBankAuthDao userBankAuthDao;

    @Override
    public UserBankAuth findByMerchantNoAndPayOrderNo(String merchantNo, String payOrderNo) {
        return userBankAuthDao.findByMerchantNoAndPayOrderNo(merchantNo, payOrderNo);
    }

    @Override
    public void updateByKey(UserBankAuth userBankAuth) {
        userBankAuthDao.update(userBankAuth);
    }
}
