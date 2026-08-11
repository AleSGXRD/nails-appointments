package com.xrd.nails_appointment.controller;

import com.xrd.nails_appointment.dto.appointment_record.AppointmentRecordResponseDTO;
import com.xrd.nails_appointment.dto.appointment_record.CreateAppointmentRecordDTO;
import com.xrd.nails_appointment.dto.appointment_record.UpdateAppointmentRecordDTO;
import com.xrd.nails_appointment.model.AppointmentRecordType;
import com.xrd.nails_appointment.service.AppointmentRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentRecordController {

    private final AppointmentRecordService appointmentRecordService;

    @PostMapping(
            value = "/{appointmentId}/records",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AppointmentRecordResponseDTO create(
            @PathVariable String appointmentId,
            @RequestPart("image") MultipartFile image,
            @RequestPart("type") String type,
            @RequestPart(value = "notes", required = false) String notes
            ){
        CreateAppointmentRecordDTO dto =
                new CreateAppointmentRecordDTO(
                        AppointmentRecordType.valueOf(type),
                        notes
                );

        return appointmentRecordService.create(appointmentId, dto, image);
    }

    @PatchMapping(
            value = "/{appointmentId}/records/{appointmentRecordId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AppointmentRecordResponseDTO update(
            @PathVariable String appointmentId,
            @PathVariable String appointmentRecordId,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "type", required = false) String type,
            @RequestPart(value = "notes", required = false) String notes
    ){
        UpdateAppointmentRecordDTO dto =
                new UpdateAppointmentRecordDTO(
                        type != null ? AppointmentRecordType.valueOf(type) : null,
                        notes
                );

        return appointmentRecordService.update(appointmentId, appointmentRecordId, dto, image);
    }

    @DeleteMapping(
            value = "/{appointmentId}/records/{appointmentRecordId}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String appointmentId,
            @PathVariable String appointmentRecordId
    ){
        appointmentRecordService.delete(appointmentId, appointmentRecordId);
    }
}
