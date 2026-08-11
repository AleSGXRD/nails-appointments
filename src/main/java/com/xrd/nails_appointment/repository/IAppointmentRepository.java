package com.xrd.nails_appointment.repository;

import com.xrd.nails_appointment.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, String> {
    @EntityGraph(attributePaths = {
            "client",
            "services",
            "records"
    })
    Page<Appointment> findAll(Pageable pageable);

    @Query("""
        SELECT DISTINCT a
        FROM Appointment a
        LEFT JOIN FETCH a.client
        LEFT JOIN FETCH a.services
        LEFT JOIN FETCH a.records
        WHERE a.id = :id
    """)
    Optional<Appointment> findByIdWithDetails(String id);
}
