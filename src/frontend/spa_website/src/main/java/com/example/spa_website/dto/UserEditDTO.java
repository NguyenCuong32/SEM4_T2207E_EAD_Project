package com.example.spa_website.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditDTO {

    private String phone;

    private String name;

    private String email;

    private String avatar;

    private String address;

}
