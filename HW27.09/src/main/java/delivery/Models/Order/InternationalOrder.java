package delivery.Models.Order;

import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.enums.OrderStatus;
import delivery.enums.OrderType;
import delivery.interfaces.Trackable;

public class InternationalOrder extends Order implements Trackable {
    protected String trackingNumber;
    private List<String> countryHistory;
    protected String countryDestination;

    public static Supplier<String> generateTrackingNumber = () ->
            "US" + System.currentTimeMillis() + String.format("%04d", (int)(Math.random() * 10000));


    public InternationalOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight, String trackingNumber, String countryDestination) {
        super(orderID, sender, creationDate, origin, destination, weight, null);
        this.trackingNumber = trackingNumber;
        this.countryDestination = countryDestination;
        this.countryHistory = new Stack<>();
        super.price = this.calculatePrice();
        // this.addTrackingEvent(super.getOrigin().getCountry());
        this.countryHistory.add(super.getOrigin().getCountry());
    }

    @Override
    public float calculatePrice() {
        // 35 USD BASE PRICE + 35 USD PER KILOGRAM
        return OrderType.INTERNATIONAL.getBaseCost() + (super.getWeight() / 1000) * 35;
    }

    public String getCountryDestination() {
        return countryDestination;
    }

    public void setCountryDestination(String countryDestination) {
        this.countryDestination = countryDestination;
    }

    public List<String> getCountryHistory() {
        return countryHistory;
    }

    public void setCountryHistory(List<String> countryHistory) {
        this.countryHistory = countryHistory;
    }

    public String gettrackingNumber() {
        return trackingNumber;
    }

    public void settrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " Type: " + "International Order";
    }

    @Override
    public String getTrackingDetails() {
        return String.join(" -> ", countryHistory);
    }

    @Override
    public void addTrackingEvent(String location) {
        // last country is the same as the new one?
        if (!countryHistory.isEmpty() && location != countryHistory.get(countryHistory.size() - 1)) {
            countryHistory.add(location);
        }
        if (this.countryDestination.equals(location)) {
            super.setstatus(OrderStatus.COMPLETED);
        }
    }

}
