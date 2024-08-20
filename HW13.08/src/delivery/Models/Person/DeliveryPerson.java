package delivery.Models.Person;
import java.util.Date;

import delivery.Models.Address;
import delivery.enums.Role;

public class DeliveryPerson extends Employee {
    private float rating;
    private Boolean isAvailable;

    public DeliveryPerson(String name, String email, Address address, double salary, Date hireDate, float rating,
            Boolean isAvailable) {
        super(name, email, address, salary, hireDate, Role.DRIVER);
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public DeliveryPerson(Employee employee, float rating, Boolean isAvailable) {
        super(employee.getName(), employee.getEmail(), employee.getAddress(), employee.getSalary(),employee.getHireDate(), Role.DRIVER);
        this.rating = rating;
        this.isAvailable = isAvailable;
    };

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean availability() {
        return isAvailable;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

}
