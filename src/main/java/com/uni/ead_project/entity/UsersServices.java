package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UsersServices {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "service_id")
    private String serviceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersServices that = (UsersServices) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, serviceId);
    }
}
