package com.fai.service.brofee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "commission_tier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private CommissionPolicy policy;

    @Column(nullable = false)
    private Integer level;

    @Column(name = "commission_rate", precision = 5, scale = 2)
    private BigDecimal commissionRate;

}
