package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {


    boolean existsByDoctorIdAndDataAndCancelReasonIsNull(Long idMedico, LocalDateTime date);

    boolean existsByDoctorIdAndData(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
