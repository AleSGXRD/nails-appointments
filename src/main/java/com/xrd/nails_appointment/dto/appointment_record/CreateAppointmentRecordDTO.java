package com.xrd.nails_appointment.dto.appointment_record;

import com.xrd.nails_appointment.model.AppointmentRecordType;
import jakarta.validation.constraints.NotNull;

public record CreateAppointmentRecordDTO(
        @NotNull
        AppointmentRecordType type,

        String notes
) {
}
