package med.voll.api.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialties specialty;
    @Embedded
    private Address address;

    public Doctor(DataMedicRegistry data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.crm = data.crm();
        this.specialty = data.Specialty();
        this.address = new Address(data.address());
    }
}
