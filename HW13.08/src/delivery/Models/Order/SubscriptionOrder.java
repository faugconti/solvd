package delivery.Models.Order;

import java.time.LocalDate;
import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;

public class SubscriptionOrder extends Order {

    private LocalDate nextDelivery;
    private Date subscriptionStartDate;
    private LocalDate subscriptionDuration;
    private int frequency; // days in one month

    public SubscriptionOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight,
            LocalDate nextDelivery, Date subscriptionStartDate, LocalDate subscriptionDuration,
            int frequency) {
        super(orderID, sender, creationDate, origin, destination, weight);
        this.nextDelivery = nextDelivery;
        this.subscriptionDuration = subscriptionDuration;
        this.subscriptionStartDate = subscriptionStartDate;

    }

    // public SubscriptionOrder(Order order, Date nextDelivery, Date
    // subscriptionStartDate, LocalDate subscriptionDuration,
    // int frequency) {
    // super(order.getOrderID(), order.getSender(), order.getCreationDate(),
    // order.getOrigin(),
    // order.getDestination(),get);
    // this.nextDelivery = nextDelivery;
    // this.subscriptionDuration = subscriptionDuration;
    // this.subscriptionStartDate = subscriptionStartDate;
    // }

    @Override
    public float calculatePrice() {
        // TODO Auto-generated method stub
        float price = 3 * this.frequency;
        // super.setPrice(price);
        return price;
    }

    public LocalDate getNextDelivery() {
        return nextDelivery;
    }

    public void setNextDelivery(LocalDate nextDelivery) {
        this.nextDelivery = nextDelivery;
    }

    public LocalDate getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(LocalDate subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " Type: " + "Subscription Order";
    }

}
