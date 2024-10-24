package travelAgency.service;


public class ServiceFactory {
    public static <T> Services<T> getService(String dataSource,Class<T> entityClass){
        return switch (dataSource.toLowerCase()) {
            case "database" -> new DatabaseService<T>(entityClass);
            case "xml" -> new XMLService<T>(entityClass);
            case "json" -> new JSONService<T>(entityClass);
            default -> throw new IllegalArgumentException("Unsupported data source");
        };
    }
}
