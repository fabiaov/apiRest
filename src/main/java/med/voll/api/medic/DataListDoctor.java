package med.voll.api.medic;

public record DataListDoctor(
       String name,
       String email,
       String crm,
       Specialties Specialty
) {
    public DataListDoctor(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
