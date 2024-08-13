package delivery.Models.Person;

import java.util.Date;

import delivery.Models.Warehouse;
import delivery.enums.Role;

public class StaffMember extends Employee {

    private boolean isPartTime;
    private Warehouse currentWarehouse;

    public StaffMember(String name, String email, String address, double salary, Date hireDate, boolean isPartTime,
    Warehouse warehouse) {
        super(name, email, address, salary, hireDate, Role.WAREHOUSE_WORKER);
        this.isPartTime = isPartTime;
        this.currentWarehouse = warehouse;
    }

    public StaffMember(Employee employee, boolean isPartTime, Warehouse warehouse) {
        super(employee.getName(), employee.getEmail(), employee.getAddress(), employee.getSalary(),
                employee.getHireDate(), Role.DRIVER);
        this.isPartTime = isPartTime;
        this.currentWarehouse = warehouse;
    }

    public Warehouse getCurrentWarehouse() {
        return currentWarehouse;
    }

    public void setCurrentWarehouse(Warehouse currentWarehouse) {
        this.currentWarehouse = currentWarehouse;
    }

    public boolean getIsPartTime() {
        return this.isPartTime;
    }

    public void setIsPartTime(Boolean isPartTime) {
        this.isPartTime = isPartTime;
    }

}
