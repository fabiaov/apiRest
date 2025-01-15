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
}
