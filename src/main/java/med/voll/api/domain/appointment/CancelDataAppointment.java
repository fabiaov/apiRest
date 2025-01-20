package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancelDataAppointment(
        @NotNull
        Long idConsulta,

        @NotNull
        CancelReason reason
) {
}
