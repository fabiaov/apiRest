package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DataDetailAppointment(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date) {
    public DataDetailAppointment(Appointment schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(),schedule.getPatient().getId(), schedule.getDate());
    }
}
