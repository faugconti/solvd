package travelAgency.service;

import travelAgency.util.JsonHandler;
import travelAgency.util.XMLHandler;

public class ServiceFactory {
    public static <T> Services<T> getService(String dataSource,Class<T> entityClass){
        return switch (dataSource.toLowerCase()) {
            case "database" -> new DatabaseService<T>(entityClass);
            case "xml" -> new XMLService<T>(entityClass,new XMLHandler());
            case "json" -> new JSONService<T>(entityClass,new JsonHandler());
            default -> throw new IllegalArgumentException("Unsupported data source");
        };
    }
}
