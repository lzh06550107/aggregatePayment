package cn.liaozhonghao.www.service.trade.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.trade.dao.RpUserBankAuthDao;
import cn.liaozhonghao.www.service.trade.entity.UserBankAuth;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rpUserBankAuthDao")
public class RpUserBankAuthDaoImpl extends BaseDaoImpl<UserBankAuth> implements RpUserBankAuthDao {

    @Override
    public UserBankAuth findByMerchantNoAndPayOrderNo(String merchantNo, String payOrderNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("merchantNo", merchantNo);
        paramMap.put("payOrderNo", payOrderNo);
        System.out.println(paramMap.toString());
        return super.getByColumn(paramMap);
    }

    @Override
    public List<UserBankAuth> listByUserInfo(String userName, String phone, String idNo, String bankAccountNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("phone", phone);
        paramMap.put("idNo", idNo);
        paramMap.put("bankAccountNo", bankAccountNo);
        return super.listBy(paramMap);
    }
}
