package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Excursion {
    @NonNull
    private int id;
    private String description;
    private String name;
    private int duration;
    private float price;
}
