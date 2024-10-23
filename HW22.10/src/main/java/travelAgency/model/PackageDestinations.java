package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class PackageDestinations
{
    @NonNull
    private int idDestination;
    @NonNull
    private int idPackage;
}
