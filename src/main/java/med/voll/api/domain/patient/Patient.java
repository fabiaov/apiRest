package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String telephone;
    @Embedded
    private Address address;
    private boolean active;
    public Patient(PatientDataRegistry data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updateInfo(DataPatientUpdate data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.telephone() != null){
            this.telephone = data.telephone();
        }
        if(data.address() != null){
            this.address.updateInfo(data.address());
        }

    }
    public void inactive() {
        this.active = false;
    }
}
