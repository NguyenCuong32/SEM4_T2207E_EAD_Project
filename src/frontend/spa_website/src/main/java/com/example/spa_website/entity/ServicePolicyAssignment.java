package com.example.spa_website.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_policy_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicePolicyAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private CommissionPolicy policy;

    @Column(name = "active")
    private Boolean active;
}
