package com.qian.blog.model;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

public class Permission {
    private Integer id;
    private Integer parent_id;
    private String parent_ids;
    private String permission;
    private String resource_type;
    private String url;
    private String name;
    private String available;
    private Set<Role> roles = new HashSet<Role>();
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParent_id() {
        return parent_id;
    }
    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
    public String getParent_ids() {
        return parent_ids;
    }
    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    public String getResource_type() {
        return resource_type;
    }
    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "Permission [id=" + id + ", parent_id=" + parent_id + ", parent_ids=" + parent_ids + ", permission="
                + permission + ", resource_type=" + resource_type + ", url=" + url + ", name=" + name + ", available="
                + available + ", roles=" + roles + "]";
    }



}

