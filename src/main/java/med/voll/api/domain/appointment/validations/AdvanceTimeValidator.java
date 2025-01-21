package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class AdvanceTimeValidator implements AppointmentScheduleValidator {
    public void validate(DatasSchedulingAppointment data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var diferenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if(diferenceInMinutes < 30) {
            throw new ValidationExcepetion("Consultation must be scheduled at least 30 minutes in advance");
        }
    }
}
