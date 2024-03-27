package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.CommissionService;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.entity.stored_procedure_entity.CommissionService_SP;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommissionDTO {

    private Long id;

    private UserDTO recipient;

    private BigDecimal total;

    private int status;

    private LocalDateTime createdAt;

    private List<CommissionService_SP> commissionServices;

}
