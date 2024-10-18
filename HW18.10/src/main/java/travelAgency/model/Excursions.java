package travelAgency.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Excursions")
public class Excursions {

    private List<Excursion> excursions;

    public Excursions() {
    }

    public Excursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    @XmlElement(name = "Excursion")
    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }
}