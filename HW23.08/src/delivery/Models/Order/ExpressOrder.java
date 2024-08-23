package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;

public class ExpressOrder extends Order {

    private int guaranteedTime; // in hours
    private int priorityLevel; // 1-5

    public ExpressOrder(int orderID, Customer sender, Date creationDate, Address origin, Address destination,
            float weight,
            int guaranteedTime, int priorityLevel, DeliveryPerson carrier) {

        super(orderID, sender, creationDate, origin, destination, weight, carrier);
        this.priorityLevel = priorityLevel;
        this.guaranteedTime = guaranteedTime;
        super.price = calculatePrice();
    }

    @Override
    public float calculatePrice() {
        // 5 USD PER KILOGRAM + 3 BASE PRICE
        float price = 3.0F + (super.getWeight() / 1000) * 5;
        // super.setPrice(price);
        return price;
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
