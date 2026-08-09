package com.xrd.nails_appointment.dto.client;

import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank
        String name,
        @NotBlank
        String telephoneNumber
) { }
