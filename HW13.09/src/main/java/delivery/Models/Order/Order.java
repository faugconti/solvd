package delivery.Models.Order;

import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import delivery.interfaces.Deliverable;
import delivery.Models.Address;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.Models.Service.NotificationService;
import delivery.enums.Message;
import delivery.enums.OrderStatus;
import delivery.exceptions.InvalidOrderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Order implements Deliverable {

    private static final Logger logger = LogManager.getLogger(Order.class);

    private int orderID;
    private Customer sender;
    private Date creationDate;
    private Address origin;
    private Address destination;
    private float weight; // in grams
    protected float price;
    protected OrderStatus status;
    private DeliveryPerson carrier;
    public static int currentOfferings;
    static {
        currentOfferings = 3;
    }

    public static IntSupplier generateOrderID = () ->
            ((int) (Math.random() * 10000));

    public static Predicate<Order> isOrderReady = order -> order.status() == OrderStatus.READY;

    protected BinaryOperator<Float> calculateTotalCost = (baseCost, weightCost) ->
            baseCost + (this.weight/1000 * weightCost);

    public static Consumer<Order> logOrderDetails = order ->
            System.out.println("Order " + order.getOrderID() + " for " + order.getSender().getName());


    public Order(int orderID, Customer sender, Date creationDate, Address origin, Address destination, float weight,
            DeliveryPerson carrier) {
        this.orderID = orderID;
        this.sender = sender;
        this.creationDate = creationDate;
        this.destination = destination;
        this.origin = origin;
        this.status = OrderStatus.DRAFT;
        this.weight = weight; // in grams
        if (carrier != null) {
            this.carrier = carrier;
            carrier.setCurrentOrder(this);
        }
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
        return "Order ID: " + this.orderID + " status:" + this.status + "  ";
    }

    public void validate() throws InvalidOrderException {
        if (this.getOrderID() == 0) {
            throw new InvalidOrderException("Order ID cannot be zero");
        }
        if (this.getSender() == null) {
            throw new InvalidOrderException("Order must have a customer");
        }
        if (this.getPrice() <= 0) {
            throw new InvalidOrderException("Order total must be greater than zero");
        }

    }

    @Override
    public void startDelivery() {
        if (this.carrier != null && this.status.canTransitionTo(OrderStatus.IN_PROGRESS)){
                this.status = OrderStatus.IN_PROGRESS;
                NotificationService.sendNotification(this.carrier, Message.START_DELIVERY);
                NotificationService.sendNotification(this.sender, Message.START_DELIVERY);
        } else {
            logger.warn("Order " + this.orderID + " is not ready for delivery.");
        }
    }

    @Override
    public void completeDelivery() {
        if (this.carrier != null && this.status.canTransitionTo(OrderStatus.COMPLETED)) {
                this.status = OrderStatus.COMPLETED;
                NotificationService.sendNotification(this.sender, Message.END_DELIVERY);
        } else {
            logger.warn("Order " + this.orderID + " cannot be completed. Current status: " + this.status);
        }
    }

    @Override
    public boolean isDelivered() {
        return this.status == OrderStatus.COMPLETED;
    }

}
