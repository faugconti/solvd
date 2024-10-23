package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class PackageTransport {
    @NonNull
    private int idTransport;
    @NonNull
    private int idPackage;
}
