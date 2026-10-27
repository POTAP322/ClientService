package com.github.potap322.clientservice.dto;

import com.github.potap322.clientservice.model.ClientGender;

public record ClientUpdateRequest(
        String fullName,
        ClientGender gender
) {
}
