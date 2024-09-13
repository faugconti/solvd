package delivery.Models.Service;

import delivery.Models.Order.Order;
import delivery.Models.Person.Customer;
import delivery.exceptions.InsufficientFundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);


    public static void processPayment(Order order, Customer customer) throws Exception {
        double orderTotal = order.getPrice();
        double customerBalance = getCustomerBalance(customer);

        if (customerBalance < orderTotal) {
            throw new InsufficientFundsException("Customer " + customer.getName() +
                    " has insufficient funds to complete order " + order.getOrderID() +
                    ". Required: " + orderTotal + ", Available: " + customerBalance);
        }

        if(order.getSender().getFavoritePaymentType().processPayment(orderTotal)){
            logger.info("Order " + order.getOrderID() + " has been paid.");
        }else{
            throw new Exception("Something went wrong paying your order");
        }
    }

    private static double getCustomerBalance(Customer customer) {
        return 1000.0; // mock? 
    }

    
}
