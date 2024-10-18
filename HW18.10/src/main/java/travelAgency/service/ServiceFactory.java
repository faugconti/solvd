package travelAgency.service;


public class ServiceFactory {
    public static <T> Services<T> getService(String dataSource,Class<T> entityClass){
        switch(dataSource.toLowerCase()){
            case "database":
                return new DatabaseService<T>(entityClass);
            case "xml":
                return new XMLService<T>(entityClass);
            default:
                throw new IllegalArgumentException("Unsupported data source");
        }

    }
}
