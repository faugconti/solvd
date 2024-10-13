package travelAgency.model;


import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Payment {
    @NonNull
    private int id;
    @NonNull
    private int idCustomer;
    @NonNull
    private int idBooking;
    private LocalDate date;
    private float amount;
    private String paymentMethod;

}
