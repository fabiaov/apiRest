package med.voll.api.domain.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record DataMedicRegistry(
        @NotBlank(message = "name is a mandatory field")
        String name,
        @NotBlank(message = "email is a mandatory field")
        @Email
        String email,
        @NotBlank(message = "telephone is a mandatory field")
        String telephone,
        @NotBlank(message = "CRM is a mandatory field")
        @Pattern(regexp = "\\d{4,6}", message = "CRM format is invalid")
        String crm,
        @NotNull(message = "specialty is a mandatory field")
        Specialties Specialty,
        @NotNull (message = "address is a mandatory field")
        @Valid AddressData address) {
}
