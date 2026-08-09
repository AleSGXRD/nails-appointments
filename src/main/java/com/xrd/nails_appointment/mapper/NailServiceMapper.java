package com.xrd.nails_appointment.mapper;

import com.xrd.nails_appointment.dto.nail_service.CreateNailServiceDTO;
import com.xrd.nails_appointment.dto.nail_service.NailServiceResponseDTO;
import com.xrd.nails_appointment.dto.nail_service.UpdateNailServiceDTO;
import com.xrd.nails_appointment.model.NailService;
import org.springframework.stereotype.Component;

@Component
public class NailServiceMapper {

    public NailService toEntity(CreateNailServiceDTO dto){
        return NailService.builder()
                .name(dto.name())
                .price(dto.price())
                .duration(dto.duration())
                .build();
    }

    public NailService toEntity(UpdateNailServiceDTO dto){
        return NailService.builder()
                .name(dto.name())
                .price(dto.price())
                .duration(dto.duration())
                .build();
    }

    public NailServiceResponseDTO toResponseDto(NailService entity){
        return new NailServiceResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDuration()
        );
    }
}
