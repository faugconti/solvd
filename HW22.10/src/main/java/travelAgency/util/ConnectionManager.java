package travelAgency.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionManager {

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency","test","test");
        } catch(SQLException e) {
            throw new RuntimeException("Unable to connect to database");
        }

    }

}