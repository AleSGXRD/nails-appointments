package com.xrd.nails_appointment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EntityListeners(EntityListeners.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentRecordType type;
    private String notes;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}
