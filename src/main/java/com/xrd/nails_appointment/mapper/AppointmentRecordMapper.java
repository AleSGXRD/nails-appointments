package com.xrd.nails_appointment.mapper;

import com.xrd.nails_appointment.dto.appointment_record.AppointmentRecordResponseDTO;
import com.xrd.nails_appointment.dto.appointment_record.CreateAppointmentRecordDTO;
import com.xrd.nails_appointment.dto.appointment_record.UpdateAppointmentRecordDTO;
import com.xrd.nails_appointment.model.Appointment;
import com.xrd.nails_appointment.model.AppointmentRecord;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRecordMapper {

    public AppointmentRecord toEntity(CreateAppointmentRecordDTO dto, Appointment appointment){
        return AppointmentRecord.builder()
                .notes(dto.notes())
                .type(dto.type())
                .appointment(appointment)
                .build();
    }


    public AppointmentRecord toEntity(UpdateAppointmentRecordDTO dto, Appointment appointment){
        return AppointmentRecord.builder()
                .notes(dto.notes())
                .type(dto.type())
                .appointment(appointment)
                .build();
    }

    public AppointmentRecordResponseDTO toResponseDto(AppointmentRecord entity){
        return new AppointmentRecordResponseDTO(
                entity.getId(),
                entity.getType(),
                entity.getNotes(),
                entity.getImageUrl(),
                entity.getAppointment().getId()
        );
    }

}
