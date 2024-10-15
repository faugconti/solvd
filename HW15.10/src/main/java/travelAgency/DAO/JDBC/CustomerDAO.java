package travelAgency.DAO.JDBC;

import travelAgency.model.Customer;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> {


    @Override
    public String getTableName() {
        return "Customer";
    }

    @Override
    public Customer mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("idCustomer"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getString("phone")
        );
    }

    @Override
    public List<String> getColumnNames() {
        List<String> mappings = new LinkedList<>();
        for (Field field : Customer.class.getDeclaredFields()) {
            mappings.add(field.getName());
        }
        return mappings;
    }


    @Override
    public void update(Customer customer, String[] params) {
        String query = "UPDATE Customer SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE idCustomer = ?";
        runUpdate(query, statement -> {
            try {
                statement.setString(1, params[0]); // First name
                statement.setString(2, params[1]); // Last name
                statement.setString(3, params[2]); // Email
                statement.setString(4, params[3]); // Phone
                statement.setInt(5, customer.getIdCustomer()); //  ID
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
