package com.github.potap322.clientservice.dto;

import com.github.potap322.clientservice.model.Client;
import com.github.potap322.clientservice.model.ClientGender;
import com.github.potap322.clientservice.model.ClientStatus;

import java.time.OffsetDateTime;

public record ClientResponse(
        Long id,
        String fullName,
        ClientStatus status,
        ClientGender gender,
        OffsetDateTime createDttm,
        OffsetDateTime modifyDttm
) {

    public static ClientResponse from(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getFullName(),
                client.getStatus(),
                client.getGender(),
                client.getCreateDttm(),
                client.getModifyDttm()
        );
    }
}
