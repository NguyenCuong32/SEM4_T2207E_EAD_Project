package com.fai.brofee_fe.dto.UserDetail;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetailDTO {
    private  Long id;
    private String code;
    private String phone;
    private String email;
    private String name;
    private String address;
    private String avatar;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
    private String role_name;
    private Long referrer_id;
    private String referrer_name;
    private String referrer_avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Long getReferrer_id() {
        return referrer_id;
    }

    public void setReferrer_id(Long referrer_id) {
        this.referrer_id = referrer_id;
    }

    public String getReferrer_name() {
        return referrer_name;
    }

    public void setReferrer_name(String referrer_name) {
        this.referrer_name = referrer_name;
    }

    public String getReferrer_avatar() {
        return referrer_avatar;
    }

    public void setReferrer_avatar(String referrer_avatar) {
        this.referrer_avatar = referrer_avatar;
    }

    public UserDetailDTO(Long id, String code, String phone, String email,String name, String address, String avatar, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at, String role_name, Long referrer_id, String referrer_name, String referrer_avatar) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.role_name = role_name;
        this.referrer_id = referrer_id;
        this.referrer_name = referrer_name;
        this.referrer_avatar = referrer_avatar;
    }
}
