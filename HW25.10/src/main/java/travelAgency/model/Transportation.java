package travelAgency.model;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Transportation {
    @NonNull
    private int idTransport;
    private String description;
    private String company;
    private String type;
    private String destination;
    private String flightNumber;
    private float price;
    private LocalDate departureDate;
}
