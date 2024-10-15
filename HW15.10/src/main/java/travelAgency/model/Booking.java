package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @NonNull
    private int idBOoking;
    @NonNull
    private int idCustomer;
    @NonNull
    private int idTrip;
    @NonNull
    private int idEmployee;
    private LocalDate bookingDate;
    private float price;
}
