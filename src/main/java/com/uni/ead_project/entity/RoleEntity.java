package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Roles", schema = "dbo", catalog = "lab")
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @Basic
    @Column(name = "user_id")
    private String userId;
//    @ManyToOne
//    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
//    private UsersEntity usersByUserId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId && Objects.equals(roleName, that.roleName) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, userId);
    }

//    public UsersEntity getUsersByUserId() {
//        return usersByUserId;
//    }
//
//    public void setUsersByUserId(UsersEntity usersByUserId) {
//        this.usersByUserId = usersByUserId;
//    }
}
