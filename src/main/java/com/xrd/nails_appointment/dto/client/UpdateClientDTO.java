package com.xrd.nails_appointment.dto.client;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientDTO(
        String name,
        String telephoneNumber
) { }
