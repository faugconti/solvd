package travelAgency.DAO.JDBC;

import travelAgency.model.Excursion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExcursionDAO extends AbstractDAO<Excursion> {
    @Override
    public String getTableName() {
        return "Excursion";
    }

    @Override
    public Excursion mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Excursion(
                resultSet.getInt("idExcursion"),
                resultSet.getString("description"),
                resultSet.getString("name"),
                resultSet.getInt("duration"),
                resultSet.getFloat("price")
        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("id", "idExcursion");
        columnMap.put("name", "name");
        columnMap.put("price", "price");
        columnMap.put("float", "float");
        columnMap.put("duration", "duration");
        columnMap.put("description", "description");
        return columnMap;
    }
}
