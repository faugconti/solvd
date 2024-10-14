package travelAgency.DAO.JDBC;

import travelAgency.model.Customer;
import travelAgency.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idCustomer");
        columnMap.put("firstName", "firstName");
        columnMap.put("lastName", "lastName");
        columnMap.put("email", "email");
        columnMap.put("phone", "phone");
        return columnMap;
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
                statement.setInt(5, customer.getId()); //  ID
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
