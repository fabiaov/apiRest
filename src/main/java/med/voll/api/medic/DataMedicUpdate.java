package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record DataMedicUpdate(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressData address

) {
}
