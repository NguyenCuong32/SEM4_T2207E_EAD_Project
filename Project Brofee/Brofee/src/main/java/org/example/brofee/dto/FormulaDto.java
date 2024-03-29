package org.example.brofee.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormulaDto {
    @NotBlank(message = "The name is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String name;

    @NotBlank(message = "The description is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String description;

    private int status;
}
