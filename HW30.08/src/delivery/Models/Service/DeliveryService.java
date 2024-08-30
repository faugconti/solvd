package delivery.Models.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import delivery.Models.Address;
import delivery.Models.MyLinkedList;
import delivery.Models.Order.DeliveryCompany;
import delivery.Models.Order.ExpressOrder;
import delivery.Models.Order.InternationalOrder;
import delivery.Models.Order.Order;
import delivery.Models.Order.OrderWriter;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.Models.Person.Employee;
import delivery.enums.MenuOption;
import delivery.enums.OrderType;
import delivery.enums.Role;
import delivery.enums.PaymentType;
import delivery.exceptions.InvalidAddressException;
import delivery.exceptions.InvalidOrderException;
import delivery.exceptions.OrderWriteException;
import delivery.exceptions.PersonNotFoundException;

public class DeliveryService {

    private static final Logger logger = LogManager.getLogger(DeliveryService.class);
    private static final String fileName = "deliveryAppInfo";

    public static void startProgram(DeliveryCompany company) {
        while (true) {
            MenuService.printMenu(MenuOption.MAIN); // Start the main menu
            performAction(MenuService.getAnswer(), company);
        }
    }

    static public void performAction(String action, DeliveryCompany company) {
        switch (action) {
            case ("1"):
                List<Employee> employees = company.getEmployees();
                employees.add(createEmployee());
                company.setEmployees(employees);
                break;
            case ("2"):
                Set<Customer> customers = company.getCustomers();
                customers.add(createCustomer());
                company.setCustomers(customers);
                break;
            case ("3"):
                MyLinkedList<Order> orders = company.getOrders();
                orders.add(createOrder(company));
                try {
                    writeOrdersToFile(orders, fileName);
                } catch (OrderWriteException exc) {
                    logger.error("Failed to write orders to file: " + exc.getMessage());
                }
                break;
            case ("4"):
                showCompanyInformation(company);
                break;
            case ("5"):
                System.exit(0);
            default:
                System.out.println("Wrong option, try again");
                startProgram(company);
        }
    }

    public static Customer findByName(DeliveryCompany company, String name) {

        // List<String> filteredList = listOfOptionals.stream()
        // .filter(Optional::isPresent)
        // .map(Optional::get)
        // .collect(Collectors.toList());

        try {
            Set<Customer> customers = company.getCustomers();
            for (Customer customer : customers) {
                if (customer.getName().equalsIgnoreCase(name)) {
                    logger.info("Customer found: " + customer.getName());
                    return customer;
                }
            }
            throw new PersonNotFoundException("No customer with name: " + name);

        } catch (PersonNotFoundException exc) {
            logger.error("Error finding your customer: " + name);
            return null;
        }

    }

    public static void writeOrdersToFile(MyLinkedList<Order> orders, String fileName) throws OrderWriteException {
        try (OrderWriter writer = new OrderWriter(fileName)) {
            for (Order order : orders) {
                writer.writeOrder(order);
            }
            logger.info("Orders successfully written to " + fileName);
        } catch (OrderWriteException exc) {
            logger.error("Error writing orders to file: " + exc.getMessage());
            throw exc; // Rethrow the custom exception
        }
    }

    private static void showCompanyInformation(DeliveryCompany company) {
        System.out.println(company);
    }

    private static Order createOrder(DeliveryCompany company) {
        Customer customer = selectCustomer(company.getCustomers());

        Order order = null;
        DeliveryPerson carrier = null;
        int randomID = ThreadLocalRandom.current().nextInt(3, 1000 + 1);
        System.out.println("Weight of package:");

        float weight = (float) MenuService.getDoubleInput();

        // newOrder.setstatus(OrderStatus.DRAFT);
        // newOrder.setCreationDate(new Date(System.currentTimeMillis()));

        System.out.println("Select type of order:");
        System.out.println("1- Express");
        System.out.println("2- International");
        System.out.println("3- Subscription");

        int orderChoice = MenuService.getIntegerInput();
        OrderType orderType;
        Address destinationAddr = null;

        switch (orderChoice) {
            case 1:
                orderType = OrderType.EXPRESS;
                System.out.println("Destination Address:");
                try {
                    destinationAddr = createAddress();
                    destinationAddr.validate();
                } catch (InvalidAddressException exc) {
                    logger.error("Invalid address format");
                    throw new RuntimeException("Address validation failed", exc);
                }

                order = new ExpressOrder(randomID, customer, new Date(System.currentTimeMillis()),
                        customer.getAddress(), destinationAddr, weight, 24, 5, carrier);
                break;
            case 2:
                orderType = OrderType.INTERNATIONAL;
                System.out.println("Destination Address:");
                destinationAddr = createAddress();

                order = new InternationalOrder(randomID, customer, new Date(System.currentTimeMillis()),
                        customer.getAddress(), destinationAddr, weight, "Unassigned", destinationAddr.getCountry());
                break;
            case 3:
                orderType = OrderType.SUBSCRIPTION;
                break;
            default:
                orderType = OrderType.EXPRESS; // Default to staff if invalid input
        }
        try {
            order.validate();
        } catch (InvalidOrderException exc) {
            logger.warn("Generated order might have invalid fields.." + exc.getMessage());
        }
        System.out.println("The order costs: " + order.getPrice() + " ¿Would you like to pay now? Press 1");
        if (MenuService.getIntegerInput() == 1) {
            customer.payOrder(order); // order payed!
        }
        customer.addOrder(order);
        return order;

    }

    public static Customer selectCustomer(Set<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return null;
        }

        List<Customer> customerList = new ArrayList<>(customers);
        System.out.println("Select a customer:");
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println((i) + ": " + customerList.get(i).getName());
        }

        int selection = MenuService.getIntegerInput();
        if (selection >= 0 && selection < customerList.size()) {
            return customerList.get(selection);
        } else {
            System.out.println("Invalid selection. Please try again.");
            return selectCustomer(customers);
        }
    }

    private static Address createAddress() {
        System.out.println("Enter address details:");

        String country = MenuService.askForInput("Enter country:");
        String zipCode = MenuService.askForInput("Enter zip code:");
        String state = MenuService.askForInput("Enter state:");
        String road = MenuService.askForInput("Enter road:");
        return new Address(country,zipCode,state,road);
    }

    private static Customer createCustomer() {
        String name = MenuService.askForInput("Enter customer name:");
        String email = MenuService.askForInput("Enter customer email:");
        Address address = createAddress();

        System.out.println("Select your payment style:");
        System.out.println("1- CREDIT CARD");
        System.out.println("2- CASH");
        System.out.println("3- PAYPAL");
        System.out.println("4- CRYPTO");

        int payChoice = MenuService.getIntegerInput();
        PaymentType paymentType;
        switch (payChoice) {
            case 1:
                paymentType = PaymentType.CREDIT_CARD;
            case 2:
                paymentType = PaymentType.CASH;
            case 3:
                paymentType = PaymentType.PAYPAL;
            case 4:
                paymentType = PaymentType.CRYPTO;
            default:
                paymentType = PaymentType.CREDIT_CARD; // Default to credit card
                break;
        }

        return new Customer(name, email, address, paymentType);
    }

    public static Employee createEmployee() {
        String name = MenuService.askForInput("Enter employee name:");
        String email = MenuService.askForInput("Enter employee email:");
        Address address = createAddress();
        Date hireDate = new Date(System.currentTimeMillis());
        System.out.println("");
        System.out.println("Enter employee salary:");
        double salary = MenuService.getDoubleInput();
        System.out.println("Select employee role:");
        System.out.println("1- Manager");
        System.out.println("2- Driver");
        System.out.println("3- Staff");

        int roleChoice = MenuService.getIntegerInput();
        Role role;
        switch (roleChoice) {
            case 1:
                role = Role.MANAGER;
                break;
            case 2:
                role = Role.DRIVER;
                break;
            case 3:
                role = Role.WAREHOUSE_WORKER;
                break;
            default:
                role = Role.WAREHOUSE_WORKER; // Default to staff if invalid input
        }

        return new Employee(name, email, address, salary, hireDate, role);
    }


}
