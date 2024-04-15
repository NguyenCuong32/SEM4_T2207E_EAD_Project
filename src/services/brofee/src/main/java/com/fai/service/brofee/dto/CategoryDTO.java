package com.fai.service.brofee.dto;

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
