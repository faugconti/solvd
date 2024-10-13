package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @NonNull
    private int id;
    @NonNull
    private int idCustomer;
    @NonNull
    private int idTrip;
    @NonNull
    private int idEmployee;
    private LocalDate date;
    private float price;
}
