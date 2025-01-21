package med.voll.api.domain.appointment.validations.cancel;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class AdvanceCancelTimeValidator implements AppointmentCancelValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(CancelDataAppointment data) {
        var schedule = repository.getReferenceById(data.idConsulta());
        var now = LocalDateTime.now();
        var diferenceInHours = Duration.between(now, schedule.getDate()).toHours();

        if(diferenceInHours < 24){
            throw new ValidationExcepetion("Appointments can only be canceled at least 24 hours in advance");
        }
    }
}
