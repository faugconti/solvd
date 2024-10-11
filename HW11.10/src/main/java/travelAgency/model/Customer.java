package travelAgency.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @NonNull
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String phone;

}
