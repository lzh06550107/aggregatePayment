package cn.liaozhonghao.www.service.trade.service;

import cn.liaozhonghao.www.service.trade.entity.UserBankAuth;

public interface RpUserBankAuthService {

    UserBankAuth findByMerchantNoAndPayOrderNo(String merchantNo, String payOrderNo);

    void updateByKey(UserBankAuth userBankAuth);

}
