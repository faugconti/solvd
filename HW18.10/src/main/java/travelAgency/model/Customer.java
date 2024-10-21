package travelAgency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.xml.bind.annotation.*;


@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Customer") @XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @NonNull @XmlAttribute
    @JsonProperty("idCustomer")
    private int idCustomer;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;

}
