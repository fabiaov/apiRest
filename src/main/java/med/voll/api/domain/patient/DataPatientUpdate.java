package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import med.voll.api.domain.address.AddressData;

public record DataPatientUpdate(
        Long id,
        String name,
        String telephone,
        @Valid AddressData address
) {
}
