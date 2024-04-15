package com.fai.service.brofee.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 225, message = "First name must be lest than 255 characters")
    private String name;

    @NotBlank(message = "Phone is mandatory")
    @Size(max = 225, message = "First name must be lest than 255 characters")
    private String phone;

    @NotBlank(message = "Email is mandatory")
    @Email
    @Size(max = 225, message = "Email must be lest than 255 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @AssertTrue(message = "You must agree to terms and conditions")
    private boolean termsAccepted;

    private String referrerCodeOrPhone;

}
