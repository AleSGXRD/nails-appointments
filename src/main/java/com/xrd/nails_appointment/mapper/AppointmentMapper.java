package com.xrd.nails_appointment.mapper;

import com.xrd.nails_appointment.dto.appointment.AppointmentResponseDTO;
import com.xrd.nails_appointment.dto.appointment.CreateAppointmentDTO;
import com.xrd.nails_appointment.dto.appointment.UpdateAppointmentDTO;
import com.xrd.nails_appointment.model.Appointment;
import com.xrd.nails_appointment.model.AppointmentRecord;
import com.xrd.nails_appointment.model.Client;
import com.xrd.nails_appointment.model.NailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final ClientMapper clientMapper;
    private final NailServiceMapper nailServiceMapper;
    private final AppointmentRecordMapper appointmentRecordMapper;

    public Appointment toEntity(
            CreateAppointmentDTO dto,
            Client client,
            List<NailService> services,
            Set<AppointmentRecord> records
    ){
        return Appointment.builder()
                .client(client)
                .services(services)
                .records(records)
                .date(dto.date())
                .startTime(dto.startTime())
                .build();
    }

    public Appointment toEntity(
            UpdateAppointmentDTO dto,
            Client client,
            List<NailService> services,
            Set<AppointmentRecord> records
    ){
        return Appointment.builder()
                .client(client)
                .services(services)
                .records(records)
                .date(dto.date())
                .startTime(dto.startTime())
                .build();
    }

    public AppointmentResponseDTO toResponseDTO(Appointment entity){
        return new AppointmentResponseDTO(
                entity.getId(),
                clientMapper.toResponseDTO(
                        entity.getClient()
                ),
                entity.getDate(),
                entity.getStartTime(),
                entity.getServices()
                        .stream()
                        .map(nailServiceMapper::toResponseDto)
                        .toList(),
                entity.getRecords()
                        .stream()
                        .map(appointmentRecordMapper::toResponseDto)
                        .collect(Collectors.toSet())// PENDIENT TO DO
        );
    }

}
