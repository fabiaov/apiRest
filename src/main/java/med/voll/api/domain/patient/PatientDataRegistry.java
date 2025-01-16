package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record PatientDataRegistry(
        @NotBlank(message = "name is a mandatory field")
        String name,
        @NotBlank(message = "email is a mandatory field")
        @Email String email,
        @NotBlank(message = "Telephone is a mandatory field")
        String telephone,
        @NotBlank(message = "cpf is a mandatory field")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String cpf,
        @NotNull(message = "address is a mandatory field")
        @Valid AddressData address
) {
}
