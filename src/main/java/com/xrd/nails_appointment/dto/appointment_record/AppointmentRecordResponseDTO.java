package com.xrd.nails_appointment.dto.appointment_record;

import com.xrd.nails_appointment.model.AppointmentRecordType;

public record AppointmentRecordResponseDTO(
        String id,
        AppointmentRecordType type,
        String notes,
        String imageUrl
) { }
