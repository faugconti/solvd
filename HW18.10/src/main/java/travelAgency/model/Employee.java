package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import travelAgency.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import travelAgency.util.LocalDateDeserializer;
import travelAgency.util.LocalDateSerializer;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Employee") @XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee {
    @NonNull @XmlAttribute
    private int idEmployee;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String email;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate hireDate;
}
