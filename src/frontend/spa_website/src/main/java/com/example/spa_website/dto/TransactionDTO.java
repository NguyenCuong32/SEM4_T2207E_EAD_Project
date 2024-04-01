package com.example.spa_website.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private UserDTO customer;

    private LocalDateTime transactionDate;

    private BigDecimal total;

    private List<TransactionServiceDTO> transactionServices;
}
