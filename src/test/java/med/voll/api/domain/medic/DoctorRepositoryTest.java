package med.voll.api.domain.medic;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDataRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Should return null when the only doctor isn't available in the date")
    void chooseRandomDoctorFreeOnDateFirstScenario() {
        var nexMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = doctorRegistry("Doctor", "doctor@voll.med", "123456", Specialties.CARDIOLOGY);
        var patient = patientRegistry("Patient", "patient@email.com", "00000000000");
        appointmentRegistry(doctor, patient,nexMondayAt10);
        var doctorFree = doctorRepository.chooseRandomDoctorFreeOnDate(Specialties.CARDIOLOGY, nexMondayAt10);
        assertThat(doctorFree).isNull();
    }
    @Test
    @DisplayName("Should return doctor when available in date")
    void chooseRandomDoctorFreeOnDateSecondScenario() {
        var nexMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = doctorRegistry("Doctor", "doctor@voll.med", "123456", Specialties.CARDIOLOGY);
        var doctorFree = doctorRepository.chooseRandomDoctorFreeOnDate(Specialties.CARDIOLOGY, nexMondayAt10);
        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void appointmentRegistry(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(Appointment.builder().doctor(doctor).patient(patient).date(date).build());
    }

    private Doctor doctorRegistry(String name, String email, String crm, Specialties specialty) {
        var doctor = new Doctor(dataMedic(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient patientRegistry(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DataMedicRegistry dataMedic(String name, String email, String crm, Specialties specialty) {
        return new DataMedicRegistry(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private PatientDataRegistry dataPatient (String name, String email, String cpf) {
        return new PatientDataRegistry(
                name,
                email,
                "61999999999",
                cpf,
                dataAddress()
        );
    }

    private AddressData dataAddress() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}