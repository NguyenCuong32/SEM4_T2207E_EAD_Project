package org.example.brofee.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BranchDto {
    @NotBlank(message = "The Name is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String name;

    @NotBlank(message = "The Address is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String address;

    @NotBlank(message = "The Phone is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    private String phone;

    @NotBlank(message = "The Email is not null.")
    @Size(min = 1, max =255, message = "Minimum length is 1 and Max is 255")
    @Email
    private String email;

    private int status;
}
