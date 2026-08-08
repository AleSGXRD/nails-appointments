package com.xrd.nails_appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne()
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "appointment_services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<NailService> services = new ArrayList<>();

    @OneToMany(
            mappedBy = "appointment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AppointmentRecord> records = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime startTime;

    @CreatedDate
    private LocalDateTime createdAt;

    public int getTotalDurationMinutes() {
        return services.stream()
                .mapToInt(service -> service.getDuration().getMinutes())
                .sum();
    }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(getTotalDurationMinutes());
    }
}
