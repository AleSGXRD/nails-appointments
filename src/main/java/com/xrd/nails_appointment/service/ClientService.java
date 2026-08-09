package com.xrd.nails_appointment.service;

import com.xrd.nails_appointment.dto.client.ClientResponseDTO;
import com.xrd.nails_appointment.dto.client.CreateClientDTO;
import com.xrd.nails_appointment.dto.client.UpdateClientDTO;
import com.xrd.nails_appointment.exception.ResourceNotFoundException;
import com.xrd.nails_appointment.mapper.ClientMapper;
import com.xrd.nails_appointment.model.Client;
import com.xrd.nails_appointment.repository.IClientRepository;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final IClientRepository repository;
    private final ClientMapper mapper;

    public ClientResponseDTO create(CreateClientDTO dto){
        Client client = mapper.toEntity(dto);

        Client saved = repository.save(client);

        return mapper.toResponseDTO(saved);
    }

    public Page<ClientResponseDTO> findAll(int page, int size){
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    public ClientResponseDTO findById(String id){
        Client client = repository.findById(id).orElseThrow(
                ()->
                        new ResourceNotFoundException("Not Client found with id: " + id)
        );

        return mapper.toResponseDTO(client);
    }

    public ClientResponseDTO update(String id, UpdateClientDTO dto){
        Client client = repository.findById(id).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not Client found with id: " + id)
        );

        if(dto.name() != null){
            client.setName(dto.name());
        }

        if(dto.telephoneNumber() != null){
            client.setTelephoneNumber(dto.telephoneNumber());
        }

        Client updated = repository.save(client);

        return mapper.toResponseDTO(updated);
    }

    public void delete(String id){
        Client client = repository.findById(id).orElseThrow(
                () ->
                        new ResourceNotFoundException("Not Client found with id: " + id)
        );

        repository.delete(client);
    }
}
