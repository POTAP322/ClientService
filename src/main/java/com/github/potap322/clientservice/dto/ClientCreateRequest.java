package com.github.potap322.clientservice.dto;

import com.github.potap322.clientservice.model.ClientGender;
import jakarta.validation.constraints.NotBlank;

public record ClientCreateRequest(
        @NotBlank(message = "fullName must not be blank")
        String fullName,
        ClientGender gender
) {

}
