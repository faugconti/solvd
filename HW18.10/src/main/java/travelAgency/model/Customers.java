package travelAgency.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Customers")
public class Customers {

    private List<Customer> customers;

    public Customers() {
    }

    public Customers(List<Customer> customers) {
        this.customers = customers;
    }

    @XmlElement(name = "Customer")
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}