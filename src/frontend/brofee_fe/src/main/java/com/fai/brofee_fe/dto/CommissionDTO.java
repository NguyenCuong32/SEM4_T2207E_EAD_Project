package com.fai.brofee_fe.dto;

import com.fai.brofee_fe.entity.CommissionService;
import com.fai.brofee_fe.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommissionDTO {

    private Long id;

    private BigDecimal total;

    private int status;

    private LocalDateTime createdAt;

    private List<CommissionServiceDTO> commissionServices;
}
