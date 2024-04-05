package com.fai.service.brofee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "commission_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_id", nullable = false)
    private Commission commission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_service_id", nullable = false)
    private TransactionService transactionService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_policy_id", nullable = false)
    private CommissionPolicy commissionPolicy;

    @Column(name = "commission_level")
    private Integer commissionLevel;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

}
