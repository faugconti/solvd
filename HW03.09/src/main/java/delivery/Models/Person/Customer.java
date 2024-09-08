package delivery.Models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import delivery.Models.Address;
import delivery.Models.Order.Order;
import delivery.Models.Service.DeliveryService;
import delivery.Models.Service.PaymentService;
import delivery.enums.Message;
import delivery.enums.OrderStatus;
import delivery.enums.PaymentType;
import delivery.exceptions.InsufficientFundsException;
import delivery.interfaces.Notifiable;
import delivery.interfaces.Payable;


public class Customer extends Person implements Payable, Notifiable {

private static final Logger logger = LogManager.getLogger(DeliveryService.class);
    private PaymentType favoritePaymentType;
    private List<Order> orders;
    protected boolean isSubscribed;

     

    public Customer(String name, String email, Address address) {
        super(name, email, address);
        this.orders = new Vector<>();
        this.favoritePaymentType = PaymentType.CREDIT_CARD;
        this.isSubscribed = false;
    }

    public Customer(String name, String email, Address address, PaymentType favoritePayment) {
        super(name, email, address);
        this.orders = new ArrayList<>();
        this.favoritePaymentType = favoritePayment;
        this.isSubscribed = false;
    }

    public void setFavoritePaymentType(PaymentType favoritePaymentType) {
        this.favoritePaymentType = favoritePaymentType;
    }

    public PaymentType getFavoritePaymentType() {
        return favoritePaymentType;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void setSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void listOrders() {
        if (orders.size() < 0) {
            System.out.println("No orders yet for this customer");
            return;
        } else {
            System.out.println("Customer " + getName() + " has " + orders.size() + " orders:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    @Override
    public String toString() {
        return "*[Customer] " + super.toString();
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + Boolean.hashCode(isSubscribed);
        hash = 31 * hash + (favoritePaymentType == null ? 0 : favoritePaymentType.hashCode());
        hash = 31 * hash + (orders == null ? 0 : orders.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        Customer other = (Customer) obj;
        boolean paymentEquals = (this.favoritePaymentType == null && other.favoritePaymentType == null)
                || (this.favoritePaymentType != null && this.favoritePaymentType.equals(other.favoritePaymentType));
        boolean subscriptionEquals = (this.isSubscribed == other.isSubscribed);
        boolean ordersEquals = this.orders.containsAll(other.orders) && other.orders.containsAll(this.orders); // o(n²)

        return paymentEquals && subscriptionEquals && ordersEquals;
    }

    @Override
    public void payOrder(Order order) {
        // order belongs to customer and is unpaid?
        if (orders.contains(order) && order.status() == OrderStatus.DRAFT) {
            try{
                PaymentService.processPayment(order,this);
            }catch(InsufficientFundsException exc){
                logger.error(exc.getMessage());
                throw new RuntimeException("Payment failed", exc);
            }
            System.out.println("[Customer] Payment of the order #" + order.getOrderID() + " $" + order.getPrice()
                    + " processed using " + this.favoritePaymentType);
            order.setstatus(OrderStatus.READY);
        }
    }

    @Override
    public void receiveNotification(Message message) {
        System.out.println("[Customer] Reading new message: " + message);
    }

}
