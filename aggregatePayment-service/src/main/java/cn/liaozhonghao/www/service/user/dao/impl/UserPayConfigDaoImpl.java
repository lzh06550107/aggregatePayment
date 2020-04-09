package cn.liaozhonghao.www.service.user.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.service.user.dao.UserPayConfigDao;
import cn.liaozhonghao.www.service.user.entity.UserPayConfig;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户支付配置dao实现类
 */
@Repository
public class UserPayConfigDaoImpl extends BaseDaoImpl<UserPayConfig> implements UserPayConfigDao {

    @Override
    public UserPayConfig getByUserNo(String userNo, String auditStatus) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return super.getBy(paramMap);
    }
}