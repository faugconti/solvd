package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Hotel {
    @NonNull @JsonProperty("idHotel")
    private int idHotel;
    @JsonProperty("description")
    private String description;
    @JsonProperty("address")
    private String address;
    @JsonProperty("name")
    private String name;
}
