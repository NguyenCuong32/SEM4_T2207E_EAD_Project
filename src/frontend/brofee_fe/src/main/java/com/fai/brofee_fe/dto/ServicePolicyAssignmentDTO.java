package com.fai.brofee_fe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePolicyAssignmentDTO {

    private Long id;

    private ServiceInPolicyDTO service;

    private Boolean active;

}