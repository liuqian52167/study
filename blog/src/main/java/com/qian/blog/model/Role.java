package com.qian.blog.model;

import com.qian.blog.model.Permission;
import com.qian.blog.model.User;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private Integer id;
    private String role;
    private String description;
    private String available;
    private Set<User> users = new HashSet<User>();
    private Set<Permission> permissions = new HashSet<Permission>();
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
                + ", users=" + users + ", permissions=" + permissions + "]";
    }


}
