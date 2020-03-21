package com.qian.blog.mapper;

import org.apache.shiro.authz.Permission;
import org.springframework.context.support.BeanDefinitionDsl;

import java.util.Set;

public class PermissionMapper {
    public Set<Permission> findPermissionsByRoleId(Set<BeanDefinitionDsl.Role> roles) {
    }
}
