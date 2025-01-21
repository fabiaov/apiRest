package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.appointment.DatasSchedulingAppointment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ClinicalOpeningHoursValidator implements AppointmentScheduleValidator {
    public void validate(DatasSchedulingAppointment data){
        var appointmentDate = data.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpeningClinic = appointmentDate.getHour() < 7;
        var afterClinicClose = appointmentDate.getHour() > 18;
        if (sunday || beforeOpeningClinic || afterClinicClose) {
            throw new ValidationExcepetion("Consultation outside clinic opening hours");
        }
    }
}
