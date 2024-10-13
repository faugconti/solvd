package travelAgency.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class PackageHotel {
    @NonNull
    private int idHotel;
    @NonNull
    private int idPackage;
}
