package travelAgency.service;

import travelAgency.model.Customer;

public class TravelAgency {


    public static <T> void addEntity(String dataSource, String entity) {

        Class<T> entityClass = getEntityClass(entity);
        Services<T> service = ServiceFactory.getService(dataSource, entityClass);

        System.out.println("Adding " + entity + " using " + dataSource);

    }

    public static void updateEntity(String dataSource, String entity) {
        System.out.println("Updating " + entity + " using " + dataSource);
        // Implement entity update logic here
    }

    public static <T> void displayEntities(String dataSource, String entity) {
        Class<T> entityClass = getEntityClass(entity);
        Services<T> service = ServiceFactory.getService(dataSource, entityClass);
        System.out.println("Displaying " + entity + "s from " + dataSource);
        System.out.println(service.findAll());
    }

    private static <T> Class<T> getEntityClass(String entityName) {
        try {
            return (Class<T>) Class.forName("travelAgency.model." + entityName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Entity class not found: " + entityName, e);
        }
    }
}
