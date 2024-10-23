package travelAgency.model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Rating {
    @NonNull
    private int idRating;
    @NonNull
    private int idTrip;
    @NonNull
    private int idCustomer;
    private int rating;
    private String commentary;
    private LocalDate date;
}
