package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Hotel;
import travelAgency.model.Transportation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportationDAO extends AbstractDAO<Transportation> {
    public String getTableName() {
        return "Transportation";
    }

    @Override
    public Transportation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Transportation(
                resultSet.getInt("idTransport"),
                resultSet.getString("description"),
                resultSet.getString("company"),
                resultSet.getString("type"),
                resultSet.getString("destination"),
                resultSet.getString("flightNumber"),
                resultSet.getFloat("price"),
                resultSet.getDate("departureDate").toLocalDate()

        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idTransport");
        columnMap.put("type", "type");
        columnMap.put("company", "company");
        columnMap.put("description", "description");
        columnMap.put("destination", "destination");
        columnMap.put("flightNumber", "flightNumber");
        columnMap.put("price", "price");
        columnMap.put("departureDate", "departureDate");

        return columnMap;
    }
}
