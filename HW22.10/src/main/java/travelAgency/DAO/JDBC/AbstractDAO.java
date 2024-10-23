package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.DAO.Entity;
import travelAgency.util.ConnectionManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractDAO<T> implements DAO<T>, Entity<T> {

    public Connection getConnection(){
        //retrieve jdbc connection
        return ConnectionManager.getConnection();
    }

    // for saving updating & deleting, retrieves the key
    protected int runUpdate(String query, Consumer<PreparedStatement> consumer) {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            consumer.accept(statement);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected T findSingle(String query, Consumer<PreparedStatement> consumer) {
        T entity = null;
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            consumer.accept(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public T getById(int id){

        String query = "SELECT * FROM "+ this.getTableName() + " WHERE "+ this.getColumnNames().get(0) +" = ?";
        return this.findSingle(query,statement->{
            try{
                statement.setInt(1,id);
            }catch(SQLException exc){
                throw new RuntimeException(exc);
            }
        });
    }

    //similar to find Single but without lambda statement
    protected List<T> findAll(String query) {
        List<T> entities = new ArrayList<>();
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            //consumer.accept(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<T> getAll(){
        return this.findAll("SELECT * FROM "+this.getTableName());
    }

    @Override
    public void save(T entity) {
        List<String> attributeNames = new ArrayList<>(getColumnNames());
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        attributeNames.remove("id");


        for (int i = 0; i < attributeNames.size(); i++) {
            columns.append(attributeNames.get(i));
            values.append("?");
            if (i < attributeNames.size() - 1) {
                columns.append(", ");
                values.append(", ");
            }
        }

        String query = "INSERT INTO " + getTableName() + " (" + columns + ") VALUES (" + values + ")";
        int generatedId = this.runUpdate(query, statement -> {
            try {
                setStatementValues(statement, entity, attributeNames);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Set the generated ID back to the entity
        try {
            Method setIdMethod = entity.getClass().getMethod("setId"+entity.getClass().getSimpleName(), int.class);
            setIdMethod.invoke(entity, generatedId);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e ) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void update(T entity, String[] params) {
        List<String> columnNames = getColumnNames();
        StringBuilder sqlSetClause = new StringBuilder();

        // Remove the ID from the attribute names for the SET clause
        String idColumnName = "id"+entity.getClass().getSimpleName();
        //columnNames.remove(0);

        // Construct the SET clause
        for (int i = 1; i < columnNames.size(); i++) {
            sqlSetClause.append(columnNames.get(i)).append(" = ?");
            if (i < columnNames.size() - 1) {
                sqlSetClause.append(", ");
            }
        }

        String query = "UPDATE " + getTableName() + " SET " + sqlSetClause + " WHERE " + idColumnName + " = ?";
        this.runUpdate(query, statement -> {
            try {
                // generate the update statement
                for (int i = 1; i < params.length; i++) {
                    statement.setString(i, params[i]);
                }

                int id = (int) entity.getClass().getMethod("getId"+entity.getClass().getSimpleName()).invoke(entity);
                statement.setInt(params.length , id);
            } catch (SQLException | ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void remove(T entity) {
        List<String> columnsName = getColumnNames();
        String query = "DELETE FROM " + getTableName() + " WHERE " + columnsName.get(0) + " = ?";
        this.runUpdate(query, statement -> {
            try {
                int id = (int) entity.getClass().getMethod("getId"+entity.getClass().getSimpleName()).invoke(entity); //using reflection
                statement.setInt(1, id);
            } catch (SQLException | ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
    }

    // Helper method to set PreparedStatement values based on entity attributes
    private void setStatementValues(PreparedStatement statement, T entity, List<String> attributeNames) throws SQLException {
        for (int i = 0; i < attributeNames.size(); i++) {
            String attributeName = attributeNames.get(i);
            try {
                Field field = entity.getClass().getDeclaredField(attributeName);
                field.setAccessible(true);
                Object value = field.get(entity);
                statement.setObject(i + 1, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error setting statement value for " + attributeName, e);
            }
        }
    }


}
