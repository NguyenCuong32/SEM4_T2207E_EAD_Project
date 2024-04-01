package com.fai.brofee_fe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String thumbnail;

    private String description;

    @Column(name = "base_price", precision = 16, scale = 2, nullable = false)
    private BigDecimal basePrice;

    @Column(precision = 16, scale = 2)
    private BigDecimal discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Relationship with other tables
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private List<ServicePolicyAssignment> policyAssignments;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private List<TransactionService> transactionServices;

}
