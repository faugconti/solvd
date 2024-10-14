package travelAgency;

import travelAgency.DAO.JDBC.CustomerDAO;
import travelAgency.model.Customer;
import travelAgency.service.CustomerService;

import java.util.List;

public class app {
    public static void main(String[] args){
        CustomerService customerService = new CustomerService(new CustomerDAO());

        Customer sample_customer = new Customer(1,"Juan","Gomez","juanGomezo@solvd.com","321321");
        customerService.save(sample_customer);
        System.out.println(customerService.findById(1));
        List<Customer> customers = customerService.findAll();
        System.out.println(customers);
        customerService.remove(sample_customer);
        System.out.println(customerService.findAll());

    }
}
