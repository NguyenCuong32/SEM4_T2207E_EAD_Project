package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.Commission;
import com.fai.brofee_fe.entity.CommissionPolicy;
import com.fai.brofee_fe.entity.TransactionService;
import com.fai.brofee_fe.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CommissionServiceDTO {

    private Long id;

    private Long commissionId;

    private TransactionService transactionService;

    private String commissionPolicyCode;

    private Integer commissionLevel;

    private BigDecimal amount;
}
