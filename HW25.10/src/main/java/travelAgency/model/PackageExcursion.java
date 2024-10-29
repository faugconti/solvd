package travelAgency.model;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class PackageExcursion {
    @NonNull
    private int idExcursion;
    @NonNull
    private int idPackage;
}
