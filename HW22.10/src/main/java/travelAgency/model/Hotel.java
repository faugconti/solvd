package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Hotel {
    @NonNull
    private int idHotel;
    private String description;
    private String address;
    private String name;
}
