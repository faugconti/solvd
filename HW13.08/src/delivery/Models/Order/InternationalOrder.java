package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;

public class InternationalOrder extends Order {
    private String trackingNumber;
    private String countryDestination;

    public InternationalOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight, String trackingNumber, String countryDestination) {
        super(orderID, sender, creationDate, origin, destination, weight);
        this.trackingNumber = trackingNumber;
        this.countryDestination = countryDestination;
        super.price = this.calculatePrice();
    }

    // public InternationalOrder(Order order, String trackingNumber, String
    // countryDestination) {
    // super(order.getOrderID(), order.getSender(), order.getCreationDate(),
    // order.getOrigin(),
    // order.getDestination(),order.getPrice());
    // this.trackingNumber = trackingNumber;
    // this.countryDestination = countryDestination;
    // }

    @Override
    public float calculatePrice() {
        // 35 USD BASE PRICE + 35 USD PER KILOGRAM
        float price = 35 + (super.getWeight() / 1000) * 35;
        // super.setPrice(price);
        return price;
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
        return super.toString() + " Type: " + "International Order";
    }

}
