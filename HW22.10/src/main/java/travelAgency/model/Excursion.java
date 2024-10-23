package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Excursion {
    @NonNull @JsonProperty("idExcursion")
    private int idExcursion;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("price")
    private float price;
}
