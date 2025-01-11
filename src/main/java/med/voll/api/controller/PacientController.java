package med.voll.api.controller;

import med.voll.api.address.AddressData;

public record PacientController(
        String name,
        String email,
        String telephone,
        String cpf,
        AddressData address
) {
}
