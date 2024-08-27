package delivery.Models.Order;

import java.time.LocalDate;
import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.interfaces.Schedulable;

public class SubscriptionOrder extends Order implements Schedulable {

    private LocalDate nextDelivery;
    private Date subscriptionStartDate;
    private LocalDate subscriptionDuration;
    private int frequency; // days in one month

    public SubscriptionOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight,
            LocalDate nextDelivery, Date subscriptionStartDate, LocalDate subscriptionDuration,
            int frequency, DeliveryPerson carrier) {
        super(orderID, sender, creationDate, origin, destination, weight, carrier);
        this.nextDelivery = nextDelivery;
        this.subscriptionDuration = subscriptionDuration;
        this.frequency = frequency;
        this.subscriptionStartDate = subscriptionStartDate;
        super.price = calculatePrice();

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

    private static class SubscriptionUtils {

        public static boolean checkDates(LocalDate endDate, LocalDate startDate) {
            return startDate.compareTo(endDate) > 0;
        }

    }

    @Override
    public float calculatePrice() {
        // TODO Auto-generated method stub
        float price = 3.0F * this.frequency;
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
        return super.toString() + " Type: " + "Subscription Order";
    }

    @Override
    public void scheduleDelivery(LocalDate deliveryDate) {
        if (SubscriptionUtils.checkDates(deliveryDate, this.subscriptionDuration)) {
            this.nextDelivery = deliveryDate;
            // System.out.println("Delivery scheduled for: " + this.getScheduledDate());
        } else {
            System.out.println("New delivery date " + deliveryDate
                    + " exceeds the duration of the Subscription ending @ " + subscriptionDuration);
        }
    }

    @Override
    public LocalDate getScheduledDate() {
        if (this.nextDelivery != null) {
            System.out.println("Next delivery will be @" + nextDelivery);
            return nextDelivery;
        } else
            return null;
    }

}
