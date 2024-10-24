package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Hotel {
    @NonNull
    private int id;
    private String description;
    private String address;
    private String name;
}
