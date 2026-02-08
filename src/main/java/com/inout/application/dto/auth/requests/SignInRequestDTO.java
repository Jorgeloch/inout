package com.inout.application.dto.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignInRequestDTO(
        @Email(message = "email must be a valid email address")
        @NotNull(message = "email must be provided")
        @NotBlank(message = "email cannot be blank")
        String email,

        @Size(min = 8, message = "password must have at least 8 characters")
        @NotBlank(message = "password cannot be blank")
        @NotNull(message = "password must be provided")
        String rawPassword
) {
}
