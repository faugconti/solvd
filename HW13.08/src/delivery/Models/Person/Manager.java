package delivery.Models.Person;

import java.util.Date;

import delivery.Models.Warehouse;
import delivery.enums.Role;

public class Manager extends Employee {
    private Warehouse currentWarehouse;
    private int employees; // at charge of how many workers?

    public Manager(String name, String email, String address, double salary,
            Date hireDate, Warehouse warehouse, int employees) {
        super(name, email, address, salary, hireDate, Role.MANAGER);
        this.currentWarehouse = warehouse;
        this.employees = employees;
    }

    public Manager(Employee employee, Warehouse warehouse, int employees) {
        super(employee.getName(), employee.getEmail(), employee.getAddress(), employee.getSalary(),
                employee.getHireDate(), Role.MANAGER);
        this.currentWarehouse = warehouse;
        this.employees = employees;
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public int getNumberOfEmployees() {
        return employees;
    }

    public void setCurrentWarehouse(Warehouse currentWarehouse) {
        this.currentWarehouse = currentWarehouse;
    }

    public void setNumberOfEmployees(int employees) {
        this.employees = employees;
    }

}
