package com.example.spa_website.dto;

import com.example.spa_website.entity.Service;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CategoryDetailDTO {

    private Long id;

    private String name;

    private String icon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<ServiceDTO> services;

}
