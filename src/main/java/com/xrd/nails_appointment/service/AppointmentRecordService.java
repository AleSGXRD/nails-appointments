package com.xrd.nails_appointment.service;

import com.xrd.nails_appointment.dto.appointment_record.AppointmentRecordResponseDTO;
import com.xrd.nails_appointment.dto.appointment_record.CreateAppointmentRecordDTO;
import com.xrd.nails_appointment.dto.appointment_record.UpdateAppointmentRecordDTO;
import com.xrd.nails_appointment.exception.ResourceNotFoundException;
import com.xrd.nails_appointment.mapper.AppointmentRecordMapper;
import com.xrd.nails_appointment.model.Appointment;
import com.xrd.nails_appointment.model.AppointmentRecord;
import com.xrd.nails_appointment.repository.IAppointmentRecordRepository;
import com.xrd.nails_appointment.repository.IAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentRecordService {
    private final AppointmentRecordMapper mapper;
    private final IAppointmentRecordRepository repository;

    private final IAppointmentRepository appointmentRepository;
    private final ImageStorageService imageStorageService;

    public AppointmentRecordResponseDTO create(
            String appointmentId,
            CreateAppointmentRecordDTO dto,
            MultipartFile image
    ) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not found Appointment with id : " + appointmentId)
        );

        String imageUrl = null;

        try {
            imageUrl = imageStorageService.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image", e);
        }

        AppointmentRecord entity = mapper.toEntity(dto, appointment);
        entity.setImageUrl(imageUrl);

        AppointmentRecord saved = repository.save(entity);

        return mapper.toResponseDto(saved);
    }

    public AppointmentRecordResponseDTO update(
            String appointmentId,
            String appointmentRecordId,
            UpdateAppointmentRecordDTO dto,
            MultipartFile image
    ) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not found Appointment with id : " + appointmentId)
        );

        AppointmentRecord record = repository.findById(appointmentRecordId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not found Record with id : " + appointmentRecordId)
        );

        if (!record.getAppointment().getId().equals(appointment.getId())) {
            throw new ResourceNotFoundException(
                    "Record does not belong to this appointment"
            );
        }

        if(image != null && !image.isEmpty()){
            String imageUrl = null;
            String oldImageUrl = record.getImageUrl();

            try {
                imageUrl = imageStorageService.save(image);

                record.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Could not save image", e);
            }

            if(oldImageUrl != null && !oldImageUrl.isBlank()){
                boolean deleted = imageStorageService.delete(oldImageUrl);

                if(deleted){
                    System.out.println("An image has not possible to be deleted");
                }
            }
        }

        if(dto.type() != null){
            record.setType(dto.type());
        }

        if (dto.notes() != null){
            record.setNotes(dto.notes());
        }

        AppointmentRecord saved = repository.save(record);

        return mapper.toResponseDto(saved);
    }

    public void delete(String appointmentId, String appointmentRecordId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not found Appointment with id : " + appointmentId)
        );

        AppointmentRecord record = repository.findById(appointmentRecordId).orElseThrow(
                () ->
                        new ResourceNotFoundException("Appointment record not found with id : " + appointmentRecordId)
        );

        if (!record.getAppointment().getId().equals(appointment.getId())) {
            throw new ResourceNotFoundException(
                    "Record does not belong to this appointment"
            );
        }

        boolean deleted = imageStorageService.delete(record.getImageUrl());

        if(deleted)
            repository.delete(record);
    }
}
