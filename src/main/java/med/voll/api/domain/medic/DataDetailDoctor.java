package med.voll.api.domain.medic;

import med.voll.api.domain.address.Address;

public record DataDetailDoctor(Long id, String name, String email, String crm, String telephone, Specialties Specialty, Address address) {
    public DataDetailDoctor (Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getEmail(), doctor.getTelephone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
