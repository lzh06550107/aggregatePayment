package cn.liaozhonghao.www.service.user.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;

/**
 * 用户支付配置dao
 */
public interface UserPayConfigDao extends BaseDao<UserPayConfig> {

    /**
     * 根据用户编号获取用户支付信息
     * @param userNo
     * @return
     */
    UserPayConfig getByUserNo(String userNo, String auditStatus);

}