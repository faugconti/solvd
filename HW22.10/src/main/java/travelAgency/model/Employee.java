package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import travelAgency.util.LocalDateAdapter;
import travelAgency.util.LocalDateDeserialize;
import travelAgency.util.LocalDateSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Employee") @XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    @NonNull @XmlAttribute @JsonProperty("idEmployee")
    private int idEmployee;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("role")
    private String role;
    @JsonProperty("email")
    private String email;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)  @JsonDeserialize(using = LocalDateDeserialize.class) @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate hireDate;
}
