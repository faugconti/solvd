package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Destination {
    @NonNull
    private int id;
    private String country;
    private String description;
    private String name;
}
