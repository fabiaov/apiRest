package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;

public class MedicalValidatorWithAnotherAppointmentAtTheSameTime {
    private AppointmentRepository repository;

    public void validate (DatasSchedulingAppointment data) {
        var doctorHasAnotherAppointmentAtSameTime = repository.existsByDoctorIdAndData(data.idDoctor(), data.date());
        if(doctorHasAnotherAppointmentAtSameTime) {
            throw new ValidationExcepetion("This doctor already has another appointment scheduled at the same time.");
        }
    }
}
