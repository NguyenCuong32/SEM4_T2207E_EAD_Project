package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Roles", schema = "dbo", catalog = "lab")
public class RolesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RoleID")
    private int roleId;
    @Basic
    @Column(name = "RoleName")
    private String roleName;
    @Basic
    @Column(name = "UserID")
    private String userId;
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private UsersEntity usersByUserId;

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
        RolesEntity that = (RolesEntity) o;
        return roleId == that.roleId && Objects.equals(roleName, that.roleName) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, userId);
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}