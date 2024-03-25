package com.fai.brofee_fe.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ServiceDetailDTO {

    private Long id;

    private String name;

    private String thumbnail;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal discount;

    private CategoryAndServiceDTO category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private List<PolicyServiceAssignmentDTO> policyAssignments;

    private Boolean conflicted;

}
