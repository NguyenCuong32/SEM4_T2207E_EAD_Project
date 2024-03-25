package org.example.brofee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceDto {
    @NotBlank(message = "The Username is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String name;

    @NotBlank(message = "The Description is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String description;

    @NotNull(message = "The Price is not null.")
    @Positive(message = "Giá trị phải là số dương")
    private BigDecimal price;

    @DecimalMin(value = "0", inclusive = true, message = "Minimum value is 0")
    @DecimalMax(value = "100", inclusive = true, message = "Maximum value is 100")
    private float level1;

    @DecimalMin(value = "0", inclusive = true, message = "Minimum value is 0")
    @DecimalMax(value = "100", inclusive = true, message = "Maximum value is 100")
    private float level2;

    @DecimalMin(value = "0", inclusive = true, message = "Minimum value is 0")
    @DecimalMax(value = "100", inclusive = true, message = "Maximum value is 100")
    private float level3;

    @DecimalMin(value = "0", inclusive = true, message = "Minimum value is 0")
    @DecimalMax(value = "100", inclusive = true, message = "Maximum value is 100")
    private float level4;

    @DecimalMin(value = "0", inclusive = true, message = "Minimum value is 0")
    @DecimalMax(value = "100", inclusive = true, message = "Maximum value is 100")
    private float level5;

    private int status;

    @JsonProperty("category")
    private UUID category;

/*    @JsonProperty("policiesid")
    private UUID policiesId;*/
}
