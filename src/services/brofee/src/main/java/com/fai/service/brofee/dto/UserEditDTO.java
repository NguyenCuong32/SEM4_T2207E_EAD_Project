package com.fai.service.brofee.dto;

import lombok.*;

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
