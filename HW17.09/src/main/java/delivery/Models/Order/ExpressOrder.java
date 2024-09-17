package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.enums.OrderType;

public class ExpressOrder extends Order {

    private int guaranteedTime; // in hours
    private int priorityLevel; // 1-5

    public ExpressOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight,
            int guaranteedTime, DeliveryPerson carrier) {

        super(orderID, sender, creationDate, origin, destination, weight, carrier);
        this.priorityLevel = OrderType.EXPRESS.getPriority();
        this.guaranteedTime = guaranteedTime;
        super.price = calculatePrice();
    }

    // public ExpressOrder(Order order, int priorityLevel, int guaranteedTime) {
    //     super(order.getOrderID(), order.getSender(), order.getCreationDate(), order.getOrigin(),
    //             order.getDestination(), order.getWeight(), order.getCarrier());
    //     this.priorityLevel = priorityLevel;
    //     this.guaranteedTime = guaranteedTime;
    // }

    @Override
    public float calculatePrice() {
        // 5 USD PER KILOGRAM + 3 BASE PRICE

        //return OrderType.EXPRESS.getBaseCost() + (super.getWeight() / 1000) * 5;
        return this.calculateTotalCost.apply(OrderType.EXPRESS.getBaseCost(), 5F);
    }

    public void setGuaranteedTime(int guaranteedTime) {
        this.guaranteedTime = guaranteedTime;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getGuaranteedTime() {
        return guaranteedTime;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public String toString() {
        return super.toString() + " Type: " + "Express Order";
    }

}
