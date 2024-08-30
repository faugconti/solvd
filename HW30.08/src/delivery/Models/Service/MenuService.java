package delivery.Models.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import delivery.Models.Address;
import delivery.Models.Order.Order;
import delivery.Models.Order.ExpressOrder;
import delivery.Models.Order.InternationalOrder;
import delivery.Models.Person.Employee;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.enums.MenuOption;
import delivery.enums.OrderStatus;
import delivery.enums.OrderType;
import delivery.enums.PaymentType;
import delivery.enums.Role;
import delivery.exceptions.InvalidAddressException;
import delivery.exceptions.InvalidOrderException;;

public class MenuService {

    private static final Logger logger = LogManager.getLogger(MenuService.class);
    static final Scanner input = new Scanner(System.in);

    public static void printMenu(MenuOption option) {
        switch (option) {
            case MAIN:
                System.out.println("1- Add new employee");
                System.out.println("2- Add new customer");
                System.out.println("3- Create new order");
                System.out.println("4- Show company information");
                System.out.println("5- Exit");
                System.out.println();
                break;
            default:
                System.out.println("Wrong option, try again");
                MenuService.printMenu(MenuOption.MAIN);
                break;

        }
    }

    public static String getAnswer() {
        return input.nextLine();
    }

    // public static Employee createEmployee() {

    //     System.out.println("Enter employee name:");
    //     String name = input.nextLine();

    //     System.out.println("Enter employee email:");
    //     String email = input.nextLine();

    //     Address address = createAddress();

    //     System.out.println("Enter employee salary:");

    //     double salary = getDoubleInput();

    //     Date hireDate = new Date(System.currentTimeMillis());

    //     System.out.println("Select employee role:");
    //     System.out.println("1- Manager");
    //     System.out.println("2- Driver");
    //     System.out.println("3- Staff");

    //     int roleChoice = getIntegerInput();
    //     Role role;
    //     switch (roleChoice) {
    //         case 1:
    //             role = Role.MANAGER;
    //             break;
    //         case 2:
    //             role = Role.DRIVER;
    //             break;
    //         case 3:
    //             role = Role.WAREHOUSE_WORKER;
    //             break;
    //         default:
    //             role = Role.WAREHOUSE_WORKER; // Default to staff if invalid input
    //     }

    //     return new Employee(name, email, address, salary, hireDate, role);
    // }

    // public static Customer createCustomer() {
    //     System.out.println("Enter customer name:");
    //     String name = input.nextLine();

    //     System.out.println("Enter customer email:");
    //     String email = input.nextLine();

    //     Address address = createAddress();

    //     System.out.println("Select your payment style:");
    //     System.out.println("1- CREDIT CARD");
    //     System.out.println("2- CASH");
    //     System.out.println("3- PAYPAL");
    //     System.out.println("4- CRYPTO");

    //     int payChoice = getIntegerInput();
    //     PaymentType paymentType;
    //     switch (payChoice) {
    //         case 1:
    //             paymentType = PaymentType.CREDIT_CARD;
    //         case 2:
    //             paymentType = PaymentType.CASH;
    //         case 3:
    //             paymentType = PaymentType.PAYPAL;
    //         case 4:
    //             paymentType = PaymentType.CRYPTO;
    //         default:
    //             paymentType = PaymentType.CREDIT_CARD; // Default to credit card
    //             break;
    //     }

    //     return new Customer(name, email, address, paymentType);
    // }

    // private static Address createAddress() {
    //     System.out.println("Enter address details:");
    //     System.out.println("Country:");
    //     String country = input.nextLine();

    //     System.out.println("Zip code:");
    //     String zipCode = input.nextLine();

    //     System.out.println("State:");
    //     String state = input.nextLine();

    //     System.out.println("Street:");
    //     String street = input.nextLine();

    //     return new Address(country, zipCode, state, street);
    // }

    // public static Order createOrder(Customer customer) {
    //     Order order = null;
    //     DeliveryPerson carrier = null;
    //     int randomID = ThreadLocalRandom.current().nextInt(3, 1000 + 1);
    //     System.out.println("Weight of package:");

    //     float weight = (float) getDoubleInput();

    //     // newOrder.setstatus(OrderStatus.DRAFT);
    //     // newOrder.setCreationDate(new Date(System.currentTimeMillis()));

    //     System.out.println("Select type of order:");
    //     System.out.println("1- Express");
    //     System.out.println("2- International");
    //     System.out.println("3- Subscription");

    //     int orderChoice = getIntegerInput();
    //     OrderType orderType;
    //     Address destinationAddr = null;

    //     switch (orderChoice) {
    //         case 1:
    //             orderType = OrderType.EXPRESS;
    //             System.out.println("Destination Address:");
    //             try {
    //                 destinationAddr = createAddress();
    //                 destinationAddr.validate();
    //             } catch (InvalidAddressException exc) {
    //                 logger.error("Invalid address format");
    //                 throw new RuntimeException("Address validation failed", exc);
    //             }

    //             order = new ExpressOrder(randomID, customer, new Date(System.currentTimeMillis()),
    //                     customer.getAddress(), destinationAddr, weight, 24, 5, carrier);
    //             break;
    //         case 2:
    //             orderType = OrderType.INTERNATIONAL;
    //             System.out.println("Destination Address:");
    //             destinationAddr = createAddress();

    //             order = new InternationalOrder(randomID, customer, new Date(System.currentTimeMillis()),
    //                     customer.getAddress(), destinationAddr, weight, "Unassigned", destinationAddr.getCountry());
    //             break;
    //         case 3:
    //             orderType = OrderType.SUBSCRIPTION;
    //             break;
    //         default:
    //             orderType = OrderType.EXPRESS; // Default to staff if invalid input
    //     }
    //     try {
    //         order.validate();
    //     } catch (InvalidOrderException exc) {
    //         logger.warn("Generated order might have invalid fields.." + exc.getMessage());
    //     }
    //     System.out.println("The order costs: " + order.getPrice() + " ¿Would you like to pay now? Press 1");
    //     if (getIntegerInput() == 1) {
    //         customer.payOrder(order); // order payed!
    //     }
    //     customer.addOrder(order);
    //     return order;

    // }

    // public static Customer selectCustomer(Set<Customer> customers) {
    // System.out.println("Select a customer:");
    // for (int i = 0; i < customers.size(); i++) {
    // System.out.println((i) + ": " + customers.get(i).getName());
    // }
    // int selection = getIntegerInput();
    // if (selection >= 0 && selection < customers.size()) {
    // return customers.get(selection);
    // } else {
    // System.out.println("Invalid selection. Please try again.");
    // return selectCustomer(customers);
    // }
    // }

    // problem: set doesnt have an get by index method 
    // so we need to create a new collection that implement List to be able to get indexed access?

    // public static Customer selectCustomer(Set<Customer> customers) {
    //     if (customers.isEmpty()) {
    //         System.out.println("No customers available.");
    //         return null;
    //     }

    //     List<Customer> customerList = new ArrayList<>(customers);
    //     System.out.println("Select a customer:");
    //     for (int i = 0; i < customerList.size(); i++) {
    //         System.out.println((i) + ": " + customerList.get(i).getName());
    //     }

    //     int selection = getIntegerInput();
    //     if (selection >= 0 && selection < customerList.size()) {
    //         return customerList.get(selection);
    //     } else {
    //         System.out.println("Invalid selection. Please try again.");
    //         return selectCustomer(customers);
    //     }
    // }

    public static String askForInput(String message) {
        System.out.println(message);
        return input.nextLine().trim();
    }

    public static int getIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                logger.warn("Invalid input. Please enter a number.");
            }
        }
    }

    public static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                logger.warn("Invalid input. Please enter a number.");
            }
        }
    }

}
