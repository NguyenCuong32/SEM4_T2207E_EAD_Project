package org.example.brofee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    @NotBlank(message = "The Username is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String nameCategory;
    private int status;
}
