package travelAgency.model;

import lombok.*;
import javax.xml.bind.annotation.*;


@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Customer") @XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @NonNull @XmlAttribute
    private int idCustomer;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
