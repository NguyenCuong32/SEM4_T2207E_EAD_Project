package com.example.ProjectEAD.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "Fullname cannot be null")
    private String fullname;
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Min length of Password is 6")
    private String password;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Gender cannot be null")
    private Integer gender;
    private Integer connecterId;
    @NotNull(message = "Status cannot be null")
    private Integer status = 1;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getConnecterId() {
        return connecterId;
    }

    public void setConnecterId(Integer connecterId) {
        this.connecterId = connecterId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
