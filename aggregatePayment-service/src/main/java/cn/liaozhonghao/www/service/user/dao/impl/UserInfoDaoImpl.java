package cn.liaozhonghao.www.service.user.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.user.dao.UserInfoDao;
import cn.liaozhonghao.www.service.user.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * 用户信息dao实现类
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {
}