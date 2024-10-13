package travelAgency.DAO;

import travelAgency.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractDAO<T> implements DAO<T>,Entity<T> {

    public abstract Connection getConnection();


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

    //similar to findSigle but without lambda statement
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
}
