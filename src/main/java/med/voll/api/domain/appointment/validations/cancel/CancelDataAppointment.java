package med.voll.api.domain.appointment.validations.cancel;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.appointment.validations.cancel.CancelReason;

public record CancelDataAppointment(
        @NotNull
        Long idConsulta,

        @NotNull
        CancelReason cancelReason
) {
}
