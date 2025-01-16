package med.voll.api.domain.medic;

public record DataListDoctor(
       Long id,
       String name,
       String email,
       String crm,
       Specialties Specialty
) {
    public DataListDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
