package com.xrd.nails_appointment.service;

import com.xrd.nails_appointment.dto.appointment.AppointmentResponseDTO;
import com.xrd.nails_appointment.dto.appointment.CreateAppointmentDTO;
import com.xrd.nails_appointment.dto.appointment.UpdateAppointmentDTO;
import com.xrd.nails_appointment.exception.ResourceNotFoundException;
import com.xrd.nails_appointment.mapper.AppointmentMapper;
import com.xrd.nails_appointment.model.Appointment;
import com.xrd.nails_appointment.model.Client;
import com.xrd.nails_appointment.model.NailService;
import com.xrd.nails_appointment.repository.IAppointmentRepository;
import com.xrd.nails_appointment.repository.IClientRepository;
import com.xrd.nails_appointment.repository.INailServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final IAppointmentRepository appointmentRepository;
    private final IClientRepository clientRepository;
    private final INailServiceRepository nailServiceRepository;
    private final AppointmentMapper mapper;

    public AppointmentResponseDTO create(CreateAppointmentDTO dto){
        Client client = clientRepository.findById(dto.clientId()).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not found Client with id: " + dto.clientId())
        );

        ArrayList<NailService> nailServices = new ArrayList<NailService>();

        for(var i = 0; i < dto.servicesId().size(); i++){
            String serviceId = dto.servicesId().get(i);
            NailService service = nailServiceRepository.findById(serviceId).orElseThrow(
                    () ->
                            new ResourceNotFoundException("Not found Nail Service with id : " + serviceId)
            );

            nailServices.add(service);
        }


        Appointment entity = mapper.toEntity(dto, client, nailServices, new HashSet<>());

        Appointment saved = appointmentRepository.save(entity);

        return mapper.toResponseDTO(saved);
    }

    public Page<AppointmentResponseDTO> findAll(int page, int size){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return appointmentRepository.findAll(pageable).map(mapper::toResponseDTO);
    }

    public AppointmentResponseDTO findById(String id){
        Appointment entity = appointmentRepository.findByIdWithDetails(id).orElseThrow(
                () ->
                        new ResourceNotFoundException("Appointment not found with id : " + id)
        );

        return mapper.toResponseDTO(entity);
    }

    public AppointmentResponseDTO update(String id, UpdateAppointmentDTO dto){
        Appointment entity = appointmentRepository.findByIdWithDetails(id).orElseThrow(
                () ->
                        new ResourceNotFoundException("Appointment not found with id : " + id)
        );

        if(dto.clientId() != null){
            String oldClientId = entity.getClient().getId();
            if(!oldClientId.equals(dto.clientId())){
                Client client = clientRepository.findById(dto.clientId()).orElseThrow(
                        () ->
                                new ResourceNotFoundException("Client not found with id : " + id)
                );

                entity.setClient(client);
            }
        }

        if(dto.servicesId() != null && !dto.servicesId().isEmpty()){
            ArrayList<NailService> newServices = new ArrayList<>();
            for(var i = 0; i < dto.servicesId().size(); i++){
                String serviceId = dto.servicesId().get(i);
                NailService service = nailServiceRepository.findById(serviceId).orElseThrow(
                        () ->
                                new ResourceNotFoundException("Not found Nail Service with id : " + serviceId)
                );

                newServices.add(service);
            }

            entity.setServices(newServices);
        }

        if(dto.date() != null){
            entity.setDate(dto.date());
        }
        if(dto.startTime() != null){
            entity.setStartTime(dto.startTime());
        }

        Appointment updated = appointmentRepository.save(entity);
        // TODO : Records still implemented yet
        return mapper.toResponseDTO(updated);
    }

    public void delete(String id){
        Appointment entity = appointmentRepository.findById(id).orElseThrow(
                () ->
                        new ResourceNotFoundException("Appointment not found with id : " + id)
        );

        appointmentRepository.delete(entity);
    }
}
