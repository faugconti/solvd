package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @NonNull
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String email;
    private LocalDate hireDate;
}
