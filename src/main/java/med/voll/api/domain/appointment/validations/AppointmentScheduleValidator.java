package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.DatasSchedulingAppointment;

public interface AppointmentScheduleValidator {
    void validate(DatasSchedulingAppointment data);
}
