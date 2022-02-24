package com.exam.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    private Long role_id;
    private String roleName;

     //one role may have multiple users
    //one-to-many relationship is between Role and UserRole so we are using type userrole
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set<UserRole>  userRoles=new HashSet<>();

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Role() {
    }

    public Role(Long role_id, String roleName) {
        this.role_id = role_id;
        this.roleName = roleName;
    }

    public Long getRole_id() {
        return role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
