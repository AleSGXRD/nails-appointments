package com.xrd.nails_appointment.dto.appointment;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record UpdateAppointmentDTO(
        String clientId,
        List<String> servicesId,
        LocalDate date,
        LocalTime startTime
) {
}
