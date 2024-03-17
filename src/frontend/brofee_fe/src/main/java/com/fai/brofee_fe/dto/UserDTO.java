package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.Role;
import jakarta.persistence.*;
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

    private Set<Role> roles = new HashSet<>();

}
