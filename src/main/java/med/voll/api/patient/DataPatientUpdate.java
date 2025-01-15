package med.voll.api.patient;

import jakarta.validation.Valid;
import med.voll.api.address.AddressData;

public record DataPatientUpdate(
        Long id,
        String name,
        String telephone,
        @Valid AddressData address
) {
}
