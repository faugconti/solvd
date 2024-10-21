package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Excursion {
    @NonNull
    private int idExcursion;
    private String description;
    private String name;
    private int duration;
    private float price;
}
