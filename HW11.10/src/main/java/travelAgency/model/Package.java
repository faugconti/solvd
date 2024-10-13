package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Package {
    @NonNull
    private int id;
    @NonNull
    private int idTrip;
    private String name;
    private float price;
}
