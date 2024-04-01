package com.example.spa_website.entity.stored_procedure_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionService_SP {

    @Id
    private Long id;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "commission_id")
    private Long commissionId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    // Transaction service fields:
    @Column(name = "transaction_service_id")
    private Long transactionServiceId;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "name")
    private String name;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;


    // Customer fields:
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;


    // Policy fields:
    @Column(name = "commission_policy_id")
    private Long commissionPolicyId;

    @Column(name = "policy_code")
    private String policyCode;

    @Column(name = "commission_rate")
    private BigDecimal commissionRate;

    @Column(name = "commission_level")
    private Integer commissionLevel;




}
