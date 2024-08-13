package delivery.Models.Person;

import java.util.ArrayList;
import java.util.List;

import delivery.Models.Order.Order;
import delivery.enums.Payments;

public class Customer extends Person {

    private Payments favoritePaymentType;
    private List<Order> orders;

    public Customer(String name, String email, String address) {
        super(name, email, address);
        this.orders = new ArrayList<>();
        this.favoritePaymentType = Payments.CREDIT_CARD;
    }

    public Customer(String name, String email, String address, Payments favoritePayment) {
        super(name, email, address);
        this.orders = new ArrayList<>();
        this.favoritePaymentType = favoritePayment;
    }

    public void setFavoritePaymentType(Payments favoritePaymentType) {
        this.favoritePaymentType = favoritePaymentType;
    }

    public Payments getFavoritePaymentType() {
        return favoritePaymentType;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void listOrders() {
        if (orders.size() < 0) {
            System.out.println("No orders yet for this customer");
            return;
        } else {
            System.out.println("Customer " + getName() + " has " + orders.size()+" orders:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

}
