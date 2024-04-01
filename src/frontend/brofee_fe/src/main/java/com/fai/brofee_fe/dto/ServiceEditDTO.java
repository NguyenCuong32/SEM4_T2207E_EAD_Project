package com.fai.brofee_fe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ServiceEditDTO {
    private Long id;

    @NotBlank(message = "Service name must not be blank")
    private String name;

    private String oldThumbnail;

    private MultipartFile newThumbnail;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal discount;

    @NotNull(message = "Category ID must not be null")
    private Long category_id;
}
