package com.xrd.nails_appointment.dto.appointment;

import com.xrd.nails_appointment.dto.appointment_record.AppointmentRecordResponseDTO;
import com.xrd.nails_appointment.dto.client.ClientResponseDTO;
import com.xrd.nails_appointment.dto.nail_service.NailServiceResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public record AppointmentResponseDTO(
        String id,
        ClientResponseDTO client,
        LocalDate date,
        LocalTime startTime,
        List<NailServiceResponseDTO> services,
        Set<AppointmentRecordResponseDTO> appointmentRecords
) { }
