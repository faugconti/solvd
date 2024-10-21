package travelAgency.model;
import lombok.*;
import travelAgency.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Employee") @XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @NonNull @XmlAttribute
    private int idEmployee;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String email;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate hireDate;
}
