package com.xrd.nails_appointment.controller;

import com.xrd.nails_appointment.dto.client.ClientResponseDTO;
import com.xrd.nails_appointment.dto.client.CreateClientDTO;
import com.xrd.nails_appointment.dto.client.UpdateClientDTO;
import com.xrd.nails_appointment.model.Client;
import com.xrd.nails_appointment.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping()
    public ClientResponseDTO create(@Valid @RequestBody CreateClientDTO dto){
        return clientService.create(dto);
    }

    @GetMapping()
    public Page<ClientResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return clientService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public ClientResponseDTO findOne(
            @PathVariable String id
    ){
        return clientService.findById(id);
    }

    @PatchMapping("/{id}")
    public ClientResponseDTO update(
            @PathVariable String id,
            @Valid @RequestBody UpdateClientDTO dto
        ){
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ){
        clientService.delete(id);
    }
}
