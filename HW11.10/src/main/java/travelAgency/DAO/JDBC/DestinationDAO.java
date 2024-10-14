package travelAgency.DAO.JDBC;

import travelAgency.model.Destination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DestinationDAO extends AbstractDAO<Destination>
{
    @Override
    public Destination mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Destination(
                resultSet.getInt("idDestination"),
                resultSet.getString("country"),
                resultSet.getString("description"),
                resultSet.getString("name")
        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idDestination");
        columnMap.put("country", "country");
        columnMap.put("description", "description");
        columnMap.put("name", "name");
        return columnMap;
    }

    @Override
    public String getTableName() {
        return "Destination";
    }




}
