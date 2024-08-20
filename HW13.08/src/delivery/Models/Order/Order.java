package delivery.Models.Order;

import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.enums.OrderStatus;

public abstract class Order {
    private int orderID;
    private Customer sender;
    private Date creationDate;
    private Address origin;
    private Address destination;
    private float weight; // in grams
    protected float price;
    protected OrderStatus status;
    private DeliveryPerson carrier;

    public Order(int orderID, Customer sender, Date creationDate, Address origin, Address destination, float weight) {
        this.orderID = orderID;
        this.sender = sender;
        this.creationDate = creationDate;
        this.destination = destination;
        this.origin = origin;
        this.status = OrderStatus.DRAFT;
        this.weight = weight;
        sender.addOrder(this);
    }

    public abstract float calculatePrice();
    // base price + weight price

    public int getOrderID() {
        return orderID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Address getDestination() {
        return destination;
    }

    public Address getOrigin() {
        return this.origin;
    }

    public Customer getSender() {
        return sender;
    }

    public DeliveryPerson getCarrier() {
        return carrier;
    }

    public float getPrice() {
        return price;
    }

    public OrderStatus status() {
        return this.status;
    }

    public void setstatus(OrderStatus status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrigin(Address origin) {
        this.origin = origin;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public void setCarrier(DeliveryPerson carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Order ID: " + this.orderID;
    }
}
