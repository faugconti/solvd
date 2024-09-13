package delivery.Models.Person;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import delivery.Models.Address;
import delivery.enums.Role;

public class Employee extends Person {

        private static final Logger logger = LogManager.getLogger(Employee.class);


    private double salary;
    private Date hireDate;
    private Role employeeRole;

    public Employee(String name, String email, Address address, double salary, Date hireDate, Role role) {
        super(name, email, address);
        this.salary = salary;
        this.hireDate = hireDate;
        this.employeeRole = role;
        // System.out.println("New employee " + this.getName() + "(" + this.employeeRole + ")" + " added to the records");
        logger.info("Employee added to the records ",this);
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

    @Override
    public String toString() {
        return "*[Employee - Role: " + this.employeeRole + "] " + super.toString();
    }

    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + (int) salary;
        hash = 31 * hash + (employeeRole == null ? 0 : employeeRole.hashCode());
        hash = 31 * hash + (hireDate == null ? 0 : hireDate.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        Employee other = (Employee) obj;
        boolean salaryEquals = this.salary == other.salary;
        boolean hireDateEquals = (this.hireDate == null && other.hireDate == null)
                || (this.hireDate != null && this.hireDate.equals(other.hireDate));
        boolean roleEquals = (this.employeeRole == null && other.employeeRole == null)
                || (this.employeeRole != null && this.employeeRole.equals(other.employeeRole));

        return salaryEquals && hireDateEquals && roleEquals;
    }
}
