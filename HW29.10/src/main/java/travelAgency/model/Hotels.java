package travelAgency.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Hotels")
public class Hotels {

    private List<Hotel> hotels;

    public Hotels() {
    }

    public Hotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @XmlElement(name = "Hotel")
    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}