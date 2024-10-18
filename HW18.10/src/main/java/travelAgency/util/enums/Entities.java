package travelAgency.util.enums;

import travelAgency.model.*;

public enum Entities {

    Booking(travelAgency.model.Booking.class,travelAgency.model.Bookings.class),
    Customer(travelAgency.model.Customer.class,travelAgency.model.Customers.class),
    Employee(travelAgency.model.Employee.class,travelAgency.model.Employees.class),
    Excursion(travelAgency.model.Excursion.class,travelAgency.model.Excursions.class),
    Hotel(travelAgency.model.Hotel.class,travelAgency.model.Hotels.class);
    //Destination(travelAgency.model.Destination.class,travelAgency.model.Destination.class);
    //Trip(travelAgency.model.Trip.class),
    //Transportation(travelAgency.model.Transportation.class),
    //Rating(travelAgency.model.Rating.class),
    //Payment(travelAgency.model.Payment.class),
    //Passport(travelAgency.model.PassportDetails.class),
    //Package(travelAgency.model.Package.class),
    //PackageDestinations(travelAgency.model.PackageDestinations.class),
    //PackageHotel(travelAgency.model.PackageHotel.class),
    //PackageExcursion(travelAgency.model.PackageExcursion.class),
    //PackageTransport(travelAgency.model.PackageTransport.class);

    private final Class<?> entityClass;
    private final Class<?> pluralClass;

    private Entities(Class<?> entityClass,Class<?> plural){
        this.entityClass = entityClass;
        this.pluralClass = plural;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public Class<?> getPluralClass() {
        return pluralClass;
    }


    public static Class<?> getPluralClassForEntity(Class<?> entityClass) {
        for (Entities e : Entities.values()) {
            if (e.getEntityClass().equals(entityClass)) {
                return e.getPluralClass();
            }
        }
        throw new IllegalArgumentException("No plural class found for: " + entityClass);
    }

}


