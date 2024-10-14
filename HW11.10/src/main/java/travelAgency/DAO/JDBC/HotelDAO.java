package travelAgency.DAO.JDBC;

import travelAgency.model.Excursion;
import travelAgency.model.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HotelDAO extends AbstractDAO<Hotel> {
    public String getTableName() {
        return "Hotel";
    }

    @Override
    public Hotel mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Hotel(
                resultSet.getInt("idExcursion"),
                resultSet.getString("description"),
                resultSet.getString("address"),
                resultSet.getString("name")
        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idHotel");
        columnMap.put("name", "name");
        columnMap.put("address", "address");
        columnMap.put("description", "description");
        return columnMap;
    }
}
