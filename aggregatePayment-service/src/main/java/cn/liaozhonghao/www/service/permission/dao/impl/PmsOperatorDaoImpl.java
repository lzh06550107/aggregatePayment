package cn.liaozhonghao.www.service.permission.dao.impl;

import cn.liaozhonghao.www.service.permission.dao.PmsOperatorDao;
import cn.liaozhonghao.www.service.permission.entity.PmsOperator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限操作员dao实现
 */
@Repository
public class PmsOperatorDaoImpl extends PermissionBaseDaoImpl<PmsOperator> implements PmsOperatorDao {

	/**
	 * 根据操作员登录名获取操作员信息.
	 * 
	 * @param loginName
	 *            .
	 * @return operator .
	 */

	public PmsOperator findByLoginName(String loginName) {
		return super.getSessionTemplate().selectOne(getStatement("findByLoginName"), loginName);
	}

	@Override
	public List<PmsOperator> listByRoleId(Long roleId) {
		return super.getSessionTemplate().selectList(getStatement("listByRoleId"), roleId);
	}

}
