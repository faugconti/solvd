package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Destination {
    @NonNull
    private int idDestination;
    private String country;
    private String description;
    private String name;
}
