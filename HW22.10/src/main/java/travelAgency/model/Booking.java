package travelAgency.model;
import lombok.*;
import travelAgency.util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Booking")
@XmlAccessorType(XmlAccessType.FIELD)
public class Booking {
    @NonNull @XmlAttribute
    private int idBooking;
    @NonNull @XmlElement
    private int idCustomer;
    @NonNull @XmlElement
    private int idTrip;
    @NonNull @XmlElement
    private int idEmployee;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate bookingDate;
    @XmlElement
    private double totalPrice;
}
