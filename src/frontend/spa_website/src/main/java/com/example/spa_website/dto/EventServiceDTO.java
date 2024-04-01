package com.example.spa_website.dto;

import com.example.spa_website.entity.Event;
import com.example.spa_website.entity.Service;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
