package cn.liaozhonghao.www.service.trade.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.trade.entity.MicroSubmitRecord;

import java.util.Map;

/**
 * 小微商户进件service接口
 */
public interface RpMicroSubmitRecordService {

    /**
     * 获取分页信息
     *
     * @param pageParam
     * @param rpMicroSubmitRecord
     * @return
     */
    PageBean listPage(PageParam pageParam, MicroSubmitRecord rpMicroSubmitRecord);

    /**
     * 保存
     *
     * @param rpMicroSubmitRecord
     */
    void saveData(MicroSubmitRecord rpMicroSubmitRecord);

    /**
     * 进件
     *
     * @param rpMicroSubmitRecord
     */
    Map<String, Object> microSubmit(MicroSubmitRecord rpMicroSubmitRecord);

    /**
     * 查询
     *
     * @param businessCode
     * @return
     */
    Map<String, Object> microQuery(String businessCode);

}
