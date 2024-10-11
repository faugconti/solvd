package travelAgency.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import travelAgency.util.ConnectionPool;

public abstract class AbstractDAO<T> implements DAO<T> {

    abstract public Connection getConnection();
    //abstract public void closeConnection();
}
