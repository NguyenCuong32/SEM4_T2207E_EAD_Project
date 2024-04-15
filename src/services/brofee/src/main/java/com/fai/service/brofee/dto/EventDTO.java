package com.fai.service.brofee.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {

    private Long id;

    private String code;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    private BigDecimal maxDiscount;

    private String banner;

    private int status;

}
