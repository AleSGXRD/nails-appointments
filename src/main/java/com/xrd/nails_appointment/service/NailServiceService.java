package com.xrd.nails_appointment.service;

import com.xrd.nails_appointment.dto.nail_service.CreateNailServiceDTO;
import com.xrd.nails_appointment.dto.nail_service.NailServiceResponseDTO;
import com.xrd.nails_appointment.dto.nail_service.UpdateNailServiceDTO;
import com.xrd.nails_appointment.exception.ResourceNotFoundException;
import com.xrd.nails_appointment.mapper.NailServiceMapper;
import com.xrd.nails_appointment.model.NailService;
import com.xrd.nails_appointment.repository.INailServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@AllArgsConstructor
public class NailServiceService {

    private INailServiceRepository repository;
    private NailServiceMapper mapper;

    public NailServiceResponseDTO create(CreateNailServiceDTO dto){
        NailService nailService = mapper.toEntity(dto);

        NailService saved = repository.save(nailService);

        return mapper.toResponseDto(saved);
    }

    public Page<NailServiceResponseDTO> findAll(int page, int size){
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return repository.findAll(pageable).map(mapper::toResponseDto);
    }

    public NailServiceResponseDTO findOne(String id){
        NailService nailService = repository.findById(id)
                .orElseThrow(
                    () ->
                            new ResourceNotFoundException("Nail Service not found with id : " + id)
                );

        return mapper.toResponseDto(nailService);
    }

    public NailServiceResponseDTO update(String id, UpdateNailServiceDTO dto){
        NailService nailService = repository.findById(id).
                orElseThrow(
                        () ->
                                new ResourceNotFoundException("Nail Service not found with id : " + id)
                );


        if (dto.name() != null) {
            nailService.setName(dto.name());
        }

        if (dto.price() != null) {
            nailService.setPrice(dto.price());
        }

        if (dto.duration() != null) {
            nailService.setDuration(dto.duration());
        }


        NailService updated = repository.save(nailService);

        return mapper.toResponseDto(updated);
    }

    public void delete(String id){
        NailService nailService = repository.findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException("Nail Service not found with id : " + id)
                );

        repository.delete(nailService);
    }
}
