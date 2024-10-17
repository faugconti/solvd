package travelAgency.service;


public class TravelAgency {


    public static <T> void addEntity(String dataSource, String entity) {

        Class<T> entityClass = getEntityClass(entity);
        Services<T> service = ServiceFactory.getService(dataSource, entityClass);

        System.out.println("Adding " + entity + " using " + dataSource);
        // TODO : ADD METHOD FOR GRABING FILD INFORMATION FROM THE ENTITY , SHOULD BE PLACED IN MENUSERVICE?
    }

    public static void updateEntity(String dataSource, String entity) {
        System.out.println("Updating " + entity + " using " + dataSource);
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
