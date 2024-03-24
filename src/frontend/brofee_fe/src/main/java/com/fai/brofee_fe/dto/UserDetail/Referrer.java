package com.fai.brofee_fe.dto.UserDetail;

import java.time.LocalDateTime;

public class Referrer {
    private Long id;
    private String name;
    private String avatar;
    private LocalDateTime created_at;


    public Referrer(Long id, String name, String avatar, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
