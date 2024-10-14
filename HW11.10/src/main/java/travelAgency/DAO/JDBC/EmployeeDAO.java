package travelAgency.DAO.JDBC;

import travelAgency.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO extends AbstractDAO<Employee> {

    @Override
    public String getTableName() {
        return "Employee";
    }

    @Override
    public Employee mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("idEmployee"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phone"),
                resultSet.getString("role"),
                resultSet.getString("email"),
                resultSet.getDate("hireDate").toLocalDate()
        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idCustomer");
        columnMap.put("firstName", "firstName");
        columnMap.put("lastName", "lastName");
        columnMap.put("phone", "phone");
        columnMap.put("role", "role");
        columnMap.put("email", "email");
        columnMap.put("hireDate", "hireDate");
        return columnMap;
    }

}
