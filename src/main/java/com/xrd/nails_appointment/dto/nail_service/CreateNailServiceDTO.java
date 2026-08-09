package com.xrd.nails_appointment.dto.nail_service;

import com.xrd.nails_appointment.model.ServiceDuration;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateNailServiceDTO(
        @Size(min = 2, max = 100)
        @NotBlank
        String name,

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        ServiceDuration duration
) { }
