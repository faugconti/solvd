package delivery.Models.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import delivery.App;
import delivery.Models.Address;
import delivery.Models.Order.DeliveryCompany;
import delivery.Models.Order.Order;
import delivery.Models.Order.OrderWriter;
import delivery.Models.Person.Customer;
import delivery.Models.Person.Employee;
import delivery.enums.MenuOption;
import delivery.exceptions.InvalidAddressException;
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
                List<Customer> customers = company.getCustomers();
                customers.add(createCustomer());
                company.setCustomers(customers);
                break;
            case ("3"):
                List<Order> orders = company.getOrders();
                orders.add(createOrder(company));
                try{
                    writeOrdersToFile(orders, fileName);
                }catch(OrderWriteException exc){
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
            List<Customer> customers = company.getCustomers();
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

    public static void writeOrdersToFile(List<Order> orders, String fileName) throws OrderWriteException {
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

    private static Employee createEmployee() {
        return MenuService.createEmployee();
    }

    private static Customer createCustomer() {
        return MenuService.createCustomer();
    }

    private static Order createOrder(DeliveryCompany company) {
        Customer customer = MenuService.selectCustomer(company.getCustomers());
        return MenuService.createOrder(customer);
    }

    // public static Address createAddress() {
    // Address address = new Address();
    // address.setCountry(MenuService.askForInput("Enter country:"));
    // address.setZipCode(MenuService.askForInput("Enter zip code:"));
    // address.setState(MenuService.askForInput("Enter state:"));
    // address.setRoad(MenuService.askForInput("Enter road:"));
    // return address;
    // }

    // public static Employee createEmployee() {
    // Employee employee = new Employee();
    // employee.setName(MenuService.askForInput("Enter employee name:"));
    // employee.setRole(MenuService.askForEmployeeRole());
    // employee.setSalary(MenuService.askForDouble("Enter employee salary:"));
    // employee.setHireDate(new Date());
    // return employee;
    // }

    // public static Customer createCustomer() {
    // Customer customer = new Customer();
    // customer.setName(MenuService.askForInput("Enter customer name:"));
    // customer.setEmail(MenuService.askForInput("Enter customer email:"));
    // customer.setAddress(createAddress());
    // return customer;
    // }

}
