package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.appointment.validations.cancel.CancelReason;
import med.voll.api.domain.medic.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;

    @Column(name = "cancel_reason")
    @Enumerated(EnumType.STRING)
    private CancelReason cancelReason;

    public void cancel(CancelReason reason) {
        this.cancelReason = reason;
    }
}
