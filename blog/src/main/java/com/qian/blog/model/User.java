package com.qian.blog.model;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String state;
    private String isAdmin;

    public String getIsAdmin() {

        return isAdmin;
        }
        public void setIsAdmin(String isAdmin) {

        this.isAdmin = isAdmin;
        }

        private Set<Role> roles = new HashSet<Role>();
        public Integer getUid(){return uid;}
        public void SetUid(Integer uid){this.uid = uid;}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString(){
            return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name
                    + ",state=" + state + ", roles=" + roles + "]";
    }

}
