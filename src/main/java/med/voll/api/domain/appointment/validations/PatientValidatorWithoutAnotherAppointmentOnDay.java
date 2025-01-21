package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;

public class PatientValidatorWithoutAnotherAppointmentOnDay {
    private AppointmentRepository repository;

    public void validate (DatasSchedulingAppointment data) {
        var firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);
        var patientHasAnotherAppointmentThisDay = repository.existsByPatientIdAndDataBetween(data.idPatient(),firstTime, lastTime);
        if (patientHasAnotherAppointmentThisDay) {
            throw new ValidationExcepetion("Patient already has an appointment scheduled that day");
        }
    }
}
