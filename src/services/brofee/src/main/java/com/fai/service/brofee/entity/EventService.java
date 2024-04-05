package com.fai.service.brofee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "event_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

}
