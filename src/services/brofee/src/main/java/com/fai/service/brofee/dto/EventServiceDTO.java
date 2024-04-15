package com.fai.service.brofee.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EventServiceDTO {

    private Long id;

    private ServiceDTO service;

    private BigDecimal discount;

}
