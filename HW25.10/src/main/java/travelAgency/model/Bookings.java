package travelAgency.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Bookings")
public class Bookings {

    private List<Booking> bookings;

    public Bookings() {
    }

    public Bookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @XmlElement(name = "Booking")
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}