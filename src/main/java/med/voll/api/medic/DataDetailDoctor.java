package med.voll.api.medic;

import med.voll.api.address.Address;

public record DataDetailDoctor(Long id, String name, String email, String crm, String telephone, Specialties Specialty, Address address) {
    public DataDetailDoctor (Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getEmail(), doctor.getTelephone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
