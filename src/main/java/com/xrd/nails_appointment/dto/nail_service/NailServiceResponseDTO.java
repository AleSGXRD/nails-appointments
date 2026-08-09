package com.xrd.nails_appointment.dto.nail_service;

import com.xrd.nails_appointment.model.ServiceDuration;

import java.math.BigDecimal;

public record NailServiceResponseDTO(
        String id,
        String name,
        BigDecimal price,
        ServiceDuration duration
) { }
