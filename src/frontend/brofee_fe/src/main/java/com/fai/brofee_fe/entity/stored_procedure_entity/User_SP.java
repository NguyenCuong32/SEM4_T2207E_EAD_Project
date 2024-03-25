package com.fai.brofee_fe.entity.stored_procedure_entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User_SP implements Serializable {

    @Id
    private Long id;

    private String code;

    private String phone;

    private String name;

    private String email;

    private String avatar;

    private String address;

    @Column(name = "role_names")
    private String roleNames;

    @Column(name = "referrer_id")
    private Long referrerId;

    @Column(name = "referrer_code")
    private String referrerCode;

    @Column(name = "referrer_phone")
    private String referrerPhone;

    @Column(name = "referrer_name")
    private String referrerName;

    @Column(name = "referrer_email")
    private String referrerEmail;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "total_referred_users")
    private Integer totalReferredUsers;

    @Column(name = "total_spent")
    private BigDecimal totalSpent;

    @Column(name = "total_commission")
    private BigDecimal totalCommission;

    @Column(name = "total_items")
    private Integer totalItems;

    @Column(name = "total_pages")
    private Integer totalPages;

}
