package travelAgency.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//methods for grabbing entity information
public interface Entity<T> {
     T mapResultSetToEntity(ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException;
     List<String> getColumnNames();
     String getTableName();
}
