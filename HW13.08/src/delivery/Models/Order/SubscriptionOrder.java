package delivery.Models.Order;

import java.time.LocalDate;
import java.util.Date;

import delivery.Models.Person.Customer;

public class SubscriptionOrder extends Order {

    private Date nextDelivery;
    private Date subscriptionStartDate;
    private LocalDate subscriptionDuration;
    private int frequency; // days in one month

    public SubscriptionOrder(int orderID, Customer sender, Date creationDate, String origin, String destination,
            Date nextDelivery, Date subscriptionStartDate, LocalDate subscriptionDuration,
            int frequency) {
        super(orderID, sender, creationDate, origin, destination);
        this.nextDelivery = nextDelivery;
        this.subscriptionDuration = subscriptionDuration;
        this.subscriptionStartDate = subscriptionStartDate;

    }

    public SubscriptionOrder(Order order, Date nextDelivery, Date subscriptionStartDate, LocalDate subscriptionDuration,
            int frequency) {
        super(order.getOrderID(), order.getSender(), order.getCreationDate(), order.getOrigin(),
                order.getDestination());
        this.nextDelivery = nextDelivery;
        this.subscriptionDuration = subscriptionDuration;
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Date getNextDelivery() {
        return nextDelivery;
    }

    public void setNextDelivery(Date nextDelivery) {
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
        return super.toString()+" Type: "+"Subscription Order";
    }


}
