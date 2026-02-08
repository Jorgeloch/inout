package com.inout.application.dto.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LogInRequestDTO(
        @Email(message = "email must be an valid email address")
        @NotBlank(message = "email cannot be blank")
        @NotNull(message = "email must be provided")
        String email,

        @NotNull(message = "password must be provided")
        String rawPassword
) {
}
