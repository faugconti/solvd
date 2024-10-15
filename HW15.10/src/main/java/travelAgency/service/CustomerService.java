package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.AbstractDAO;
import travelAgency.model.Customer;

import java.util.List;

public class CustomerService implements Services<Customer>{

    private final DAO<Customer> customerDAO;

    public CustomerService(AbstractDAO<Customer> customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer findById(int id){
        return this.customerDAO.getById(id);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerDAO.getAll();
    }

    @Override
    public void save(Customer customer) {
        this.customerDAO.save(customer);
    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void remove(Customer customer) {
        this.customerDAO.remove(customer);
    }



}
