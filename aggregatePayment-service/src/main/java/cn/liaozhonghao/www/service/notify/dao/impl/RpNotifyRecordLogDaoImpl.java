package cn.liaozhonghao.www.service.notify.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.notify.dao.RpNotifyRecordLogDao;
import cn.liaozhonghao.www.service.notify.entity.RpNotifyRecordLog;
import org.springframework.stereotype.Repository;

@Repository("rpNotifyRecordLogDao")
public class RpNotifyRecordLogDaoImpl extends BaseDaoImpl<RpNotifyRecordLog> implements RpNotifyRecordLogDao {
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(RpNotifyRecordLog record) {
        return 0;
    }

    @Override
    public RpNotifyRecordLog selectByPrimaryKey(String id) {
        return null;
    }


    @Override
    public int updateByPrimaryKey(RpNotifyRecordLog record) {
        return 0;
    }
}
