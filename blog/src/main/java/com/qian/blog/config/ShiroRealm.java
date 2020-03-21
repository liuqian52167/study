package com.qian.blog.config;

import com.qian.blog.mapper.PermissionMapper;
import com.qian.blog.mapper.RoleMapper;
import com.qian.blog.mapper.UserMapper;
import com.qian.blog.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;

import javax.management.relation.Role;
import java.util.Iterator;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public ShiroRealm(){
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        new String(usernamePasswordToken.getPassword());
        User user = this.userMapper.findByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        } else if ("1".equals(user.getState())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        } else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            return info;
        }
    }
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Set<BeanDefinitionDsl.Role> roles = this.roleMapper.findRolesByUserId(user.getUid());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Iterator var6 = roles.iterator();

        while(var6.hasNext()) {
            Role role = (Role)var6.next();
            authorizationInfo.addRole(role.getRole());
        }

        Set<Permission> permissions = this.permissionMapper.findPermissionsByRoleId(roles);
        Iterator var7 = permissions.iterator();

        while(var7.hasNext()) {
            Permission permission = (Permission)var7.next();
            authorizationInfo.addStringPermission(permission.getPermission());
        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);
        session.setAttribute("roles", roles);
        session.setAttribute("permissions", permissions);
        return authorizationInfo;
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
        this.getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        this.getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        this.clearAllCachedAuthenticationInfo();
        this.clearAllCachedAuthorizationInfo();
    }
}

