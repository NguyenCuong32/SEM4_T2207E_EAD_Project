package com.fai.service.brofee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    // Min length of password is 8
    @Pattern(regexp = "^.{8,}$", message = "Password must be at least 8 characters")
    private String password;

}
