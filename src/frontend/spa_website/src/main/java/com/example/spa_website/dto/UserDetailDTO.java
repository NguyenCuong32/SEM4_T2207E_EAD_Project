package com.example.spa_website.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDetailDTO {

    private String code;

    private String phone;

    private String name;

    private String email;

    private String avatar;

    private String address;

    private Set<RoleDTO> roles = new HashSet<>();

    private UserDTO referrer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
