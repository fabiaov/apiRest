package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import med.voll.api.domain.medic.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveValidator implements AppointmentScheduleValidator{

    @Autowired
    private DoctorRepository repository;

    public void validate(DatasSchedulingAppointment data) {
        if (data.idDoctor() == null) {
            return;
        }
        var doctorIsActive = repository.findActiveById(data.idDoctor());
        if(!doctorIsActive) {
            throw new ValidationExcepetion("Consultation must be scheduled at least 30 minutes in advance!");
        }
    }
}
