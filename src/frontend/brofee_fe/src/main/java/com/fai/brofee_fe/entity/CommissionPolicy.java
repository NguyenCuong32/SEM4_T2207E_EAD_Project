package com.fai.brofee_fe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commission_policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "max_referral_levels", nullable = false)
    private Integer maxReferralLevels;

    @OneToMany(mappedBy = "policy", fetch = FetchType.EAGER)
    private List<CommissionTier> commissionTiers;

    // Relationship with other tables
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    private List<ServicePolicyAssignment> policyAssignments;

}
