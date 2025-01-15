package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String publicPlace;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Address(AddressData data) {
        this.publicPlace = data.publicPlace();
        this.neighborhood = data.neighborhood();
        this.zipCode = data.zipCode();
        this.city = data.city();
        this.uf = data.uf();
        this.complement = data.complement();
        this.number = data.number();
    }

    public void updateInfo(AddressData data) {
        if(data.publicPlace() != null) {
            this.publicPlace = data.publicPlace();
        }
        if(data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if(data.zipCode() != null) {
            this.zipCode = data.zipCode();
        }
        if(data.city() != null) {
            this.city = data.city();
        }
        if(data.uf() != null) {
            this.uf = data.uf();
        }
        if(data.complement() != null) {
            this.complement = data.complement();
        }
        if(data.number() != null) {
            this.number = data.number();
        }
    }
}
