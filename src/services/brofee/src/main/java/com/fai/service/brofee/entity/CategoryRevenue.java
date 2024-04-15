package com.fai.service.brofee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRevenue {

    @Id
    private Long category_id;

    private String category_name;

    private String category_icon;

    private BigDecimal total_revenue;

    private Integer total_service;

    private Integer total_pages;

}
