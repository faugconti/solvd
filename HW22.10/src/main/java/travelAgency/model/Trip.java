package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Trip {
    @NonNull
    private int idTrip;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
