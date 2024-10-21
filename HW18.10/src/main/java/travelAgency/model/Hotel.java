package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Hotel {
    @NonNull
    private int idHotel;
    private String description;
    private String address;
    private String name;
}
