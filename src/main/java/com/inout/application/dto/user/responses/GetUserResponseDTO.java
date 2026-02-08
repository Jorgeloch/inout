package com.inout.application.dto.user.responses;

import java.util.UUID;

public record GetUserResponseDTO(
        UUID userId,
        String name
) {
}
