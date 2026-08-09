package com.xrd.nails_appointment.mapper;

import com.xrd.nails_appointment.dto.client.ClientResponseDTO;
import com.xrd.nails_appointment.dto.client.CreateClientDTO;
import com.xrd.nails_appointment.dto.client.UpdateClientDTO;
import com.xrd.nails_appointment.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(CreateClientDTO dto){
        return Client.builder()
                .name(dto.name())
                .telephoneNumber(dto.telephoneNumber())
                .build();
    }

    public Client toEntity(UpdateClientDTO dto){
        return Client.builder()
                .name(dto.name())
                .telephoneNumber(dto.telephoneNumber())
                .build();
    }

    public ClientResponseDTO toResponseDTO(Client entity){
        return new ClientResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getTelephoneNumber()
        );
    }
}
