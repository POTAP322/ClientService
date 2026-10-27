package com.github.potap322.clientservice.dto;

import java.time.OffsetDateTime;

public record ErrorResponse(
        int status,
        String message,
        OffsetDateTime timestamp
) {
}
