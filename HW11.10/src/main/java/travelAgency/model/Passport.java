package travelAgency.model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Passport {
    @NonNull
    private int id;
    @NonNull
    private int idCustomer;
    private String passportNumber;
    private String nationality;
    private LocalDate issueDate;
    private LocalDate expirationDate;

}
