package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Person.Customer;

public class Order {
    private int orderID;
    private Customer sender;
    private Date creationDate;
    private String origin;
    private String destination;
    private boolean completed;

    public Order(int orderID, Customer sender, Date creationDate, String origin, String destination) {
        this.orderID = orderID;
        this.sender = sender;
        this.creationDate = creationDate;
        this.destination = destination;
        this.completed = false;
        sender.addOrder(this);
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public Customer getSender() {
        return sender;
    }

    public boolean status() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Order ID: "+this.orderID;
    }
}
