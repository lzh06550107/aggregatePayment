package cn.liaozhonghao.www.service.user.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.user.entity.UserPayInfo;

/**
 * 用户第三方支付信息dao
 */
public interface UserPayInfoDao extends BaseDao<UserPayInfo> {

    /**
     * 通过商户编号获取商户第三方支付信息
     * @param userNo
     * @param payWayCode
     * @return
     */
    public UserPayInfo getByUserNo(String userNo, String payWayCode);

}