package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medic.Specialties;

import java.time.LocalDateTime;

public record DatasSchedulingAppointment(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date,
        Specialties specialty) {
}
