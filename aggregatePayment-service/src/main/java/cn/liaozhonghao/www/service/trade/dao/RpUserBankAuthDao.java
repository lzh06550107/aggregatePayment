package cn.liaozhonghao.www.service.trade.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.trade.entity.UserBankAuth;

import java.util.List;

public interface RpUserBankAuthDao extends BaseDao<UserBankAuth> {

    UserBankAuth findByMerchantNoAndPayOrderNo(String merchantNo, String payOrderNo);

    List<UserBankAuth> listByUserInfo(String userName, String phone, String idNo, String bankAccountNo);
}
