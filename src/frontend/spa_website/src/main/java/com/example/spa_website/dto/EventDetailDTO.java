package com.example.spa_website.dto;

import com.example.spa_website.entity.EventService;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventDetailDTO {

    private Long id;

    private String code;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    private BigDecimal maxDiscount;

    private String banner;

    private int status;

    private List<EventServiceDTO> eventServices;

}
