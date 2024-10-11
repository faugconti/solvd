package travelAgency.service;

import travelAgency.DAO.AbstractDAO;
import travelAgency.model.Customer;

public class CustomerService {

    private final AbstractDAO<Customer> customerDAO;

    public CustomerService(AbstractDAO<Customer> customerDAO){
        this.customerDAO = customerDAO;
    }

    public Customer findCustomerById(int id){
        return this.customerDAO.getById(id);
    }


}
