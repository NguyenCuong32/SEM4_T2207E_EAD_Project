package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.Category;
import com.fai.brofee_fe.entity.ServicePolicyAssignment;
import com.fai.brofee_fe.entity.TransactionService;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ServiceDTO {

    private Long id;

    private String name;

    private String thumbnail;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal discount;

    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private List<ServicePolicyAssignment> policyAssignments;

    private BigDecimal serviceRevenue;

}