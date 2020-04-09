package cn.liaozhonghao.www.service.user.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.user.dao.UserPayInfoDao;
import cn.liaozhonghao.www.service.user.entity.UserPayInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户第三方支付信息dao实现类
 */
@Repository
public class UserPayInfoDaoImpl extends BaseDaoImpl<UserPayInfo> implements UserPayInfoDao {
    /**
     * 通过商户编号获取商户第三方支付信息
     *
     * @param userNo
     * @param payWayCode
     * @return
     */
    @Override
    public UserPayInfo getByUserNo(String userNo, String payWayCode) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("payWayCode",payWayCode);
        return super.getBy(paramMap);
    }
}