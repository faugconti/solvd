package travelAgency.DAO.JDBC;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityDAO<T> extends AbstractDAO<T> {
    private final Class<T> entityClass; // from model folder

    public EntityDAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @Override
    public T mapResultSetToEntity(ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T entity = entityClass.getDeclaredConstructor().newInstance();
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            String columnName = field.getName();
            field.set(entity, resultSet.getObject(columnName));
        }
        return entity;
    }



    @Override
    public List<String> getColumnNames() {
        List<String> mappings = new LinkedList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            mappings.add(field.getName());
        }
        return mappings;
    }



    @Override
    public String getTableName() {
        return entityClass.getSimpleName();
    }

}
