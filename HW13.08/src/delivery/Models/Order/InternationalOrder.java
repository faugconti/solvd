package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Person.Customer;

public class InternationalOrder extends Order {
    private String trackingNumber;
    private String countryDestination;

    public InternationalOrder(int orderID, Customer sender, Date creationDate, String origin, String destination,
            String trackingNumber, String countryDestination) {
        super(orderID, sender, creationDate, origin, destination);
        this.trackingNumber = trackingNumber;
        this.countryDestination = countryDestination;
    }

    public InternationalOrder(Order order, String trackingNumber, String countryDestination) {
        super(order.getOrderID(), order.getSender(), order.getCreationDate(), order.getOrigin(),
                order.getDestination());
        this.trackingNumber = trackingNumber;
        this.countryDestination = countryDestination;
    }

    public String getCountryDestination() {
        return countryDestination;
    }

    public void setCountryDestination(String countryDestination) {
        this.countryDestination = countryDestination;
    }

    public String gettrackingNumber() {
        return trackingNumber;
    }

    public void settrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+" Type: "+"International Order";
    }

}
