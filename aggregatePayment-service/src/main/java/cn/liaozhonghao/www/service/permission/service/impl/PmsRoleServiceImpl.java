package cn.liaozhonghao.www.service.permission.service.impl;

import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.permission.dao.PmsRoleDao;
import cn.liaozhonghao.www.service.permission.entity.PmsRole;
import cn.liaozhonghao.www.service.permission.service.PmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色service接口实现
 */
@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {

	@Autowired
	private PmsRoleDao pmsRoleDao;

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsRole pmsRole) {
		pmsRoleDao.insert(pmsRole);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsRole pmsRole) {
		pmsRoleDao.update(pmsRole);
	}

	/**
	 * 根据id获取数据pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsRole getDataById(Long id) {
		return pmsRoleDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * 
	 * @param pageParam
	 * @param pmsRole
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsRole pmsRole) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
		paramMap.put("roleName", pmsRole.getRoleName()); // 角色名称（模糊查询）
		return pmsRoleDao.listPage(pageParam, paramMap);
	}

	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<PmsRole> listAllRole() {
		return pmsRoleDao.listAll();
	}

	/**
	 * 判断此权限是否关联有角色
	 * 
	 * @param permissionId
	 * @return
	 */
	public List<PmsRole> listByPermissionId(Long permissionId) {
		return pmsRoleDao.listByPermissionId(permissionId);
	}

	/**
	 * 根据角色名或者角色编号查询角色
	 * 
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
		return pmsRoleDao.getByRoleNameOrRoleCode(roleName, roleCode);
	}

	/**
	 * 删除
	 * 
	 * @param roleId
	 */
	public void delete(Long roleId) {
		pmsRoleDao.delete(roleId);
	}
}
