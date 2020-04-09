package cn.liaozhonghao.www.permission.shiro.realm;

import cn.liaozhonghao.www.common.core.enums.PublicStatusEnum;
import cn.liaozhonghao.www.service.permission.entity.PmsOperator;
import cn.liaozhonghao.www.service.permission.service.PmsOperatorRoleService;
import cn.liaozhonghao.www.service.permission.service.PmsOperatorService;
import cn.liaozhonghao.www.service.permission.service.PmsRolePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义realm
 */
public class OperatorRealm extends AuthorizingRealm {

    @Autowired
    private PmsOperatorService pmsOperatorService;
    @Autowired
    private PmsOperatorRoleService pmsOperatorRoleService;
    @Autowired
    private PmsRolePermissionService pmsRolePermissionService;

    // 获取权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String loginName = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        PmsOperator operator = (PmsOperator) session.getAttribute("PmsOperator");
        if (operator == null) {
            operator = pmsOperatorService.findOperatorByLoginName(loginName);
            session.setAttribute("PmsOperator", operator);
        }
        // 根据登录名查询操作员
        Long operatorId = operator.getId();

        Set<String> roles = (Set<String>) session.getAttribute("ROLES");
        if (roles == null || roles.isEmpty()) {
            roles = pmsOperatorRoleService.getRoleCodeByOperatorId(operatorId);
            session.setAttribute("ROLES", roles);
        }
        // 查询角色信息
        authorizationInfo.setRoles(roles);

        Set<String> permisstions = (Set<String>) session.getAttribute("PERMISSIONS");
        if (permisstions == null || permisstions.isEmpty()) {
            permisstions = pmsRolePermissionService.getPermissionsByOperatorId(operatorId);
            session.setAttribute("PERMISSIONS", permisstions);
        }
        // 根据用户名查询权限
        authorizationInfo.setStringPermissions(permisstions);
        return authorizationInfo;
    }

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String loginName = (String) token.getPrincipal();
        if (StringUtils.isEmpty(loginName.trim())) {
            throw new UnknownAccountException();// 没找到帐号
        }

        // 根据登录名查询操作员
        PmsOperator operator = pmsOperatorService.findOperatorByLoginName(loginName);

        if (operator == null) {
            throw new UnknownAccountException();// 没找到帐号
        }

        if (PublicStatusEnum.UNACTIVE.equals(operator.getStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(operator.getLoginName(), // 登录名
                operator.getLoginPwd(), // 密码
                ByteSource.Util.bytes(operator.getCredentialsSalt()), // salt=username+salt
                getName() // realm name
        );

        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
