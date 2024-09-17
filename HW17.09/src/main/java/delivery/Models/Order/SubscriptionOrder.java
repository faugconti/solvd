package delivery.Models.Order;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;

import delivery.enums.OrderType;
import delivery.interfaces.Schedulable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SubscriptionOrder extends Order implements Schedulable {

    private static final Logger logger = LogManager.getLogger(SubscriptionOrder.class);

    
    // private LocalDate nextDelivery;
    private Date subscriptionStartDate;
    private LocalDate subscriptionDuration;
    private int frequency; // days in one month
    private Queue<LocalDate> deliveryQueue;

    public SubscriptionOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight,
            LocalDate nextDelivery, Date subscriptionStartDate, LocalDate subscriptionDuration,
            int frequency, DeliveryPerson carrier) {

        super(orderID, sender, creationDate, origin, destination, weight, carrier);
        this.subscriptionDuration = subscriptionDuration;
        this.frequency = frequency;
        this.subscriptionStartDate = subscriptionStartDate;
        this.deliveryQueue = new PriorityQueue<>();
        if (nextDelivery != null) {
            this.scheduleDelivery(nextDelivery);
        }
        super.price = calculatePrice();

    }

    private static class SubscriptionUtils {

        public static boolean checkDates(LocalDate endDate, LocalDate startDate) {
            return startDate.compareTo(endDate) > 0;
        }

    }

    @Override
    public float calculatePrice() {
        return OrderType.SUBSCRIPTION.getBaseCost() * this.frequency;
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
        // transform start date to LOcalDate?
        LocalDate startDate = LocalDate.ofInstant(this.subscriptionStartDate.toInstant(), ZoneId.systemDefault());
        // check if new date is after the start of the contract and before the
        // termination
        if (SubscriptionUtils.checkDates(deliveryDate, this.subscriptionDuration)
                && SubscriptionUtils.checkDates(startDate, deliveryDate)) {
            // this.nextDelivery = deliveryDate;
            deliveryQueue.offer(deliveryDate);
            logger.info("Delivery scheduled for: " + deliveryDate);
        } else {
            logger.warn("New delivery date " + deliveryDate
                    + " exceeds the duration of the Subscription ending @ " + subscriptionDuration);
        }
    }

    @Override
    public LocalDate getScheduledDate() {
        LocalDate nextDelivery = deliveryQueue.peek();
        logger.info("next delivery @"+nextDelivery);
        return nextDelivery;
    }

}
