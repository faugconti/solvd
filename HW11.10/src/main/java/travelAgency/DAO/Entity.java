package travelAgency.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

//methods for grabbing entity information
public interface Entity<T> {
     T mapResultSetToEntity(ResultSet resultSet) throws SQLException;
     Map<String,String> getColumnMappings();
     String getTableName();
}
