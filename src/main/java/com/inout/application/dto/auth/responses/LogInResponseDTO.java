package com.inout.application.dto.auth.responses;

public record LogInResponseDTO(
        String token,
        String type
) {
}
