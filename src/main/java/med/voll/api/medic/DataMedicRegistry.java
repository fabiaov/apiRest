package med.voll.api.medic;

import med.voll.api.address.AddressData;

public record DataMedicRegistry(String name, String email, String crm, Specialties Specialty, AddressData address) {
}
