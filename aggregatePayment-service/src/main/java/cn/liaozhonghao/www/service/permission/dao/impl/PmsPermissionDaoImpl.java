package cn.liaozhonghao.www.service.permission.dao.impl;

import cn.liaozhonghao.www.service.permission.dao.PmsPermissionDao;
import cn.liaozhonghao.www.service.permission.entity.PmsPermission;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限功能点dao实现
 */
@Repository
public class PmsPermissionDaoImpl extends PermissionBaseDaoImpl<PmsPermission> implements PmsPermissionDao {

	/**
	 * 根据实体ID集字符串获取对象列表.
	 * 
	 * @param idStr
	 * @return
	 */
	public List<PmsPermission> findByIds(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		return this.getSessionTemplate().selectList(getStatement("findByIds"), ids);
	}

	/**
	 * 检查权限名称是否已存在
	 * 
	 * @param permissionName
	 * @return
	 */
	public PmsPermission getByPermissionName(String permissionName) {
		return this.getSessionTemplate().selectOne(getStatement("getByPermissionName"), permissionName);

	}

	/**
	 * 检查权限是否已存在
	 * 
	 * @param permission
	 * @return
	 */
	public PmsPermission getByPermission(String permission) {
		return this.getSessionTemplate().selectOne(getStatement("getByPermission"), permission);
	}

	/**
	 * 检查权限名称是否已存在(其他id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", permissionName);
		paramMap.put("id", id);
		return this.getSessionTemplate().selectOne(getStatement("getByPermissionNameNotEqId"), paramMap);
	}

	/**
	 * 获取叶子菜单下所有的功能权限
	 * 
	 * @param menuId
	 * @return
	 */
	public List<PmsPermission> listAllByMenuId(Long menuId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuId", menuId);
		return this.getSessionTemplate().selectList(getStatement("listAllByMenuId"), param);
	}
}
