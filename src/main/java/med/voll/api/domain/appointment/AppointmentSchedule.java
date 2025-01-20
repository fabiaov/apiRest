package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.medic.Doctor;
import med.voll.api.domain.medic.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void schedule(DatasSchedulingAppointment data) {
        if (!patientRepository.existsById(data.idPatient())){
            throw new ValidationExcepetion("Patient Id informed does not exists");
        }
        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationExcepetion("Doctor Id informed does not exists");
        }
        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);
        var schedule = new Appointment(null, doctor, patient, data.date(),null);

        appointmentRepository.save(schedule);
    }

    private Doctor chooseDoctor(DatasSchedulingAppointment data) {
        if (data.idDoctor() != null){
            doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null) {
            throw new ValidationExcepetion("Specialty field is mandatory when doctor isn't chosen!");
        }
        return doctorRepository.chooseRandomDoctorFreeOnDate(data.specialty(), data.date());
    }

    public void cancel(CancelDataAppointment data) {
        if (!appointmentRepository.existsById(data.idConsulta())) {
            throw new ValidationExcepetion("Appointment id entered does not exist!");
        }
        var schedule = appointmentRepository.getReferenceById(data.idConsulta());
        schedule.cancel(data.reason());
    }
}
