package delivery.Models.Person;

import java.util.Date;

import delivery.enums.Role;

public class Employee extends Person {

    private double salary;
    private Date hireDate;
    private Role employeeRole;

    public Employee(String name, String email, String address, double salary, Date hireDate, Role role) {
        super(name, email, address);
        this.salary = salary;
        this.hireDate = hireDate;
        this.employeeRole = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Role getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(Role employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
