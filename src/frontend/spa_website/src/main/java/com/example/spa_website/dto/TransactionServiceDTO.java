package com.example.spa_website.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionServiceDTO {

    private Long id;

    private ServiceInPolicyDTO service;

    private BigDecimal price;

}
