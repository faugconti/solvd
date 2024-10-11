package travelAgency;

import travelAgency.DAO.JDBC.CustomerDAO;
import travelAgency.model.Customer;
import travelAgency.service.CustomerService;

public class app {
    public static void main(String[] args){
        CustomerService customerService = new CustomerService(new CustomerDAO());

        Customer sample_customer = customerService.findCustomerById(1);
        System.out.println(sample_customer);
    }
}
