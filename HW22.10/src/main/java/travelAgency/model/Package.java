package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Package {
    @NonNull
    private int idPackage;
    @NonNull
    private int idTrip;
    private String name;
    private float price;
}
