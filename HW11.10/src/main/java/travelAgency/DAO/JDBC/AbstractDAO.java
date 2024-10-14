package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.DAO.Entity;
import travelAgency.util.ConnectionManager;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public T getById(int id){
        Map<String, String> ColumnMappings = getColumnMappings();
        String idColumn = ColumnMappings.getOrDefault("id", "id"+this.getTableName());

        String query = "SELECT * FROM "+ this.getTableName() + " WHERE "+ idColumn +" = ?";
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
        } catch (SQLException e) {
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
        Map<String, String> columnMappings = getColumnMappings();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<String> attributeNames = new ArrayList<>(columnMappings.keySet());
        attributeNames.remove("id");

        // Construct column and value placeholders for SQL query
        for (int i = 0; i < attributeNames.size(); i++) {
            columns.append(columnMappings.get(attributeNames.get(i)));
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
            // Find the setId method in the entity class
            Method setIdMethod = entity.getClass().getMethod("setId", int.class);
            // Invoke the method to set the ID
            setIdMethod.invoke(entity, generatedId);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e ) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void update(T entity, String[] params) {
        Map<String, String> columnMappings = getColumnMappings();
        StringBuilder sqlSetClause = new StringBuilder();
        List<String> attributeNames = new ArrayList<>(columnMappings.keySet());

        // Remove the ID from the attribute names for the SET clause
        attributeNames.remove("id");

        // Construct the SET clause
        for (int i = 0; i < attributeNames.size(); i++) {
            sqlSetClause.append(columnMappings.get(attributeNames.get(i))).append(" = ?");
            if (i < attributeNames.size() - 1) {
                sqlSetClause.append(", ");
            }
        }

        String query = "UPDATE " + getTableName() + " SET " + sqlSetClause + " WHERE " + columnMappings.get("id") + " = ?";

        this.runUpdate(query, statement -> {
            try {
                // Set the parameters from the array
                for (int i = 0; i < params.length; i++) {
                    statement.setString(i + 1, params[i]);
                }

                // Set the ID parameter at the end
                int id = (int) entity.getClass().getMethod("getId").invoke(entity);
                statement.setInt(params.length + 1, id);

            } catch (SQLException | ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void remove(T entity) {
        Map<String, String> columnMappings = getColumnMappings();
        String query = "DELETE FROM " + getTableName() + " WHERE " + columnMappings.get("id") + " = ?";
        this.runUpdate(query, statement -> {
            try {
                int id = (int) entity.getClass().getMethod("getId").invoke(entity); //using reflection
                statement.setInt(1, id);
            } catch (SQLException | ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        });
    }



    // Helper method to set PreparedStatement values based on entity attributes
    private void setStatementValues(PreparedStatement statement, T entity, List<String> attributeNames) throws SQLException {
        try {
            for (int i = 0; i < attributeNames.size(); i++) {
                String attributeName = attributeNames.get(i);
                String getterName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                Object value = entity.getClass().getMethod(getterName).invoke(entity);
                statement.setObject(i + 1, value);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Error accessing entity attributes", e);
        }
    }


}
