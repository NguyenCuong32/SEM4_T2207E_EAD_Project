package com.example.spa_website.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private String icon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
