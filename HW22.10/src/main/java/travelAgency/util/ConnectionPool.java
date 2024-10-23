package travelAgency.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionPool {
    private static int POOL_SIZE = 5;
    private static String url;
    private static String user;
    private static String password;
    private static List<Connection> connectionPool;
    private static List<Connection> usedConnections = new ArrayList<>();;

    // load props
    static {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            if (input == null) {
                System.out.println("Unable to find db.properties");
                throw new RuntimeException();
            }
            properties.load(input);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ConnectionPool create(){
        for(int i = 0; i < POOL_SIZE; i++){
            connectionPool.add(createConnection(url, user, password));
        }
        return new ConnectionPool();
    }

    public static Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

}
