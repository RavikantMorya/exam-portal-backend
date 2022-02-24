package com.exam.models;

import javax.persistence.*;

// this entity is going to be used for One to Many realtionship from User to UserRole
// this entity is going to be used for One to Many realtionship from Role to UserRole
//finally with the help of this entity we established many-to-many relationship between user and role
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
