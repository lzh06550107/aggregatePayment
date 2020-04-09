package cn.liaozhonghao.www.service.user.dao.impl;

import cn.liaozhonghao.www.common.core.dao.impl.BaseDaoImpl;
import cn.liaozhonghao.www.service.user.dao.BuildNoDao;
import cn.liaozhonghao.www.service.user.entity.SeqBuild;
import org.springframework.stereotype.Repository;

/**
 *  生成编号dao实现类
 */
@Repository
public class BuildNoDaoImpl extends BaseDaoImpl<SeqBuild> implements BuildNoDao {

}
