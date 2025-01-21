package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import med.voll.api.domain.patient.PatientRepository;

public class PatientActiveValidator {
    private PatientRepository repository;

    public void validate(DatasSchedulingAppointment data) {
        var patientIsActive = repository.findActiveById(data.idPatient());
        if (!patientIsActive) {
            throw new ValidationExcepetion("Appointment cannot be scheduled with an excluded patient");
        }
    }
}
