package com.xrd.nails_appointment.repository;

import com.xrd.nails_appointment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {
}
