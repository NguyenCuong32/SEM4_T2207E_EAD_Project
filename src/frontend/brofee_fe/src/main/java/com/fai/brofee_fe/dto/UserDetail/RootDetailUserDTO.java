package com.fai.brofee_fe.dto.UserDetail;

import lombok.Data;

import java.util.List;

@Data
public class RootDetailUserDTO {
    private UserDetailDTO user;
    private List<Referrer> referralsList;
}
