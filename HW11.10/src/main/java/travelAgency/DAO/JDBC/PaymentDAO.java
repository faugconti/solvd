package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PaymentDAO extends AbstractDAO<Payment> {

    @Override
    public Payment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Map<String, String> getColumnMappings() {
        return Map.of();
    }

    @Override
    public String getTableName() {
        return "Payment";
    }
}
