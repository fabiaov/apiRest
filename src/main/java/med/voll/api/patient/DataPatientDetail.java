package med.voll.api.patient;

import med.voll.api.address.Address;
import med.voll.api.medic.DataDetailDoctor;

public record DataPatientDetail(String name, String email, String telephone, String cpf, Address address) {
    public DataPatientDetail(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getTelephone(), patient.getCpf(), patient.getAddress());
    }
}
