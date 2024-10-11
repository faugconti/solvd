package travelAgency.DAO.JDBC;

import travelAgency.DAO.AbstractDAO;
import travelAgency.model.Customer;
import travelAgency.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {


    @Override
    public Connection getConnection(){
        //retrieve JDBC connection
        return ConnectionPool.getConnection();
    }

    @Override
    public Customer getById(int id) {

        Customer customer = new Customer();
        String query = "SELECT * from Customer WHERE idCustomer = ?";
        Connection connection = null;
        try{
            connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";
        Connection connection = null;
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));
                customers.add(customer);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void remove(Customer customer) {

    }

}
