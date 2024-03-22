package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.CommissionTier;
import com.fai.brofee_fe.entity.ServicePolicyAssignment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommissionPolicyDTO {

    private Long id;

    private String code;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer maxReferralLevels;

    private List<CommissionTierDTO> commissionTiers;

    private BigDecimal totalRate;

    private List<ServicePolicyAssignmentDTO> policyAssignments;

    private PolicyStatus status;

}
