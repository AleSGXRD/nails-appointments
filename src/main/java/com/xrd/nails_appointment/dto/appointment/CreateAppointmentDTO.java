package com.xrd.nails_appointment.dto.appointment;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CreateAppointmentDTO(
        @NotNull
        String clientId,

        @NotEmpty
        List<String> servicesId,

        @NotNull
        @FutureOrPresent
        LocalDate date,

        @NotNull
        LocalTime startTime
) { }
