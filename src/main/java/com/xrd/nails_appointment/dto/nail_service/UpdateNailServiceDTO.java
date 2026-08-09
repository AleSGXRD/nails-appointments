package com.xrd.nails_appointment.dto.nail_service;

import com.xrd.nails_appointment.model.ServiceDuration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateNailServiceDTO(
        @Size(min = 2, max = 100)
        String name,

        @Positive
        BigDecimal price,

        ServiceDuration duration
) { }
