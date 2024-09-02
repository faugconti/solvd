package delivery.Models.Service;

import delivery.Models.Order.Order;
import delivery.Models.Person.Customer;
import delivery.exceptions.InsufficientFundsException;

public class PaymentService {
    public static void processPayment(Order order, Customer customer) throws InsufficientFundsException {
        double orderTotal = order.getPrice();
        double customerBalance = getCustomerBalance(customer);

        if (customerBalance < orderTotal) {
            throw new InsufficientFundsException("Customer " + customer.getName() +
                    " has insufficient funds to complete order " + order.getOrderID() +
                    ". Required: " + orderTotal + ", Available: " + customerBalance);
        }

        
    }

    private static double getCustomerBalance(Customer customer) {
        return 1000.0; // mock? 
    }

    
}
