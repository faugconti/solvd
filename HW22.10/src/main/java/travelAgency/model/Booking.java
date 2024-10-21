package travelAgency.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import travelAgency.util.LocalDateAdapter;
import travelAgency.util.LocalDateDeserialize;
import travelAgency.util.LocalDateSerializer;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@XmlRootElement(name = "Booking")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)  @JsonDeserialize(using = LocalDateDeserialize.class) @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate bookingDate;
    @XmlElement
    private double totalPrice;
}
