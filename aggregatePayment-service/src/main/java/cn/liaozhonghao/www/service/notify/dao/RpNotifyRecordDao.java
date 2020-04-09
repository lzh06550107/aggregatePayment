package cn.liaozhonghao.www.service.notify.dao;

import cn.liaozhonghao.www.common.core.dao.BaseDao;
import cn.liaozhonghao.www.service.notify.entity.RpNotifyRecord;

/**
 * 通知记录数据库操作
 */
public interface RpNotifyRecordDao  extends BaseDao<RpNotifyRecord> {

    RpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyType);

    int deleteByPrimaryKey(String id);

    int insertSelective(RpNotifyRecord record);

    RpNotifyRecord selectByPrimaryKey(String id);

    int updateByPrimaryKey(RpNotifyRecord record);
}