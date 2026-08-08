package com.xrd.nails_appointment.repository;

import com.xrd.nails_appointment.model.NailService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INailService extends JpaRepository<NailService, String> {
}
