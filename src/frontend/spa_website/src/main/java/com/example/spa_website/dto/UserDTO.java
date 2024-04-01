package com.example.spa_website.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private String code;

    private String phone;

    private String name;

    private String email;

    private String avatar;

    private String address;

    private Set<RoleDTO> roles = new HashSet<>();

}
