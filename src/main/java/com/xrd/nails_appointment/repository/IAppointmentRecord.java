package com.xrd.nails_appointment.repository;

import com.xrd.nails_appointment.model.AppointmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRecord extends JpaRepository<AppointmentRecord, String> {
}
