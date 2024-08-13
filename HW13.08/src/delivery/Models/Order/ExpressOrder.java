package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Person.Customer;

public class ExpressOrder extends Order {

    private int guaranteedTime; // in hours
    private int priorityLevel; // 1-5

    public ExpressOrder(int orderID, Customer sender, Date creationDate, String origin, String destination,
            int guaranteedTime, int priorityLevel) {

        super(orderID, sender, creationDate, origin, destination);
        this.priorityLevel = priorityLevel;
        this.guaranteedTime = guaranteedTime;
    }

    public ExpressOrder(Order order, int priorityLevel, int guaranteedTime) {
        super(order.getOrderID(), order.getSender(), order.getCreationDate(), order.getOrigin(),
                order.getDestination());
        this.priorityLevel = priorityLevel;
        this.guaranteedTime = guaranteedTime;
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
        // TODO Auto-generated method stub
        return super.toString()+" Type: "+"Express Order";
    }

}
