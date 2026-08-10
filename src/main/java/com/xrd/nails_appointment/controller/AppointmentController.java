package com.xrd.nails_appointment.controller;

import com.xrd.nails_appointment.dto.appointment.AppointmentResponseDTO;
import com.xrd.nails_appointment.dto.appointment.CreateAppointmentDTO;
import com.xrd.nails_appointment.dto.appointment.UpdateAppointmentDTO;
import com.xrd.nails_appointment.model.Appointment;
import com.xrd.nails_appointment.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @PostMapping
    public AppointmentResponseDTO create(@Valid @RequestBody CreateAppointmentDTO dto){
        return service.create(dto);
    }

    @GetMapping
    public Page<AppointmentResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.findAll(page, size);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO findById(
            @PathVariable String id
    ){
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public AppointmentResponseDTO update(
            @PathVariable String id,
            @Valid @RequestBody UpdateAppointmentDTO dto
            ){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ){
        service.delete(id);
    }

}
