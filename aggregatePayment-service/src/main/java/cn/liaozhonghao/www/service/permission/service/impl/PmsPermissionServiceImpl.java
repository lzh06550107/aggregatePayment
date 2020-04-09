package cn.liaozhonghao.www.service.permission.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.permission.dao.PmsPermissionDao;
import cn.liaozhonghao.www.service.permission.dao.PmsRolePermissionDao;
import cn.liaozhonghao.www.service.permission.entity.PmsPermission;
import cn.liaozhonghao.www.service.permission.entity.PmsRolePermission;
import cn.liaozhonghao.www.service.permission.service.PmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限service接口实现
 */
@Service("pmsPermissionService")
public class PmsPermissionServiceImpl implements PmsPermissionService {

	@Autowired
	private PmsPermissionDao pmsPermissionDao;
	@Autowired
	private PmsRolePermissionDao pmsRolePermissionDao;

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsPermission pmsPermission) {
		pmsPermissionDao.insert(pmsPermission);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsPermission pmsPermission) {
		pmsPermissionDao.update(pmsPermission);
	}

	/**
	 * 根据id获取数据pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsPermission getDataById(Long id) {
		return pmsPermissionDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * 
	 * @param pageParam
	 * @param pmsPermission
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsPermission pmsPermission) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", pmsPermission.getPermissionName()); // 权限名称（模糊查询）
		paramMap.put("permission", pmsPermission.getPermission()); // 权限（精确查询）
		return pmsPermissionDao.listPage(pageParam, paramMap);
	}

	/**
	 * 检查权限名称是否已存在
	 * 
	 * @param permissionName
	 * @return
	 */
	public PmsPermission getByPermissionName(String permissionName) {
		return pmsPermissionDao.getByPermissionName(permissionName);
	}

	/**
	 * 检查权限是否已存在
	 * 
	 * @param permission
	 * @return
	 */
	public PmsPermission getByPermission(String permission) {
		return pmsPermissionDao.getByPermission(permission);
	}

	/**
	 * 检查权限名称是否已存在(其他id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		return pmsPermissionDao.getByPermissionNameNotEqId(permissionName, id);
	}

	/**
	 * pmsPermissionDao 删除
	 * 
	 * @param permissionId
	 */
	public void delete(Long permissionId) {
		pmsPermissionDao.delete(permissionId);
	}

	/**
	 * 根据角色查找角色对应的功能权限ID集
	 * 
	 * @param roleId
	 * @return
	 */
	public String getPermissionIdsByRoleId(Long roleId) {
		List<PmsRolePermission> rmList = pmsRolePermissionDao.listByRoleId(roleId);
		StringBuffer actionIds = new StringBuffer();
		if (rmList != null && !rmList.isEmpty()) {
			for (PmsRolePermission rm : rmList) {
				actionIds.append(rm.getPermissionId()).append(",");
			}
		}
		return actionIds.toString();
	}

	/**
	 * 查询所有的权限
	 */
	public List<PmsPermission> listAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return pmsPermissionDao.listBy(paramMap);
	}
}
