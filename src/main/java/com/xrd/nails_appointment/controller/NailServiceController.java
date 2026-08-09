package com.xrd.nails_appointment.controller;

import com.xrd.nails_appointment.dto.nail_service.CreateNailServiceDTO;
import com.xrd.nails_appointment.dto.nail_service.NailServiceResponseDTO;
import com.xrd.nails_appointment.dto.nail_service.UpdateNailServiceDTO;
import com.xrd.nails_appointment.service.NailServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nail-services")
@RequiredArgsConstructor
public class NailServiceController {
    private final NailServiceService nailService;

    @PostMapping
    public NailServiceResponseDTO create(
            @Valid @RequestBody CreateNailServiceDTO dto
            ){
        return nailService.create(dto);
    }

    @GetMapping
    public Page<NailServiceResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return nailService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public NailServiceResponseDTO findById(@PathVariable String id) {
        return nailService.findOne(id);
    }

    @PatchMapping("/{id}")
    public NailServiceResponseDTO update(
            @PathVariable String id,
            @Valid @RequestBody UpdateNailServiceDTO dto
        ){
        return nailService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        nailService.delete(id);
    }
}
