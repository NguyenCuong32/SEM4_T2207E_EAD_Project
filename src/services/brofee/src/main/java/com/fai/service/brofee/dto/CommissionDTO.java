package com.fai.service.brofee.dto;

import com.fai.service.brofee.entity.stored_procedure_entity.CommissionService_SP;
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
