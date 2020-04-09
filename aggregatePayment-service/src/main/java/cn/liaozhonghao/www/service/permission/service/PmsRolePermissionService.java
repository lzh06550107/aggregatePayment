package cn.liaozhonghao.www.service.permission.service;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.permission.entity.PmsRolePermission;

import java.util.Set;

/**
 * 角色权限service接口
 */
public interface PmsRolePermissionService {

	/**
	 * 根据操作员ID，获取所有的功能权限集
	 * 
	 * @param operatorId
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId);

	/**
	 * 创建pmsRolePermission
	 */
	void saveData(PmsRolePermission pmsRolePermission);

	/**
	 * 修改pmsRolePermission
	 */
	void updateData(PmsRolePermission pmsRolePermission);

	/**
	 * 根据id获取数据pmsRolePermission
	 * 
	 * @param id
	 * @return
	 */
	PmsRolePermission getDataById(Long id);

	/**
	 * 分页查询pmsRolePermission
	 * 
	 * @param pageParam
	 * @param pmsRolePermission
	 * @return
	 */
	PageBean listPage(PageParam pageParam, PmsRolePermission pmsRolePermission);
	
	/**
	 * 保存角色和权限之间的关联关系
	 */
	void saveRolePermission(Long roleId, String rolePermissionStr);

}
