package cn.liaozhonghao.www.service.permission.dao.impl;

import cn.liaozhonghao.www.service.permission.dao.PmsMenuRoleDao;
import cn.liaozhonghao.www.service.permission.entity.PmsMenuRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单角色
 */
@Repository("pmsRoleMenuDao")
public class PmsMenuRoleDaoImpl extends PermissionBaseDaoImpl<PmsMenuRole> implements PmsMenuRoleDao {

	@Override
	public void deleteByRoleId(Long roleId) {
		super.getSessionTemplate().delete(getStatement("deleteByRoleId"), roleId);
	}

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	@Override
	public List<PmsMenuRole> listByRoleId(Long roleId) {
		return super.getSessionTemplate().selectList(getStatement("listByRoleId"), roleId);
	}
}